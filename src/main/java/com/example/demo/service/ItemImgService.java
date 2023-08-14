package com.example.demo.service;


import com.example.demo.dto.ItemImgDto;
import com.example.demo.entity.ItemImg;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    @Autowired
    S3Service s3Service;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgUUIDName = UUID.randomUUID() + "-" + itemImgFile.getOriginalFilename();
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgUrl = s3Service.uploadS3FileAndReturnUrl(imgUUIDName, itemImgFile);
        }

        itemImg.updateItemImg(oriImgName, imgUUIDName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
        if(!itemImgFile.isEmpty()){
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgUUIDName = UUID.randomUUID() + "-" + itemImgFile.getOriginalFilename();
            if(!StringUtils.isEmpty(savedItemImg.getImgName())){
                s3Service.deleteS3File(savedItemImg.getImgName());
            }

            String imgUrl = s3Service.uploadS3FileAndReturnUrl(imgUUIDName, itemImgFile);
            savedItemImg.updateItemImg(oriImgName, imgUUIDName, imgUrl);
        }
    }

    public List<ItemImgDto> getItemImgDtosByOrderItems(List<OrderItem> orderItems){
        List<ItemImgDto> result = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            result.add(ItemImgDto.of(itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(), "Y")));
        }

        return result;
    }
}
