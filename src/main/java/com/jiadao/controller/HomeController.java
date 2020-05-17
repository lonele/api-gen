package com.jiadao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author lvenle
 */
@Controller
public class HomeController
{

    @RequestMapping("/")
    String index() {
        System.out.println("home index");
        return "redirect:/pages/index.html";
    }

}
