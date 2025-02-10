package org.company.note.utils.alyoss;//package com.example.sushe.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class Ossutil2 {

    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String secretAccessKey;

    @Value("${aliyun.oss.endpoint}")
    private String endPoint;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    public String uploadOneFile(MultipartFile file) {
        if (!(file.getOriginalFilename().endsWith(".png")) && !(file.getOriginalFilename().endsWith(".jpg")) && !(file.getOriginalFilename().endsWith(".PNG"))) {
            try {
                throw new Exception("文件类型错误，只能为png或者jpg");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, secretAccessKey);
        //设置文件名
        String fileName = new Date()
                + UUID.randomUUID().toString().replace("-", "")
                + file.getOriginalFilename();

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, fileName, file.getInputStream());

            String url = "http://" + bucketName + "." + endPoint + "/" + fileName;
            // System.out.println(url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public List<String> uploadArrayFile(MultipartFile[] files) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, secretAccessKey);
        List<String> list = new ArrayList<>();

        try {
            //设置文件名
            for (MultipartFile file : files) {
                String fileName = new Date()
                        + UUID.randomUUID().toString().replace("-", "")
                        + file.getOriginalFilename();
                // 创建PutObject请求。
                ossClient.putObject(bucketName, fileName, file.getInputStream());

                String url = "http://" + bucketName + "." + endPoint + "/" + fileName;
                // System.out.println(url);
                list.add(url);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return list;

    }

    public boolean deleteFile(String fileUrl) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, secretAccessKey);
        /** oss删除文件是根据文件完成路径删除的，但文件完整路径中不能包含Bucket名称。
         * 比如文件路径为：http://edu-czf.oss-cn-guangzhou.aliyuncs.com/2022/08/abc.jpg",
         * 则完整路径就是：2022/08/abc.jpg
         */
        int begin = ("http://" + bucketName + "." + endPoint + "/").length(); //找到文件路径的开始下标
        String deleteUrl = fileUrl.substring(begin);

        try {
            // 删除文件请求
            ossClient.deleteObject(bucketName, deleteUrl);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public boolean deleteFiles(List<String> fileUrls) {
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, secretAccessKey);

        // 要删除的对象列表
//        List<String> keysToDelete = new ArrayList<>();
//        keysToDelete.add("path/to/image1.jpg");
//        keysToDelete.add("path/to/image2.jpg");
//        keysToDelete.add("path/to/image3.jpg");
        // 创建删除请求
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
        deleteObjectsRequest.setKeys(fileUrls);
        try {
            // 删除文件。
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteObjectsRequest);
            System.out.println("批量删除的结果:"+deleteObjectsResult.toString());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

       return true;
    }


}

