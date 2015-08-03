<jsp:useBean id="user_details" class="user.UserDetails" scope="session"/>
<jsp:useBean id="user_login" class="user.UserLogin" scope="session"/>

<!--
<jsp:setProperty name="user_details" property="userId" value="8"/>
 <% out.println("\""+user_login.getId()+"\""); %>/>     <%= user_login.getEmail() %>
<jsp:setProperty name="user_details" property="email" value="rhama@chargebee.com"/>
-->
<%
if(session.getAttribute("user_id")==null){
    response.sendRedirect("login.html");
    return;
}
%>


<html>
	<head>
		<title>Personal Details</title>
	</head>

	<body bgcolor="CORNSILK">
        <% 
user_details.setEmail(user_login.getEmail());
user_details.setUserId(user_login.getId());
try{
    user_details.setProperties();
%>
<p align="right">
    <table border="0" cellpadding="50">
        <tr>
            <td> <a href="logout.jsp">Log out</a></td>
            <td><a href="deactivate.jsp">Deactivate Account</a></td>
        </tr>
    </table>
</p>

		
            <br><br>
            <!--<h2>Session Id <%=session.getId()%> </h2>-->
            	<center>
            	<table border="0" width="30%" cellpadding="5">
            	    <thead>
                	    <tr><font color="DIMGRAY" size=3>
                    	    <th colspan="2" align="left">Personal Details </th>
                            </font>
                    	</tr>
                        <tr>
                            <font color="DIMGRAY">
                                <td><a href="details_edit.jsp"> (Click here to edit) </a></td>
                            </font>
                        </tr>
                	</thead>
                	<tbody>
                        <tr>
                            <td>First Name:</td>
                            <td><%= user_details.getFname() %></td>
                        </tr>
                        <tr>
                            <td>Last Name:</td>
                            <td><%= user_details.getLname() %></td>
                        </tr>
                    	<tr>
                    	    <td>Email:</td>
                    	    <td> <%= user_details.getEmail() %></td>
                    	</tr>
                    	<tr>
                    	    <td>Address:</td>
                    	    <td> <%= user_details.getAddress() %> </td>
                    	</tr>
                	</tbody>
            	</table>
            	</center>
        <%
        }catch(Exception e){
            session.invalidate();
            out.println("Error while logging in... Please try again");
        %>
        <br>
        <a href="login.html">Login!!</a>
        <br>
        <!--
        StackTraceElement[] elem=e.getStackTrace();
        for(int i=0; i<elem.length; i++){
            out.println(elem[i].getFileName()+",");
            out.println(elem[i].getClassName()+",");
            out.println(elem[i].getMethodName()+",");
            out.println(elem[i].getLineNumber());
        }
        -->
        <%
    }
    %>
    </body>
</html>