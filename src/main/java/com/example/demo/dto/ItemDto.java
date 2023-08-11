package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class ItemDto {
    private final Long id;
    private final String itemName;
    private final Integer price;
    private final String itemDetail;
    private final String sellStatCd;
    private final LocalDateTime regTime;
    private final LocalDateTime updateTime;
}
