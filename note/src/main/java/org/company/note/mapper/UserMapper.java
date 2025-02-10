package org.company.note.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.company.note.entity.User;


import java.util.Date;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: User实体类对应的mapper接口
 * @date 2022/7/10 17:03
 */
@Mapper
public interface UserMapper {

    /**
     * Description : 用户注册
     * @date 2022/7/10
     * @param user 用户信息
     * @return int 插入的结果
     **/
    int addUser(User user);

    /**
     * Description : 根据用户名查询用户信息
     * @date 2022/7/10
     * @param username 用户名
     * @return top.year21.computerstore.entity.User
     **/
    User queryUserByUsername(String username);

    /**
     * Description : 用户重置密码
     * @date 2022/7/12
     * @param password 要修改的密码
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @param username 用户名
     * @return int
     **/
    int updatePassword(String password,
                       String modifiedUser,
                       Date modifiedTime,
                       String username);


    /**
     * Description : 根据id查询用户信息
     * @date 2022/7/12
     * @param uid 用户id
     * @return top.year21.computerstore.entity.User
     **/
    User queryUserByUid(Integer uid);

    /**
     * Description : 更新用户信息
     * @date 2022/7/12
     * @param phone 电话
     * @param email 邮箱
     * @param gender 性别
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @param uid 用户id
     * @return int
     **/
    int UpdateUserInfo(String phone,
                       String email,
                       Integer gender,
                       String modifiedUser,
                       Date modifiedTime,
                       Integer uid);
    /**
     * Description : 处理用户上传头像
     * @date 2022/7/13
     * @param ImgAddress 保存图片的地址
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @param uid 用户uid
     * @return int
     **/
    int updateUserAvatar(@Param("file") String ImgAddress,
                         String modifiedUser,
                         Date modifiedTime,
                         Integer uid);



    /**
     * 检查是否存在指定电子邮件地址的账户
     *
     * @param email 要检查的电子邮件地址
     * @return 如果存在返回 true，否则返回 false
     */
    boolean existsByEmail(@Param("email") String email);
    /**
     * 检查是否存在指定用户名的账户
     *
     * @param username 要检查的用户名
     * @return 如果存在返回 true，否则返回 false
     */
    boolean existsByUsername(@Param("username") String username);

    /**
     * 检查是否存在指定电话号码的账户
     *
     * @param phone 要检查的电话号码
     * @return 如果存在返回 true，否则返回 false
     */
    boolean existsByPhone(@Param("phone") String phone);



    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") Long id);

    @Select("select * from user where code = #{code}")
    public User getByActivationCode(@Param("code") String code);

    @Insert("insert into user(uid, email, password, code)values(#{id}, #{email}, #{password} , #{code})")
    public int insert(User user);

    @Insert("update user set enabled = 1 where id = #{id}")
    public int update(@Param("id") Long id);


    User findUserById(@Param("uid") int uid);


    void updateUserStatus(@Param("uid") int uid, @Param("status") int status , @Param("modifiedUser") String modifiedUser, @Param("phone") String phone,@Param("email") String email);


}
