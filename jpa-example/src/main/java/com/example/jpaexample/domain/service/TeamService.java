package com.example.jpaexample.domain.service;

import com.example.jpaexample.domain.dto.TeamDto;
import com.example.jpaexample.domain.entity.Team;
import com.example.jpaexample.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final EntityManager entityManager;

    @Transactional
    public TeamDto save(TeamDto teamDto) {
        Team team = new Team(teamDto.getName());    // 비영속
        team = teamRepository.save(team);   // 영속

        TeamDto teamResponse = TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .build();

        return teamResponse;
    }


    @Transactional
    public Team findTeam(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public Team cashTest(Long id) {
        Team team1 = teamRepository.findById(id)
                .orElseThrow(RuntimeException::new);    // (2)

        Team team2 = teamRepository.findById(id)
                .orElseThrow(RuntimeException::new);    // (2)

        return team1;
    }

    @Transactional
    public Team detachTest(Long id) {
        Team team = teamRepository.findById(id).get();
        entityManager.detach(team); // 준영속

        return team;
    }

    @Transactional
    public void deleteTest(Long id) {
        Team team = teamRepository.findById(id).get();
        teamRepository.delete(team);
    }
}
