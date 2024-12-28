package com.example.ArtGallery.db;
import java.sql.*;
import java.util.Properties;

public class DB {
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/";
    private final static String DBUSER = "root";
    private final static String DBPASS = "";
    private Connection con;
    private static Statement st;
    private String query;

    public DB(){
        if(this.checkDriver("com.mysql.cj.jdbc.Driver")){
            System.out.println("Drivers OK");
        }else{
            System.out.println("No Driver");
        }

        con = this.getConnection("jdbc:mysql://", "localhost", 3306, "root", "");
        st = this.createStatement(con);
        String dbName = "ArtGallery";

        if(this.executeUpdate(st,"USE " + dbName + ";") == 0) {
            System.out.println("Database used: " + dbName);
        }else{
            System.out.println("Database doesn't exist! Create database: ");
            if(this.executeUpdate(st,"CREATE DATABASE " + dbName + ";") == 1) {
                System.out.println("Database created");
            }else{
                System.out.println("Cannot create database");
            }
        }
    }

    // ------------------- METHODS -------------------
    public static String getDataString(String query){
        ResultSet rs = executeQuery(st, query);
        try {
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static Integer getDataInt(String query){
        ResultSet rs = executeQuery(st, query);
        try {
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static Connection connectToDatabase(String kindOfDatabase, String adress, String dataBaseName, String userName, String password){
        System.out.println("Connecting with database:");
        String baza = kindOfDatabase + adress + "/" + dataBaseName;
        java.sql.Connection connection = null;
        try{
            connection = DriverManager.getConnection(baza, userName, password);
        } catch (SQLException e) {
            System.out.println("Cannot connect with database");
            System.exit(1);
        }
        return connection;
    }
    public static Connection getConnection(String kindOfDatabase, String adres, int port, String userName, String password){
        Connection conn = null;
        Properties connnectionProps = new Properties();
        connnectionProps.put("user", userName);
        connnectionProps.put("password", password);
        try{
            conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/", connnectionProps);
        }catch (SQLException e){
            System.out.println("Cannot connect to database! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(2);
        }
        System.out.println("Database connection: ... OK");
        return conn;
    }
    public static Statement createStatement(Connection conn){
        try{
            return conn.createStatement();
        }catch (SQLException e){
            System.out.println("ERROR createStatement! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(3);
        }
        return null;
    }
    public static void closeConnection(Connection conn, Statement s){
        System.out.println("Closing database connection:");
        try{
            s.close();
            conn.close();
        }catch (SQLException e){
            System.out.println("Cannot close database connection! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(4);
        }
        System.out.println("Closed database connection: ... OK");
    }
    public static ResultSet executeQuery(Statement s, String sql){
        try{
            return s.executeQuery(sql);
        }catch (SQLException e){
            System.out.println("Cannot execute query! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return null;
    }
    public Statement getSt() {
        return st;
    }
    public Connection getCon() {
        return con;
    }
    public static int executeUpdate(Statement s, String sql){
        try{
            return s.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println("Query executed! " + e.getMessage() + ": " + e.getErrorCode());

        }
        return -1;
    }
    private static void printDataFromQuery(ResultSet r){
        ResultSetMetaData rsmd;
        try{
            rsmd = r.getMetaData();
            int numcols = rsmd.getColumnCount();
            for(int i = 1 ;i <= numcols; i++){
                System.out.print("\t" + rsmd.getColumnLabel(i) + "\t|");
            }
            System.out.print("\n___________________________________________________________\n");
            while(r.next()){
                for(int i = 1; i<= numcols; i++){
                    Object obj = r.getObject(i);
                    if(obj != null)
                        System.out.print("\t" + obj.toString() + "\t|");
                    else
                        System.out.print("\t");
                }
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println("Cannot read from database! " + e.getMessage() + ": " + e.getErrorCode());

        }
    }
    public static void sqlGetDataByName(ResultSet r){
        try{
            ResultSetMetaData rsmd = r.getMetaData();
            int numcols = rsmd.getColumnCount();
            for(int i = 1; i <= numcols; i++){
                System.out.print(rsmd.getColumnLabel(i) + "\t|\t");
            }
            System.out.print("\n___________________________________________________________\n");
            while(r.next()){
                int size = r.getMetaData().getColumnCount();
                for(int i = 1; i <= size; i++){
                    switch (r.getMetaData().getColumnTypeName(i)){
                        case "INT":
                            System.out.print(r.getInt(r.getMetaData().getColumnName(i)) + "\t|\t");
                            break;
                        case "DATE":
                            System.out.print(r.getDate(r.getMetaData().getColumnName(i)) + "\t|\t");
                            break;
                        case "VARCHAR":
                            System.out.print(r.getString(r.getMetaData().getColumnName(i)) + "\t|\t");
                            break;
                        default:
                            System.out.print(r.getObject(r.getMetaData().getColumnName(i)));
                    }
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Cannot read from database! " + e.getMessage() + ": " + e.getErrorCode());
        }
    }
    public static boolean checkDriver(String driver){
        System.out.print("Sprawdzanie sterownika:");
        try{
            Class.forName(driver);
            System.out.println("OK");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Błąd");
            return false;
        }
    }


}
