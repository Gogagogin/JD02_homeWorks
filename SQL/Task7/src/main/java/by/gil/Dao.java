package by.gil;

import by.gil.expense.ExpenseDto;
import by.gil.receiver.ReceiverDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Dao {

    ReceiverDto getReceiver(int id) throws SQLException;
    List<ReceiverDto> getReceiver() throws SQLException;
    ExpenseDto getExpense (int id) throws SQLException;
    List<ExpenseDto> getExpense () throws SQLException;
    int addReceiver(ReceiverDto receiver) throws SQLException;
    int addExpense(ExpenseDto expense) throws SQLException;
    int getMaxId(String table) throws SQLException;

}
