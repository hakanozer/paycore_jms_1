package com.works.controllers;

import com.works.models.CustomerMessage;
import com.works.services.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    final HomeService homeService;

    String message = "";
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", message);
        message = "";
        return "home";
    }

    @PostMapping("/send")
    public String send(CustomerMessage customerMessage) {
        boolean status = homeService.send(customerMessage);
        message = status == true ? "Message Send" : "Message Send Fail";
        return "redirect:/";
    }

}
