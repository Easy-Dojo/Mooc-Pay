package com.imooc.pay.service.impl;

import com.imooc.pay.entities.PayInfo;
import com.imooc.pay.entities.dao.PayInfoMapper;
import com.imooc.pay.service.IPayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.enums.OrderStatusEnum;
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

    @Autowired
    private PayInfoMapper payInfoMapper;

    @Override
    public PayResponse create(String orderId, BigDecimal amount) {
        //订单写入数据库
        PayInfo payInfo = new PayInfo(
                Long.parseLong(orderId),
                OrderStatusEnum.NOTPAY.name(),
                amount);
        payInfoMapper.insertSelective(payInfo);

        //发起支付
        PayRequest payRequest = new PayRequest();
        payRequest.setOrderName(orderId + "-支付订单");
        payRequest.setOrderId(orderId);
        payRequest.setOrderAmount(amount.doubleValue());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);

        PayResponse response = bestPayService.pay(payRequest);

        log.info("response={}", response);

        return response;
    }

    @Override
    public String asyncNotify(String notifyData) {
        //1. 校验签名
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("payResponse={}", payResponse);

        //2. 金额校验（从数据中查询订单）

        //3. 修改订单支付状态

        //4. 告诉微信不要再通知了
        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
    }
}
