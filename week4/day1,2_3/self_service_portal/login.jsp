<jsp:useBean id="user_login" class="user.UserLogin" scope="session"/>
<jsp:setProperty name="user_login" property="*"/> 
<%@ page import="java.util.Date" %>

<%
if(session.getAttribute("user_id")!=null){
    response.sendRedirect("details.jsp");
    return;
}else{
%>


<html>
	<head>
		<title>Login page</title>
        <%
            if(user_login.getLoginConformation()){
                session.setAttribute("user_id", user_login.getId());
                session.setAttribute("login_time", new Date());
            %>
                <!--<jsp:forward page="details.jsp" />-->
                <meta http-equiv="refresh" content="0; url=details.jsp" />
            <%
            }
        %>
        
	</head>

	<body bgcolor="CORNSILK">
		<font color="BLACK" size=3>
            <!--%
            else{
            %-->
            <br><br><br><br><br><br>
            <center>
            Wrong Username or Password, Enter again...<br>
            New User??? Sign Up!!!
            </center>
            <!--<h2>Session Id <%=session.getId()%> </h2>-->
			<form method="post" action="login.jsp">
            	<center>
            	<table border="1" width="30%" cellpadding="5">
            	    <thead>
                	    <tr>
                    	    <th colspan="2">Login</th>
                    	</tr>
                	</thead>
                	<tbody>
                    	<tr>
                    	    <td>Email</td>
                    	    <td><input type="text" name="email" value="" placeholder="eg: username@domain.com" size="25" required/></td>
                    	</tr>
                    	<tr>
                    	    <td>Password</td>
                    	    <td><input type="password" name="pass" value="" size="25" required/></td>
                    	</tr>
                    	<tr>
                            <td colspan="2">
                                <center>
                                    <input type="submit" value="Login" /> 
                        	        <input type="reset" value="Reset" />
                                </center>
                            </td>
                    	</tr>
                    	<tr>
                    	    <td colspan="2">New User??? <a href="signup.html">SignUp Here!!!</a></td>
                    	</tr>
                	</tbody>
            	</table>
            	</center>
        	</form>
            <!--%
            }
            %-->
        </font>
    </body>
</html>
<%
}
%>