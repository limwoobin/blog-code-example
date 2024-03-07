package com.example.jpawhereannotation.domain.entity;

import com.example.jpawhereannotation.domain.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction(value = "status = 'ACTIVE'")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;

  @Enumerated(value = EnumType.STRING)
  private Status status;

  @Builder
  public Member(String name, Team team, Status status) {
    this.name = name;
    this.team = team;
    this.status = status;
  }
}
