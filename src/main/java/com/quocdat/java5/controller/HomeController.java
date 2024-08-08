package com.quocdat.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/SinhVien")
    public String doGetViewSinhVien(Model model){
        model.addAttribute("currentView", "SinhVien");
        return "user/SinhVien/SinhVien";
    }

    @GetMapping({"/","/login"})
    public String doGetViewLogin () {
        return "pages/login";
    }
}
