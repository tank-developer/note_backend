package org.company.note.controller;

//import com.google.code.kaptcha.Constants;
import com.aliyuncs.utils.StringUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.company.note.controller.exception.ValidCodeNotMatchException;
import org.company.note.entity.User;
import org.company.note.service.ContentService;
import org.company.note.service.IUserService;
import org.company.note.service.MailService;
import org.company.note.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import org.springframework.web.servlet.View;

import static com.aliyun.teautil.Common.toJSONString;


//import javax.servlet.http.HttpSession;HttpSession

//电话短信：https://blog.csdn.net/weixin_51351637/article/details/130188499

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理用户请求的控制器
 * @date 2022/7/10 23:44
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name="用户相关")
public class UserController extends top.year21.computerstore.controller.BaseController {

    @Autowired
    private IUserService userService;
    @Resource
    private MailService mailService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ContentService contentService;
    @Autowired
    private View error;


    /**
     * 邮件注册
     * @param username
     * @param email
     * @param password
     * @param code
     * @return
     */
    @PostMapping("/email-register")
    @Operation(summary = "邮件注册", description = "====")
    public JsonResult<String> userEmailRegister(String username,String email,String password, String code) {
//        String email = user.getEmail();
        String code1 = this.getEmailVerifyCode(email);
        if(code == null) return new JsonResult<>(OK,"请先获取验证码");
        if(code1 == null) return new JsonResult<>(OK,"请先获取验证码");

        if(!code1.equals(code))  return new JsonResult<>(OK,"验证码错误，请重新输入");
        if (userService.checkEmailExists(email)) return new JsonResult<>(OK,"该邮件地址已被注册");
        if (userService.existsAccountByUsername(username)) return new JsonResult<>(OK,"该用户名已被他人使用，请重新更换");
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setCreatedUser("wu");
        //执行插入操作
        userService.userRegister(user);
        return new JsonResult<>(OK);
    }

    /**
     * 通过电话注册用户
     * @param username
     * @param phone
     * @param password
     * @param code
     * @return
     */
    @PostMapping("/phone-register")
    @Operation(summary = "手机号注册", description = "手机号注册接口")
    public JsonResult<String> userPhoneRegister(String username,String phone,String password, String code) {
//        String email = user.getEmail();
        String code1 = this.getPhoneVerifyCode(phone);
        if(code == null) return new JsonResult<>(OK,"请先获取验证码");
        if(code1 == null) return new JsonResult<>(OK,"请先获取验证码");

        if(!code1.equals(code))  return new JsonResult<>(OK,"验证码错误，请重新输入");
        if (userService.checkPhoneExists(phone)) return new JsonResult<>(OK,"该电话地址已被注册");
        if (userService.existsAccountByUsername(username)) return new JsonResult<>(OK,"该用户名已被他人使用，请重新更换");
        User user = new User();
        user.setPhone(phone);
        user.setUsername(username);
        user.setPassword(password);
        user.setCreatedUser("wu");

        //执行插入操作
        userService.userRegister(user);
        return new JsonResult<>(OK,"注册成功");
    }

    @Operation(summary = "登录", description = "登录的接口")
    @PostMapping("/login")
    public JsonResult<Map<String,Object>> userLogin(@RequestBody User user, String code){
        //执行登录操作
        User loginUser = userService.userLogin(user);
        Map<String,Object> map = new HashMap<>();
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("uid", String.valueOf(loginUser.getUid()));
            payload.put("username",loginUser.getUsername());
            payload.put("email",loginUser.getEmail());
            payload.put("phone",loginUser.getPhone());

            // 生成jwt令牌
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功！");
            map.put("token",token);  // 响应token
            if (loginUser.getEmail() != null) {
                this.deleteEmailVerifyCode(loginUser.getEmail());
            }
            if (loginUser.getPhone() != null){
                this.deletePhoneVerifyCode(loginUser.getPhone());
            }
        } catch (Exception e) {
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return new JsonResult<>(OK,map);
    }

