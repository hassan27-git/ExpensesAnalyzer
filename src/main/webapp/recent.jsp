<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.expensetracker.Expenses" %>

<%
    List<Expenses> expenses = (List<Expenses>) request.getAttribute("expenses");

    double totalAmount   = (Double) request.getAttribute("totalAmount");
    double monthTotal    = (Double) request.getAttribute("monthTotal");
    double foodTotal     = (Double) request.getAttribute("foodTotal");
    double travelTotal   = (Double) request.getAttribute("travelTotal");
    double billsTotal    = (Double) request.getAttribute("billsTotal");
    double shoppingTotal = (Double) request.getAttribute("shoppingTotal");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Personal Expense Analyzer</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
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

                <div class="summary-top">
                    <div class="summary-card">
                        <h3>Total Expenses</h3>
                        <p>₹ <%= totalAmount %></p>
                    </div>

                    <div class="summary-card">
                        <h3>This Month</h3>
                        <p>₹ <%= monthTotal %></p>
                    </div>
                </div>

                <div class="summary-bottom">
                    <h4>Category Breakdown</h4>
                    <ul>
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
                        if (expenses != null && !expenses.isEmpty()) {
                            for (Expenses e : expenses) {
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
                            <td colspan="4" style="text-align:center;">
                                No expenses found
                            </td>
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
