package com.example.demo.controller;

import com.example.demo.constant.OrderStatus;
import com.example.demo.dto.ItemImgDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderHistDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.service.ItemImgService;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ItemImgService itemImgService;

    @PostMapping("/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long orderId;

        try{
            orderId = orderService.order(orderDto, email);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @GetMapping(value = {"/orders", "/orders/{page}"})
    public String orderHist(@PathVariable("page") Optional<Integer> page, Principal principal, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 4);

        Page<OrderHistDto> ordersHistDtoList = orderService.getOrderList(principal.getName(), pageable);

        model.addAttribute("orders", ordersHistDtoList);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);

        return "order/orderHist";
    }

    @GetMapping(value = "/orders/{orderId}/edit")
    public String editOrder(@PathVariable("orderId") Long orderId, Principal principal, Model model, HttpServletRequest request){
        List<OrderItem> orderItemList = orderService.getOrderItemsById(orderId);
        List<ItemImgDto> ItemImgDtoList = itemImgService.getItemImgDtosByOrderItems(orderItemList);

        Order order = orderService.getOrderReferenceById(orderId);
        if(order.getOrderStatus().equals(OrderStatus.COMPLETE)){
            model.addAttribute("msg", "주문 완료 상태입니다.");
            model.addAttribute("url", "orders");
        }else{
            model.addAttribute("msg", null);
            model.addAttribute("url", null);
        }


        model.addAttribute("orderId", orderId);
        model.addAttribute("ItemImgDtoList", ItemImgDtoList);
        return "order/orderEdit";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public @ResponseBody ResponseEntity cancelOrder(@PathVariable("orderId") Long orderId, Principal principal){
        if(!orderService.validateOrder(orderId, principal.getName())){
            return new ResponseEntity<String>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        orderService.cancelOrder(orderId);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @PostMapping("/orders/{orderId}/complete")
    public @ResponseBody ResponseEntity completeOrder(@PathVariable("orderId") Long orderId, Principal principal){
        if(!orderService.validateOrder(orderId, principal.getName())){
            return new ResponseEntity<String>("주문 완료 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        if(!orderService.isOrderNotComplete(orderId)){
            return new ResponseEntity<String>("이미 주문 완료 되었습니다.", HttpStatus.FORBIDDEN);
        }

        orderService.completeOrder(orderId);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
}
