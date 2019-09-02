package com.light.blog.web.test;

import com.alibaba.fastjson.JSON;
import com.light.blog.common.utils.JsonUtils;
import com.light.blog.common.utils.http.MapWrapper;
import com.light.blog.common.utils.http.RestTemplateWrapper;
import com.light.blog.web.BlogApplication;
import com.light.blog.web.controller.msg.MsgCommentController;
import com.sun.javafx.collections.MappingChange;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicTest {


    @Test
    public void query() {

        Map params = new HashMap<String,String>();
        params.put("params","iKXhS10Rb8HR01D+s0iEV8DtuK1WRCCGIygrHcn3s//nKKsmYS31zNHCikCC/6d/jVDkaSTkcODDT6L2NuV8G3ZZBVQWIpjrA8GPxyDoZNihB7FWPaj+LfCJh97WTsPZzBQ1GNdORoYhXYKsJMLkV1QZ55hS9pMSWzjzPSKlyFc76G+UdZhgro9+X89IAH6o");
        params.put("encSecKey","644cf0d6da398d675f3cb0d1349b271e37871a1e40315adcb11783da01b55643349b705712bc636c3f3dfcf64f1fdbae9a2ff5c96a0802239cb35f441cddf830787637d33927a0ea0b73c4e337fcf741528e04a6816853961d92c17b0222724172bd3c5b451c9bf43f991fd93c61e403d85867ec63b81cfbc55d26815eeac9ba");
        String r = RestTemplateWrapper.post("https://music.163.com/weapi/playlist/detail",params,String.class).getBody();
        MapWrapper mw = MapWrapper.wrap(JSON.parseObject(r));
        List<Map> songList = mw.getJsonAsList("result.tracks",Map.class);
        for (Map song : songList) {
            MapWrapper songWrapper = MapWrapper.wrap(song);

            String id = songWrapper.getString("id");
            String name = songWrapper.getString("name");
            String artist = songWrapper.getString("artists.0.name");
            String cover = songWrapper.getString("album.picUrl");
            String url = "https://music.163.com/song/media/outer/url?id="+id+".mp3";
        }

    }

}