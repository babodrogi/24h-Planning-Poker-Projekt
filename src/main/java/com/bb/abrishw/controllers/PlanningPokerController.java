package com.bb.abrishw.controllers;

import com.bb.abrishw.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanningPokerController {

  private IssueService issueService;

  @Autowired
  public PlanningPokerController(IssueService issueService) {
    this.issueService = issueService;
  }

  @GetMapping
  public String displayIssues(Model model){
    model.addAttribute("issues",issueService.findAll());
    return "issues";
  }

}
