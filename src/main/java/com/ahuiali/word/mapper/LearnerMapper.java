package com.ahuiali.word.mapper;


import com.ahuiali.word.pojo.Learner;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by shkstart on 2019/10/20
 */
@Repository
@Mapper
public interface LearnerMapper {

    //插入一个learner
    @Insert("insert into learner(activecode,password,nickname,email,status,created,modified) values" +
            "(#{activecode},#{password},#{nickname},#{email},0,NOW(),NOW())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void addLearner(Learner learner);

    //根据邮箱和密码查询learner
    @Select("select id, nickname, status from learner where " +
            "email = #{email} " +
            "and " +
            "password = #{password} limit 1;" )
    Learner queryLearner(Learner learner);

    //根据邮箱查询learner
    @Select("select email from learner where email = #{email} limit 1;")
    Learner queryLearnerByEmail(String email);

    //根据昵称查询learner，返回值无所谓
    @Select("select nickname from learner where nickname = #{nickname} limit 1;")
    Learner queryLearnerByNickname(String nickname);

    //根据激活码查询id
    @Select("select id from learner where activecode = #{activecode}")
    Learner haveActive(String activecode);

    //根据激活码激活learner
    @Update("update learner set status = 1, modified = NOW() where activecode = #{activecode}")
    void confirmLearner(String activecode);

    //根据邮箱更新激活码
    @Update("update learner set activecode = #{activecode}, modified = NOW() where email = #{email}")
    void setActivecodeByEmail(String email,String activecode);

    @Update("update learner set password = #{password}, modified = NOW() where email = #{email}")
    void updatePassword(String email, String password);
}
