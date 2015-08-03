<jsp:useBean id="user_details" class="user.UserDetails" scope="session"/>

<%
if(session.getAttribute("user_id")==null){
    response.sendRedirect("login.html");
}
%>
<html>
	<head>
		<title>Personal Details</title>
	</head>

	<body bgcolor="CORNSILK">
<p align="right">
    <table border="0" cellpadding="50">
        <tr>
            <td> <a href="logout.jsp">Log out</a></td>
            <td><a href="deactivate.jsp">Deactivate Account</a></td>
        </tr>
    </table>
</p>

		<font color="BLACK" size=3>
            <br><br>
            <!--<h2>Session Id <%=session.getId()%> </h2>-->
            <form method="post" action="details_to_db.jsp" name="details_form">
            	<center>
            	<table border="0" width="30%" cellpadding="5">
            	    <thead>
                	    <tr>
                    	    <th colspan="2" align="left">Personal Details </th>
                    	</tr>
                	</thead>
                	<tbody>
                        <tr>
                            <td>First Name:</td>
                            <td><input type="text" name="fname" size=25 value=<%= user_details.getFname() %> /> </td>
                        </tr>
                        <tr>
                            <td>Last Name:</td>
                            <td><input type="text" name="lname" size=25 value=<%= user_details.getLname() %> required /> </td>
                        </tr>
                    	<tr>
                    	    <td>Email:</td>
                    	    <td> <%= user_details.getEmail() %></td>
                    	</tr>
                    	<tr>
                    	    <td>Address:</td>
                    	    <td><input type="textarea" name="address" rows=20 cols=50 value=<%= user_details.getAddress()%> 
                                />
                                <!--<textarea form="details_form" rows=4 cols=25 name="address"><%= user_details.getAddress()%> </textarea> -->
                            </td>
                    	</tr>

                        <tr>
                            <td><input type="submit" value="Done" /></td>
                            <td><input type="reset" value="Reset" /></td>
                        </tr>
                	</tbody>
            	</table>
            	</center>
            </form>
        </font>
    </body>