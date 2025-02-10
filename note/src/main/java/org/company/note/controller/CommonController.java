package org.company.note.controller;


//import com.example.entity.RestBean;
//import com.example.utils.alyoss.AliOssUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.company.note.entity.RestBean;
import org.company.note.utils.alyoss.AliOssUtil;
import org.company.note.utils.alyoss.Ossutil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 用于公用的接口
 */
@Validated
@RestController
@RequestMapping("/api/common")
@Tag(name = "上传图片", description = "包括用户笔记图片的上传")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;


    @Autowired
    private Ossutil2 ossutil2;


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @Operation(summary = "文件上传")
    public RestBean<String> upload(MultipartFile file){
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀   dfdfdf.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;
            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
//            return Result.success(filePath);
            return RestBean.success(filePath);
        } catch (IOException e) {
            System.out.println("文件上传失败：{}");
        }
        return RestBean.failure(404,"上传失败");
    }
    
    @PostMapping("/delete")
    @Operation(summary = "删除文件")
    public RestBean<String> delete(String objectName){
        try {
            //原始文件名
//            String originalFilename = file.getOriginalFilename();
//            //截取原始文件名的后缀   dfdfdf.png
//            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//            //构造新文件名称
//            String objectName = UUID.randomUUID().toString() + extension;

            //文件的请求路径
             aliOssUtil.delete(objectName);
            return RestBean.success();
        } catch (Exception e) {
            System.out.println("文件删除失败：{}");
        }
        return RestBean.failure(404,"删除失败");
    }

    /**
     * 上传一张图片
     * @param file
     * @return
     */
    @PostMapping("/uploadOneFile")
    public RestBean<String> uploadFile(MultipartFile file) {
        //返回上传oss的url

        String imageUrl = ossutil2.uploadOneFile(file);

        return RestBean.success(imageUrl);
    }

    /**
     * 批量上传图片
     * @param files
     * @return
     */
    @PostMapping("/uploadArrayFile")
    public RestBean<List<String>> uploadArrayFile(MultipartFile[] files) {
        //返回上传oss的url
        List<String> imageUrls = ossutil2.uploadArrayFile(files);
        return RestBean.success(imageUrls);
    }

    /**
     * 删除一张图片
     * @param fileUrl 单张图片的名字
     * @return
     */
    @PostMapping("/deleteFile")
    public RestBean<Boolean> deleteFile(String fileUrl) {
        //返回是否删除成功
        boolean result = ossutil2.deleteFile(fileUrl);
        return RestBean.success(result);
    }

    /**
     * 批量删除图片
     * @param fileUrls 图片名字的数组
     * @return
     */
    @PostMapping("/deleteFiles")
    public RestBean<Boolean> deleteFiles(@RequestBody List<String> fileUrls) {
        //返回是否删除成功
        boolean result = ossutil2.deleteFiles(fileUrls);
        return RestBean.success(result);
    }
}
