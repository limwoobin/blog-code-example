package com.example.jpaexample.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TeamDto {
    private Long id;
    private String name;
}
