package com.example.pj.mapper;

import com.example.pj.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM message WHERE user_id = #{userId}")
    List<Message> getMessagesByUserId(Long userId);
}
