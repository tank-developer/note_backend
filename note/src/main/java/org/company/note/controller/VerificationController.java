package org.company.note.controller;


import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.utils.StringUtils;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import org.company.note.entity.User;
import org.company.note.mapper.UserMapper;
import org.company.note.service.MailService;
import org.company.note.utils.*;
//import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.aliyun.teautil.Common.toJSONString;
import static top.year21.computerstore.controller.BaseController.OK;



@RestController
@RequestMapping("/code")
@Tag(name="发送验证码")
public class VerificationController {
    //https://blog.csdn.net/lhmyy521125/article/details/139309089

    private String appName = "简笔记";
    private String templateCode = "xxx";
    private static String AccessKeyId = "xxx";
    private static String AccessKeySecret = "xx";
    private static String endpoint = "xx.aliyuncs.com";

    public static Client createClient() throws Exception {
        Config config = new Config()
                // 配置 AccessKey ID，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(AccessKeyId)
                // 配置 AccessKey Secret，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(AccessKeySecret);
        // 配置 Endpoint
        config.endpoint = endpoint;
        return new Client(config);
    }

    @Resource
    private UserMapper userMapper;
    @Resource
    private MailService mailService;
    @Resource
    StringRedisTemplate stringRedisTemplate;


    //验证邮件发送冷却时间限制，秒为单位
    @Value("${spring.web.verify.mail-limit}")
    int verifyLimit;

//    @Resource
//    AmqpTemplate rabbitTemplate;
    @Resource
    FlowUtils flow;


    /**
     * 发送手机短信验证码
     * @param user
     * @return
     * @throws Exception
     */
    @Operation(summary = "发送手机验证码", description = "====")
    @PostMapping("/phone-code")
    public JsonResult<String> sendMsg(@RequestBody User user) throws Exception {
//      1.获取手机号
        String phone = user.getPhone();

        if(StringUtils.isEmpty(phone)){
            return new JsonResult<>(OK,"发送短信失败");
        }
//      2.随机生成四位验证码
        String code = ValidateCodeUtils.generateValidateCode(6).toString();

//      3.调用阿里云提供的短信服务
        // 初始化请求客户端
        Client client = VerificationController.createClient();

        // 构造请求对象，请填入请求参数值
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(appName)
                .setTemplateCode(templateCode)
                .setTemplateParam("{\"code\":\""+code+"\"}");

        // 获取响应对象
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);

        // 响应包含服务端响应的 body 和 headers
        System.out.println(toJSONString(sendSmsResponse));

        //存储验证码在redis里面，用电话号码作为key值
        stringRedisTemplate.opsForValue()
                .set(phone, String.valueOf(code), 3, TimeUnit.MINUTES);

        return new JsonResult<>(OK,"验证码短信发送成功");

    }


    /**
     * 发送邮件验证码
     * @param user 用户的信息
     * @return
     */
    @Operation(summary = "发送邮件验证码", description = "====")
    @PostMapping("/email-code")
    public JsonResult sendCode(User user,HttpServletRequest request) {
        String email = user.getEmail();
        String remoteAddr = request.getRemoteAddr();
        synchronized (remoteAddr.intern()) {//异步锁，address.intern()，防止多用户请求时卡死，这么写可以让请求排队。
            if (!this.verifyLimit(remoteAddr))//限流
                return new JsonResult<>(OK,"请求频繁，请稍后再试");
            if (email == null){
                return new JsonResult<>(OK,"邮件为空");
            }
            Random random = new Random();
            int code = random.nextInt(899999) + 100000;
//        userMapper.insert(user);
            String content = "" + code;
//        mailService.sendSimpleMail("注册验证" , content, String.valueOf(code),"1051136697@qq.com");
            mailService.send("注册验证码",email,content);
            Map<String, Object> data = Map.of("type","register","email", email, "code", code);

            //存储验证码在redis里面，用邮箱作为key值
            stringRedisTemplate.opsForValue()
                    .set(Const.VERIFY_EMAIL_DATA + email, String.valueOf(code), 10, TimeUnit.MINUTES);
        }
        return new JsonResult<>(OK,"验证码邮件发送成功");
    }


    @Operation(summary = "接口测试", description = "====")
    @PostMapping("/user/test")
    public Map<String,Object> test(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        // 验证令牌  交给拦截器去做
        // 只需要在这里处理自己的业务逻辑
        String token = request.getHeader("token");

        try {
            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            map.put("state",true);
            map.put("msg","请求成功");
//            log.info("用户id：[{}]",verify.getClaim("uid").asString());
//            log.info("用户名字：[{}]",verify.getClaim("username").asString());
            System.out.println("用户uid："+verify.getClaim("uid").asString());
            System.out.println("用户名字："+verify.getClaim("username").asString());
            System.out.println("用户邮件："+verify.getClaim("email").asString());
            System.out.println("用户电话："+verify.getClaim("phone").asString());

            map.put("state", true);
            map.put("msg", "请求成功");
            return map;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg","无效签名！");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效！");
        }
        map.put("state",false);
        return map;
    }

    /**
     * 针对IP地址进行邮件验证码获取限流
     * @param address 地址
     * @return 是否通过验证
     */
    private boolean verifyLimit(String address) {
        String key = Const.VERIFY_EMAIL_LIMIT + address;
        return flow.limitOnceCheck(key, verifyLimit);
    }

}
