package com.ahuiali.word.controller;


import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.LearnerJson;
import com.ahuiali.word.pojo.Learner;
import com.ahuiali.word.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created by shkstart on 2019/10/20
 */

@Controller
@RequestMapping("/learner")
public class LearnerController {

    @Autowired
    private LearnerService learnerService;

    @Autowired
    private JsonBase jsonBase;

    @Autowired
    private LearnerJson learnerJson;

    //跳转至个人界面
    @RequestMapping("/profile")
    public String gotoProfile(){
        return "/learner/profile";
    }

    @RequestMapping(value = "/register/{check}",produces = "application/json;charset=utf-8;")
    public @ResponseBody LearnerJson registerCheck(@RequestBody Learner learner, @PathVariable("check") String check){
        if("checkEmail".equals(check)){
            learnerJson = learnerService.queryLearnerByEmail(learner.getEmail());
        }else if("checkNickname".equals(check)){
            learnerJson = learnerService.queryLearnerByNickname(learner.getNickname());
        }else{

            learnerJson.setCode(-1);
            learnerJson.setMessage("参数错误！");
        }

        return learnerJson;
    }

    /**
     * 注册
     * @param learner
     * @return
     */
    @RequestMapping(value = "/register",produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase  register(@RequestBody Learner learner){
        jsonBase =  learnerService.register(learner);
        return jsonBase;
    }

    /**
     * 邮箱注册检验
     * @param token
     * @return
     */
    @RequestMapping(value = "/register/confirm/{token}")
    public @ResponseBody JsonBase confirm(@PathVariable("token") String token){
        jsonBase =learnerService.confirm(token);
        return jsonBase;

    }

    //登陆
    @RequestMapping(value = "/login", produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase login(@RequestBody Learner learner,  HttpSession session){
        //根据邮箱和密码查询
        learnerJson =  learnerService.queryLearner(learner);
        if(learnerJson.getCode() == 200){
            session.setAttribute("learnerId",learnerJson.getLearner().getId());
        }
        return learnerJson;
    }

    //跳转登录
    @RequestMapping("/gotoLogin")
    public String gotoLogin(){
        return "/learner/login";
    }
    //跳转注册
    @RequestMapping("/gotoRegister")
    public String gotoRegister(){
        return "/learner/register";
    }
    //跳转找回密码
    @RequestMapping("/gotoFindPsw")
    public String gotoFindPsw(){
        return "/learner/resetPassword";
    }

    //重新发送邮箱
    @RequestMapping(value = "/login/sentEmailAgain", produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase sentEmailAgain(@RequestBody Learner learner){

        jsonBase = learnerService.sentEmailAgain(learner.getEmail());
        System.out.println(jsonBase);
        return jsonBase;
    }

    //找回密码
    @RequestMapping(value = "/findPassword",produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase findPassword(@RequestBody Learner learner){
            jsonBase = learnerService.findPassword(learner.getEmail());
        return jsonBase;
    }

    //修改密码
    @RequestMapping(value = "/updatePassword",produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase updatePassword(@RequestBody Learner learner){
        jsonBase = learnerService.updatePassword(learner.getEmail(),learner.getPassword());
        return jsonBase;
    }

    //退出登录
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("learnerId");
        return "/learner/login";
    }
}
