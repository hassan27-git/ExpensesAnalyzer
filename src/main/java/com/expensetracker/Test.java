package com.expensetracker;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/expense_tracker?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = "root123";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to MySQL successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

