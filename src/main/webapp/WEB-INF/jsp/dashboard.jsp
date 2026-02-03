<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Expense Manager</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>

    <body>

        <div class="container">

            <nav class="navbar">
                <div class="nav-title">Expense Manager</div>
                <a href="/setting" class="settings-btn">
                    <img src="https://png.pngtree.com/png-clipart/20190904/original/pngtree-settings-icon-png-image_4491632.jpg"
                        alt="Settings" class="settings-icon">
                </a>
            </nav>

            <div class="content-wrapper">

                <!-- Form Card -->
                <div class="card form-card">

                    <form action="/dashboard" method="post">
                        <div class="form-group">
                            <label>Date:</label>
                            <span class="date">${date}</span>
                        </div>

                        <div class="form-group">
                            <label>Item</label>
                            <input type="text" name="item" required>
                        </div>

                        <div class="form-group">
                            <label>Quantity</label>
                            <input type="number" name="quantity" required>
                            <select name="type">
                                <option>Piece</option>
                                <option>Kg</option>
                                <option>L</option>
                                <option>Items</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Total Price</label>
                            <input type="number" id="total" name="total" required>
                        </div>

                        <div class="form-group">
                            <label>Paid Price</label>
                            <input type="number" id="paid" name="paid" required>
                        </div>

                        <div class="form-group">
                            <label>Due Price</label>
                            <input type="number" id="due" name="due" required>
                            <button type="button" class="small-btn" onclick="calculateDue()">FDP</button>
                        </div>

                        <div class="btn-group">
                            <button type="reset" class="reset-btn">Clean</button>
                            <button type="submit" class="submit-btn">Submit</button>
                        </div>
                    </form>

                </div>

                <!-- Table Section -->
                <div class="card table-card">

                    <form action="/filter" method="post" id="filterForm">
                        <select name="type" onchange="submitFilterOnClick()">
                            <option value="day" ${type eq 'Day' ? 'selected="selected"' : '' }>Day</option>
                            <option value="month" ${type eq 'Month' ? 'selected="selected"' : '' }>Month</option>
                            <option value="year" ${type eq 'Year' ? 'selected="selected"' : '' }>Year</option>
                        </select>
                    </form>


                    <form action="/SpecificDate" method="post">
                        <input type="date" name="date" required />
                        <input type="submit" value="find" />
                    </form>
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>S No.</th>
                                <th>User</th>
                                <th>Item</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Paid</th>
                                <th>Due</th>
                                <c:if test="${type=='month' || type=='year'}">
                                    <th>Date</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${expense}" varStatus="loop">
                                <tr>
                                    <td>${loop.index+1}.</td>
                                    <td>${item.user}</td>
                                    <td>${item.item}</td>
                                    <td>${item.quantity} ${item.type}</td>
                                    <td>${item.total}</td>
                                    <td>${item.paid}</td>
                                    <td>${item.due}</td>
                                    <c:if test="${type=='month' || type=='year'}">
                                        <td>${item.date}</td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <table class="styled-table summary-table">
                        <thead>
                            <tr>
                                <th>Status</th>
                                <th>Total</th>
                                <th>Paid</th>
                                <th>Due</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${type == 'find'}">
                                            Day
                                        </c:when>
                                        <c:otherwise>
                                            ${type}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${total}</td>
                                <td>${paid}</td>
                                <td>${due}</td>
                            </tr>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>

        <script>
            function calculateDue() {
                let total = document.getElementById("total").value;
                let paid = document.getElementById("paid").value;
                document.getElementById("due").value = total - paid;
            }
            function submitFilterOnClick() {
                document.getElementById("filterForm").submit();
            }
        </script>

    </body>

    </html>