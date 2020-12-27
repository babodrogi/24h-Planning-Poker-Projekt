package com.bb.abrishw.controllers;

import com.bb.abrishw.services.IssueService;
import com.bb.abrishw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PlanningPokerController {

  private IssueService issueService;
  private UserService userService;

  @Autowired
  public PlanningPokerController(IssueService issueService, UserService userService) {
    this.issueService = issueService;
    this.userService = userService;
  }

  @GetMapping
  public String displayIssues(Model model) {
    model.addAttribute("issues", issueService.findAll());
    return "issues";
  }

  @GetMapping("/scoring/{userId}")
  public String displayUserSpecificScorableIssues(Model model, @PathVariable int userId) {
    model.addAttribute("issues", issueService.findScorableIssuesForSpecificUser(userId));
    return "scoring-board";
  }

  @PostMapping("add-score")
  public String setScoreToIssue(Model model, Integer issue_id, @CookieValue("token") String token) {

    String id = issue_id.toString();
    return "redirect:vmi/" + id;
  }

  @GetMapping("/vmi/{id}")
  public String rendervmi(Model model, @PathVariable int id) {
    model.addAttribute("vmi", id);
    return "vmi";
  }
}
