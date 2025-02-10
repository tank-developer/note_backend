package org.company.note.utils.alyoss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.alioss")
@Data
public class AliOssProperties {


    private String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    private String accessKeyId = "xxx";
    private String accessKeySecret = "xxx";
    private String bucketName = "note-content";

//    private String endpoint;
//    private String accessKeyId;
//    private String accessKeySecret;
//    private String bucketName;

}
