package com.light.blog.web.controller.common;

import com.light.blog.common.vo.OutputModel;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Clock;

/**
 * @auther: light
 * @since: 2018/10/7 12:59
 * <p></p>
 */
@RestController
@Slf4j
@Api(hidden = true)
public class UploadController {

    @Value("${app.url}")
    String url;
    @Value(("${app.context-path}"))
    String contextPath;

    @Value("${app.upload-path}")
    String uploadPath;

    @PostMapping("/upload")
    public OutputModel<String> upload(@RequestParam("file") MultipartFile targetFile) throws IOException {

        String relativePath = Clock.systemUTC().millis() + "_" + targetFile.getOriginalFilename();
        File localFile = new File(uploadPath + relativePath);

        targetFile.transferTo(localFile);
        log.debug("save file:{}=>{}", targetFile.getOriginalFilename(), localFile.getPath());

        String uri = url + contextPath + "/files/" + relativePath; //使用nginx映射路径
        return OutputModel.ofSuccess(uri);
    }


}
