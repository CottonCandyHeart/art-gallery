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
    public static Float getDataFloat(String query){
        ResultSet rs = executeQuery(st, query);
        try {
            if(rs.next()){
                return rs.getFloat(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static Double getDataDouble(String query){
        ResultSet rs = executeQuery(st, query);
        try {
            if(rs.next()){
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static Date getDataDate(String query){
        ResultSet rs = executeQuery(st, query);
        try {
            if(rs.next()){
                return rs.getDate(1);
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
    public static Connection getConnection(String kindOfDatabase, String adress, int port, String username, String password){
        Connection conn = null;
        Properties connnectionProps = new Properties();
        connnectionProps.put("user", username);
        connnectionProps.put("password", password);
        try{
            conn = DriverManager.getConnection(kindOfDatabase + adress + ":" + port + "/", connnectionProps);
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
    public void callProcedure(String procedureName, Object... params) {
        StringBuilder procedureCall = new StringBuilder("{CALL " + procedureName + "(");
        for (int i = 0; i < params.length; i++) {
            procedureCall.append(i > 0 ? ", ?" : "?");
        }
        procedureCall.append(")}");

        try (CallableStatement callableStatement = con.prepareCall(procedureCall.toString())) {
            for (int i = 0; i < params.length; i++) {
                callableStatement.setObject(i + 1, params[i]); // Parametry numerowane od 1
            }

            callableStatement.execute();
            System.out.println("Procedure " + procedureName + " executed successfully.");
        } catch (SQLException e) {
            System.err.println("Error while calling procedure " + procedureName + ": " + e.getMessage());
            e.printStackTrace();
        }
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
    public static String sqlGetDataByName(ResultSet r){
        String result = "";
        try{
            ResultSetMetaData rsmd = r.getMetaData();
            int numcols = rsmd.getColumnCount();
            for(int i = 1; i <= numcols; i++){
                System.out.print(rsmd.getColumnLabel(i) + "\t|\t");
                result += rsmd.getColumnLabel(i) + "\t|\t";
            }
            System.out.print("\n___________________________________________________________\n");
            result += "\n___________________________________________________________\n";

            while(r.next()){
                int size = r.getMetaData().getColumnCount();
                for(int i = 1; i <= size; i++){
                    switch (r.getMetaData().getColumnTypeName(i)){
                        case "INT":
                            System.out.print(r.getInt(r.getMetaData().getColumnName(i)) + "\t|\t");
                            result += r.getInt(r.getMetaData().getColumnName(i)) + "\t|\t";
                            break;
                        case "FLOAT":
                            System.out.print(r.getFloat(r.getMetaData().getColumnName(i)) + "\t|\t");
                            result += r.getFloat(r.getMetaData().getColumnName(i)) + "\t|\t";
                            break;
                        case "DOUBLE":
                            System.out.print(r.getDouble(r.getMetaData().getColumnName(i)) + "\t|\t");
                            result += r.getDouble(r.getMetaData().getColumnName(i)) + "\t|\t";
                            break;
                        case "DATE":
                            System.out.print(r.getDate(r.getMetaData().getColumnName(i)) + "\t|\t");
                            result += r.getDate(r.getMetaData().getColumnName(i)) + "\t|\t";
                            break;
                        case "VARCHAR":
                        case "TEXT":
                            System.out.print(r.getString(r.getMetaData().getColumnName(i)) + "\t|\t");
                            result += r.getString(r.getMetaData().getColumnName(i)) + "\t|\t";
                            break;
                        default:
                            System.out.print(r.getObject(r.getMetaData().getColumnName(i)));
                            result += r.getObject(r.getMetaData().getColumnName(i));
                    }
                }
                System.out.println();
                result += "\n";
            }
        } catch (SQLException e) {
            System.out.println("Cannot read from database! " + e.getMessage() + ": " + e.getErrorCode());
        }

        return result;
    }
    public static boolean checkDriver(String driver){
        System.out.print("Checking the driver:");
        try{
            Class.forName(driver);
            System.out.println("OK");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Error");
            return false;
        }
    }


}
