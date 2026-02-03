package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.model.Member;
import com.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private MemberService service;

    @GetMapping("/home")
    public String HomePage() {
        return "home";
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String CheckLoginUser(@RequestParam String email, @RequestParam String password, HttpSession session,
            RedirectAttributes redirect) {
        Member member = service.getMemberByEmail(email);
        if (member == null) {
            redirect.addFlashAttribute("errormessage", "Email not found !!");
            return "redirect:/login";
        }
        if (member.getPassword().equals(password)) {
            session.setAttribute("member", member);
            return "redirect:/dashboard";
        }
        redirect.addFlashAttribute("errormessage", "Enter valide email and password !!");
        return "redirect:/login";
    }

    @GetMapping("/familyGroup")
    public String familyGroup(@RequestParam String role, Model model) {
        model.addAttribute("mode", role);
        return "familygroup";
    }

    @GetMapping("/response")
    public String response(){
        return "response";
    }
}
