package com.test.projectOne.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.projectOne.Model.Expense;
import com.test.projectOne.Repository.expenseRepository;

@Service
public class expenseService {
    private expenseRepository expenseRepository;

    public expenseService(com.test.projectOne.Repository.expenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }

    public void updateExpense(Expense expense){
        Expense savedExpense = expenseRepository.findById(expense.getId()).
        orElseThrow(() -> new RuntimeException(String.format("Expense id not found %s", expense.getId())));

        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseName(expense.getExpenseName());

        expenseRepository.save(savedExpense);
        
    }

    public Expense getExpenseByName(String name){
        return expenseRepository.findByName(name).
        orElseThrow(() -> new RuntimeException(String.format("Expense name not found %s", name)));
    }

    public List<Expense> getAllExpense(){
         return expenseRepository.findAll();
    }

    public void deleteExpense(String id){
        expenseRepository.deleteById(id);
    }
}
