package com.bb.abrishw.controllers;

import com.bb.abrishw.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {


  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String displayLogin(@RequestParam(required = false) String errorMessage ,Model model){
    model.addAttribute("errorMessage",errorMessage);
    return "login";
  }

  @PostMapping("/login")
    public String login(String username, String password, HttpServletResponse response){

    if(userService.findByUsername(username) == null){
      String errorMessage = "User Doesn't Exist";
      return "redirect:/login?errorMessage=" + errorMessage;
    }else if(!userService.findByUsername(username).getPassword().equals(password)){
      String errorMessage = "Wrong Password";
      return "redirect:/login?errorMessage=" + errorMessage;
    }else{
      //setCookie
      Cookie cookie = new Cookie("username",username);
      cookie.setHttpOnly(true);
      cookie.setMaxAge(60 * 60);
      cookie.setPath("/");
      response.addCookie(cookie);

      return "redirect:/";
    }
  }

  @GetMapping("/logout")
  public String logout(HttpServletResponse response){
    Cookie cookie = new Cookie("username", null);
    cookie.setMaxAge(0);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    response.addCookie(cookie);
    return "redirect:/login";
  }
}
