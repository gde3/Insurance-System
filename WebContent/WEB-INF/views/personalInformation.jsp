<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/Insurance-System/jquery-1.7.2.js"></script>
<script type="text/javascript">

	$(function(){
		
		var hideOrShow = function(b, id, pre, after){
			
			$("#"+id).css("display", "none");
			$("#"+b).click(function(){
				
				var content = $(this).html();
				
				if(content == pre){
					
					$("#"+id).css("display", "block");
					$(this).html(after);
				}else{
					
					$("#"+id).css("display", "none");
					$(this).html(pre);
				}
			});
		}
		
		hideOrShow("btn1", "insuranceTable", "Click to see insurance detail", "Click to hide insurance detail");
		hideOrShow("btn2", "loanTable", "Click to see loan detail", "Click to hide loan detail");
		hideOrShow("btn3", "queryTable", "Click to see query detail", "Click to hide query detail");
	});
</script>
</head>
<body>

	<h1>Hello, ${sessionScope.customer.customerName }, this is your
		personal page</h1>
	<br>

	<table border="1" cellpadding="10">

		<tr>

			<th>ID</th>
			<th>Name</th>
			<th>Gender</th>
			<th>Password</th>
			<th>Insurances</th>
			<th>loan</th>
			<th>queries</th>
		</tr>
		<tr>

			<td>${sessionScope.customer.customerId }</td>
			<td>${sessionScope.customer.customerName }</td>
			<td>${sessionScope.customer.customerGender }</td>
			<td>${sessionScope.customer.customerPassword }</td>
			<td><c:if
					test="${sessionScope.customer.insurancesOrdered == null }">No insurance ordered</c:if>
				<c:if test="${sessionScope.customer.insurancesOrdered != null }">
					<button id="btn1">Click to see insurance detail</button>
				</c:if></td>
			<td><c:if test="${sessionScope.customer.loan == 0 }">No loan</c:if>
				<c:if test="${sessionScope.customer.loan != 0 }">
					<button id="btn2">Click to see loan detail</button>
				</c:if></td>
			<td><c:if
					test="${sessionScope.customer.queriesCreated == null }">No query created</c:if>
				<c:if test="${sessionScope.customer.queriesCreated != null }">
					<button id="btn3">Click to see query detail</button>
				</c:if></td>
		</tr>
	</table><br><br>
	
	<div id="insuranceTable">
		<table border="1" cellpadding="10">
			<tr>
				<th>Id</th>
				<th>InsuranceName</th>
				<th>Desc</th>
				<th>Price</th>
				<th>From</th>
				<th>To</th>
				<th>Order</th>
			</tr>
			<c:forEach items="${sessionScope.customer.insurancesOrdered }"
				var="insurance">
				<tr>
					<td>${insurance.insurance.insuranceId }</td>
					<td>${insurance.insurance.insuranceName }</td>
					<td>${insurance.insurance.description }</td>
					<td>${insurance.insurance.insurancePrice }</td>
					<td>${insurance.fromDate }</td>
					<td>${insurance.endDate }</td>
					<td><button id="btn4">Order</button></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7"><a id="btn5" href="/Insurance-System/insurancepro/customerOpe/getAllInsurances">insurance</a></td>
			</tr>
		</table><br><br>
	</div>
	
	<div id="loanTable">
		<table border="1" cellpadding="10">
			<tr>
				<th>Id</th>
				<th>payOrBorrow</th>
				<th>balance</th>
				<th>date</th>
			</tr>
			<c:forEach items="${sessionScope.customer.loanDetails }"
				var="loanDetail">
				<tr>
					<td>${loanDetail.loanId }</td>
					<td>${loanDetail.payOrBorrow }</td>
					<td>${loanDetail.balance }</td>
					<td>${loanDetail.date }</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4"><button id="btn6">loan</button></td>
			</tr>
		</table><br><br>
	</div>

	<div id="queryTable">
		<table border="1" cellpadding="10">
			<tr>
				<th>Id</th>
				<th>Desc</th>
				<th>Name</th>
				<th>Solved</th>
			</tr>
			<c:forEach items="${sessionScope.customer.queriesCreated }" var="query">
				<tr>
					<td>${query.queryId }</td>
					<td>${query.queryDescription }</td>
					<td>${query.queryName }</td>
					<td><c:if test="${query.answer == null}">UnResolved</c:if> <c:if
							test="${query.answer != null}">
							<button id="ans" class="${query.answer.answerId}">Click
								to see answer</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>



















