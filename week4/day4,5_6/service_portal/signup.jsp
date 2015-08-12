<%@page import="user.SignupFlags"%>

<% if(session.getAttribute("user_id")!=null ){
	response.sendRedirect("userdetails");
} %>
<% SignupFlags flags=(SignupFlags)request.getAttribute("flags"); %>
<html> 
	<head> 
		<title>
			SignUp page
		</title> 
		<link rel="stylesheet" type="text/css" href="custom.css"> 
		</head> 
		<body> 
			<br><br><br>
			<p color="red" name="start_para" align="center"></p> 
			<form method="post" action="usersignup"> 
				<center> 
					<table class="withborder_table"> 
						<thead> 
							<tr> 
								<th id="reg_to" colspan="2">Register to</th> 
							</tr> 
							<tr> 
								<th class="space_portal1" colspan="2">Space Portal</th> 
							</tr> 
						</thead> 
						<tbody> 
							<tr> 
								<td><input type="text" name="fname" value="" size=25 placeholder="First Name" /></td>
								<td><input type="text" name="lname" value="" size=25 placeholder="Last Name" required/></td> 
							</tr> 
							<tr> 
								<td><input type="email" name="email" value="" size=25 placeholder="Email" required /></td> 
								<td><input type="email" name="cemail" value="" size=25/ placeholder="Confirm Email" required /></td> 
							</tr> 
							<tr> 
								<td><input type="password" name="pass" value="" size=25 placeholder="Password" required /></td> 
								<td><input type="password" name="cpass" value="" size=25 placeholder="ConfirmPassword" required  /></td> 
							</tr> 
							<tr> 
								<td colspan="2" align="center"><input class="submit_button" type="submit" value="Create an Account" /></td> 
							</tr> 
							<tr> 
								<td class="ref" colspan="2" align="center"><a href="login.jsp">Have an account already?</a></td> 
							</tr> 
						</tbody> 
					</table>
				</center>
			</form>

			
	
			<% if(flags!=null){ %>
			<script> 
				if(<%=flags.getPassFlag1()%>){

					document.getElementsByName("pass")[0].className = "changeRed"; 
					document.getElementsByName("pass")[0].placeholder = "Passwords do Not Match!"; 
					document.getElementsByName("pass")[0].style.borderColor= 'red'; 
					document.getElementsByName("cpass")[0].className = "changeRed"; 
					document.getElementsByName("cpass")[0].placeholder = "Passwords do Not Match!"; 
					document.getElementsByName("cpass")[0].style.borderColor = 'red'; 
				} 

				if(<%= flags.getEmailFlag() %>){
					document.getElementsByName("email")[0].placeholder = "Emails do Not Match!"; 
					document.getElementsByName("cemail")[0].placeholder = "Emails do Not Match!"; 
					document.getElementsByName("email")[0].style.borderColor='red'; 
					document.getElementsByName("cemail")[0].style.borderColor='red'; 
					document.getElementsByName("email")[0].className = "changeRed";
					document.getElementsByName("cemail")[0].className = "changeRed";  
				}
				if(<%= flags.getDbFlagNot() %>){
					document.getElementsByName("start_para")[0].innerHTML="This EmailId has already been registed.";
				}
			</script> 
			<% } %>
			
		</body> 
	</html>




