<%@page import="com.R254.entities.Insurance"%>
<%@page import="java.util.List"%>
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
		
		$("a:odd").click(function(){
			
			var node = this;

			$.post("/Insurance-System/insurancepro/customerOpe/checkWhetherOrdered",
					{
				
						"custId" : $(node).attr("class"),
						"insurId" : $(node).attr("id")
					},
					function(data){
						
						if(data == 1){
							
							$.post("/Insurance-System/insurancepro/customerOpe/checkInsuranceDate",
									{
								
										"custId" : $(node).attr("class"),
										"insurId" : $(node).attr("id")
									},function(date){
										
										var bool = confirm("You've already ordered this insurance and it will ends in " + date + 
												" ,you can click yes to extend the insurance duration!!!");
										
										if(bool){
											
											alert("Thanks for purchasing!!!");
										}else{
											
											alert("Hope you can find other insurance which suit for you!!!");
										}
									});
						}else{
							
							alert("Not");
						}
					});
			
			return false;
		});
	});
</script>
</head>
<body>

	<jsp:include page="linkToForum.html"></jsp:include>
	
	<c:if test="${requestScope.insurances == null}"> 
		No insurance available right now! 
	</c:if>
	
	<c:if test="${requestScope.insurances != null}">
		<table border="1" cellpadding="10"> 
			<tr>
				<th>ID</th>
				<th>DESC</th>
				<th>NAME</th>
				<th>PRICE</th>
				<th>ORDER</th>
				<th>DELETE</th>
			</tr>		
			<c:forEach items="${requestScope.insurances}" var="insurance">
				<tr>
					<td>${insurance.insuranceId }</td>
					<td>${insurance.description }</td>
					<td>${insurance.insuranceName }</td>
					<td>${insurance.insurancePrice }</td>
					<td><a id="${insurance.insuranceId }" class="${sessionScope.custId }" href="">order</a></td>
					<td><a href="">delete</a></td>
				</tr>
			</c:forEach>
		</table> 
	</c:if>
	
</body>
</html>