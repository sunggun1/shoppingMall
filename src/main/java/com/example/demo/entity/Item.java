package com.example.demo.entity;

import com.example.demo.constant.ItemSellStatus;
import com.example.demo.dto.ItemFormDto;
import com.example.demo.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Item extends BaseEntity{

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemName;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    public Item() {
    }

    @Builder
    public Item(Long id, String itemName, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus, LocalDateTime regTime, LocalDateTime updateTime) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
        this.regTime = regTime;
        this.updateTime = updateTime;
    }

    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock < 0){
            throw new OutOfStockException("상품의 재고가 부족합니다.(현재 재고 수량: "+ this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }
}