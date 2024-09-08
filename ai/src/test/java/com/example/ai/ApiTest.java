package com.example.ai;

import com.example.ai.domain.sercurity.service.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

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


    /**
     * 因为官网模型更新，大家测试的时候使用 test_chatGPT_3_5 方法。
     */
    @Test
    public void test_chatGPT_3_5() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // 如果你有自己的apihost、apikey也可以替换使用。
            HttpPost httpPost = new HttpPost("https://service-d6wuqy4n-1320869466.cd.apigw.tencentcs.com/v1/chat/completions");
            String json = "{\n" +
                    "    \"model\": \"gpt-3.5-turbo-1106\",\n" +
                    "    \"max_tokens\": 1024,\n" +
                    "    \"messages\": [\n" +
                    "        {\n" +
                    "            \"role\": \"user\",\n" +
                    "            \"content\": [\n" +
                    "                {\n" +
                    "                    \"text\": \"写个java冒泡排序\",\n" +
                    "                    \"type\": \"text\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);

            httpPost.setEntity(requestEntity);
            httpPost.setHeader("Authorization", "Bearer 阅读链接评论置顶第一条获取key https://t.zsxq.com/163o5FKvc");
            httpPost.setHeader("Content-Type", "application/json");

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                // 打印响应体的内容
                String result = EntityUtils.toString(responseEntity);
                System.out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }

        // 等待
        countDownLatch.await();
    }


}
