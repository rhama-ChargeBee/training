
<html>
	<head>
		<title>User Details</title>
		<link rel="stylesheet" type="text/css" href="custom.css">
	</head>

	<body>
		<div
		class="heading">
		<hr>
		<ul>
			<li><a href="logout">Logout</a></li>
				<li><a href="deactivate">Deactivate Account</a></li>
				</ul>
				<hr>
			</div>
			<font>
				<br><br><br>
				<center>
					<table class="noborder_table">
						<thead>
							<tr>
								<th class="space_portal1" colspan="2">Welcome to the Contacts Portal</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="label">Name:</td>
								<td class="value, fixed">
									<%= request.getAttribute("fname") %> <%= request.getAttribute("lname")%>
								</td> 
							</tr> 
							<tr> 
								<td class="label">Email:</td> 
								<td class="value, fixed"><%= request.getAttribute("email") %></td> 
							</tr>
							<tr> 
								<td>
									<button onclick="redirectToContact()" name="submit_button" class="submit_button" type="button">View Contacts</button> 
								</td>
								
								<td> 
									 <button onclick="redirectToDetailsEdit()" name="submit_button" class="submit_button" type="button">Edit Details</button>
								</td> 
							</tr> 
						</tbody> 
					</table> 
				</center> 
		</font> 
		<script>
			function redirectToDetailsEdit() {
				document.cookie="page=fromDetails";
    			window.location="userdetails";
			}
			function redirectToContact() {
				document.cookie="page=fromDetails";
    			window.location="usercontacts";
			}
		</script>
	</body> 
</html>
