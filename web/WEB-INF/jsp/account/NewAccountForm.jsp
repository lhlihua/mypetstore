<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="registerNewAccount" method="post">

		<h3>User Information</h3>

		<table>
			<tr>
				<td>User ID:</td>
				<td>
					<input type="text" name="username" id = "username"/><span id = "isExistInfo"></span>
				</td>
			</tr>
			<tr>
				<td>New password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="password" name="repeatedPassword" /></td>
			</tr>
			<tr>
				<td><font color="red">${sessionScope.message}</font></td>
			</tr>
		</table>

		<%@ include file="IncludeAccountFields.jsp"%>

		<input type="submit" name="newAccount" value="Save Account Information" />

	</form>
</div>
<script src = "js/signOn.js"></script>

<%@ include file="../common/IncludeBottom.jsp"%>