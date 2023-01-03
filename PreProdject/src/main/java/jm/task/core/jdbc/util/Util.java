package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Connection con = null;
    private static SessionFactory sessionFactory = null;

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    static {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.url", DB_URL);
                properties.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
                properties.setProperty("hibernate.connection.username", DB_USERNAME);
                properties.setProperty("hibernate.connection.password", DB_PASSWORD);
                sessionFactory = new Configuration()
                        .addProperties(properties)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (HibernateException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    static {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
        String user = "roоt";
        String pass = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url , user , pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }


}
