<%@ include file="../common/IncludeTop.jsp"%>
<div id="Catalog">
	<form action="login" method="post">
		<p>Please enter your username and password.</p>
<%--		<p>Username:<input type="text" name="username" , value="j2ee"/><br/>--%>
<%--			Password:<input type = "text" name="password" value="j2ee"/></p>--%>
		<p>
			Username:<input type="text" name="username" id = "username" value="${sessionScope.inputusername}"/><br />
			Password:<input type="password" name="password" value="${sessionScope.inputpassword}"/><br />
			<span id = "isExistInfo"></span><br />
			<c:if test="${sessionScope.times>2}">
				checktext:<input type="text" name = "imagetext"/><br />
				<a href="login">
					<img id="Img" src="valiImage" onclick="changeValilmage(this)"  />
				</a>
			</c:if>
		</p>

		<font color="red">${sessionScope.messageSignon}</font>
		<input type="submit" name="signon" value="Login" />
	</form>
		Need a user name and password?
	<a class="Button" href="viewNewAccountForm">Register Now!</a>
</div>
<script src = "js/signOn.js"></script>

<%@ include file="../common/IncludeBottom.jsp"%>

