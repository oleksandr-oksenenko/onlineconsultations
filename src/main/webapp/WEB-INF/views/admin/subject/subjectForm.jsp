<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <c:import url="../../common/js-include.jsp"/>
    <c:import url="../../common/css-include.jsp"/>
</head>

<body>
<div class="container">
    <div class="page-header">
        <ul class="nav nav-pills">
            <li class="active"><a href="<c:url value='/admin/subjects'/>">Subjects</a></li>
            <li><a href="<c:url value='/admin/sub_subjects'/>">Sub subjects</a></li>
            <li><a href="<c:url value='/admin/users'/>">Users</a></li>
        </ul>
    </div>

    <form:form method="POST" commandName="subject">
        <div class="form-group">
            <form:label path="name">Name</form:label>
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <form:label path="description">Description</form:label>
            <form:textarea path="description" cssClass="form-control"/>
            <form:errors path="description" cssClass="text-danger"/>
        </div>
        <span class="pull-right">
            <button type="submit" class="btn btn-default">Save</button>
            <a href="<c:url value="/admin/subjects/"/>" class="btn btn-default">Cancel</a>
        </span>
    </form:form>

</div>
</body>

</html>
