package com.yegetables.controller;


import com.yegetables.pojo.User;
import com.yegetables.utils.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class WebControllerTest extends BaseController {

    @RequestMapping(value = "/sendEmail1", method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail1(@RequestParam String email) {
        log.warn("[" + email + "]");
        return "test";
    }

    @RequestMapping(value = "/sendEmail2", method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail2(@RequestParam Map email) {
        log.warn("[" + email + "]");
        return "test";
    }

    @RequestMapping(value = "/sendEmail3", method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail3(@RequestBody String email) {
        log.warn("[" + email + "]");
        return "test";
    }

    @RequestMapping(value = "/sendEmail4", method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail4(@RequestBody Map email) {
        log.warn("[" + email + "]");
        return "test";
    }

    @RequestMapping(value = "/sendEmail5", method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail5(String email) {
        log.warn("[" + email + "]");
        return "test";
    }

    @RequestMapping(value = "/chinese")
    @ResponseBody
    public String chinese1() {
        return "我是中文测试";
    }

    @RequestMapping(value = "/value")
    @ResponseBody
    public String value1() {
        log.warn("max=" + PropertiesConfig.getNameMaxLength());
        //        log.warn("email=" + PropertiesConfig.getEmailRegex());
        log.warn("authCodeLength=" + PropertiesConfig.getAuthCodeLength());
        return "";
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/redis")
    @ResponseBody
    public String redis1() {
        log.warn("redis=" + redisTemplate);
        try
        {
            redisTemplate.opsForValue().set("test1", "value1");
            log.warn("test1=" + redisTemplate.opsForValue().get("test1"));

            redisTemplate.opsForValue().set("test2", new User().name("hello").password("123456"));
            User user2 = (User) redisTemplate.opsForValue().get("test2");
            assert user2 != null;
            log.warn("test2=" + user2.name() + "," + user2.password());
            System.out.println(user2.toString());
        } catch (Exception e)
        {
            log.error("", e);

        }
        return "";
    }


}