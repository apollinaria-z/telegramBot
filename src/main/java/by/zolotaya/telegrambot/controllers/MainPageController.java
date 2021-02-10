package by.zolotaya.telegrambot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping()
    public String clientPage(){
        return "mainpage/mainpage";
    }
}
