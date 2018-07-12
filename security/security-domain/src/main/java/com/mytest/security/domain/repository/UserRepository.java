package com.mytest.security.domain.repository;

import com.mytest.security.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2017/8/30.
 */
public interface UserRepository {

    @Select("SELECT * FROM user WHERE user_id = #{userId} AND is_delete = #{deleted}")
    User findOneByUserId(@Param("userId") Integer userId, @Param("deleted") boolean deleted);

    @Select("SELECT * FROM USER WHERE email = #{email} AND is_delete = #{deleted}")
    User findOneByEmail(@Param("email") String email, @Param("deleted") boolean deleted);
}
