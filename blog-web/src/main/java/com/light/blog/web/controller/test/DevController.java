package com.light.blog.web.controller.test;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @auther: light
 * @since: 2018/10/29 10:27
 * <p></p>
 */
@RequestMapping("/test")
@Controller
public class DevController {


    @RequestMapping("/zuul")
    @ResponseBody
    public String fun(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        InputStreamReader reader = new InputStreamReader(is);

        StringBuffer stringBuffer = new StringBuffer();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        while (reader.read(charBuffer) > 0) {
            charBuffer.flip();
            stringBuffer.append(charBuffer.array(), charBuffer.position(), charBuffer.limit());
            charBuffer.clear();
        }
        is.close();
        reader.close();

        System.out.println(stringBuffer.toString());

        return stringBuffer.toString();
    }

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model,@RequestParam String name){
        model.addAttribute("name",name);
        return "hello";
    }



    public static void fun0() throws IOException {
        InputStream is = new FileInputStream("/Users/light/Desktop/core.ts");
        InputStreamReader reader = new InputStreamReader(is);
        CharBuffer charBuffer = CharBuffer.allocate(1024);

        StringBuffer stringBuffer = new StringBuffer();
        while (reader.read(charBuffer) > 0) {
            charBuffer.flip();
            stringBuffer.append(charBuffer.array());
            charBuffer.clear();
        }
        is.close();
        reader.close();
        System.out.println(stringBuffer);
    }

    public static void fun1() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/light/Desktop/core.ts"), StandardOpenOption.READ);

        StringBuffer stringBuffer = new StringBuffer();

        ByteBuffer buff = ByteBuffer.allocate(1024);
        while (inChannel.read(buff) != -1) {
            buff.flip();
            stringBuffer.append(new String(buff.array(), buff.position(), buff.limit()));
            buff.clear();
        }
        inChannel.close();
        System.out.println(stringBuffer);
    }
}
