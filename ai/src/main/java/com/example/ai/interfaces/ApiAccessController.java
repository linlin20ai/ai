package com.example.ai.interfaces;

import com.example.ai.domain.sercurity.service.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 林圣涛
 * API访问准入管理；当访问OpenAI接口时，需要进行准入验证
 */
@RestController
public class ApiAccessController {

    private Logger logger = LoggerFactory.getLogger(ApiAccessController.class);

    /*
    *1. 本地访问；http://localhost:8080/authorize?username=xfg&password=123
    * 2. 云服务访问；http://207.246.123.150:8080/authorize?username=xfg&password=123
    * 3. 内网穿透；在docs/natapp/natapp执行；通过获得的地址访问服务  http://xfg.nat300.top/authorize?username=xfg&password=123
     * */
    @RequestMapping("/authorize")
    public ResponseEntity<Map<String,String>> authorize(String username,String password){
        Map<String,String> map = new HashMap<>();
        //模拟账号和密码校验
        if (!"xfg".equals(username) || !"123".equals(password)) {
            map.put("msg","用户名密码错误");
            return  ResponseEntity.ok(map);
        }

        //校验通过生成token
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username",username);
        String jwtToken = jwtUtil.encode(username,60*60*1000,chaim);
        map.put("msg","授权成功");
        map.put("token",jwtToken);
        //返回token码
        return ResponseEntity.ok(map);
    }





    /*
    * http://localhost:8080/verify?token=
    * */
    @RequestMapping("/verify")
    public ResponseEntity<String> verify(String token){
        logger.info("验证 token：{}",token);
        return ResponseEntity.status(HttpStatus.OK).body("verify success!");
    }

    /*
    * http://qj497z.natappfree.cc/success
    * */

    @GetMapping("/success")
    public String success(){
        return "test success by xfg";
    }

}
