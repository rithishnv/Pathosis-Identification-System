<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pathosis</title>
    <link rel="stylesheet" href="homestyle.css">
</head>
<body>
    <div class="container">
            <header>
                <h1>Pathosis Prediction</h1>
                <div class="menu">
                </div>
            </header>
            <main>
                <aside>
                    <ul>
                        <li>Patient</li>
                        <li>Symptoms</li>
                        <li>Questionnaire</li>
                        <li class="active">Results</li>
                    </ul>
                </aside>
     <section class="content">
                     <h2>Based on the survey you may possibly have :</h2>
    <%
        // Get the value from the request attribute
        String value = (String) request.getAttribute("value");

        // Check if the value is not null
        if (value != null && !value.isEmpty()) {
            out.println("<h2>" + value + "</h2>");
        } else {
            out.println("<p>No value received.</p>");
        }
    %>
        </section>
       </main>
     <footer>
   <p>Pathosis Identification System</p>
    </footer>
 </div>
</body>
</html>
