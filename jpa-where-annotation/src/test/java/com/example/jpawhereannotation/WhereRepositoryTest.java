package com.example.jpawhereannotation;

import com.example.jpawhereannotation.domain.Status;
import com.example.jpawhereannotation.domain.entity.Member;
import com.example.jpawhereannotation.domain.entity.QMember;
import com.example.jpawhereannotation.domain.entity.QTeam;
import com.example.jpawhereannotation.domain.entity.Team;
import com.example.jpawhereannotation.domain.repository.MemberRepository;
import com.example.jpawhereannotation.domain.repository.TeamRepository;
import com.example.jpawhereannotation.domain.value.MemberDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WhereRepositoryTest {

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private EntityManager entityManager;

  private JPAQueryFactory jpaQueryFactory;

  private Team team1;
  private Member member1;
  private Member member2;

  @BeforeEach
  void setUp() {
    jpaQueryFactory = new JPAQueryFactory(entityManager);

    team1 = Team.builder()
      .name("team1")
      .status(Status.ACTIVE)
      .build();

    teamRepository.save(team1);

    member1 = Member.builder()
      .name("member1")
      .team(team1)
      .status(Status.ACTIVE)
      .build();

    member2 = Member.builder()
      .name("member2")
      .team(team1)
      .status(Status.DISABLE)
      .build();

    memberRepository.save(member1);
    memberRepository.save(member2);
  }

  @Test
  void basic_test() {
    entityManager.flush();
    entityManager.clear();

    Optional<Team> team = teamRepository.findById(1L);

    assertThat(team.isPresent()).isTrue();
  }

  @DisplayName(value = "Lazy Loading 의 경우에도 @Where 조건이 정상적으로 동작한다")
  @Test
  void test() {
    entityManager.flush();
    entityManager.clear();

    Team team = teamRepository.findById(1L).get();
    List<Member> members = team.getMembers();

    assertThat(members.size()).isEqualTo(1);
  }

  @DisplayName(value = "JPQL 실행시에도 @Where 조건이 정상적으로 동작한다")
  @Test
  void test2() {
    List<Member> members = memberRepository.findAll();

    assertThat(members.size()).isEqualTo(1);
  }

  @DisplayName(value = "식별자를 이용하는 경우에도 @Where 조건이 정상적으로 동작한다")
  @Test
  void test3() {
    entityManager.flush();
    entityManager.clear();

    Optional<Member> result = memberRepository.findById(1L);
    Optional<Member> result2 = memberRepository.findById(2L);

    assertThat(result.isPresent()).isTrue();
    assertThat(result2.isPresent()).isFalse();
  }

  @Test
  void test4() {
    QTeam team = QTeam.team;

    Team result = jpaQueryFactory.selectFrom(team)
      .where(team.id.eq(1L))
      .fetchOne();

    assertThat(result.getId()).isEqualTo(1L);
  }

  @Test
  void test5() {
//    entityManager.flush();
//    entityManager.clear();

    QTeam team = QTeam.team;
    QMember member = QMember.member;

    List<Member> result = jpaQueryFactory.selectFrom(member)
      .join(team).on(team.eq(member.team))
      .where(member.id.eq(1L))
      .fetch();

    assertThat(result.size()).isEqualTo(1L);
  }

  @Test
  void test6() {
//    entityManager.flush();
//    entityManager.clear();

    QTeam team = QTeam.team;
    QMember member = QMember.member;

    List<MemberDto> result = jpaQueryFactory.select(
      Projections.constructor(MemberDto.class,
        member.id,
        member.name,
        team.id
      ))
      .from(member)
      .join(team).on(team.eq(member.team))
      .where(member.id.eq(1L))
      .fetch();

    assertThat(result.size()).isEqualTo(1L);
  }

  @Test
  void test7() {
    entityManager.flush();
    entityManager.clear();

    QTeam team = QTeam.team;
    QMember member = QMember.member;

    List<MemberDto> result = jpaQueryFactory.select(
        Projections.constructor(MemberDto.class,
          member.id,
          member.name,
          team.id
        ))
      .from(team)
      .join(member).on(member.team.eq(team))
      .where(team.id.eq(1L))
      .fetch();

    System.out.println(result);
  }

  @DisplayName("native query")
  @Test
  void test8() {
    List<Member> members = memberRepository.findByTeamId(1L);

    assertThat(members.size()).isEqualTo(2);
  }

  @DisplayName(value = "1차 캐시")
  @Test
  void test9() {
    Optional<Member> optionalMember = memberRepository.findById(1L);
    if (optionalMember.isPresent()) {
      Member member = optionalMember.get();
      member.delete();
      memberRepository.save(member);
    }

    Optional<Member> persistMember = memberRepository.findById(1L);
    assertThat(persistMember.isEmpty()).isTrue();

//    Member persistMember = memberRepository.findByName("member1");
//    System.out.println(persistMember.getName());
  }

  @Test
  void test10() {
    Member newMember = Member.builder()
      .team(team1)
      .name("member3")
      .status(Status.ACTIVE)
      .build();

    memberRepository.save(newMember);

    Member member = memberRepository.findByName(newMember.getName());
    System.out.println(member.getName());
  }
}
