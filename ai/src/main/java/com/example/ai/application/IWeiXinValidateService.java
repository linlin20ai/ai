package com.example.ai.application;



/**
 * @author 林圣涛
 * 微信公众号验签服务
 */

public interface IWeiXinValidateService {

    boolean checkSign(String signature, String timestamp , String nonce);
}
