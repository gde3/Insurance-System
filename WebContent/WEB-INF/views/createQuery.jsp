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
		
		$("#curQuery").css("display", "none");
		$("#btn").click(function(){
			
			var content = $(this).html();
			
			if(content == "Click to see all queries you submitted"){
				
				$("#curQuery").css("display", "block");
				$(this).html("Click to hide queries");
			}else{
				
				$("#curQuery").css("display", "none");
				$(this).html("Click to see all queries you submitted");
			}
		});
		
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

	<c:if test="${sessionScope.queries == null }">You never post query before</c:if>
	
	<c:if test="${sessionScope.queries != null }">
		<table border="1" cellspacing="10" id="curQuery">
			<tr>
				<th>Id</th>
				<th>Desc</th>
				<th>Name</th>
				<th>Solved</th>
			</tr>
			<c:forEach items="${sessionScope.queries }" var="query">
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
		<button id="btn">Click to see all queries you submitted</button>
	</c:if><br><br><br>
	
	<form action="/Insurance-System/insurancepro/customerOpe/createQuery" method="post">
		
		userId : <input type="text" name="custId" value="${sessionScope.custId }"/><br>
		queryName : <input type="text" name="queryName"/><br>
		desc : <input type="text" name="desc"/><br>
		<input type="submit" value="submit"/>
	</form>
</body>
</html>




