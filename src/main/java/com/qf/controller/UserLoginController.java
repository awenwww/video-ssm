package com.qf.controller;

import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    UserService userService;
    @PostMapping("/loginUser")
    @ResponseBody
    public String login(User user, HttpSession session){
       // System.out.println(user);
        User byEmailAndPass = userService.findByEmailAndPass(user);
       // System.out.println(byEmailAndPass);
        session.setAttribute("user",byEmailAndPass);
        if(byEmailAndPass!=null){
            return "success";
        }else{
            return "failed";
        }
    }
    @GetMapping("/loginOut")
    @ResponseBody
    public void loginOut(HttpSession session){
        //System.out.println("111");
        session.invalidate();
    }
    @PostMapping("/validateEmail")
    @ResponseBody
    public String validateEmail(User user){
        System.out.println(user);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());
        user.setCreateTime(format1);
        System.out.println(user);
        User user1=userService.validateEmail(user);
        if(user1!=null){
            return "failed";
        }else{
            return "success";
        }
    }

    @PostMapping("/insertUser")
    @ResponseBody
    public String insertUser(User user,HttpSession session){
        System.out.println(user);
        int count=userService.insertUser(user);
        System.out.println(count);
        if(count>=1){
            session.setAttribute("user",user);
            return "success";
        }else{
            return "failed";
        }
    }

    @GetMapping("/showMyProfile")
    public String showMyProfile(HttpSession session, Model model){
       // User user = (User)session.getAttribute("user");
        //System.out.println(user);
       // model.addAttribute("user1",user);
        return "/before/my_profile.jsp";

    }
    @PostMapping("/validatePassword")
    @ResponseBody
    public String validatePassword(User user,HttpSession session){
        User oldUser=(User)session.getAttribute("user");
        if(oldUser.getPassword().equals(user.getPassword())){
            return "success";
        }else{
            return "failed";
        }
    }
    @GetMapping("/passwordSafe")
    public String toPasswordSafe(){
        return "/before/password_safe.jsp";
    }
    @PostMapping("/updatePassword")
    public String updatePassword(String newPassword,HttpSession session) throws Exception {
        User user = (User)session.getAttribute("user");
        User user1 = new User();
        user1.setId(user.getId());
        user1.setPassword(newPassword);
        int count=userService.update(user1);
        if(count>=1){
            String email = user.getEmail();
            MailUtils.sendMail(email,
                    "你的密码已经被修改 如非本人操作请尽快找会密码",
                    "密码修改通知" );
            session.invalidate();
            return "redirect:../index";
        }

            throw new Exception("修改密码失败");

    }
    @GetMapping("/changeProfile")
    public String toChangeProfile(){
        return "/before/change_profile.jsp";
    }
    @PostMapping("/updateUser")
    public String updateUser(User user,HttpSession session) throws Exception {
        System.out.println(user);
        int update = userService.update(user);
        if(update>0){
            User u=(User)session.getAttribute("user");
            //System.out.println(u);
            User byEmailAndPass = userService.findByEmailAndPass(u);
            session.removeAttribute("user");
            session.setAttribute("user",byEmailAndPass);
            //System.out.println(byEmailAndPass);
            return "/before/my_profile.jsp";
        }
        throw new Exception("修改信息失败");
    }
    @GetMapping("/changeAvatar")
    public String toChangeAvatar(){
        return "/before/change_avatar.jsp";
    }
    @PostMapping("/upLoadImage")
    public String upLoadImage(MultipartFile file,HttpSession session){
        String originalFilename = file.getOriginalFilename();
        String path="D:\\apache-tomcat-8.5.47(1)\\webapps\\upload\\video\\";
        File file1=new File(path);
        if(!file1.exists()){
            file1.mkdirs();
        }
        System.out.println(path);
        originalFilename=UUID.randomUUID().toString().replace("-","")+"_"+originalFilename;
        try {
            file.transferTo(new File(path,originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
       User user= (User)session.getAttribute("user");
        System.out.println(user);
        User user1 = new User();
        user1.setImgUrl("http://localhost:8083/upload/video/"+originalFilename);
        user1.setId(user.getId());
        System.out.println(user1);
        int update = userService.update(user1);
        if(update>=1){
            user.setImgUrl("http://localhost:8083/upload/video/"+originalFilename);
            session.removeAttribute("user");
            session.setAttribute("user",user);
        }

        return "redirect:../user/showMyProfile";
    }
    @GetMapping("/loginOut2")
    public String loginOut2(HttpSession session){
        session.invalidate();
        return "redirect:../index";
    }
    @GetMapping("/forgetPassword")
    public String toForgetPassword(){
        return "/before/forget_password.jsp";
    }


    @RequestMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(HttpSession session,User user){

        String validateCode = MailUtils.getValidateCode(6);
        try {
            MailUtils.sendMail(user.getEmail(),"您正在进行重置密码操作，验证码："+validateCode,"重置密码");
        }catch (Exception e){
            return "failed";
        }

        return "success";
    }
    @PostMapping("/validateEmailCode")
    public String validateEmailCode(Model model,User u){
        int update = userService.updateByEmail(u);
        if(update>=1){
            //跳转到重置密码界面
            model.addAttribute("repassUser",u);
            return "forward:/user/toResetPassword";
        }
        return null;
    }

    @RequestMapping("/toResetPassword")
    public String toResetPassword(){
        return "/before/reset_password.jsp";
    }
    @PostMapping("/resetPassword")
    public String resetPassword(User user) throws Exception {
        int i = userService.repassByEmail(user);
        if(i>=0){
            return "redirect:../index";
        }
        throw new Exception("重置密码失败");
    }

}
