package com.example.ai.domain.sercurity.model.vo;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 林圣涛
 */
public class JwtToken implements AuthenticationToken {

    private String jwt;

    public JwtToken(String jwt){
        this.jwt = jwt;
    }

    /*
    * 等同于账号
    * */
    @Override
    public Object getPrincipal() {
        return jwt;
    }


    /*
    * 等同于密码
    * */
    @Override
    public Object getCredentials() {
        return jwt;
    }

}
