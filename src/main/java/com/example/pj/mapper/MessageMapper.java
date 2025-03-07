package com.example.pj.mapper;

import com.example.pj.entity.Message;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM Message WHERE userId = #{userId} order by messageDate desc")
    List<Message> getMessagesByUserId(Long userId);

    // 预订确认
    @Insert("INSERT INTO Message (userId, content,messageDate) VALUES (#{userId},  #{msg}, #{now})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    void insertReserveConfirmation(Long userId,  String msg, LocalDateTime now);

    // 订单状态更新为 Completed
    @Insert("INSERT INTO Message (userId, content,messageDate) VALUES (#{userId}, #{msg},#{now})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    void updateOrderStatus2Completed(Long userId, String msg, LocalDateTime now);

    @Delete("""
        DELETE FROM Message 
        WHERE messageId IN (
            SELECT messageId 
            FROM (
                SELECT messageId, 
                        ROW_NUMBER() OVER (PARTITION BY content ORDER BY messageId DESC) AS rnum 
                FROM Message
            ) t
            WHERE t.rnum > 1
        )
        """)
    void deleteDuplicateMessages();

    // 订单状态更新为 Ended
    @Insert("INSERT INTO Message (userId, content,messageDate) VALUES (#{userId}, #{msg},#{now})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    void updateOrderStatus2Ended(Long userId, String msg, LocalDateTime now);
}
