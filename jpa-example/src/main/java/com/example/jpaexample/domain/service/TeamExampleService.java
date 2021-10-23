package com.example.jpaexample.domain.service;

import com.example.jpaexample.domain.entity.Team;
import com.example.jpaexample.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamExampleService {
    private final TeamRepository teamRepository;
    private final EntityManager entityManager;

    @Transactional
    public void getTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        team.setName("dirty-checking-test");

        Team team2 = teamRepository.findByName("test");
//        entityManager.flush();
    }
}
