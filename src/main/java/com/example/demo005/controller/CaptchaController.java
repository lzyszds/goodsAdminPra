package com.example.demo005.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * 验证码Controller
 * 主要生成验证码
 * 获取验证码 String code = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
 */


@Controller
public class CaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @RequestMapping("/common/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        Random random = new Random();
        try {
            //生产验证码字符串并保存到session中
            String text = captchaProducer.createText();
            String s1 = text.substring(0, 1);
            String s2 = text.substring(1, 2);
            //生成随机数1-3位
            int kaptchaLength = random.nextInt(2) + 1;
            String[] countKey = {"+", "-", "×"};
            int[] countRes = {
                    Integer.valueOf(s1).intValue() + Integer.valueOf(s2).intValue(), //加
                    Integer.valueOf(s1).intValue() - Integer.valueOf(s2).intValue(), //减
                    Integer.valueOf(s1).intValue() * Integer.valueOf(s2).intValue(), //乘
//                    Integer.valueOf(s1).intValue() / Integer.valueOf(s2).intValue()  //除  除法让用户算，有点难算，不需要了
            };
            //生成图片验证码
            httpServletRequest.getSession().setAttribute("verifyCode", countRes[kaptchaLength]);
            BufferedImage challenge = captchaProducer.createImage(s1 + countKey[kaptchaLength] + s2 + "=?    ");
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}


