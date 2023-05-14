package com.example.demo005.controller;


import com.example.demo005.domain.Goods;
import com.example.demo005.domain.User;
import com.example.demo005.service.GoodsService;
import com.example.demo005.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

@Controller
public class GetUserController {
    @Autowired
    private UserService userservice;

    @Autowired
    private GoodsService goodsService;

    private Boolean isLogin(HttpSession session) {
        return session.getAttribute("loginUser") == null;
    }

    //重置登录信息
    private void resetSession(HttpSession session) {
        //退出用户
        Boolean isexist = session.getAttribute("loginUser") == null;
        userservice.updateUserStatus(isexist ? "" : session.getAttribute("loginUser").toString(), 0);
        session.removeAttribute("loginUser");
        session.removeAttribute("user");
        session.removeAttribute("userId");
        session.removeAttribute("username");
        session.removeAttribute("email");
        session.removeAttribute("power");
    }

    @RequestMapping("/admin/index")
    public String toIndex(@RequestParam Map<String, Object> map, Model model, HttpSession session) {
        if (isLogin(session)) return "tip";

        model.addAttribute("user_name", session.getAttribute("loginUser"));

        int page = 1;
        int limit = 5;
        String search = map.get("search") == null || map.get("search") == "" ? "" : map.get("search").toString();

        User[] dataUser, userAll;
        userAll = userservice.querySearchUsers("%" + search + "%");

        //使用模板引擎来传递数据给前端
        if (!map.isEmpty()) {
            //获取分页 page跟limit
            page = Integer.parseInt(map.get("page").toString());
            limit = Integer.parseInt(map.get("limit").toString());
            /*
                1、先判断当前查询页面是否大于总数据
                2、如果不大于则直接截取数据 获取分页后的实际数据
                3、如果大于则进行计算，start+用户长度 除于 条数 取余
                (page <= userAll.length / limit) ? (limit * page) : (start + userAll.length % limit)
            */
            int start = limit * (page - 1);
            int end = (page <= userAll.length / limit) ? (limit * page) : (start + userAll.length % limit);
            dataUser = Arrays.copyOfRange(userAll, start, end);
        } else {
            dataUser = Arrays.copyOfRange(userAll, limit * (page - 1), limit * page);
        }
        model.addAttribute("dataTable", dataUser);
        return "admin/index";
    }


    @RequestMapping("/login")
    public String toLoginPage(HttpSession session) {
        resetSession(session);
        return "login";
    }

    @RequestMapping("/register")
    public String toRegisterPage(HttpSession session) {
        return "register";
    }

    @RequestMapping("/out")
    public String outFn(HttpSession session) {
        resetSession(session);
        return "redirect:login";
    }


    //删除用户
    @RequestMapping("/deleteUser")
    public String deleteUser(int id) {
        int code = userservice.deleteUser(id);
        //路由重定向，防止暴露删除用户的接口
        return "redirect:/tip";
    }

    //操作结果提示页面
    @RequestMapping("/tip")
    public String tipSuccess() {
        return "tip";
    }


    @RequestMapping("/admin/goods")
    public String toGoodsPage(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
        if (session.getAttribute("loginUser") == null) {
            return "tip";
        }
        model.addAttribute("user_name", session.getAttribute("loginUser"));

        int page = 1;
        int limit = 5;
        String search = map.get("search") == null || map.get("search") == "" ? "" : map.get("search").toString();
        Goods[] dataGoods;
        System.out.println(search);
        Goods[] goodsAll = goodsService.querySearchGoods("%" + search + "%");//"%"+search+"%"

        //使用模板引擎来传递数据给前端
        if (!map.isEmpty()) {
            //获取分页 page跟limit
            page = Integer.parseInt(map.get("page").toString());
            limit = Integer.parseInt(map.get("limit").toString());
            /*
                1、先判断当前查询页面是否大于总数据
                2、如果不大于则直接截取数据 获取分页后的实际数据
                3、如果大于则进行计算，start+用户长度 除于 条数 取余
                (page <= userAll.length / limit) ? (limit * page) : (start + userAll.length % limit)
            */
            int start = limit * (page - 1);
            int end = (page <= goodsAll.length / limit) ? (limit * page) : (start + goodsAll.length % limit);
            dataGoods = Arrays.copyOfRange(goodsAll, start, end);
        } else {
            dataGoods = Arrays.copyOfRange(goodsAll, limit * (page - 1), limit * page);
        }

        model.addAttribute("user_name", session.getAttribute("loginUser"));
        model.addAttribute("dataTable", dataGoods);
        return "admin/goods";
    }

    //删除商品
    @RequestMapping("/deleteGoods")
    public String deleteGoods(int id) {
        int code = goodsService.deleteGoods(id);
        System.out.println(code);
        //路由重定向，防止暴露删除用户的接口
        return "redirect:/tip";
    }

    //下载文件 通过文件名
    @RequestMapping("/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String filename) {
        String distPath = "K://SpringBoot//file//";
        File file = new File(distPath + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        try {
            filename = getFilename(request, filename);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<byte[]>(e.getMessage().getBytes(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    //解决不同浏览器下载文件名乱码问题
    public String getFilename(HttpServletRequest request, String filename) throws Exception {
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                return URLEncoder.encode(filename, "UTF-8").replace("+", " ");
            }
        }
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }


}
