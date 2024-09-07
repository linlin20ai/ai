package com.example.ai;

import com.example.ai.domain.sercurity.service.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ApiTest {

    @Test
    public void text_jwt(){
        JwtUtil util = new JwtUtil("xfg", SignatureAlgorithm.HS256);
        //以tom作为密钥，以HS256加密
        Map<String,Object> map = new HashMap<>();
        map.put("username","xfg");
        map.put("password","123");
        map.put("age",100);

        String jwtToken = util.encode("xfg",30000,map);

        util.decode(jwtToken).forEach((key,value)-> System.out.println(key+":"+value));
    }

}
