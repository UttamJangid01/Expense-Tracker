<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <title>Create Family Group</title>

        <!-- External CSS -->
        <link rel="stylesheet" href="/css/family.css">
    </head>

    <body>

        <div class="page-wrapper">
            <div class="card">
                <c:choose>
                    <c:when test="${mode=='create'}">
                        <h2>Create Family Group</h2>
                        <p class="subtitle">Start managing expenses together</p>

                        <form action="/createFamily" method="post">

                            <div class="form-group">
                                <label>Family Name</label>
                                <input type="text" name="famliyName" placeholder="Enter family name" required>
                            </div>

                            <div class="form-group">
                                <label>Your Name</label>
                                <input type="text" name="user" placeholder="Enter your name" required>
                            </div>

                            <div class="form-group">
                                <label>Your Role</label>
                                <div class="radio-group">
                                    <label class="radio-card">
                                        <input type="radio" name="role" value="Host" required>
                                        <span>Host</span>
                                    </label>

                                    <label class="radio-card">
                                        <input type="radio" name="role" value="Member">
                                        <span>Member</span>
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" name="email" placeholder="example@gmail.com" required>
                            </div>

                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="password" placeholder="password" required>
                            </div>

                            <button type="submit" class="btn-primary">
                                Create Group
                            </button>

                        </form>
                    </c:when>

                    <c:otherwise>
                        <h2>Join Family Group</h2>
                        <p class="subtitle">Start managing expenses together</p>

                        <form action="/joinFamily" method="post">

                            <div class="form-group">
                                <label>Family Name</label>
                                <input type="text" name="famliyName" placeholder="Enter family name" required>
                            </div>

                            <div class="form-group">
                                <label>Your Name</label>
                                <input type="text" name="user" placeholder="Enter your name" required>
                            </div>

                            <div class="form-group">
                                <label>Your Role</label>
                                <div class="radio-group">
                                    <label class="radio-card">
                                        <input type="radio" name="role" value="Member" required>
                                        <span>Member</span>
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" name="email" placeholder="example@gmail.com" required>
                            </div>

                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="password" placeholder="password" required>
                            </div>

                            <button type="submit" class="btn-primary">
                                Join Group
                            </button>

                        </form>

                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    </body>

    </html>