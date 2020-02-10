package com.imooc.pay.service;

import java.math.BigDecimal;

public interface IPayService {
    void create(String orderId, BigDecimal amount);
}
