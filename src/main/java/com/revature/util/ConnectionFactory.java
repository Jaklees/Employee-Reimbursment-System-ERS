package com.revature.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

/** * <p>This ConnectionFactory class follows the Singleton Design Pattern and facilitates obtaining a connection to a Database for the ERS application.</p>
* <p>Following the Singleton Design Pattern, the provided Constructor is private, and you obtain an instance via the {@link ConnectionFactory#getInstance()} method.</p> */
public class ConnectionFactory {

//private static ConnectionFactory instance;
private static Connection connection;

private ConnectionFactory() {//super();
}
/** * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
 * <p>It is invoked via:</p>
 * {@code ConnectionFactory.getInstance()} */
 public static Connection getInstance() {
    if (connection == null) {
        connection = getConnection();
    }
    return connection;
}
/** * <p>The {@link ConnectionFactory#getConnection()} method is responsible for leveraging a specific Database Driver to obtain an instance of the {@link java.sql.Connection} interface.</p>
 * <p>Typically, this is accomplished via the use of the {@link java.sql.DriverManager} class.</p> */
public static Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("application.properties");
            props.load(input);

            String connectionString = "jdbc:postgresql://" +
                    props.getProperty("hostname") + ":" +
                    props.getProperty("port") + "/" +
                    props.getProperty("dbname") + "?schema=" +
                    props.getProperty("schema");

            String username = props.getProperty("username");
            String password = props.getProperty("pass");

            connection = DriverManager.getConnection(connectionString, username, password);

            String sql = "set search_path to \"$user\", publicjk";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();

            System.out.println("Connection String: " + connectionString);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close() {
    try {
        connection.close();
    } catch (SQLException e){
        e.printStackTrace();
    }
    connection = null;
    }
}
