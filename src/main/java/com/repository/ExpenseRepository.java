package com.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

        List<Expense> findByDateAndFamliyId(LocalDate date, Long famliyId);

        List<Expense> findByDate(LocalDate date);

        @Query("""
                        SELECT e FROM Expense e
                        WHERE e.famliyId = :famliyId
                        AND YEAR(e.date) = :year
                        """)
        List<Expense> findByFamliyIdAndYear(
                        @Param("famliyId") Long famliyId,
                        @Param("year") int year);

        @Query("""
                        SELECT e FROM Expense e
                        WHERE e.famliyId = :famliyId
                        AND MONTH(e.date) = :month
                        AND YEAR(e.date) = :year
                        """)
        List<Expense> findByFamliyIdAndMonthAndYear(
                        @Param("famliyId") Long famliyId,
                        @Param("month") int month,
                        @Param("year") int year);

        @Query("SELECT DISTINCT e FROM Expense e WHERE e.famliyId = :famliyId")
        List<Expense> findDistinctUsersByFamliyId(@Param("famliyId") Long famliyId);

}
