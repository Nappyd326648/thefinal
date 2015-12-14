package com.eugeneStewart;

/**
 * Created by Eugene on 12/10/2015.
 */
import java.sql.*;
import java.util.ArrayList;

public class database {


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";        //Configure the driver
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/saves";     //Connect database
    static final String USER = "root";   //TODO replace with your username
    static final String PASSWORD = "112233";   //TODO replace with your password


    protected static void startdb(String artist,Object selectedValue) throws Exception {      //TODO handle exceptions properly
        Class.forName(JDBC_DRIVER);   //Instantiate the driver class
        Connection  connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);  //Create a connection to DB
        Statement statement = connection.createStatement();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS tweets (Artist VARCHAR(100),poster VARCHAR(100),Tweet VARCHAR(250) )";  //Run some SQL – create table
        statement.executeUpdate(createTableSQL);

//stores the whole tweet, splits the user name from the body
        String tweet= selectedValue.toString();
        String[] parts=tweet.split("-");
        String poster=parts[0];
        String Ntweet=parts[1];
        //stores save tweets to the data base
        String prepStatInsert = ("INSERT INTO tweets VALUES ( ? , ?, ? )");
        PreparedStatement psInsert = connection.prepareStatement(prepStatInsert);
        psInsert.setString(1, artist);    //Start counting from 1…
        psInsert.setString(2, poster);   //now this is should be an int
        psInsert.setString(3, Ntweet.trim());
        psInsert.executeUpdate();


    }
protected static void onclose() throws SQLException {
    Connection  connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);  //Create a connection to DB
    Statement statement = connection.createStatement();
    ResultSet rs = statement.executeQuery("SELECT * FROM tweets");

    System.out.println("Results from all save...");int numberOfResults = 0;
    while (rs.next()) {    numberOfResults ++;
        String art = rs.getString("Artist");
        String po = rs.getString("Poster");
        String tw=rs.getString("tweet");
        System.out.println(art + " " + po + " " + tw);}

    connection.close();
    statement.close();

}

}