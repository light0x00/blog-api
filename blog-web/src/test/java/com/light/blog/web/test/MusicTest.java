package com.light.blog.web.test;

import com.alibaba.fastjson.JSON;
import com.light.blog.common.utils.ShellUtils;
import com.light.blog.common.utils.http.MapWrapper;
import com.light.blog.common.utils.http.RestTemplateWrapper;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
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


    @Test
    public void q2(){
        String url = "https://music.163.com/weapi/playlist/detail?params=iKXhS10Rb8HR01D%2Bs0iEV8DtuK1WRCCGIygrHcn3s%2F%2FnKKsmYS31zNHCikCC%2F6d%2FjVDkaSTkcODDT6L2NuV8G3ZZBVQWIpjrA8GPxyDoZNihB7FWPaj%2BLfCJh97WTsPZzBQ1GNdORoYhXYKsJMLkV1QZ55hS9pMSWzjzPSKlyFc76G%2BUdZhgro9%2BX89IAH6o&encSecKey=644cf0d6da398d675f3cb0d1349b271e37871a1e40315adcb11783da01b55643349b705712bc636c3f3dfcf64f1fdbae9a2ff5c96a0802239cb35f441cddf830787637d33927a0ea0b73c4e337fcf741528e04a6816853961d92c17b0222724172bd3c5b451c9bf43f991fd93c61e403d85867ec63b81cfbc55d26815eeac9ba";
        HttpHeaders map = new HttpHeaders();
        map.put("Content-Type", Arrays.asList("text/plain"));
        String r = RestTemplateWrapper.post(url,null,map,String.class).getBody();
        System.out.println(r);
    }

    @Test
    public void q3(){
        String url = "https://music.163.com/weapi/playlist/detail?params=iKXhS10Rb8HR01D%2Bs0iEV8DtuK1WRCCGIygrHcn3s%2F%2FnKKsmYS31zNHCikCC%2F6d%2FjVDkaSTkcODDT6L2NuV8G3ZZBVQWIpjrA8GPxyDoZNihB7FWPaj%2BLfCJh97WTsPZzBQ1GNdORoYhXYKsJMLkV1QZ55hS9pMSWzjzPSKlyFc76G%2BUdZhgro9%2BX89IAH6o&encSecKey=644cf0d6da398d675f3cb0d1349b271e37871a1e40315adcb11783da01b55643349b705712bc636c3f3dfcf64f1fdbae9a2ff5c96a0802239cb35f441cddf830787637d33927a0ea0b73c4e337fcf741528e04a6816853961d92c17b0222724172bd3c5b451c9bf43f991fd93c61e403d85867ec63b81cfbc55d26815eeac9ba";
        ShellUtils.CommandResult r = ShellUtils.execCommand("curl -X POST \""+url+"\"",false);
        System.out.println(r.result);
        System.out.println(r.successMsg);
    }



}