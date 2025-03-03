<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.crud.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Operation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Toastify CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">CRUD Operation Using JSP and Bootstrap</h2>
        
        <!-- Add User Button -->
        <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#userModal" onclick="clearForm()">Add User</button>
        
        <!-- Users Table -->
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Get the list of users from the request attribute
                    List<User> users = (List<User>) request.getAttribute("users");
                    if (users != null) {
                        for (User user : users) {
                %>
                            <tr>
                                <td><%= user.getId() %></td>
                                <td><%= user.getName() %></td>
                                <td><%= user.getEmail() %></td>
                                <td><%= user.getCountry() %></td>
                                <td>
                                    <!-- Edit User Button -->
                                    <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#userModal" 
                                            onclick="editUser(<%= user.getId() %>, '<%= user.getName() %>', '<%= user.getEmail() %>', '<%= user.getCountry() %>')">Edit</button>
                                    
                                    <!-- Delete User Button -->
                                    <a href="UserServlet?action=delete&id=<%= user.getId() %>" class="btn btn-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5" class="text-center">No users found.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- User Modal -->
    <div class="modal fade" id="userModal" tabindex="-1" aria-labelledby="userModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="userModalLabel">Add/Edit User</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="userForm" action="UserServlet" method="post">
                        <input type="hidden" id="userId" name="id">
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="country" class="form-label">Country</label>
                            <input type="text" class="form-control" id="country" name="country" required>
                        </div>
                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Toastify JS -->
    <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
    <script>
        // Clear form for adding a new user
        function clearForm() {
            document.getElementById('userId').value = '';
            document.getElementById('name').value = '';
            document.getElementById('email').value = '';
            document.getElementById('country').value = '';
            document.getElementById('userModalLabel').innerText = 'Add User';
        }

        // Populate form for editing a user
        function editUser(id, name, email, country) {
            document.getElementById('userId').value = id;
            document.getElementById('name').value = name;
            document.getElementById('email').value = email;
            document.getElementById('country').value = country;
            document.getElementById('userModalLabel').innerText = 'Edit User';
        }

        // Trigger Toastify Notification
        function showToast(message, type) {
            Toastify({
                text: message,
                duration: 3000,
                gravity: "top", // Position: top/bottom
                position: "right", // Position: left/center/right
                backgroundColor: type === "success" ? "green" : "red",
                stopOnFocus: true, // Prevent dismissing on click
            }).showToast();
        }

        // Backend Message Integration
        <% 
            String message = (String) request.getAttribute("message");
            String type = (String) request.getAttribute("type");
            if (message != null && type != null) {
        %>
            showToast("<%= message %>", "<%= type %>");
        <% 
            }
        %>
    </script>
</body>
</html>
