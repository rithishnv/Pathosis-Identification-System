import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/home")
public class home1 extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            String ans=req.getParameter("name");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/symptom","root","admin");
            PreparedStatement stmt= con.prepareStatement("insert into demo(Symptom) values(?);");
            stmt.setString(1, ans);
            stmt.executeUpdate();
            con.close();
            String redirectURL;
            if ("eye pain".equalsIgnoreCase(ans)) {
                redirectURL = "questionnaire.html";
            } else if ("leg pain".equalsIgnoreCase(ans)) {
                redirectURL = "questionnaire1.html";
            } else if ("headache".equalsIgnoreCase(ans)) {
                redirectURL = "questionnaire.html";
            } else {
                redirectURL = "invalidAnswer.html";
            }

            // Perform the redirection
            res.setContentType("text/html");
            PrintWriter out=res.getWriter();
            res.sendRedirect(redirectURL);
            out.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}