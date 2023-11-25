package com.example.osivexample.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "teams")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
  private List<Member> members = new ArrayList<>();
}
