package com.bb.abrishw.controllers;

import com.bb.abrishw.dtos.ScoredIssueBySpecificUserDto;
import com.bb.abrishw.dtos.ScoringDto;
import com.bb.abrishw.services.IssueService;
import com.bb.abrishw.services.UserService;
import com.bb.abrishw.utilities.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PlanningPokerController {

  private IssueService issueService;
  private UserService userService;
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  public PlanningPokerController(IssueService issueService, UserService userService, JwtTokenUtil jwtTokenUtil) {
    this.issueService = issueService;
    this.userService = userService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @GetMapping()
  public String displayIssues(Model model,@CookieValue(value = "authToken",required = false) String token) {
    if(token == null){
      return "redirect:/login";
    }else {
      model.addAttribute("username", userService.findById(jwtTokenUtil.getIdFromToken(token)).getUsername());
      model.addAttribute("issues", issueService.findAll());
      return "issues";
    }
  }

  @GetMapping("/scoring")
  public String displayUserSpecificScorableIssues(Model model,@CookieValue(value = "authToken",required = false) String token) {
    if(token == null){
      return "redirect:/login";
    }else {
      int userId = jwtTokenUtil.getIdFromToken(token);
      List<ScoredIssueBySpecificUserDto> scoredIssues = issueService.findAlreadyScoredIssuesForSpecificUser(userId);
      model.addAttribute("username", userService.findById(userId).getUsername());
      model.addAttribute("userId", userId);
      model.addAttribute("scorableIssues", issueService.findScorableIssuesForSpecificUser(userId));
      model.addAttribute("scoredIssues", scoredIssues);
      return "scoring-board";
    }
  }

  @PostMapping("add-score")
  public String setScoreToIssue(@ModelAttribute ScoringDto scoringDto) {
    issueService.conductScoringProcess(scoringDto);
    return "redirect:/scoring";
  }
}
