package com.xs.myblog.controller.admin;


import com.xs.myblog.pojo.User;
import com.xs.myblog.service.UserService;
import com.xs.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String loginPage() {

        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);//不保存密码
            session.setAttribute("user", user);
            return "/admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(String password1,String password2,
                                 HttpSession session,RedirectAttributes attributes) {
        if (password1 == null || password2 == null || password1.equals("") || password2.equals("")) {
            attributes.addFlashAttribute("message", "密码不能为空");
            return "redirect:/admin/updatePassword1";
        }
        if (!password1.equals(password2)) {
            attributes.addFlashAttribute("message", "密码不一致");
            return "redirect:/admin/updatePassword1";
        }
        attributes.addFlashAttribute("message", "密码更新成功，请重新登录");
        String code = MD5Utils.code(password1);
        User user = (User) session.getAttribute("user");
        userService.updatePassword(user.getId(), code);
        return "redirect:/admin";
    }

    @GetMapping("/updatePassword1")
    public String updatePassword1() {
        return "/admin/updatePassword";
    }
}
