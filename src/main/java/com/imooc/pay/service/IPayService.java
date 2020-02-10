package com.imooc.pay.service;

import com.lly835.bestpay.model.PayResponse;

import java.math.BigDecimal;

public interface IPayService {
    PayResponse create(String orderId, BigDecimal amount);

    void asyncNotify(String notifyData);
}
