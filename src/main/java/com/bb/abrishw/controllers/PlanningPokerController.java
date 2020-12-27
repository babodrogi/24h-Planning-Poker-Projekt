package com.bb.abrishw.controllers;

import com.bb.abrishw.model.Issue;
import com.bb.abrishw.model.Score;
import com.bb.abrishw.dtos.ScoringDto;
import com.bb.abrishw.model.User;
import com.bb.abrishw.services.IssueService;
import com.bb.abrishw.services.UserService;
import com.bb.abrishw.utilities.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

  @GetMapping
  public String displayIssues(Model model,@CookieValue(value = "authToken",required = false) String token) {
    int id = jwtTokenUtil.getIdFromToken(token);
    model.addAttribute("id",id);
    model.addAttribute("issues", issueService.findAll());
    return "issues";
  }

  @GetMapping("/scoring")
  public String displayUserSpecificScorableIssues(Model model,@CookieValue("authToken") String token) {
    int userId = jwtTokenUtil.getIdFromToken(token);
    model.addAttribute("userId",userId);
    model.addAttribute("issues", issueService.findScorableIssuesForSpecificUser(userId));
    return "scoring-board";
  }

  @PostMapping("add-score")
  public String setScoreToIssue(Model model, @ModelAttribute ScoringDto scoringDto) {
    Issue issue = issueService.findById(scoringDto.getIssueId());
    User user = userService.findById(scoringDto.getUserId());
    Score score = new Score(user,issue,scoringDto.getScoreValue());

    user.getScoresGiven().add(score);
    user.getIssuesVotedFor().add(issue);
    issue.getVoters().add(user);
    issue.getScores().add(score);

    userService.save(user);
    issueService.save(issue);

    return "redirect:/scoring";
  }

}
