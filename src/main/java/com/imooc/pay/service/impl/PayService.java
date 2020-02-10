package com.imooc.pay.service.impl;

import com.imooc.pay.service.IPayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class PayService implements IPayService {

    @Autowired
    private BestPayService bestPayService;

    @Override
    public PayResponse create(String orderId, BigDecimal amount) {
        //发起支付
        PayRequest payRequest = new PayRequest();
        payRequest.setOrderName("3083396-支付订单");
        payRequest.setOrderId(orderId);
        payRequest.setOrderAmount(amount.doubleValue());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);

        PayResponse response = bestPayService.pay(payRequest);

        log.info("response={}", response);

        return response;
    }

    @Override
    public void asyncNotify(String notifyData) {
        //1. 校验签名
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);

        log.info("payResponse={}", payResponse);
    }
}
