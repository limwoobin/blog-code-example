package com.example.jpaexample.team;

import com.example.jpaexample.domain.entity.Member;
import com.example.jpaexample.domain.entity.Team;
import com.example.jpaexample.domain.repository.MemberRepository;
import com.example.jpaexample.domain.repository.TeamRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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


    @Nested
    class 동일성_테스트_클래스 {

        public Team init() {
            Team team = new Team("test");
            teamRepository.save(team);

            entityManager.clear();
            entityManager.close();

            return team;
        }

        @Test
        @Transactional
        public void 동일성_테스트() {
            Long id = this.init().getId();

            Team team1 = teamRepository.findById(id).get();
            Team team2 = teamRepository.findById(id).get();

            boolean val = team1 == team2;
            System.out.println("### 동일성 테스트 결과 : " + val);
            assertThat(val).isEqualTo(true);

            teamRepository.deleteAll();
        }

        @Test
        public void 동일성_테스트_v2() {
            Long id = this.init().getId();

            Team team1 = teamRepository.findById(id).get();
            Team team2 = teamRepository.findById(id).get();

            boolean val = team1 == team2;
            System.out.println("### 동일성 테스트 결과 : " + val);
            assertThat(val).isEqualTo(false);
        }
    }


    @Nested
    class 지연_로딩_테스트_클래스 {

        Team init() {
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

            return team;
        }

        @Test
        @Transactional
        public void 연관관계_객체가_프록시객체라면_성공() {
            Long id = init().getId();

            Team team = teamRepository.findById(id)
                    .orElseThrow(RuntimeException::new);

            boolean isLoad = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(team.getMembers());
            assertThat(isLoad).isEqualTo(false);
        }

        @Test
        @Transactional
        public void 연관관계_객체가_초기화_되었다면_성공() {
            Long id = init().getId();

            Team team = teamRepository.findById(id)
                    .orElseThrow(RuntimeException::new);

            System.out.println(team.getMembers().size());

            boolean isLoad = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(team.getMembers());
            assertThat(isLoad).isEqualTo(true);
        }
    }

    @Nested
    class 쓰기_지연_테스트_클래스 {

        @BeforeEach
        void init2() {
            teamRepository.deleteAll();
        }

        Team init() {
            Team team = new Team("test");
            teamRepository.save(team);

            entityManager.clear();
            entityManager.close();

            return team;
        }

        @Test
        @Transactional
        @Rollback(value = false)
        public void 쓰기지연_insert_test() {
            System.out.println("### INSERT BEGIN");
            Team team = new Team("test");
            Team team2 = new Team("test2");
            teamRepository.save(team);
            teamRepository.save(team2);
            System.out.println("### INSERT END");
        }

        @Test
        @Transactional
        @Rollback(value = false)
        public void 쓰기지연_update_test() {
            Long id = init().getId();

            Team team = teamRepository.findById(id).get();

            System.out.println("### UPDATE BEGIN");
            team.setName("CHANGE");
            System.out.println("### UPDATE END");
        }

        @Test
        @Transactional
        @Rollback(value = false)
        public void 쓰기지연_delete_test() {
            Long id = init().getId();

            Team team = teamRepository.findById(id).get();

            System.out.println("### DELETE BEGIN");
            teamRepository.delete(team);
            System.out.println("### DELETE END");
        }
    }
}
