<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="viewConfirmOrder" method="post">
	<table>
		<tr>
			<th colspan=2>Payment Details</th>
		</tr>
		<tr>
			<td>Card Type:</td>
			<td>
				<select name="cardType">
					<c:forEach var="creditCardType" items="${sessionScope.creditCardTypes}">
						<option>${creditCardType}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Card Number:</td>
			<td><input type="text" name="creditCard" value="${sessionScope.creditCard}"/> * Use a fake
			number!</td>
		</tr>
		<tr>
			<td>Expiry Date (MM/YYYY):</td>
			<td><input type="text" name="expiryDate" value="${sessionScope.expiryDate}"/></td>
		</tr>
		<tr>
			<th colspan=2>Billing Address</th>
		</tr>

		<tr>
			<td>First name:</td>
			<td><input type="text" name="billToFirstName" value="${sessionScope.billToFirstName}"/></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input type="text" name="billToLastName" value="${sessionScope.billToLastName}"/></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><input type="text" size="40" name="billAddress1" value="${sessionScope.billAddress1}"/></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><input type="text" size="40" name="billAddress2" value="${sessionScope.billAddress2}"/></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" name="billCity" value="${sessionScope.billCity}"/></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><input type="text" size="4" name="billState" value="${sessionScope.billState}"/></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><input type="text" size="10" name="billZip" value="${sessionScope.billZip}"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><input type="text" size="15" name="billCountry" value="${sessionScope.billCountry}"/></td>
		</tr>

		<tr>
			<td colspan=2><input type="checkbox" name="shippingAddressRequired" value="1"/>
			Ship to different address...</td>
		</tr>

	</table>

	<input type="submit" name="newOrder" value="Continue" />

	</form></div>

<%@ include file="../common/IncludeBottom.jsp"%>