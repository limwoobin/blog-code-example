package com.example.jpawhereannotation.controller;

import com.example.jpawhereannotation.domain.entity.Member;
import com.example.jpawhereannotation.domain.entity.Team;
import com.example.jpawhereannotation.domain.repository.MemberRepository;
import com.example.jpawhereannotation.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class TestController {
  private final TeamRepository teamRepository;
  private final MemberRepository memberRepository;

  @GetMapping(value = "/teams")
  public ResponseEntity<String> hi() {
    List<Team> teams = teamRepository.findAll();
    System.out.println(teams.size());

    for (Team team : teams) {
      System.out.println(team.getMembers().size());
    }

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @GetMapping(value = "/members")
  public ResponseEntity<String> hi2() {
    List<Member> members = memberRepository.findAll();
    System.out.println(members.size());

    for (Member member : members) {
      System.out.println(member.getTeam().getName());
    }

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @GetMapping(value = "/members2")
  public ResponseEntity<String> hi3() {
//    Member member = memberRepository.findByName("member4");

//    List<Member> members = memberRepository.findByName("member4");
//    System.out.println(members.size());

//    for (Member member : members) {
//      System.out.println(member.getTeam().getName());
//    }

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

}
