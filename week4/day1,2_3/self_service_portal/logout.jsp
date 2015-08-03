<html>
	<body>
<%
//out.println(request.getSession());
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
		<h2>Logout Successful...</h2>
		<a href="login.html">Click here to login again!!!</a>
	<%
}
%>
	</body>
</html>