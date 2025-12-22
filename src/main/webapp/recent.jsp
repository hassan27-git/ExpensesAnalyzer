<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.expensetracker.Expenses" %>
<%
    // Retrieve the expenses list from request attribute
    List<Expenses> expenses = (List<Expenses>) request.getAttribute("expenses");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Personal Expense Analyzer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>

<div class="container">

    <!-- HEADER -->
    <header class="header">
        <h1>Personal Expense Analyzer</h1>
        <p>Track • Analyze • Control</p>
    </header>

    <!-- MAIN LAYOUT -->
    <div class="main">

        <!-- LEFT COLUMN -->
        <div class="left">

            <!-- FORM -->
            <section class="card">
                <h2>Add Expense</h2>

                <form action="addExpenses" method="post">
                    <input type="text" name="itemName" placeholder="Item name" required>
                    <input type="number" name="amount" placeholder="Amount" required>

                    <select name="category" required>
                        <option value="">Select category</option>
                        <option>Food</option>
                        <option>Travel</option>
                        <option>Shopping</option>
                        <option>Bills</option>
                    </select>

                    <input type="date" name="date" required>

                    <button type="submit">Add Expense</button>
                </form>
            </section>

            <!-- SUMMARY -->
            <section class="summary-wrapper">

                <!-- TOP SUMMARY -->
                <div class="summary-top">
                    <div class="summary-card">
                        <h3>Total Expenses</h3>
                        <p>
                            ₹ 
                            <%
                                double total = 0;
                                if(expenses != null){
                                    for(Expenses e : expenses){
                                        total += e.getAmount();
                                    }
                                }
                                out.print(total);
                            %>
                        </p>
                    </div>

                    <div class="summary-card">
                        <h3>This Month</h3>
                        <p>
                            ₹ 
                            <%
                                double monthTotal = 0;
                                java.time.LocalDate now = java.time.LocalDate.now();
                                if(expenses != null){
                                    for(Expenses e : expenses){
                                        java.time.LocalDate expenseDate = java.time.LocalDate.parse(e.getDate());
                                        if(expenseDate.getMonthValue() == now.getMonthValue() &&
                                           expenseDate.getYear() == now.getYear()){
                                            monthTotal += e.getAmount();
                                        }
                                    }
                                }
                                out.print(monthTotal);
                            %>
                        </p>
                    </div>
                </div>

                <!-- CATEGORY BREAKDOWN -->
                <div class="summary-bottom">
                    <h4>Category Breakdown</h4>
                    <ul>
                        <%
                            double foodTotal = 0, travelTotal = 0, billsTotal = 0, shoppingTotal = 0;
                            if(expenses != null){
                                for(Expenses e : expenses){
                                    switch(e.getCategory()){
                                        case "Food": foodTotal += e.getAmount(); break;
                                        case "Travel": travelTotal += e.getAmount(); break;
                                        case "Bills": billsTotal += e.getAmount(); break;
                                        case "Shopping": shoppingTotal += e.getAmount(); break;
                                    }
                                }
                            }
                        %>
                        <li>Food <span>₹ <%= foodTotal %></span></li>
                        <li>Travel <span>₹ <%= travelTotal %></span></li>
                        <li>Bills <span>₹ <%= billsTotal %></span></li>
                        <li>Shopping <span>₹ <%= shoppingTotal %></span></li>
                    </ul>
                </div>

            </section>

        </div>

        <!-- RIGHT COLUMN -->
        <div class="right">
            <section class="card">
                <h2>Recent Expenses</h2>

                <table>
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Item</th>
                            <th>Category</th>
                            <th>Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if(expenses != null){
                                for(Expenses e : expenses){
                        %>
                        <tr>
                            <td><%= e.getDate() %></td>
                            <td><%= e.getItemName() %></td>
                            <td><%= e.getCategory() %></td>
                            <td>₹ <%= e.getAmount() %></td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="4" style="text-align:center;">No expenses found.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </section>
        </div>

    </div>
</div>

</body>
</html>
