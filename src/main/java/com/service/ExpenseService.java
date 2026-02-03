package com.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Expense;
import com.repository.ExpenseRepository;

@Service
public class ExpenseService {

  @Autowired
  private ExpenseRepository repo;

  private int total;
  private int paid;
  private int due;

  public ExpenseService() {
    total = paid = due = 0;
  }

  public void saveExpense(Expense expense) {
    repo.save(expense);
  }

  // ye dono same hi h bass me ek me localdate ko method me provide kar rha hun
  // (peramiter) or ek me nhi
  public List<Expense> getTableDataByFamliyIdAndDate(Long famliyId, LocalDate date) {
    return repo.findByDateAndFamliyId(date, famliyId);
  }

  public List<Expense> getTableDataByDayAndFamliyId(Long famliyId) {
    List<Expense> list = repo.findByDateAndFamliyId(LocalDate.now(), famliyId);
    getCalculate(list);
    return list;
  }

  public List<Expense> getTableDataByDay(Long famliyId) {
    List<Expense> list = repo.findByDateAndFamliyId(LocalDate.now(), famliyId);
    getCalculate(list);
    return list;
  }

  public List<Expense> getTableDataByMonth(Long famliyId) {
    List<Expense> list = repo.findByFamliyIdAndMonthAndYear(famliyId, LocalDate.now().getMonthValue(),
        LocalDate.now().getYear());
    getCalculate(list);
    return list;
  }

  public List<Expense> getTableDataByYear(Long famliyId) {
    List<Expense> list = repo.findByFamliyIdAndYear(famliyId, LocalDate.now().getYear());
    getCalculate(list);
    return list;
  }

  public void getCalculate(List<Expense> list) {
    total = paid = due = 0;
    for (int i = 0; i < list.size(); i++) {
      total += list.get(i).getTotal();
      paid += list.get(i).getPaid();
      due += list.get(i).getDue();
    }
  }

  public List<Expense> getAllByFamliyId(Long famliyId) {
    return repo.findDistinctUsersByFamliyId(famliyId);
  }

  public int getTotal() {
    return total;
  }

  public int getPaid() {
    return paid;
  }

  public int getDue() {
    return due;
  }
}
