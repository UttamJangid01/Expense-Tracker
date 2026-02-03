package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

    List<Member> findByFamliyId(Long famliyId);

    Member findByEmail(String email);

}
