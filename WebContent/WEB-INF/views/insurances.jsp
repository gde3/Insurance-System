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
												", you can click yes to extend the insurance duration!!!");
										
										if(bool){
											
											var price = $($($($(node).parentsUntil(".row")).parent()).children().eq(3)).html();
											
											var options =	 
													"<td>" + 
														"<div><input type='radio' name='duration' value='3'/>3 month   <span>" + price * 0.3 + "</span></div>" +
														"<div><input type='radio' name='duration' value='6'/>6 month   <span>" + price * 0.6 + "</span></div>" +
														"<div><input type='radio' name='duration' value='12'/>12 month   <span>" + price + "</span></div>" +
														"<div><input type='radio' name='duration' class='DIY'/>other duration</div>" +
														"<div><input type='submit' value='purchase'/></div>" +
													"</td>"
											$($($(node).parent()).parent()).append(options);
											
											$("span").css("display", "none");
											$("input:radio").on({
											    mouseenter: function(){
											        $($($(this).parent()).children("span")).fadeIn();
											    }, 
											    mouseleave: function(){
											    	$($($(this).parent()).children("span")).fadeOut();
											    }
											});
											$(".DIY").on({
												
												click:function(){
													
													$($(this).parent()).append("<input type='text' class='dur'/>");
													$(this).unbind("click");
													$(".dur").focus(function(){
														
														$($(this).parent()).children().eq(0).attr("checked", "true");
													});
													$(".dur").change(function(){
														
														$($(this).parent()).children().eq(0).val($(this).val());
													});
												}
											});
											$("input:submit").click(function(){
												
												$.post("/Insurance-System/insurancepro/customerOpe/extendInsuranceDate",
														{
													
															"custId" : $($($($($(node).parentsUntil(".row")).parent()).children().eq(4)).children().eq(0)).attr("class"),
															"insurId" : $($($($($(node).parentsUntil(".row")).parent()).children().eq(4)).children().eq(0)).attr("id"),
															"duration" : $("input:checked").val()
														},
														function(date2){
															
															alert("Thank you for purchasing and your insurance will end at " + date2);
														}
														);
											});
										}else{
											
											alert("Hope you can find other insurance which suit for you!!!");
										}
									});
						}else{
							
							var bool = confirm("You haven't ordered this insurance yet, you can click yes to start this insurance!!!");
					
							if(bool){
								
								var price = $($($($(node).parentsUntil(".row")).parent()).children().eq(3)).html();
								
								var options =	 
										"<td>" + 
											"<div><input type='radio' name='duration' value='3'/>3 month   <span>" + price * 0.3 + "</span></div>" +
											"<div><input type='radio' name='duration' value='6'/>6 month   <span>" + price * 0.6 + "</span></div>" +
											"<div><input type='radio' name='duration' value='12'/>12 month   <span>" + price + "</span></div>" +
											"<div><input type='radio' name='duration' class='DIY'/>other duration</div>" +
											"<div><input type='submit' value='purchase'/></div>" +
										"</td>"
								$($($(node).parent()).parent()).append(options);
								
								$("span").css("display", "none");
								$("input:radio").on({
								    mouseenter: function(){
								        $($($(this).parent()).children("span")).fadeIn();
								    }, 
								    mouseleave: function(){
								    	$($($(this).parent()).children("span")).fadeOut();
								    }
								});
								$(".DIY").on({
									
									click:function(){
										
										$($(this).parent()).append("<input type='text' class='dur'/>");
										$(this).unbind("click");
										$(".dur").focus(function(){
											
											$($(this).parent()).children().eq(0).attr("checked", "true");
										});
										$(".dur").change(function(){
											
											$($(this).parent()).children().eq(0).val($(this).val());
										});
									}
								});
								$("input:submit").click(function(){
									
									$.post("/Insurance-System/insurancepro/customerOpe/orderNewInsurance",
											{
										
												"custId" : $($($($($(node).parentsUntil(".row")).parent()).children().eq(4)).children().eq(0)).attr("class"),
												"insurId" : $($($($($(node).parentsUntil(".row")).parent()).children().eq(4)).children().eq(0)).attr("id"),
												"duration" : $("input:checked").val()
											},
											function(date3){
												
												alert("Thank you for purchasing and your insurance will end at " + date3);
											}
											);
								});
							}else{
								
								alert("Hope you can find other insurance which suit for you!!!");
							}
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
				<tr class="row">
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