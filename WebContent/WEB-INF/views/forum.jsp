<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/Insurance-System/jquery-1.7.2.js"></script>
<script type="text/javascript">

	$(function(){
		
		$("#ans").click(function(){
			
			var id = $(this).attr('class');
			var node = this;
			
			$.post("/Insurance-System/insurancepro/customerOpe/getQueryAnswer",
					{"id" : id},
					function(data){
						
						var pa = $(node).parent();
						$(node).parent().html(data);
						$(pa).css("background-color", "lightblue");
					});
		});
	})
</script>
</head>
<body>

	<c:if test="${requestScope.queries == null}">
		Forum is empty right now ...
	</c:if>
	
	<c:if test="${requestScope.queries != null}">
		<table border="1" cellpadding="10">
			<tr>
				<th>Id</th>
				<th>Desc</th>
				<th>Name</th>
				<th>Solved</th>
			</tr>
			<c:forEach items="${requestScope.queries }" var="query">
				<tr>
					<td>${query.queryId }</td>
					<td>${query.queryDescription }</td>
					<td>${query.queryName }</td>
					<td>
						<c:if test="${query.answer == null}">UnResolved</c:if>
						<c:if test="${query.answer != null}">
							<button id="ans" class="${query.answer.answerId}">Click to see answer</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if><br>
	
	<a href="/Insurance-System/insurancepro/customerOpe/getCountOfQuery">post a query</a>
</body>
</html>