<%@ page import="user.ContactsBean" %>
<% ContactsBean contact= (ContactsBean)request.getAttribute("contact_details"); %>
<% session.setAttribute("page",new String("fromEditContacts"));
System.out.println("contact_id: "+(String)request.getAttribute("contact_id")); %>
<html>
    <head>
        <title>Contact</title>
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
            <form method="post" action="usercontacts">
                <input type="hidden" name="contact_id" size=25 value=<% out.println("\""+(String)request.getAttribute("contact_id")+"\""); %> />
                <center>
                <table class="noborder_table">
                    <thead>
                        <tr>
                            <td></td>
                            <th class="space_portal1" colspan="2">Contact</th>
                        </tr>

                    </thead>
                    <tbody>
                        <tr>
                            <td class="label">Name:</td>
                            <td>
                                <input type="text" name="fname" size=25 value=<% out.println("\""+contact.getFname()+"\""); %> placeholder="First Name" />
                            </td>
                            <td><input type="text" name="lname" size=25 value=<% out.println("\""+contact.getLname()+"\""); %> placeholder="Last Name" required/></td>
                        </tr>
                        <tr>
                            <td class="label">Address:</td>
                            <td colspan="2"><input type="text" name="address_line1" size=59 value=<% out.println("\""+contact.getAddressLine1()+"\""); %> placeholder="Address Line 1" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="2"><input type="text" name="address_line2" size=59 value=<% out.println("\""+contact.getAddressLine2()+"\""); %> placeholder="Address Line 2" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="text" name="city" size=25 value=<% out.println("\""+contact.getCity()+"\""); %> placeholder="City" /></td>
                            <td><input type="text" name="state" size=25 value=<% out.println("\""+contact.getState()+"\""); %> placeholder="State" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="text" name="zip" size=25 value=<% out.println("\""+contact.getZip()+"\""); %> placeholder="zip" /></td>
                            <td><input type="text" name="country" size=25 value=<% out.println("\""+contact.getCountry()+"\""); %> placeholder="Country" /></td>
                        </tr>
                        <tr>
                            <td class="label">Phone(Mobile):</td>
                            <td colspan="2"><input type="text" name="mobile" size=59 placeholder="Mobile" value=<% out.println("\""+contact.getMobile()+"\""); %> /></td>
                        </tr>
                        <tr>
                            <td class="label">Phone(Work):</td>
                            <td colspan="2"><input type="text" name="work" size=59 placeholder="Work" value=<% out.println("\""+contact.getWork()+"\""); %>/></td>
                        </tr>
                        <tr>
                            <td class="label">Phone(Home):</td>
                            <td colspan="2"><input type="text" name="home" size=59 placeholder="Home" value=<% out.println("\""+contact.getHome()+"\""); %> /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td align="center"><input name="submit_button" class="submit_button" type="submit" value="Save New Contact" /></td>
                            <td align="center"><input class="reset_button" type="reset" value="Reset Details" /></td>
                        </tr>
                    </tbody>
                </table>
                </center>
            </form>
    </body>
    </html>