package dataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Driver {
    public static Connection connection;

    public static String url = "jdbc:postgresql://localhost:5432/online_shop?currentSchema=public";
    public static String user = "postgres";
    public static String password = "your_password";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    url,
                    user,
                    password
            );

            System.out.println("Successfully connected to DataBase");

        } catch (Exception e) {
            System.out.println("Failed connected");
        }
    }
}
