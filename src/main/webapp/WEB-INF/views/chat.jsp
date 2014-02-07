<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css"
        href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">

    <script type="text/javascript" src="<c:url value='/resources/jquery/1.9.0/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/chat.js'/>"></script>
    <c:choose>
        <c:when test="${ consultant ne null }">
            <script type="text/javascript" src="<c:url value='/js/chat_consultant.js'/>"></script>
        </c:when>
        <c:otherwise>
            <script type="text/javascript" src="<c:url value='/js/chat_anonym.js'/>"></script>
        </c:otherwise>
    </c:choose>
    
</head>

<body>
    <div class="container">
        <c:import url="common/header.jsp" />
        
        
        <div id="messages">
        
        </div>
        
        <form role="form">
            <div class="form-group">
                <label for="message">New message (<small>you can send messages using ctrl+enter</small>)</label>
                <textarea rows="3" class="form-control" id="message" disabled="disabled" onkeypress="javascript:onEnterPress(event);"></textarea>
            </div>
            <button 
                type="button"
                class="btn btn-default" 
                onclick="javascript:postMessage();" 
                disabled="disabled"
                id="btnPostMessage">
                Post a message
            </button>
            <button 
                type="button" 
                class="btn btn-default"
                onclick="javascript:endChat();" 
                disabled="disabled"
                id="btnEndChat">
                End the chat
            </button>
        </form>

        <c:import url="common/footer.jsp" />
    </div>
</body>

</html>