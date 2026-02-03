<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <title>Login Page</title>
    </head>

    <body>
        <div>
            <h2>Login</h2>
            <hr>
            <pre>${errormessage}</pre>
            <form action="/login" method="post">
                <input type="email" name="email" placeholder="email" required /><br><br>
                <input type="password" name="password" placeholder="password" required /><br><br>
                <button>login</button>
            </form>
        </div>
    </body>

    </html>