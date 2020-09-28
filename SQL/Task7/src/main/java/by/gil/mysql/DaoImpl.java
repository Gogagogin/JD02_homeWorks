package by.gil.mysql;

import by.gil.Dao;
import by.gil.expense.ExpenseDto;
import by.gil.receiver.ReceiverDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DaoImpl implements Dao {

    private static Logger log = Logger.getLogger(DaoImpl.class.getName());

    private Connection connection;
    boolean isTestInstance;

    public DaoImpl() throws SQLException{
        this.isTestInstance = false;
    }

    public DaoImpl(boolean isTestInstance) throws SQLException{
        this.isTestInstance = isTestInstance;
    }

    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            if (isTestInstance) {
                this.connection = MySqlDataSource.getTestConnection();
            } else {
                this.connection = MySqlDataSource.getConnection();
            }
        }

    }

    @Override
    public ReceiverDto getReceiver(int id) throws SQLException {

        connect();
        PreparedStatement preparedStatement = connection.
                prepareStatement(" select * from receivers where id =?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<ReceiverDto> receiverDtos = parseResultSet(resultSet);
        preparedStatement.close();
        return  receiverDtos.size() > 0 ? receiverDtos.get(0) : null;

    }

    private List<ReceiverDto> parseResultSet(ResultSet resultSet) throws SQLException {
        connect();
        List<ReceiverDto> receivers = new ArrayList<>();
        while (resultSet.next()){
            ReceiverDto receiver = new ReceiverDto();
            receiver.setId(resultSet.getInt(1));
            receiver.setReceiverName(resultSet.getString(2));
            receivers.add(receiver);
        }
        return  receivers;
    }

    @Override
    public List<ReceiverDto> getReceiver() throws SQLException {
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                " select * from receivers "
        );
        List<ReceiverDto> receiverDtos = parseResultSet(resultSet);
        statement.close();
        return receiverDtos;
    }

    @Override
    public ExpenseDto getExpense(int id) throws SQLException {
        connect();
        PreparedStatement statement = connection.
                prepareStatement(" select * from expenses where id =?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<ExpenseDto> expenseDtos = parseResultSetExpenses(resultSet);
        statement.close();
        return expenseDtos.size() > 0 ? expenseDtos.get(0) : null;
    }

    private List<ExpenseDto> parseResultSetExpenses(ResultSet resultSet) throws SQLException {
        connect();
        List<ExpenseDto> expenses = new ArrayList<>();
        while (resultSet.next()){
            ExpenseDto expense = new ExpenseDto();
            expense.setId(resultSet.getInt(1));
            expense.setPaydate(resultSet.getDate(2));
            expense.setReceiver(resultSet.getInt(3));
            expense.setTotal(resultSet.getFloat(4));
            expenses.add(expense);
        }
        return expenses;
    }

    @Override
    public List<ExpenseDto> getExpense() throws SQLException {
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                " select * from expenses "
        );
        List<ExpenseDto> expenseDtos = parseResultSetExpenses(resultSet);
        statement.close();
        return expenseDtos;
    }

    @Override
    public int addReceiver(ReceiverDto receiver) throws SQLException {
        log.info("Creating new receiver: " + receiver);
        connect();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into receivers " + " values (?,?) "
        );
        preparedStatement.setInt(1, receiver.getId());
        preparedStatement.setString(2, receiver.getReceiverName());

        boolean result = preparedStatement.execute();
        preparedStatement.close();
        if (result) return receiver.getId();
        else  return -1;
    }

    @Override
    public int addExpense(ExpenseDto expense) throws SQLException {
        log.info("Creating new Expense: " + expense);
        connect();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert  into expenses" + " values (?, ?, ?, ?) "
        );
        preparedStatement.setInt(1, expense.getId());
        preparedStatement.setDate(2, expense.getPaydate());
        preparedStatement.setInt(3, expense.getId());
        preparedStatement.setFloat(4, expense.getTotal());

        boolean result = preparedStatement.execute();
        preparedStatement.close();
        if (result) return expense.getId();
        else  return -1;
    }

    @Override
    public int getMaxId(String table) throws SQLException {
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select max(id) from " + table);
        int id = 0;
        if (resultSet.next()) id = resultSet.getInt(1);
        statement.close();
        return id;
    }


}
