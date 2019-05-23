package utils;

import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
        private static String userName = "root";
        private static String password = "fgithjy23";
        private static String url = "jdbc:mysql://localhost:3306/i_shop?autoReconnect=true&useSSL=false";

    public static Connection openConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        DOMConfigurator.configure("loggerConfig.xml");
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection(url, userName, password);
    }
}
