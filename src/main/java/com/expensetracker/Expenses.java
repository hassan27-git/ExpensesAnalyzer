package com.expensetracker;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Scanner;

public class Expenses {
    private int id;
    private String itemName;
    private double amount;
    private String category;
    private String date;


    public Expenses(String itemName, double amount, String category, String date) {
        this.itemName = itemName;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

//    method to insert data into database
    public void SaveToDatabase() {
        String url = "jdbc:mysql://localhost:3306/expense_tracker?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = "root123";
        String sql = "INSERT INTO expenses (item_name, amount, category, date_added) VALUES (?, ?, ?, ?)";

        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("Connecting to database...");

            stmt.setString(1, this.itemName);
            stmt.setDouble(2, this.amount);
            stmt.setString(3, this.category);
            stmt.setDate(4, java.sql.Date.valueOf(this.date));

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Expense added successfully!");
            } else {
                System.out.println("Failed to insert expense.");
            }

        } catch (Exception e) {
            System.out.println("Database insert error:");
            e.printStackTrace();
        }
    }

 

 
}
