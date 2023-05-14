package com.example.demo005.controller;

import com.example.demo005.domain.Goods;
import com.example.demo005.domain.User;
import com.example.demo005.service.GoodsService;
import com.example.demo005.service.UserService;
import com.example.demo005.utill.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;


/**
 * @className: LoginController.java
 * @author: moyu
 * @date: 2023年04月05日  10:11
 **/
@Controller
public class PostUserController {

    @Autowired
    private UserService userservice;

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/login")
    public String login(String username, String password, String verifyCode, Model model, HttpSession session) {
        if (username.isEmpty()) {
            model.addAttribute("msg", "请输入用户名");
            return "login";
        }

        //查询用户密码是否存在，并返回当前用户的详情
        User user = userservice.selectUser(username, password);

        //设置 session
//        HttpSession session = request.getSession();
        //设置 application
        ServletContext application = session.getServletContext();
        if (user != null) {

            if (!session.getAttribute("verifyCode").toString().trim().equals(verifyCode)) {
                model.addAttribute("msg", "验证码输入错误");
                return "login";
            }
            //验证成功

            //将用户信息添加到session当中
            String userId = user.getId();
            session.setAttribute("user", user);
            session.setAttribute("userId", userId);
            session.setAttribute("loginUser", user.getUsername());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("power", user.getPower());
            //设置状态为在线
            userservice.updateUserStatus(username, 1);
            //取值
            Set<String> onlineUserSet = (Set) application.getAttribute("onlineUserSet");
            String newDate = DateTimeUtil.getCurrentTimeString();
            onlineUserSet.add(userId);
            application.setAttribute("onlineUserSet", onlineUserSet);

            //输出用户登录情况到控制台
            System.out.println("------分割线-------" + newDate + "------开始-------");
            System.out.println("用户：" + user.getUsername() + "已登录");
            System.out.println("当前在线用户的id：" + onlineUserSet.toString());
            System.out.println("当前在线用户的个数：" + onlineUserSet.toArray().length);
            System.out.println("------分割线-------" + newDate + "------结束-------");
            if (session.getAttribute("power").toString().equals("0")) {
                return "redirect:admin/index";
            } else {
                return "redirect:admin/goods";
            }
        }
        model.addAttribute("msg", "账号密码错误");
        return "login";
    }

    @PostMapping("/register")
    public String toRegisterPage(String username, String password, String repassword, String email, String type, int power, Model model) {

        if (username.isEmpty() || password.isEmpty()) {
            model.addAttribute("msg", "用户名或者密码不能为空");
            return "login";
        }
        if (!password.trim().equals(repassword.trim())) {
            model.addAttribute("msg", "两次密码不匹配");
            return "login";
        }
        User user = userservice.selectUserName(username);
        if (user != null) {
            model.addAttribute("msg", "用户名已存在");
            return "login";
        }
        User userInfo = new User();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setEmail(email);
        userInfo.setPower(type.trim().equals("index") ? power : 1);
        //获取当前时间
        userInfo.setCreate_time(DateTimeUtil.getCurrentTime());
        int num = userservice.insertUser(userInfo);
        if (num > 0) {
            if (type.trim().equals("index")) {
                model.addAttribute("msg", "添加成功");
                return "tip";
            }
            model.addAttribute("msg", "注册成功");
            return "login";
        }
        if (type.trim().equals("index")) {
            model.addAttribute("msg", "添加失败,原因未知");
            return "tip";
        }
        model.addAttribute("msg", "注册失败,原因未知");
        return "login";
    }

    @PostMapping("/update")
    public String updatefn(String username, String password, String repassword, String email, String type, int power, HttpSession session, Model model) {
        if (password.isEmpty() || repassword.isEmpty()) {
            model.addAttribute("msg", "密码不能为空");
            return "tip";
        }
        if (!password.trim().equals(repassword.trim())) {
            model.addAttribute("msg", "两次输入的密码不匹配");
            return "tip";
        }
        if (email.isEmpty()) {
            model.addAttribute("msg", "邮箱不能为空");
            return "tip";
        }
        User userInfo = new User();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setEmail(email);
        userInfo.setPower(type.trim().equals("index") ? power : 1);

        int info = userservice.updateUser(userInfo);
        if (info > 0) {
            model.addAttribute("msg", "修改成功");
            session.setAttribute("email", email);
            if (type.trim().equals("index")) {
                return "tip";
            }
            return "login";
        }
        model.addAttribute("msg", "修改失败！原因未知");
        if (type.trim().equals("index")) {
            return "tip";
        }
        return "login";
    }

    @PostMapping("/addGoods")
    public String toAddGoods(String titleName, double price, int number, int goodsType, int status, String picture, HttpSession session, Model model) {
        if (titleName.isEmpty()) {
            model.addAttribute("msg", "商品标题不能为空");
            return "tip";
        }
        if (picture.isEmpty()) {
            model.addAttribute("msg", "图片不能为空");
            return "tip";
        }
        Goods goods = new Goods();
        goods.setG_titleName(titleName);
        goods.setG_price(price);
        goods.setG_number(number);
        goods.setG_goodsType(goodsType);
        goods.setG_status(status);
        goods.setG_picture(picture);
        goods.setG_createTime(DateTimeUtil.getCurrentTime());
        int info = goodsService.insertGoods(goods);

        if (info > 0) {
            model.addAttribute("msg", "新增商品成功");
            return "redirect:admin/goods";
        }
        model.addAttribute("msg", "新增商品失败");
        return "redirect:admin/goods";
    }

    @PostMapping("/updateGoods")
    public String updateGoods(@RequestParam Map<String, Object> paras) {
        System.out.println(paras);
        Goods goods = new Goods();
        goods.setG_id(Integer.parseInt(paras.get("id").toString()));
        goods.setG_titleName(paras.get("titleName").toString());
        goods.setG_price(Double.parseDouble(paras.get("price").toString()));
        goods.setG_number(Integer.parseInt(paras.get("number").toString()));
        goods.setG_goodsType(Integer.parseInt(paras.get("goodsType").toString()));
        goods.setG_status(Integer.parseInt(paras.get("status").toString()));
        goods.setG_picture(paras.get("picture").toString());

        goodsService.updateGoods(goods);
        return "redirect:admin/goods";
    }

}
