/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-2-29
 */
package com.stamen.sample.rop;

import com.stamen.rop.validation.DefaultRopValidator;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class SampleRestServiceTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("method", "rop.sample.method1");//<--指定方法名称
        form.add("appKey", "00001");
        form.add("v", "1.0");

        form.add("sessionId", "test");
        form.add("format", "xml");
        form.add("locale", "en");

        form.add("userName", "wwww");
//        form.add("birthday", "1999-07-12");
        form.add("salary", "2,500.00");

        String sign = DefaultRopValidator.sign(new ArrayList<String>(
                         form.keySet()), form.toSingleValueMap(),"abcdeabcdeabcdeabcdeabcde");

        form.add("sign", sign);

        String response = restTemplate.postForObject(
                "http://localhost:9080/router", form, String.class);
        System.out.println("response:\n" + response);
    }
}

