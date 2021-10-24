package com.example.jpaexample.domain.repository;

import com.example.jpaexample.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member , Long> {
}
