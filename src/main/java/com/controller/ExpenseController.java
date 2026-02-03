package com.controller;

import com.service.ExpenseService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.model.Expense;
import com.model.Member;

@Controller
@SessionAttributes("date")
public class ExpenseController {

  @Autowired
  private ExpenseService service;

  @GetMapping("/dashboard")
  public String ExpenseDashBoardPage(HttpSession session, Model model) {

    model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    if (!model.containsAttribute("expense")) {
      Member member = (Member) session.getAttribute("member");
      List<Expense> expenseList = service.getTableDataByDayAndFamliyId(member.getFamliyId());
      service.getCalculate(expenseList);

      model.addAttribute("expense", expenseList);
      model.addAttribute("type", "Day");
    }

    modelMethod(model);

    return "dashboard";
  }

  @PostMapping("/dashboard")
  public String addExpenseData(@ModelAttribute Expense expense, HttpSession session, RedirectAttributes redirect) {

    Member member = (Member) session.getAttribute("member");

    expense.setDate(LocalDate.now());
    expense.setUser(member.getUser());
    expense.setMemberId(member.getId());
    expense.setFamliyId(member.getFamliyId());
    service.saveExpense(expense);

    List<Expense> expenseList = service.getTableDataByDay(member.getFamliyId());
    service.getCalculate(expenseList);

    redirect.addFlashAttribute("expense", expenseList);
    redirect.addFlashAttribute("type", "Day");

    return "redirect:/dashboard";
  }

  @PostMapping("/filter")
  public String filterTable(@RequestParam String type, HttpSession session, RedirectAttributes redirect) {

    type = type.trim().toLowerCase();

    Member member = (Member) session.getAttribute("member");

    List<Expense> list;

    switch (type) {
      case "day":
        list = service.getTableDataByDay(member.getFamliyId());
        redirect.addFlashAttribute("type", "Day");
        break;

      case "month":
        list = service.getTableDataByMonth(member.getFamliyId());
        redirect.addFlashAttribute("type", "Month");
        break;

      case "year":
        list = service.getTableDataByYear(member.getFamliyId());
        redirect.addFlashAttribute("type", "Year");
        break;

      default:
        list = List.of();
    }

    service.getCalculate(list);

    redirect.addFlashAttribute("expense", list);

    return "redirect:/dashboard";
  }

  @PostMapping("/SpecificDate")
  public String SpecificDate(@RequestParam LocalDate date, HttpSession session, RedirectAttributes redirect) {

    Member member = (Member) session.getAttribute("member");

    List<Expense> list = service.getTableDataByFamliyIdAndDate(member.getFamliyId(), date);

    service.getCalculate(list);
    redirect.addFlashAttribute("expense", list);
    redirect.addFlashAttribute("type", "find");

    return "redirect:/dashboard";
  }

  private void modelMethod(Model model) {
    model.addAttribute("total", service.getTotal());
    model.addAttribute("paid", service.getPaid());
    model.addAttribute("due", service.getDue());
  }
}
