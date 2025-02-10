package org.company.note.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 对应数据表t_user的实体类
 * @date 2022/6/16 18:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    //如果用雪花算法生成主键id,那么模型里id用这个
//    private Long id;
    private Integer uid;
    private String username;
    private String password;
    private String salt; //用于加密密码
    private String phone;
    private String email;
    private Integer gender;//'性别:0-女，1-男',
    private String avatar;
    private Integer isDelete;



    /*

    MySQL自带雪花算法函数
    https://blog.51cto.com/u_16175447/8612032
    在建表之前看一下这个，用uuid()函数去插入数据。要用雪花算法作为id主键



    CREATE TABLE `user` (
    `id` CHAR(36) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(100),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

    -- 插入新用户时使用UUID()函数为id生成值
    INSERT INTO `user` (`id`, `username`, `password`, `email`) VALUES (UUID(), 'exampleUser', 'examplePassword', 'user@example.com');
    这个user表对应的Java模型是怎么样的？

    public class User implements Serializable {
        private static final long serialVersionUID = 1L;
        private String id; // UUID生成的id是字符串类型
        private String username;
        private String password;
        private String email;
        private LocalDateTime createdAt; // 用于存储时间戳
        private LocalDateTime updatedAt; // 用于存储时间戳
    }
    * */
}

