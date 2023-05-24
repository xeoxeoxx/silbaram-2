package com.project.silbaram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderListDTO {

    private Long ogid;
    private LocalDateTime orderDate;
    private Long memberID;
    private int totalPrice;
    private String orderNum;
    private String payBy;
    private boolean status;
    private String orderName;

    /* book */
    private BookDTO book;
    private String bookName;
    private String writerName;
}
