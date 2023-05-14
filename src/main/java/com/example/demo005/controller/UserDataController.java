package com.example.demo005.controller;

import com.example.demo005.domain.User;
import com.example.demo005.service.GoodsService;
import com.example.demo005.service.UserService;
import com.example.demo005.utill.DateTimeUtil;
import com.example.demo005.utill.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@RestController
public class UserDataController {
    private String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/uploadFile/";

    @Autowired
    private UserService userservice;

    @Autowired
    private GoodsService goodsService;


    //以接口的形式返回全部用户数据
    @RequestMapping("/userApi/allUserDet")
    public JsonResult<User[]> queryAllUserDet(@RequestParam Map<String, Object> map) {
        if (!map.isEmpty()) {
            //获取分页 page跟limit
            int page = Integer.parseInt(map.get("page").toString());
            int limit = Integer.parseInt(map.get("limit").toString());
            //截取数组，获取分页后的实际数据
            User[] userAll = userservice.querySearchUsers("");
            User[] users = Arrays.copyOfRange(userAll, limit * (page - 1), limit);

            return new JsonResult<User[]>(users);
        }
        return new JsonResult<User[]>(userservice.querySearchUsers(""));
    }

    //以接口的形式返回全部用户的个数
    @RequestMapping("/userApi/allUserLength")
    public JsonResult<String> queryAllUserLength(String search) {
        int length = userservice.querySearchUsers("%" + search + "%").length;
        return new JsonResult<String>(Integer.toString(length));
    }

    //以接口的形式返回全部用户的个数
    @RequestMapping("/userApi/allGoodsLength")
    public JsonResult<String> queryAllGoodsLength(String search) {
        int length = goodsService.querySearchGoods("%" + search + "%").length;
        return new JsonResult<String>(Integer.toString(length));
    }

    @PostMapping("/userApi/upload")
    public JsonResult<String> uploadImgpost(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest req) {
        //判断文件是否为空
        if (!file.isEmpty()) {
            //获取文件类型
            String type = file.getContentType();
            //判断提交的类型是否为图片
            if (!type.matches("image/.*")) {
                model.addAttribute("msg", "只能上传图片");
                return new JsonResult<String>("上传失败！只能上传图片！");
            }
            //获取当前时间的时间戳
            String format = Integer.toString(DateTimeUtil.getCurrentTimeInt());
            //生成文件夹
            File folder = new File(uploadPath);
            //判断文件夹是否存在
            if (!folder.isDirectory()) {
                if (!folder.mkdirs()) {
                    new JsonResult<String>("文件夹创建失败");
                }
            }
            //获取文件名
            String oldName = file.getOriginalFilename();
            //生成新的文件名 防止重复 UUID+时间戳+后缀名 UUID.randomUUID() +
            String newName = format + oldName.substring(oldName.lastIndexOf("."), oldName.length());
            //初始化要返回的图片路径
            String filePath;
            try {
                //将文件保存到指定位置
                file.transferTo(new File(folder, newName));
                //返回图片路径
                filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + newName;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new JsonResult<String>(filePath);
        } else {
            return new JsonResult<String>("上传失败！上传的内容不存在");
        }
    }

//    @RequestMapping("/userApi/downloadImg")
//    //文件下载
//    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String filename) {
////        检查文件名是否是路径 如果是路径则剪去路径，保留文件名
//        if (filename.contains("/")) {
//            filename = filename.substring(filename.lastIndexOf("/") + 1);
//        }
//        File file = new File(uploadPath + filename);
//        HttpHeaders headers = new HttpHeaders();
//        try {
//            filename = getFilename(request, filename);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        headers.setContentDispositionFormData("attachment", filename);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        try {
//            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
//        } catch (IOException e) {
//            return new ResponseEntity<byte[]>(e.getMessage().getBytes(), HttpStatus.EXPECTATION_FAILED);
//        }
//    }
//
//    //解决不同浏览器下载文件名乱码问题
//    public String getFilename(HttpServletRequest request, String filename) throws Exception {
//        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
//        String userAgent = request.getHeader("User-Agent");
//        for (String keyWord : IEBrowserKeyWords) {
//            if (userAgent.contains(keyWord)) {
//                return URLEncoder.encode(filename, "UTF-8").replace("+", " ");
//            }
//        }
//        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
//    }
//
//    //操作用户在线状态接口
//    @RequestMapping("/userApi/userStatusOper")
//    public JsonResult<String> offline(String username, int status) {
//        userservice.updateUserStatus(username, status);
////        System.out.println(username + "" + status);
//        return new JsonResult<String>("tip");
//    }

}
