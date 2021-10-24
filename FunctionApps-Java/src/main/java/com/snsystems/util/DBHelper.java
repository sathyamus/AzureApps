package com.snsystems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.azure.core.util.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snsystems.models.EmailAlert;

public class DBHelper {

    public static void main111(String[] args) throws SQLException {


    	// JdbcTemplate
        Connection connection = DBHelper.getConnection("email-alert-db-conn-url");

        System.out.println(" EmailAlerts ... Before processing ... ");
        
        String selectSqlAll = "SELECT * from EMAIL_ALERT";
        List<EmailAlert> emailAlerts = DBHelper.executeQuery(connection, selectSqlAll, true);

        System.out.println("Processing EmailAlerts ...");
        String selectSql = "SELECT * from EMAIL_ALERT where IS_EMAIL_SENT = 0";
        emailAlerts = DBHelper.executeQuery(connection, selectSql, true);

        String updateSql = "UPDATE EMAIL_ALERT SET IS_EMAIL_SENT = 1 where IS_EMAIL_SENT = 0";
        DBHelper.executeQuery(connection, updateSql, false);

        emailAlerts = DBHelper.executeQuery(connection, selectSqlAll, true);
        //fetchResults(selectResultSet);
 
        System.out.println(" EmailAlerts ... After processing ... ");
    }

    static String getConnectionURL(String connUrlEnv) {
		return Configuration.getGlobalConfiguration().get(connUrlEnv);
	}
	
    public static Connection getConnection(String connEnvProp) {

        System.out.println("getConnection...");
        Connection connection = null;
       String connectionUrl =
       "jdbc:sqlserver://snsystems-sql.database.windows.net:1433;"
       + "database=sathya-sql-db;"
       + "user={your_user_name};"
       + "password={your_password};"
       + "encrypt=true;"
       + "trustServerCertificate=false;"
       + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

        // String connectionUrl = getConnectionURL(connEnvProp);
        
        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return connection;
    }

    public static List<EmailAlert> executeQuery(Connection connection, String sqlQuery, boolean isSelect) {

        System.out.println("executeQuery...");
        ResultSet resultSet = null;
        List<EmailAlert> emailAlerts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            if (isSelect) {
                // Create and execute a SELECT SQL statement.
                resultSet = statement.executeQuery(sqlQuery);
                // fetchResults(resultSet);
                fetchEmailAlerts(resultSet);
            } else {
                int updatedRows = statement.executeUpdate(sqlQuery);
                System.out.println("Rows Updated : " + updatedRows);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emailAlerts;
    }
    
    public static List<String> fetchResults(ResultSet resultSet) {
    	List<String> results = new ArrayList<String>();
        // Print results from select statement
        try {
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            StringBuilder builder = null;
            while (resultSet.next()) {
                System.out.println("\n\n");
                builder = new StringBuilder();
                for (int i = 1; i<=rsMetaData.getColumnCount(); i++) {
                    builder.append(rsMetaData.getColumnName(i) + " : " + resultSet.getString(i));
                    builder.append("\n");
                    //log.info(result);
                }
                results.add(builder.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
    
    public static List<EmailAlert> fetchEmailAlerts(ResultSet resultSet) {
        List<EmailAlert> emailAlerts = new ArrayList<>();
        System.out.println("fetchEmailAlerts...");
        // Print results from select statement
        try {
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            EmailAlert emailAlert = null;
            while (resultSet.next()) {
                System.out.println("\n\n");
                emailAlert = new EmailAlert();

                //for (int i = 1; i<=rsMetaData.getColumnCount(); i++) {
                emailAlert.setId(resultSet.getInt(1));
                emailAlert.setToAddress(resultSet.getString(2));
                emailAlert.setMailSubject(resultSet.getString(3));
                emailAlert.setMailBody(resultSet.getString(4));
                emailAlert.setEmailSent(resultSet.getBoolean(5));
                //}
                System.out.println(new ObjectMapper().writeValueAsString(emailAlert));
                emailAlerts.add(emailAlert);
            }
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return emailAlerts;
    }
}
