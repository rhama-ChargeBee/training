<%@ page import="java.sql.ResultSet"%>
<%  response.addCookie(new Cookie("page", "fromSearch"));%>
<html>
    <head>
        <title>Contacts</title>
        <link rel="stylesheet" type="text/css" href="custom.css">
    </head>

    <body>
        <div class="heading">
            <hr>
            <ul>
                <li><a href="logout">Logout</a></li>
                <li><a href="deactivate">Deactivate Account</a></li>
            </ul>
            <hr>
        </div>
        <div>
            <center>
                <a href="userdetails">Back to Home</a>
            </center>
            <center>
                <a href="newcontact.jsp">Create a New Contact</a>
            </center>
        </div>
        <center>
            <form id="filter" method="get" action="usercontacts">
                
                <table class="noborder_table">
                    <thead>
                        <tr>
                            <td><input type="text" name="searchbox" size=15 style="height:34px; width:100px" /></td>
                            <td >Search by:</td>
                            <td>
                                <select class="select" name="option" form="filter" required>
                                    <option value="name">Name</option>
                                    <option value="number">Number</option>
                                </select>
                            </td>
                            <td>
                                <input style="height:40px" name="submit_button" class="submit_button" type="submit" value="Search" />
                            </td>
                        </tr>
                    </thead>
                </table>
            </form>
                <table class="contacts_table">
                    <tbody>
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Mobile</th>
                            <th>Work</th>
                            <th>Home</th>
                        </tr>
                        <% ResultSet rs= (ResultSet) request.getAttribute("table");
                        boolean flag=true;
                        boolean trFlag=false;
                        int i=0, count=3;
                        while(rs.next()){ 
                            flag=false;
                            if(i!=rs.getInt("contact_id")){
                                flag=true;
                            }
                            if(flag){
                                i=rs.getInt("contact_id");
                                if(trFlag){
                                    System.out.println("count final:"+count);
                                    for(int j=0;j<count; j++){
                                        out.println("<td></td>");
                                    }
                                    out.println("</tr>");
                                    count=3;
                                }
                                trFlag=true;
                            %>
                                <input type="hidden" name="contact_id" value=<% out.println("\""+rs.getInt("contact_id")+"\""); %>>
                            <tr style="border:none">
                                <td> <%= rs.getString("fname")%> <%= rs.getString("lname") %></td>
                                <td> <% if(rs.getString("address_line1")!=null){out.println(rs.getString("address_line1")+"  ");}
                                        if(rs.getString("address_line2")!=null){out.println(rs.getString("address_line2")+"  ");}
                                        if(rs.getString("city")!=null){out.println(rs.getString("city")+"  ");}
                                        if(rs.getString("state")!=null){out.println(rs.getString("state")+"  ");}
                                        if(rs.getString("country")!=null){out.println(rs.getString("country")+"  ");}
                                        if(rs.getString("zip")!=null){out.println(rs.getString("zip")+" ");}    
                                    %>
                            <% }
                            if(trFlag){ 
                                try{if(rs.getString("number_type").equals("Mobile")){
                                %>
                                    <td> 
                                        <% out.println((rs.getString("phone_number")==null)? "" :  rs.getString("phone_number"));
                                        count=count-1;
                                        System.out.println("count Mobile"+count); 
                                        %>
                                    </td>
                                <% }
                                }catch(NullPointerException e){
                                    out.println("<td></td>");
                                    count=count-1;
                                    System.out.println("count catch"+count); 
                                }
                                try{ if(rs.getString("number_type").equals("Work")){
                                %>
                                    <td> <% out.println((rs.getString("phone_number")==null)? "" :  rs.getString("phone_number"));
                                    count=count-1;  
                                    System.out.println("count Work"+count); 
                                    %></td>
                                <% }
                                }catch(NullPointerException e){
                                    out.println("<td></td>");
                                    count=count-1;
                                    System.out.println("count catch"+count); 
                                }
                                try{if(rs.getString("number_type").equals("Home")){
                                %>
                                    <td> <% out.println((rs.getString("phone_number")==null)? "" :  rs.getString("phone_number"));
                                    count=count-1;  
                                    System.out.println("count Home"+count); 
                                    %></td>
                                <% }
                                }catch(NullPointerException e){
                                    out.println("<td></td>");
                                    count=count-1;
                                    System.out.println("count catch"+count); 
                                } 
                            }
                            
                        } %>
                    </tbody>
                </table>
            </center>
    </body>
</html>