    //用户重置密码
    @Operation(summary = "用户重置密码", description = "====")
    @PostMapping("/resetPassword")
    public JsonResult<Void> userResetPwd(@RequestParam("oldPassword") String oldPwd,
                                         @RequestParam("newPassword") String newPwd,
                                         HttpSession session){
        userService.userResetPwd(oldPwd, newPwd, session);

        //在用户修改密码之后清除session中保存的密码
        session.setAttribute("uid",null);
        return new JsonResult<>(OK);
    }
    @Operation(summary = "查询用户信息", description = "====")
    @GetMapping("/queryUser")
    public JsonResult<User> queryUserByUid(HttpSession session){
        Integer uid = getUserIdFromSession(session);

        User user = userService.queryUserByUid(uid);

        //将用户名、id、电话、邮箱、性别进行回传
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setUid(user.getUid());
        newUser.setGender(user.getGender());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setAvatar(user.getAvatar());

        return new JsonResult<>(OK,newUser);
    }


    //用户个人信息更新
    @PostMapping("/updateInfo")
    public JsonResult<User> userInfoUpdate(String phone,String email,Integer gender,HttpSession session){
        //从session中取出用户名和uid
        String username = getUsernameFromSession(session);
        Integer uid = getUserIdFromSession(session);

        //更新数据
        userService.userUpdateInfo(phone, email, gender, username, uid);

        User user = userService.queryUserByUid(uid);

        //将用户名、id、电话、邮箱、性别进行回传
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setUid(user.getUid());
        newUser.setGender(user.getGender());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setAvatar(user.getAvatar());

        return new JsonResult<>(OK,newUser);
    }

    //处理用户退出登录的请求
    @GetMapping("/exit")
    public JsonResult<Void> exitUserLoginStatus(HttpSession session){
        session.removeAttribute("username");
        session.removeAttribute("uid");
        return new JsonResult<>(OK);
    }
    @Operation(summary = "根据uid查询用户数据", description = "====")
    @PostMapping("/queryUserByUid")
    public JsonResult<User> queryUserByJwtUid(HttpServletRequest request){
        String token = request.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            String username = verify.getClaim("username").asString();
            int idByUsername = contentService.getIdByUsername(username);
            User user = userService.getUserById(idByUsername);
            //将用户名、id、电话、邮箱、性别进行回传
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setUid(user.getUid());
            newUser.setGender(user.getGender());
            newUser.setPhone(user.getPhone());
            newUser.setEmail(user.getEmail());
            newUser.setAvatar(user.getAvatar());
            return new JsonResult<>(OK,newUser);
        }
        User newUser = new User();
        return new JsonResult<>(OK,newUser);
    }

    @PostMapping("/deleteAccount")
    public JsonResult<String> deleteAccount(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        if(token != null) {
            DecodedJWT verify = JWTUtils.verify(token);
            String username = verify.getClaim("username").asString();
            String uid = verify.getClaim("uid").asString();

//            int idByUsername = contentService.getIdByUsername(username);
            userService.deleteUser(Integer.valueOf(uid));

            Map respMap = new HashMap();
            respMap.put("username",username);
            return new JsonResult<>(OK,respMap.toString());
        }
        return new JsonResult<>(OK,"注销失败");
    }


    /**
     * 移除Redis中存储的邮件验证码
     * @param email 电邮
     */
    private void deleteEmailVerifyCode(String email){
        String key = Const.VERIFY_EMAIL_DATA + email;
        stringRedisTemplate.delete(key);
    }
    /**
     * 移除Redis中存储的电话验证码
     * @param phone 电邮
     */
    private void deletePhoneVerifyCode(String phone){
        String key = phone;
        stringRedisTemplate.delete(key);
    }
    /**
     * 获取Redis中存储的邮件验证码
     * @param email 电邮
     * @return 验证码
     */
    private String getEmailVerifyCode(String email){
        String key = Const.VERIFY_EMAIL_DATA + email;
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取Redis中存储的电话验证码
     * @param phone 电邮
     * @return 验证码
     */
    private String getPhoneVerifyCode(String phone){
        String key = phone;
        return stringRedisTemplate.opsForValue().get(key);
    }



}
