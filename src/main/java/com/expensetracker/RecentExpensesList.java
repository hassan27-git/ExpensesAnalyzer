package com.expensetracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/recentExpenses")
public class RecentExpensesList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get data from DB
        List<Expenses> expenses = new ArrayList<>();
        
        String url = "jdbc:mysql://localhost:3306/expense_tracker?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";          
        String password = "root123";
        String sql = "SELECT * FROM expenses ORDER BY id DESC LIMIT 10";

        try (
        	
        	Connection conn=DriverManager.getConnection(url,user,password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        		)
        {
        	while (rs.next()) {
                Expenses e = new Expenses(
                    rs.getString("item_name"),
                    rs.getDouble("amount"),
                    rs.getString("category"),
                    rs.getDate("date_added").toString()
                );
                expenses.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. Attach data to request
        request.setAttribute("expenses", expenses);

        // 3. Show JSP
        RequestDispatcher rd =
                request.getRequestDispatcher("recent.jsp");
        rd.forward(request, response);
    }
}
