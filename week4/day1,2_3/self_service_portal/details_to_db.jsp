<jsp:useBean id="user_details" class="user.UserDetails" scope="session"/>
<jsp:setProperty name="user_details" property="*"/> 

<%
user_details.changePropertiesInDb(); 
%>
<jsp:forward page="details.jsp" />