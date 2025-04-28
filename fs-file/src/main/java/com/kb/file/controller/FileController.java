package com.kb.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.kb.common.base.BaseResponse;
import com.kb.common.exception.InfoException;
import com.kb.common.utils.OSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * 文件上传，下载，支持分片上传，断点续传
 * @author syg
 * @version 1.0
 */
@RestController
@Slf4j
public class FileController {

    @Value("${aliyun.accessKeyId}")
    private  String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private  String accessKeySecret;

    @Value("${aliyun.oss.endpoint}")
    private  String endpoint;

    @Value("${aliyun.oss.bucketName}")
    private  String bucketName;

    @Value("${aliyun.oss.folder}")
    private  String folder;

    @PostMapping("/upload")
    public BaseResponse upload(MultipartFile file){
        OSSClient ossClient= OSSUtils.getOSSClient(endpoint,accessKeyId,accessKeySecret);
        String fileName=OSSUtils.upload(ossClient,file,true,bucketName,folder);
        return BaseResponse.success(fileName);
    }

    @PostMapping("/uploadVideo")
    public BaseResponse uploadVideo(MultipartFile file){
        //获取ossclient，oss连接客户端
        OSSClient ossClient= OSSUtils.getOSSClient(endpoint,accessKeyId,accessKeySecret);
        //上传
        String fileName=OSSUtils.upload(ossClient,file,true,bucketName,folder);
        //转换成本地临时文件
        File file1=multipartFileToFile(file);
        //MultimediaObject 这个类是 JAVE 库提供的，专门用来分析多媒体文件，比如：
        //
        //视频长度
        //
        //帧率
        //
        //音频码率
        //
        //分辨率等等
        MultimediaObject instance = new MultimediaObject(file1);
        try {
            MultimediaInfo result = instance.getInfo();
            //视频时长
            long time = result.getDuration();
            //把视频时长转换成小时：分：秒的格式
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            String hms = formatter.format(time);
            log.info(hms);
            //打包两个字段
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("fileName",fileName);
            jsonObject.put("time",hms);
            return BaseResponse.success(jsonObject);
        } catch (EncoderException e) {
            log.error("获取时长异常",e);
            throw new InfoException("获取时长异常",e);
        }

    }

    public static File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("uploadPart")
    public BaseResponse uploadPartVideo(MultipartFile file){
        OSSClient ossClient= OSSUtils.getOSSClient(endpoint,accessKeyId,accessKeySecret);
        final File sampleFile = multipartFileToFile(file);
        try {
            String fileName=OSSUtils.uploadPartFile(bucketName,sampleFile,file.getOriginalFilename(),ossClient);
        } catch (Exception e) {
            return BaseResponse.failed("请重试");
        }
        return BaseResponse.success(file.getOriginalFilename());
    }

    @PostMapping("uploadRequest")
    public BaseResponse uploadRequest(MultipartFile file){
        OSSClient ossClient= OSSUtils.getOSSClient(endpoint,accessKeyId,accessKeySecret);
        final File sampleFile = multipartFileToFile(file);
        try {
            String fileName = OSSUtils.uploadFileRequestFile(bucketName,sampleFile,file.getOriginalFilename(),ossClient);
        } catch (Throwable e) {
            return BaseResponse.failed("请重试");
        }
        return BaseResponse.success(file.getOriginalFilename());
    }

    @GetMapping("/getFile")
    public BaseResponse getFile(@RequestParam("filename") String fileName, HttpServletResponse response) {
        OSSClient ossClient= OSSUtils.getOSSClient(endpoint,accessKeyId,accessKeySecret);
        OSSUtils.getFile(ossClient,response,fileName,bucketName,folder);
        return BaseResponse.success("获取文件成功");
    }
}
