<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Calculation Result</title>

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 500px;
            margin-top: 50px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white text-center">
            <h2>Calculation Result</h2>
        </div>
        <div class="card-body text-center">
            <%
                // Fetch input values
                String num1Str = request.getParameter("num1");
                String num2Str = request.getParameter("num2");
                String operation = request.getParameter("operation");

                if (num1Str != null && num2Str != null && operation != null) {
                    try {
                        double num1 = Double.parseDouble(num1Str);
                        double num2 = Double.parseDouble(num2Str);
                        double result = 0;

                        switch (operation) {
                            case "add":
                                result = num1 + num2;
                                break;
                            case "subtract":
                                result = num1 - num2;
                                break;
                            case "multiply":
                                result = num1 * num2;
                                break;
                            case "divide":
                                if (num2 != 0) {
                                    result = num1 / num2;
                                } else {
            %>
                                    <div class="alert alert-danger">Cannot divide by zero!</div>
            <%
                                    return;
                                }
                                break;
                        }
            %>
                        <h3 class="text-success">Result: <%= result %></h3>
            <%
                    } catch (NumberFormatException e) {
            %>
                        <div class="alert alert-danger">Invalid input! Please enter numeric values.</div>
            <%
                    }
                }
            %>
        </div>
        <div class="card-footer text-center">
            <a href="index.jsp" class="btn btn-primary">Go Back</a>
        </div>
    </div>
</div>

<!-- Bootstrap 5 JS Bundle (for components) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
