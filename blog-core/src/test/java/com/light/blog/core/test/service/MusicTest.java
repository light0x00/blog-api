package com.light.blog.core.test.service;

import com.light.blog.common.utils.JsonUtils;
import com.light.blog.common.vo.PageInfo;
import com.light.blog.core.service.msg.MsgCommentService;
import com.light.blog.core.service.msg.MsgCommentVo;
import com.light.blog.core.test.CoreTestApplication;
import com.light.blog.dao.vo.QueryMsgCommentVo;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author light
 * @since 2019/8/19
 */
public class MusicTest {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void query() {

        Map params = new HashMap<String,String>();
        params.put("params","iKXhS10Rb8HR01D+s0iEV8DtuK1WRCCGIygrHcn3s//nKKsmYS31zNHCikCC/6d/jVDkaSTkcODDT6L2NuV8G3ZZBVQWIpjrA8GPxyDoZNihB7FWPaj+LfCJh97WTsPZzBQ1GNdORoYhXYKsJMLkV1QZ55hS9pMSWzjzPSKlyFc76G+UdZhgro9+X89IAH6o");
        params.put("encSecKey","644cf0d6da398d675f3cb0d1349b271e37871a1e40315adcb11783da01b55643349b705712bc636c3f3dfcf64f1fdbae9a2ff5c96a0802239cb35f441cddf830787637d33927a0ea0b73c4e337fcf741528e04a6816853961d92c17b0222724172bd3c5b451c9bf43f991fd93c61e403d85867ec63b81cfbc55d26815eeac9ba");

        String r = restTemplate.postForObject(
                "https://music.163.com/weapi/playlist/detail",null,String.class,params
        );

        System.out.println(r);

    }



}
