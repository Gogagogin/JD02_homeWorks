package by.gil;

import by.gil.expense.ExpenseDto;
import by.gil.receiver.ReceiverDto;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {



    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {


        try{

        Dao methodDao = DaoFactory.getDao("hometaskdatabase");
        //add
        ReceiverDto receiver = new ReceiverDto();
        int id = methodDao.getMaxId("receivers")+1;
        receiver.setId(id);
        receiver.setReceiverName("ReceiverName");
        log.info("Receiver is created !" + receiver);
        methodDao.addReceiver(receiver);

        //readReceivers
        ReceiverDto receiverDtoRead = methodDao.getReceiver(id);
        log.info("Read receiver with id =" + id + " " + receiverDtoRead);

        //readAllReceivers
            List<ReceiverDto> receiverDtoList = methodDao.getReceiver();
            log.info(receiverDtoList.toString());

            //ExpenseDto
            // create

            ExpenseDto expenses = new ExpenseDto();
            int maxIdExpense = methodDao.getMaxId("Expenses")+1;
            expenses.setId(maxIdExpense);
            expenses.setPaydate(Date.valueOf("2020-08-08"));
            expenses.setReceiver(3);
            expenses.setTotal(20.75F);
            log.info("Expense added !");
            methodDao.addExpense(expenses);

            //read
            ExpenseDto expenseDtoRead = methodDao.getExpense(id);
            log.info("Read after create: " + expenseDtoRead);

            //ReadAll
            List<ExpenseDto> expenseDtoList = methodDao.getExpense();
            log.info(expenseDtoList.toString());


    } catch (
    SQLException e) {
        log.log(Level.SEVERE, e.getMessage(), e);
        System.exit(-1);
    }  catch (Exception e) {
        log.log(Level.SEVERE, e.getMessage(), e);
        System.exit(-1);
    } finally {
        log.info("Finished successfully");
        System.exit(0);
    }
    }

}
