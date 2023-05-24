package com.project.silbaram.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderListVO {
    private Long oid;
    private LocalDateTime orderDate;
    private Long memberID;
    private int totalPrice;
    private String orderNum;
    private String payBy;
    private boolean status;
    private String orderName;
}
