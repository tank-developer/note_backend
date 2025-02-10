package org.company.note.service;



import jakarta.servlet.http.HttpSession;
import org.company.note.entity.User;

//import javax.servlet.http.HttpSession;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理用户相关业务层接口
 * @date 2022/7/10 22:16
 */
public interface IUserService {

    //处理用户注册
    void userRegister(User user);

    //处理用户登录
    User userLogin(User user);

    //处理用户修改密码
    void userResetPwd(String oldPwd, String newPwd, HttpSession session);

    //处理用户修改个人资料
    void userUpdateInfo(String phone,String email,Integer gender,String username,Integer uid);

    //根据id查询用户信息
    User queryUserByUid(Integer uid);

    //处理用户上传图片
    void userUploadImg(String imgAddress,Integer uid);


    /**
     * 检查是否存在指定电子邮件地址的账户
     *
     * @param email 要检查的电子邮件地址
     * @return 如果存在返回 true，否则返回 false
     */
    boolean checkEmailExists(String email);

    /**
     * 检查是否存在指定用户名的账户
     *
     * @param username 要检查的用户名
     * @return 如果存在返回 true，否则返回 false
     */
    boolean existsAccountByUsername(String username);


    /**
     * 检查是否存在指定电话号码的账户
     *
     * @param phone 要检查的电子邮件地址
     * @return 如果存在返回 true，否则返回 false
     */
    boolean checkPhoneExists(String phone);



    User getUserById(int uid);

    void deleteUser(int uid);

}