<!--%@ page session="false" %-->
<% String conformation=(String)request.getAttribute("conformation"); %>
<% if(session.getAttribute("user_id")!=null ){
	response.sendRedirect("userdetails");
} %>

<html> 
	<head> 
		<title>Login page</title> 
		<link rel="stylesheet" type="text/css" href="custom.css"> 
	</head> 
	<body> 
		<font> 
			<br><br><br><br><br>
			<% if(conformation!=null ){ %>
			<p class="error">Error! Enter Again</p> 
			<% }else{ %> <br> <% } %>
			<form method="get" action="userlogin"> 
				<center> 
					<table class="withborder_table"> 
						<thead> 
							<tr> 
								<th class="space_portal" colspan="2">SpacePortal</th> 
							</tr> 
							<tr> 
								<th class="login" colspan="2">login</th> 
							</tr> 
						</thead> 
						<tbody> 
							<tr> 
								<td colspan="2">
									<input class="login_inp" type="email" name="email" value="" placeholder="Email" size="25" required/>
								</td> 
							</tr> 
							<tr> 
								<td colspan="2">
									<input class="login_inp" type="password" name="pass" value="" placeholder="Password" size="25" required pattern="^([a-zA-Z0-9@*#]{8,15})$"/>
								</td> 
							</tr> 
							<tr> 
								<td align="center"> 
									<input name="submit_button" class="submit_button" type="submit" value="Login" /> 
								</td> 
								<td align="center">
									<input class="reset_button" type="reset" value="Reset" />
								</td> 
							</tr> 
							<tr> 
								<td class="ref" colspan="2">
									<a href="signup.html">New around here?</a>
								</td> 
							</tr> 
						</tbody> 
					</table> 
				</center> 
			</form> 
		</font>
		<% if(conformation!=null ){ %>
		<script>
			if(<%= conformation.equals("emailNotExists") %>){
				document.getElementsByName("email")[0].className = "changeRed";
				document.getElementsByName("email")[0].placeholder = "Email does not Exists"; 
				document.getElementsByName("email")[0].style.borderColor= 'red';
			}
			if(<%= conformation.equals("passwordDoesNotMatch") %>){ 
				document.getElementsByName("pass")[0].className = "changeRed"; 
				document.getElementsByName("pass")[0].placeholder = "Password incorrect!";
				document.getElementsByName("pass")[0].style.borderColor= 'red';
			}
		</script> 
		<%} %>
	</body> 
</html>
