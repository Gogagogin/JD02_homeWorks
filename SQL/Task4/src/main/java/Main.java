import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Properties properties = new Properties();
                properties.put("user", "root");
                properties.put("password", "Root");
                properties.put("useSSL", "false");
                properties.put("serverTimezone", "UTC");

                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/hometaskdatabase",
                        properties
                );

            ResultSet resultSet;
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select id FROM expenses ORDER BY id DESC LIMIT 1;");

            Integer maxIndex = 0;
            while (resultSet.next()) {
                maxIndex = resultSet.getInt("id") + 1;
            }


          Scanner in = new Scanner(System.in);
            System.out.println("Введите сумму :");
            Integer summa = in.nextInt();
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Получатель :");
            Integer sentence = in.nextInt();
            System.out.println(maxIndex);

               String query = "INSERT INTO expenses " +
                    "VALUES ('" + maxIndex + "','"+ date + "','"+ sentence +  "', '"+ summa + "' );";
               statement.executeUpdate(query);
                resultSet = statement.executeQuery("SELECT expenses.id, paydate, receivername, total  FROM expenses, receivers where (total > 10) and (receiver=receivers.id);");
                System.out.println(resultSet);
               while (resultSet.next()) {
                    System.out.print(resultSet.getInt("id") + " ");
                    System.out.print(resultSet.getDate(2) + " ");
                    System.out.print(resultSet.getString(3) + " ");
                    System.out.println(resultSet.getFloat(4) + " ");
                }



            } catch (ClassNotFoundException|SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Finished");
            }

    }
}