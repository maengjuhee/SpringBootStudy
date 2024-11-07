package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
   @GetMapping("/")
   public String main_page(Model model) {
      model.addAttribute("msg","Hello Spring Boot^0^");
      return "main"; // main.html
   }
}
