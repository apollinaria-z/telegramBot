package by.zolotaya.telegrambot.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/citydb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;

    public static Connection getInstance(){
        if(connection == null)
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        return connection;
    }
}
