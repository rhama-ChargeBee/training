<%@ page import="java.util.HashMap,java.util.ArrayList" %>
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
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Mobile</th>
                            <th>Work</th>
                            <th>Home</th>
                        </tr>
                        <% 
                        HashMap<String, ArrayList<String>> result= (HashMap<String, ArrayList<String>>) request.getAttribute("table");
                        boolean flag=true;
                        boolean trFlag=false;
                        int i=0, count=3;
                        for(int k=0;k<result.get("contact_id").size(); k++){
                            flag=false;
                            if(i!=Integer.parseInt(result.get("contact_id").get(k))){
                                flag=true;
                            }
                            if(flag){
                                i=Integer.parseInt(result.get("contact_id").get(k));
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
                                
                            <tr style="border:none">
                                <td> <%= result.get("fname").get(k)%> <%= result.get("lname").get(k) %></td>
                                <td> 
                            <% 
                                String[] address= new String[]{"address_line1", "address_line2", "city", "state", "country", "zip"};
                                for(int l=0;l<address.length;l++){
                                    if(result.get(address[l]).get(k)!=null){out.println(result.get(address[l]).get(k)+"  ");}
                                }
                            %>
                                </td>
                            <% }
                            if(trFlag){ 
                                try{if(result.get("number_type").get(k).equals("Mobile")){
                                %>
                                    <td> 
                                        <% out.println((result.get("phone_number").get(k)==null)? "" :  result.get("phone_number").get(k));
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
                                try{ if(result.get("number_type").get(k).equals("Work")){
                                %>
                                    <td> <% out.println((result.get("phone_number").get(k)==null)? "" :  result.get("phone_number").get(k));
                                    count=count-1;  
                                    System.out.println("count Work"+count); 
                                    %></td>
                                <% }
                                }catch(NullPointerException e){
                                    out.println("<td></td>");
                                    count=count-1;
                                    System.out.println("count catch"+count); 
                                }
                                try{if(result.get("number_type").get(k).equals("Home")){
                                %>
                                    <td> <% out.println((result.get("phone_number").get(k)==null)? "" :  result.get("phone_number").get(k));
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
                </table>
            </center>
    </body>
</html>