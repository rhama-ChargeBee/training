<%response.addCookie(new Cookie("page", "fromNewContacts"));%>
<html>
    <head>
        <title>New Contact</title>
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
                                <input type="text" name="fname" size=25 placeholder="First Name" />
                            </td>
                            <td><input type="text" name="lname" size=25 placeholder="Last Name" required/></td>
                        </tr>
                        <tr>
                            <td class="label">Address:</td>
                            <td colspan="2"><input type="text" name="address_line1" size=59 placeholder="Address Line 1" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="2"><input type="text" name="address_line2" size=59 placeholder="Address Line 2" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="text" name="city" size=25  placeholder="City" /></td>
                            <td><input type="text" name="state" size=25 placeholder="State" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="text" name="zip" size=25 placeholder="zip" /></td>
                            <td><input type="text" name="country" size=25 placeholder="Country" /></td>
                        </tr>
                        <tr>
                            <td class="label">Phone(Mobile):</td>
                            <td colspan="2"><input type="text" name="mobile" size=59 placeholder="Mobile" /></td>
                        </tr>
                        <tr>
                            <td class="label">Phone(Work):</td>
                            <td colspan="2"><input type="text" name="work" size=59 placeholder="Work" /></td>
                        </tr>
                        <tr>
                            <td class="label">Phone(Home):</td>
                            <td colspan="2"><input type="text" name="home" size=59 placeholder="Home" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td align="center"><input name="submit_button" class="submit_button" type="submit" value="Save New Contact" /></td>
                            <td align="center"><a href="userdetails">Cancel</a></td>
                        </tr>
                    </tbody>
                </table>
                </center>
            </form>
    </body>
    </html>