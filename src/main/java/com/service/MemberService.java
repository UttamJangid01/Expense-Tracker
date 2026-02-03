package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Member;
import com.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repo;

    public List<Member> getAllMemberByFamliyId(Long famliyId){
        return repo.findByFamliyId(famliyId);
    }

    public Member getMemberByEmail(String email) {
        return repo.findByEmail(email);
    }

    public void saveMember(Member member) {
        repo.save(member);
    }
}
