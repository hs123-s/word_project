package com.ahuiali.word.service.impl;

import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.LearnerJson;
import com.ahuiali.word.mapper.LearnerMapper;
import com.ahuiali.word.pojo.Learner;
import com.ahuiali.word.service.LearnerService;
import com.ahuiali.word.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Transactional
@Service
public class LearnerServiceImpl implements LearnerService {

    @Autowired
    LearnerMapper learnerMapper;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    JsonBase jsonBase;

    @Autowired
    LearnerJson learnerJson;

    @Override
    public JsonBase addLearn(Learner learner) {
        if(learner.getNickname() != "" && learner.getNickname() != null
                && learner.getEmail() != "" && learner.getEmail() != null
                && learner.getPassword() != "" && learner.getPassword() != null)
        learnerMapper.addLearner(learner);

        return null;
    }

    //根据邮箱和密码查询用户
    @Override
    public LearnerJson queryLearner(Learner learner1) {
        //md5加密
        learner1.setPassword(Md5Utils.md5(learner1.getPassword()));
        //查询
        Learner learner = learnerMapper.queryLearner(learner1);
        //该用户存在时
        if(learner!=null){
            Integer status = learner.getStatus();
            if(status == 1){
                learnerJson.setCode(200);
                learnerJson.setMessage("success");
                learnerJson.setLearner(learner);
                return learnerJson;
            }
            else if(status == 2){
                //封禁中
                learnerJson.setCode(404);
                learnerJson.setMessage("用户封禁中");
                return learnerJson;
            } else if(status == 0){
                //审核中
                learnerJson.setCode(405);
                learnerJson.setMessage("用户未完成邮箱验证");
                return learnerJson;
            }
        }
        //用户不存在直接重新返回
        learnerJson.setCode(406);
        learnerJson.setMessage("找不到用户");
        return learnerJson;
    }

    @Override
    public LearnerJson queryLearnerByEmail(String email) {

        Learner learner =learnerMapper.queryLearnerByEmail(email);
        LearnerJson learnerJson = new LearnerJson();
        if(learner == null){
            //如果在数据库中找不到该email
            learnerJson.setCode(407);
            learnerJson.setMessage("邮箱不存在");
            return learnerJson;
        } else{
            //如果在数据库中找到该email
            learnerJson.setCode(408);
            learnerJson.setMessage("邮箱已存在");
            return learnerJson;

        }

    }

    @Override
    public LearnerJson queryLearnerByNickname(String nickname) {
        Learner learner =learnerMapper.queryLearnerByNickname(nickname);
        LearnerJson learnerJson = new LearnerJson();
        if(learner == null){
            //如果在数据库中找不到该昵称

            //敏感检测
            if(1 == 1){
                learnerJson.setCode(200);
                learnerJson.setMessage("success");
            }else{
                learnerJson.setCode(444);
                learnerJson.setMessage("敏感词汇");
            }

            return learnerJson;
        } else{
            //如果在数据库中找到该昵称

            learnerJson.setCode(401);
            learnerJson.setMessage("昵称已存在");
            return learnerJson;
        }

    }

    //保存用户，并向用户发送邮箱
    @Override
    public JsonBase register(Learner learner) {
        //password加密
        learner.setPassword(Md5Utils.md5(learner.getPassword()));
        //生成token，时间戳+邮箱
        String token = System.currentTimeMillis() + learner.getEmail();
        //设置激活码
        learner.setActivecode(token);
        //添加用户
        learnerMapper.addLearner(learner);

        //发送邮箱
        String title = "注册检测（背词系统）";
        String msg = "<html><body><a href='http://119.23.219.54:80/learner/register/confirm/"+
                token +"'>点击即可确认身份！</a></body></html>";
        jsonBase = sentEmail(learner.getEmail(),title,msg);

        return jsonBase;

    }

    //激活用户
    @Override
    public JsonBase confirm(String activecode) {
        //30分钟失效
        if((System.currentTimeMillis() - Long.parseLong(activecode.substring(0,13)))/(1000*60) > 30){
            jsonBase.setCode(402);
            jsonBase.setMessage("激活过期！");
            return jsonBase;
        }else{
            if(learnerMapper.haveActive(activecode) != null){
                //将用户状态设置为可用
                learnerMapper.confirmLearner(activecode);
                jsonBase.setCode(200);
                jsonBase.setMessage("success");
                return jsonBase;
            }else{
                //找不到激活码,说明无效
                jsonBase.setCode(403);
                jsonBase.setMessage("激活码无效！");
                return jsonBase;
            }

        }

    }

    //重新发送邮箱
    @Override
    public JsonBase sentEmailAgain(String email) {
        //生成token，时间戳+邮箱
        String token = System.currentTimeMillis() + email;
        //查询邮箱是否存在
        LearnerJson learnerJson = queryLearnerByEmail(email);
        //如果存在该邮箱,408表示邮箱已存在
        if(learnerJson.getCode() == 408){
            //发送邮箱
            String title = "注册检测（背词系统）";
            String msg = "<html><body><a href='http://119.23.219.54:80/learner/register/confirm/"+
                    token +"'>点击即可确认身份！</a></body></html>";
            jsonBase = sentEmail(email,title,msg);
            //邮箱发送成功
            if(jsonBase.getCode() == 200){
                //设置激活码
                learnerMapper.setActivecodeByEmail(email,token);
                return jsonBase;
            }else if(jsonBase.getCode() == 409){
                //邮箱发送失败
                return jsonBase;
            }

        }

        //不存在邮箱
        jsonBase.setCode(407);
        jsonBase.setMessage("该邮箱不存在");
        return jsonBase;


    }

    /**
     * 找回密码
     * @param email
     * @return
     */
    @Override
    public JsonBase findPassword(String email) {
        //该邮箱是否存在
        LearnerJson learnerJson = queryLearnerByEmail(email);
        //邮箱存在
        if(learnerJson.getCode() == 408){
            String title = "找回密码";
            //设置新密码（MD5不可解密，故只能自动设置一个新的密码，然后返回，用户可根据该密码登陆，然后再修改）
//            String newPassword = learnerJson.getLearner().getPassword().substring(0,7);
            String newPassword = "12345678";
            //发送邮箱
            jsonBase = sentEmail(email,title,"密码默认设置为 ："+newPassword);
            //邮箱发送成功
            if(jsonBase.getCode() == 200){
                //当邮箱发送成功才修改密码
                learnerMapper.updatePassword(email,Md5Utils.md5(newPassword));
                return jsonBase;
            }else if(jsonBase.getCode() == 409){
                //邮箱发送失败
                return jsonBase;
            }

        }

        //不存在邮箱
        jsonBase.setCode(407);
        jsonBase.setMessage("该邮箱不存在");
        return jsonBase;

    }

    /**
     *  修改密码
     * @param email
     * @param newPassword
     * @return
     */
    @Override
    public JsonBase updatePassword(String email, String newPassword) {
        learnerMapper.updatePassword(email,newPassword);

        jsonBase.setCode(200);
        jsonBase.setMessage("success");
        return jsonBase;
    }

    /**
     * 邮箱类
     * @param email 用户邮箱
     * @return
     */
    public JsonBase sentEmail(String email,String title, String msg){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("1170782807@qq.com");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(msg, true);
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            e.printStackTrace();
            //出错，邮箱发送失败
            jsonBase.setCode(409);
            jsonBase.setMessage("邮箱发送失败");
            return jsonBase;

        }
        //成功，返回200
        jsonBase.setCode(200);
        jsonBase.setMessage("success");
        return jsonBase;
    }
}
