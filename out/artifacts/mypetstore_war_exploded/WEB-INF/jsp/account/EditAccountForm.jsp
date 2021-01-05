<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="editAccount" method="post">

		<h3>User Information</h3>
		<table>
			<tr>
				<td>User ID:</td>
				<td>${sessionScope.username}</td>
			</tr>
			<tr>
				<td>New password:</td>
				<td><input type="text" name="password1" /></td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="text" name="repeatedPassword1" /></td>
			</tr>
			<tr>
				<td>${sessionScope.message}</td>
			</tr>
		</table>
		<%@ include file="IncludeAccountFields.jsp"%>

	<input type="submit" name="editAccount" value="Save Account Information" />

	</form>
	<a href="viewListOrder">My Orders</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
