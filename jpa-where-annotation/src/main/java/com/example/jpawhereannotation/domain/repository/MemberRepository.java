package com.example.jpawhereannotation.domain.repository;

import com.example.jpawhereannotation.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

  @Query(value = "select * from members where team_id = :teamId", nativeQuery = true)
  List<Member> findByTeamId(Long teamId);

  Member findByName(String name);
}
