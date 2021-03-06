package com.imooc.pay.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayInfo {
    private Integer id;

    private Integer userId;

    private Long orderNo;

    private Integer payPlatform;

    private String platformNumber;

    private String platformStatus;

    private BigDecimal payAmount;

    private Date createTime;

    private Date updateTime;

    public PayInfo(long orderNo, String platformStatus, BigDecimal amount) {
        this.orderNo = orderNo;
        this.platformStatus = platformStatus;
        this.payAmount = amount;
    }
}
