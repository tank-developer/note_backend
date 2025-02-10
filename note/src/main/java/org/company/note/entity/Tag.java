package org.company.note.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 数据库中的用户信息
 */
@Data
//@TableName("db_content")
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseEntity {
    //如果用雪花算法生成主键id,那么模型里id用这个
//    private Long id;
    private int id;
    private String name;
    // Getters and Setters

}
