<h3>Account Information</h3>

<table>
	<tr>
		<td>First name:</td>
		<td><input type="text" name="firstName" value="${sessionScope.firstName}"/></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text" name="lastName" value="${sessionScope.lastName}"/></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text" size="40" name="email" value="${sessionScope.email}"/></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text" name="phone" value="${sessionScope.phone}"/></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text" size="40" name="address1" value="${sessionScope.address1}"/></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text" size="40" name="address2" value="${sessionScope.address2}"/></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text" name="city" value="${sessionScope.city}"/></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" size="4" name="state" value="${sessionScope.state}"/></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text" size="10" name="zip" value="${sessionScope.zip}"/></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text" size="15" name="country" value="${sessionScope.country}"/></td>
	</tr>
</table>

<h3>Profile Information</h3>

<table>
	<tr>
		<td>Language Preference:</td>
		<td>
			<select name="languagePreference">
				<c:forEach var="language" items="${sessionScope.languages}">
					<c:if test="${language == sessionScope.mylanguage}">
						<option selected = "selected">${language}</option>
					</c:if>
					<c:if test="${language != sessionScope.mylanguage}">
						<option>${language}</option>
					</c:if>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
		<td>
			<select name="favouriteCategoryId">
				<c:forEach var="favouriteCategoryId" items="${sessionScope.favouriteCategoryIds}">
					<c:if test="${favouriteCategoryId == sessionScope.myfavouriteCategoryId}">
						<option selected = "selected">${favouriteCategoryId}</option>
					</c:if>
					<c:if test="${favouriteCategoryId != sessionScope.myfavouriteCategoryId}">
						<option>${favouriteCategoryId}</option>
					</c:if>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<td><input type="checkbox" name="listOption" value="1" /></td>
	</tr>
	<tr>
		<td>Enable MyBanner</td>
		<td><input type="checkbox" name="bannerOption" value="1" /></td>
	</tr>
</table>
