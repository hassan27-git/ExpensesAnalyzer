# 💰 Personal Expense Tracker (Java Web Application)

A full-stack financial tracking solution designed to help users manage, track, and analyze their personal finances. This project demonstrates core competencies in Java EE, JDBC database integration, and MVC (Model-View-Controller) architecture.

---

## 📖 Project Purpose
The goal of this project is to provide a lightweight, local-host environment where a user can maintain a digital ledger of expenses. It replaces manual spreadsheets with a dynamic web interface that automates total calculations and categorizes spending data in real-time.

---

## 🛠️ Technology Stack
| Layer | Technology |
| :--- | :--- |
| **Language** | Java |
| **Frontend** | HTML, CSS, JSP |
| **Backend** | Java Servlets |
| **Database** | MySQL |
| **JDBC Driver** | MySQL Connector/J |
| **Server** | Apache Tomcat 9 |

---

## 📂 Project Structure
ExpenseTracker/
├── src/
│   └── main/
│       ├── java/          # Backend Servlets and Database Logic
│       └── webapp/        # JSP files and CSS styling
├── WEB-INF/               # Configuration (web.xml)
└── sql/                   # Database scripts

---

## 🗄️ Database Setup & Configuration
To get the backend running, follow these steps:

1. Create a MySQL database named: expense_tracker

2. Execute the following SQL to create the required table:

   CREATE TABLE expenses (
       id INT AUTO_INCREMENT PRIMARY KEY,
       item_name VARCHAR(255) NOT NULL,
       amount DOUBLE NOT NULL,
       category VARCHAR(100),
       date_added DATE NOT NULL
   );

3. Update your JDBC credentials in your connection logic (usually in a DBUtil or Servlet file):
   - URL: jdbc:mysql://localhost:3306/expense_tracker
   - User: [Your MySQL Username]
   - Password: [Your MySQL Password]

---

## 🚀 Key Features
* ➕ Add Expense: Log item names, amounts, categories, and dates.
* 📊 Summary Dashboard: View total lifetime expenses and current month totals.
* 🗂️ Categorization: See a breakdown of spending by category (e.g., Food, Transport, Rent).
* 🕒 Recent Activity: A quick-access table showing the last 10 transactions.

---

## 💻 How to Run
1. Clone the repository to your local machine.
2. Deployment: Move the project folder into the 'webapps' directory of your Apache Tomcat installation.
3. Start Server: Launch Tomcat (ensure it is configured to run on port 8081).
4. Access: Open your browser and go to: http://localhost:8081/ExpenseTracker

---

## 📌 Technical Notes
* Architecture: Built using the DAO (Data Access Object) pattern for clean database interaction.
* Single User: This version is designed for local, single-user usage.
* Port Configuration: If your Tomcat uses the default port 8080, adjust the URL accordingly.

---

