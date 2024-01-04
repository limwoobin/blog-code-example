package com.example.transactionpropagation.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "team_histories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;

  private TeamHistory(Team team) {
    this.name = team.getName();
    this.team = team;
  }

  public static TeamHistory from(Team team) {
    return new TeamHistory(team);
  }
}
