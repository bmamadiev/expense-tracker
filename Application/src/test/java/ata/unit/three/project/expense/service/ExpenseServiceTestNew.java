package ata.unit.three.project.expense.service;

import ata.unit.three.project.expense.dynamodb.ExpenseItem;
import ata.unit.three.project.expense.dynamodb.ExpenseItemList;
import ata.unit.three.project.expense.dynamodb.ExpenseServiceRepository;
import ata.unit.three.project.expense.lambda.models.Expense;
import ata.unit.three.project.expense.service.model.ExpenseItemConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ExpenseServiceTestNew {
    @Mock
    ExpenseServiceRepository expenseServiceRepository;
    @Mock
    ExpenseItemConverter expenseItemConverter;
    @InjectMocks
    ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetExpenseById() {
        when(expenseServiceRepository.getExpenseById(anyString())).thenReturn(new ExpenseItem());

        ExpenseItem result = expenseService.getExpenseById("expenseId");
        Assertions.assertEquals(new ExpenseItem(), result);
    }

    @Test
    void testGetExpensesByEmail() {
        when(expenseServiceRepository.getExpensesByEmail(anyString())).thenReturn(Arrays.<ExpenseItem>asList(new ExpenseItem()));

        List<ExpenseItem> result = expenseService.getExpensesByEmail("email");
        Assertions.assertEquals(Arrays.<ExpenseItem>asList(new ExpenseItem()), result);
    }

    @Test
    void testCreateExpense() {
        when(expenseItemConverter.convert(any())).thenReturn(new ExpenseItem());

        String result = expenseService.createExpense(new Expense("email", "title", Double.valueOf(0)));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testUpdateExpense() {
        when(expenseServiceRepository.getExpenseById(anyString())).thenReturn(new ExpenseItem());

        expenseService.updateExpense("expenseId", new Expense(null, "title", Double.valueOf(0)));
    }

    @Test
    void testDeleteExpense() {
        expenseService.deleteExpense("expenseId");
    }

    @Test
    void testCreateExpenseList() {
        String result = expenseService.createExpenseList("email", "title");
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testAddExpenseItemToList() {
        expenseService.addExpenseItemToList("id", "expenseId");
    }

    @Test
    void testRemoveExpenseItemFromList() {
        expenseService.removeExpenseItemFromList("id", "expenseId");
    }

    @Test
    void testGetExpenseListByEmail() {
        when(expenseServiceRepository.getExpenseListsByEmail(anyString())).thenReturn(Arrays.<ExpenseItemList>asList(new ExpenseItemList()));

        List<ExpenseItemList> result = expenseService.getExpenseListByEmail("email");
        Assertions.assertEquals(Arrays.<ExpenseItemList>asList(new ExpenseItemList()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme