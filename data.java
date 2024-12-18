import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/res1")
public class data extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            // Get parameters from the request
            String param1 = req.getParameter("q1");
            String param2 = req.getParameter("q2");
            String param3 = req.getParameter("q3");
            String param4 = req.getParameter("q4");
            String param5 = req.getParameter("q5");
            String param6 = req.getParameter("q6");

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/symptom", "root", "admin");

            // Prepare the SQL query
            String query = "SELECT disease FROM symptom WHERE symptom1 = ? AND symptom2 = ? AND symptom3 = ? AND symptom4 = ? AND symptom5 = ? AND symptom6 = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, param1);
            pstmt.setString(2, param2);
            pstmt.setString(3, param3);
            pstmt.setString(4, param4);
            pstmt.setString(5, param5);
            pstmt.setString(6, param6);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Get the result
            String disease = null;
            if (rs.next()) {
                disease = rs.getString(1);
            }

            // Close the ResultSet and PreparedStatement
            rs.close();
            pstmt.close();
            con.close();

            // Set the disease as a request attribute
            req.setAttribute("value", disease);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            // Log the exception (use a logging framework in real applications)
            e.printStackTrace();
            // Optionally, forward to an error page
            req.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
            dispatcher.forward(req, res);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
