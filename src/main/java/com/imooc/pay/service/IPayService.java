package com.imooc.pay.service;

import com.imooc.pay.entities.PayInfo;
import com.lly835.bestpay.model.PayResponse;

import java.math.BigDecimal;

public interface IPayService {
    PayResponse create(String orderId, BigDecimal amount);

    String asyncNotify(String notifyData);

    PayInfo queryByOrderId(String orderId);
}
