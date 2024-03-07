package com.example.jpawhereannotation.domain.entity;

import com.example.jpawhereannotation.domain.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "teams")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction(value = "status = 'ACTIVE'")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
  private List<Member> members = new ArrayList<>();

  @Enumerated(value = EnumType.STRING)
  private Status status;

  @Builder
  public Team(String name, List<Member> members, Status status) {
    this.name = name;
    this.members = members;
    this.status = status;
  }
}
