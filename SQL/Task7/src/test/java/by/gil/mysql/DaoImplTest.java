package by.gil.mysql;

import by.gil.DaoFactory;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mssql.MsSqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DaoImplTest {

    private DaoImpl daoImpl;
    private IDatabaseConnection connection;

    @Before
    public void setUp() throws Exception {
        try {
            daoImpl = (DaoImpl) DaoFactory.getDao("hometaskdatabase");
            connection = new MsSqlConnection(MySqlDataSource.getTestConnection(), "hometaskdatabase_test");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getReceiver() {
    }

    @Test
    public void testGetReceiver() {
    }

    @Test
    public void getExpense() {
    }

    @Test
    public void testGetExpense() {
    }

    @Test
    public void addReceiver() throws DatabaseUnitException, SQLException {
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DaoImplTest.class.getResourceAsStream("DaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    }

    @Test
    public void addExpense() {
    }

    @Test
    public void getMaxId() throws DatabaseUnitException, SQLException {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DaoImplTest.class.getResourceAsStream("DaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);


        //When
        int maxId = daoImpl.getMaxId("receivers");

        //Then
        assertEquals(3,maxId);
        DatabaseOperation.DELETE.execute(connection,dataSet);

    }

    @After
    public void tearDown() throws Exception {
        connection.close();
        daoImpl = null;
    }
}