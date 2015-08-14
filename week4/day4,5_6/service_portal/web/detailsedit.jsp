
<html>
    <head>
        <title>Edit Details</title>
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
            <form method="post" action="setdetails">
                <center>
                <table class="noborder_table">
                    <thead>
                        <tr>
                            <td></td>
                            <th id="reg_to" colspan="2">Register to</th>
                        </tr>

                        <tr>
                            <td></td>
                            <th class="space_portal1" colspan="2">Space Portal</th>
                        </tr>

                    </thead>
                    <tbody>
                        <tr>
                            <td class="label">Name:</td>
                            <td>
                                <input type="text" name="fname" size=25 value=<% out.println("\""+(String)request.getAttribute("fname")+"\""); %> placeholder="First Name" />
                            </td>
                            <td><input type="text" name="lname" size=25 value=<% out.println("\""+(String)request.getAttribute("lname")+"\""); %> placeholder="Last Name" required/></td>
                        </tr>
                        <tr>
                            <td class="label">Email:</td>
                            <td colspan="2"><input type="email" name="email" size=59 value=<% out.println("\""+(String)request.getAttribute("email")+"\""); %> placeholder="Email" disabled required /></td>
                        </tr>
                        <tr>
                            <td class="label">Address:</td>
                            <td colspan="2"><input type="text" name="address_line1" size=59 value=<% out.println("\""+(String)request.getAttribute("address")+"\""); %> placeholder="Address" required /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td align="center"><input name="submit_button" class="submit_button" type="submit" value="Save Details" /></td>
                            <td align="center"><input class="reset_button" type="reset" value="Reset Details" /></td>
                        </tr>
                    </tbody>
                </table>
                </center>
            </form>
    </body>
    </html>