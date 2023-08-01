package com.works.controllers;

import com.works.services.CustomerMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    final CustomerMessageListener customerMessageListener;

    //@Scheduled(fixedDelay = 5000, timeUnit = TimeUnit.MILLISECONDS)
    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("all", customerMessageListener.allMessage().getContent());
        return "dashboard";
    }

    // delete message
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customerMessageListener.delete(id);
        return "redirect:/";
    }

}
