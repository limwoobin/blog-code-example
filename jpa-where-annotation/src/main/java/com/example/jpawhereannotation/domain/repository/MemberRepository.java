package com.example.jpawhereannotation.domain.repository;

import com.example.jpawhereannotation.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
