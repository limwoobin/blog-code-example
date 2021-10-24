package com.example.jpaexample.team;

import com.example.jpaexample.domain.entity.Member;
import com.example.jpaexample.domain.entity.Team;
import com.example.jpaexample.domain.repository.MemberRepository;
import com.example.jpaexample.domain.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TeamDomainTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

//    @BeforeEach
//    public void init() {
//        Team team = new Team("test");
//        teamRepository.save(team);
//    }

    @Test
    @Transactional
    public void 동일성_테스트() {
        Team team1 = teamRepository.findById(1L).get();
        Team team2 = teamRepository.findById(1L).get();

        boolean val = team1 == team2;
        System.out.println("### 동일성 테스트 결과 : " + val);
        assertThat(val).isEqualTo(true);
    }

    @Test
    public void 동일성_테스트_v2() {
        Team team1 = teamRepository.findById(1L).get();
        Team team2 = teamRepository.findById(1L).get();

        boolean val = team1 == team2;
        System.out.println("### 동일성 테스트 결과 : " + val);
        assertThat(val).isEqualTo(true);
    }

    @Nested
    class 지연_로딩_테스트_클래스 {

        @BeforeEach
        void init() {
            Team team = new Team("test");
            Member member = new Member("member1");
            Member member2 = new Member( "member2");
            Member member3 = new Member("member3");

            team.addMembers(member);
            team.addMembers(member2);
            team.addMembers(member3);
            teamRepository.save(team);

            entityManager.flush();
            entityManager.clear();
            entityManager.close();
        }

        @Test
        @Transactional
        public void 연관관계_객체가_프록시객체라면_성공() {
            Team team = teamRepository.findById(1L)
                    .orElseThrow(RuntimeException::new);

            boolean isLoad = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(team.getMembers());
            assertThat(isLoad).isEqualTo(false);
        }

        @Test
        @Transactional
        public void 연관관계_객체가_초기화_되었다면_성공() {
            Team team = teamRepository.findById(1L)
                    .orElseThrow(RuntimeException::new);

            System.out.println(team.getMembers().size());

            boolean isLoad = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(team.getMembers());
            assertThat(isLoad).isEqualTo(true);
        }
    }
}
