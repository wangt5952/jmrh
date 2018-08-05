package com.bz.xtcx.manager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

@Component
public class SmsUtil {

	private String url = "http://112.74.76.186:8040/service/httpService/httpInterface.do";
    private String username = "JSM41961";
    private String password = "5tjfzvne";
    private String veryCode = "upxg5dedtrk2";
    private String tempid = "JSM41961-0001";
    
    public ResponseEntity<String> sendSmsByTemplate(final String mobile, final String content) throws UnsupportedEncodingException {

        StringBuffer sb = new StringBuffer("method=sendMsg"); // 写死
        sb.append("&username=").append(username);
        sb.append("&password=").append(password);
        sb.append("&veryCode=").append(veryCode);
        sb.append("&msgtype=2"); // 写死
        sb.append("&tempid=").append(tempid);
        sb.append("&mobile=").append(mobile);
        sb.append("&content=").append(URLEncoder.encode("@1@=" + content, "UTF-8"));
        sb.append("&code=utf-8"); // 写死

        RestTemplate restTemplate = new RestTemplate();

        String bodyValTemplate = sb.toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> httpEntity = new HttpEntity<String>(bodyValTemplate, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        return responseEntity;
    }

    public String getValidCode() {
        Random random = new Random();
        int x = random.nextInt(899999);
        x += 100000;
        return String.valueOf(x);
    }

    public static void main(String[] args)  throws UnsupportedEncodingException{
        new SmsUtil().sendSmsByTemplate("15957357787","111111");
    }
}
