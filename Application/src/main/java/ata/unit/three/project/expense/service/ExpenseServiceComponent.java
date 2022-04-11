package ata.unit.three.project.expense.service;

import ata.unit.three.project.App;
import ata.unit.three.project.expense.dynamodb.ExpenseServiceRepository;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {App.class})
public interface ExpenseServiceComponent {
    ExpenseService expenseService();
}
