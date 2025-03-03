<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JSP Calculator</title>

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
            <h2>JSP Calculator</h2>
        </div>
        <div class="card-body">
            <form action="result.jsp" method="post">
                <div class="mb-3">
                    <label class="form-label">Enter First Number:</label>
                    <input type="text" class="form-control" name="num1" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Enter Second Number:</label>
                    <input type="text" class="form-control" name="num2" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Select Operation:</label>
                    <select class="form-select" name="operation">
                        <option value="add">Addition (+)</option>
                        <option value="subtract">Subtraction (-)</option>
                        <option value="multiply">Multiplication (*)</option>
                        <option value="divide">Division (/)</option>
                    </select>
                </div>
              
                              <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Calculate</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap 5 JS Bundle (for components) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
