<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="login" method="post">
		<p>Please enter your username and password.</p>
<%--		<p>Username:<input type="text" name="username" , value="j2ee"/><br/>--%>
<%--			Password:<input type = "text" name="password" value="j2ee"/></p>--%>
		<p>

			<c:if test="${sessionScope.inputpassword!=null&&sessionScope.inputusername!=null}">
				Username:<input type="text" name="username" value="${sessionScope.inputusername}"/><br />
				Password:<input type="password" name="password" value="${sessionScope.inputpassword}"/><br />
			</c:if>

			<c:if test="${sessionScope.inputpassword==null&&sessionScope.inputusername==null}">
				Username:<input type="text" name="username" /><br />
				Password:<input type="password" name="password" /><br />
			</c:if>
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

<%@ include file="../common/IncludeBottom.jsp"%>

