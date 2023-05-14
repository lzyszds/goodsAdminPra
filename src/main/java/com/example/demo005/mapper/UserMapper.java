package com.example.demo005.mapper;

import com.example.demo005.domain.Goods;
import com.example.demo005.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    //验证账号密码
    @Select("SELECT * FROM tb_user WHERE username=#{username} and password=#{password}")
    public User selectUser(String username, String password);

    //查询用户是否存在
    @Select("SELECT * FROM tb_user WHERE username=#{username}")
    public User selectUserName(String username);

//    @Select("SELECT * FROM tb_user")
//    public User[] queryAllUsers();

    //模糊查询用户
    @Select("SELECT * FROM tb_user WHERE id like '${search}' or username like '${search}' or email like '${search}'")
    public User[] querySearchUsers(String search);

    //添加用户
    @Insert("INSERT INTO tb_user(username,password,create_time,Email) VALUES (#{user.username},#{user.password}," +
            "#{user.create_time},#{user.Email})")
    public int insertUser(@Param("user") User user);

    //修改用户信息（全）
    @Update("UPDATE tb_user SET password=#{user.password}  ,Email=#{user.Email}   where username=#{user.username}")
    public int updateUser(@Param("user") User user);

    //修改用户信息（状态）
    @Update("UPDATE tb_user SET status=#{status}   where username=#{username}")
    public int updateUserStatus(String username, int status);

    //删除用户
    @Delete("DELETE FROM tb_user WHERE id = #{id}")
    public int deleteUser(int id);


}
