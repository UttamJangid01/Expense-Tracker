package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Famliy;
import com.model.Member;
import com.service.EmailService;
import com.service.FamliyService;
import com.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FamilyController {

    private FamliyService fservice;
    private MemberService mservice;
    private EmailService eservice;

    @Autowired
    public void FamliyController(FamliyService fservice, MemberService mservice, EmailService eservice) {
        this.fservice = fservice;
        this.mservice = mservice;
        this.eservice = eservice;
    }

    @GetMapping("/setting")
    public String settingsPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        List<Member> memberList = mservice.getAllMemberByFamliyId(member.getFamliyId());
        String famliyName = fservice.getFamliy(member.getFamliyId()).getFamliyName();

        model.addAttribute("famliyName", famliyName);
        model.addAttribute("memberList", memberList);
        return "setting";
    }

    @PostMapping("/addMember")
    public String SendEmailForMember(@RequestParam String email, HttpSession session){
        Member member = (Member) session.getAttribute("member");
        String familyName = fservice.getFamliy(member.getFamliyId()).getFamliyName();
        
        eservice.sendEmail(email, familyName);
        return "redirect:/setting";
    }

    // Empty Data record because user create new famliy.
    @PostMapping("/createFamily")
    public String saveFamliyDetails(Famliy famliy, Member member, HttpSession session) {
        fservice.saveFamliy(famliy);
        Famliy f = fservice.getLastFamliy();

        member.setFamliyId(f.getId());
        mservice.saveMember(member);

        session.setAttribute("member", member); 
        return "redirect:/dashboard";
    }

    @PostMapping("/joinFamily")
    public String checkFamilyIsExists(Famliy famliy, Member member, HttpSession session){
        String familyName = famliy.getFamliyName();

        Famliy f = fservice.familyExist(familyName);

        if(f != null){
            member.setFamliyId(f.getId());
            mservice.saveMember(member);
            session.setAttribute("member", member);
            return "redirect:/dashboard";
        }
        return "joinFamily";
    }
}