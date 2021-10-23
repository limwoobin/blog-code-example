package com.example.jpaexample.team;

import com.example.jpaexample.domain.entity.Team;
import com.example.jpaexample.domain.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
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
    private EntityManager entityManager;

    @BeforeEach
    public void init() {
        Team team = new Team("test");
        teamRepository.save(team);
    }

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
}
