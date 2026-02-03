<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <title>Family Settings</title>

        <!-- Link External CSS -->
        <link rel="stylesheet" href="/css/setting.css">
    </head>

    <body>

        <div class="container">

            <h2>Family Settings</h2>

            <div class="family-name">
                Family: <b>${famliyName}</b>
            </div>

            <div class="add-member">
                <form action="/addMember" method="post">
                    <input type="email" name="email" placeholder="Enter member email">
                    <button>Add Member</button>
                </form>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Email</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" items="${memberList}">
                        <tr>
                            <td>${i.user}</td>
                            <td>${i.email}</td>
                            <td>
                                <span class="role">${i.role}</span>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>

    </body>

    </html>