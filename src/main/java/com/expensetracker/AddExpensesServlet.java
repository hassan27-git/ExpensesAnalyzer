package com.expensetracker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addExpenses")
public class AddExpensesServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String itemName = request.getParameter("itemName");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String category = request.getParameter("category");
        String date = request.getParameter("date");
        
        Expenses expense = new Expenses(itemName, amount, category, date);
        expense.SaveToDatabase();
        
       

        response.sendRedirect("recentExpenses");

	}

}
