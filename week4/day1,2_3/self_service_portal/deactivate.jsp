<html>
	<body>
<jsp:useBean id="user_details" class="user.UserDetails" scope="session"/>

<%
user_details.removeFromDb();

session.removeAttribute("user_id");
session.removeAttribute("login_time");
session.invalidate();
try{
	if(session.getAttribute("user_id")!=null){
	%>
				<h2>Session Id <%=session.getId()%> </h2>
				<h2>Session Still alive!!!</h2>
	<%
	}else{
	%>
				<h2>Logged out</h2>
	<%
	}
}catch(IllegalStateException e){
	%>
		<h2>Your account has been deactivated successfully...</h2>
		<a href="signup.html">Click here to SignUp again!!!</a>
	<%
}
%>
	</body>
</html>