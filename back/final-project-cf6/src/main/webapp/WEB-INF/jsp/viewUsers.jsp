<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>View Users</title>
    <link href="<c:url value="/resources/static/css/common.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Username</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.username}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>