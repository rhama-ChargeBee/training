<jsp:useBean id="user_signup" class="user.UserSignup" scope="session"/>
<jsp:setProperty name="user_signup" property="*"/> 


<%
if(session.getAttribute("user_id")!=null){
    response.sendRedirect("details.jsp");
    return;
}
%>

<html>
	<head>
		<title>SignUp page</title>
	</head>
<!--user_signup.getSignupConformation(); -->
	<body bgcolor="CORNSILK">
		<font color="BLACK" size=3>
            <br><br><br><br>
            <%
            boolean emailFlag=true;
            boolean passFlag=true;
            boolean dbFlag= false;
            if( !user_signup.getCemail().equals(user_signup.getEmail()) ){
                emailFlag=false;
                %>
                <center>
                    EmailId does not Match!!!
                </center>
                <%
            }else{
                %> 
                    <br> 
                <%
            }
            String emailStr=user_signup.getEmail();
            if(!emailStr.contains("@") && !emailStr.contains(".") && 
                (emailStr.lastIndexOf('.')-emailStr.indexOf('@') ) <1){
                emailFlag=false;
                %>
                <center>
                    Enter valid EmailID!!!
                </center>
                <%
            }else{
                %> 
                    <br> 
                <%
            }
            if( !user_signup.getCpass().equals(user_signup.getPass()) ){
                passFlag=false;
                %>
                <center>
                    Password does not Match!!!
                </center>
                <%
            }else{
                %> 
                    <br> 
                <%
            }
            String passStr=user_signup.getPass();
            if( passStr.length()<7 ){
                passFlag=false;
                %>
                <center>
                    Password Should be a minimum of 8 charecters!!!
                </center>
                <%
            }else{
                %> 
                    <br> 
                <%
            }
            
            if(emailFlag && passFlag){
                dbFlag=user_signup.getSignupConformation();
                if(!dbFlag){
                    %>
                    <center>
                        Email already exists... Please Login...
                    </center>
                    <%

                }else{
                    %> 
                        <br> 
                    <%
                }
            }

            if(dbFlag){
                %>
                <jsp:forward page="login.html" />
                <%
                return;
            }else{
                %>
                <!--<h2>Session Id <%=session.getId()%> </h2>-->
        			<form method="post" action="signup.jsp">
                    	<center>
                    	<table border="1" width="30%" cellpadding="5">
                    	    <thead>
                        	    <tr>
                            	    <th colspan="2">SignUp!!!</th>
                            	</tr>
                        	</thead>
                        	<tbody>
                        	    <tr>
                        	        <td>First Name</td>
                        	        <td><input type="text" name="fname" value="" size=25 placeholder="eg: FirstName"/></td>
                        	    </tr>
                            	<tr>
                            	    <td>Last Name</td>
                            	    <td><input type="text" name="lname" value="" size=25 placeholder="eg: LastName" required/></td>
                            	</tr>
                            	<tr>
                            	    <td>Email</td>
                            	    <td><input type="text" name="email" value="" size=25 placeholder="eg: username@domain.com" required /></td>
                            	</tr>
                            	<tr>
                            	    <td>Confirm Email</td>
                            	    <td><input type="text" name="cemail" value="" size=25/ placeholder="eg: UserName" required ></td>
                            	</tr>
                            	<tr>
                            	    <td>Password</td>
                            	    <td><input type="password" name="pass" value="" size=25 required pattern="^([a-zA-Z0-9@*#]{8,15})$"/></td>
                            	</tr>
                                <tr>
                                    <td>Confirm Password</td>
                                    <td><input type="password" name="cpass" value="" size=25 required pattern="^([a-zA-Z0-9@*#]{8,15})$"/></td>
                                </tr>
                            	<tr>
                            	    <td><input type="submit" value="Submit" /></td>
                            	    <td><input type="reset" value="Reset" /></td>
                            	</tr>
                            	<tr>
                            	    <td colspan="2">Already registered??? <a href="login.html">Login Here</a></td>
                            	</tr>
                        	</tbody>
                    	</table>
                    	</center>
                	</form>
                <%
            }
            %>
        </font>
    </body>