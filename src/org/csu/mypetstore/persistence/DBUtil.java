package org.csu.mypetstore.persistence;

import java.sql.*;

public class DBUtil {
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/jpetstore?useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws Exception{
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(URL , USERNAME , PASSWORD);
    }

    public static void closeConnection(Connection connection)throws Exception{
        if(connection != null){
            connection.close();
        }
    }

    public static void closeStatement(Statement statement)throws Exception{
        if(statement != null){
            statement.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement)throws Exception{
        if(preparedStatement != null){
            preparedStatement.close();
        }
    }

    public static void closeResultSet(ResultSet resultSet)throws Exception{
        if(resultSet != null){
            resultSet.close();
        }
    }

    public static void closePreparedStatementETC(Connection connection , PreparedStatement preparedStatement , ResultSet resultSet)throws Exception{
        closeConnection(connection);
        closeResultSet(resultSet);
        closePreparedStatement(preparedStatement);
    }
    public static void closeStatementETC(Connection connection , Statement statement , ResultSet resultSet)throws Exception{
        closeConnection(connection);
        closeResultSet(resultSet);
        closeStatement(statement);
    }
}
