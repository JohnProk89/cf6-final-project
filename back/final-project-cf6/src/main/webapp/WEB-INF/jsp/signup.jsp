<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up User</title>
</head>
<body>
<c:if test="${signUpUserSuccess}">
    <div>Successfully signed-up user with username: ${signedUpUser.username}</div>
</c:if>

<c:url var="signup_user_url" value="/auth/signup"/>
<form:form action="/auth/signup" method="post" modelAttribute="user">
    <form:label path="username">Username: </form:label> <form:input type="text" path="username"/>
    <form:label path="password">Password: </form:label> <form:input type="text" path="password"/>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>