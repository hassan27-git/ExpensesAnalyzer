package com.expensetracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
        String password = "yourPassword";
        String sql = "SELECT * FROM expenses ORDER BY id DESC LIMIT 10";

        double totalAmount=0;
        double monthTotal=0;
        double food = 0, travel = 0, bills = 0, shopping = 0;
        
        LocalDate now= LocalDate.now();
        
        
//        accessing database
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
        
        
//        Business log and calculations here
        for(Expenses e: expenses) {
        	
        	totalAmount+=e.getAmount();
        	
        	LocalDate expenseDate=LocalDate.parse(e.getDate());
        	
        	if (expenseDate.getMonthValue() == now.getMonthValue()
                    && expenseDate.getYear() == now.getYear()) {
                monthTotal += e.getAmount();
            }
        	
        	
        	
        	switch(e.getCategory()) {
        	case "Food": food+=e.getAmount(); break;
        	case "Travel": travel+=e.getAmount();break;
        	case "Bills": bills+=e.getAmount();break;
        	case "Shopping": shopping+=e.getAmount();break;
        	}
        }
        

        // 2. Attach data to request
        request.setAttribute("expenses", expenses);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("monthTotal", monthTotal);
        request.setAttribute("foodTotal", food);
        request.setAttribute("travelTotal", travel);
        request.setAttribute("billsTotal", bills);
        request.setAttribute("shoppingTotal", shopping);
        

        // 3. Show JSP
        RequestDispatcher rd =
                request.getRequestDispatcher("recent.jsp");
        rd.forward(request, response);
    }
}
