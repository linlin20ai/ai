package com.example.ai.domain.validate;

import com.example.ai.application.IWeiXinValidateService;
import com.example.ai.infrastructure.util.sdk.SignatureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 林圣涛
 */
@Service
public class WeiXinValidateServiceImpl implements IWeiXinValidateService {
    @Value("${wx.config.token}")
    private String token;


    @Override
    public boolean checkSign(String signature, String timestamp, String nonce) {
        return SignatureUtil.check(token,signature,timestamp,nonce);
    }
}
