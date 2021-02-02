<%@ include file="../common/IncludeTop.jsp"%>

<div class = "tab">
	<h3 class = "active" id = "First_lab"><span>First</span></h3>
<div class="Catalog" id = "tab_1">
	<form action="viewConfirmOrder" method="post" >
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
			<td><input type="text" name="billToFirstName" id = "billToFirstName" value="${sessionScope.billToFirstName}"/></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input type="text" name="billToLastName" id = "billToLastName" value="${sessionScope.billToLastName}"/></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><input type="text" size="40" name="billAddress1" id = "billAddress1" value="${sessionScope.billAddress1}"/></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><input type="text" size="40" name="billAddress2" id = "billAddress2" value="${sessionScope.billAddress2}"/></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" name="billCity" id = "billCity" value="${sessionScope.billCity}"/></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><input type="text" size="4" name="billState" id = "billState" value="${sessionScope.billState}"/></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><input type="text" size="10" name="billZip" id = "billZip" value="${sessionScope.billZip}"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><input type="text" size="15" name="billCountry" id = "billCountry" value="${sessionScope.billCountry}"/></td>
		</tr>

	</table>
	</form>
</div>
	<h3 id = "Second_lab"><span>Second</span></h3>
<div class="Catalog" id = "tab_2">
		<form action="viewConfirmOrder" method="post">
			<table>
				<tr>
					<th colspan=2>Shipping Address</th>
				</tr>

				<tr>
					<td>First name:</td>
					<td><input type="text" name="shipToFirstName" id = "shipToFirstName" value="${sessionScope.billToFirstName}"/></td>
				</tr>
				<tr>
					<td>Last name:</td>
					<td><input type="text" name="shipToLastName" value="${sessionScope.billToLastName}"/></td>
				</tr>
				<tr>
					<td>Address 1:</td>
					<td><input type="text" size="40" name="shipAddress1" value="${sessionScope.billAddress1}"/></td>
				</tr>
				<tr>
					<td>Address 2:</td>
					<td><input type="text" size="40" name="shipAddress2" value="${sessionScope.billAddress2}"/></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><input type="text" name="shipCity" value="${sessionScope.billCity}"/></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><input type="text" size="4" name="shipState" value="${sessionScope.billState}"/></td>
				</tr>
				<tr>
					<td>Zip:</td>
					<td><input type="text" size="10" name="shipZip" value="${sessionScope.billZip}"/></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><input type="text" size="15" name="shipCountry" value="${sessionScope.billCountry}"/></td>
				</tr>


			</table>

			<input type="submit" name="newOrder" value="Continue" id = "newOrder" />

		</form>
	</div>
</div>
<script src = "js/tab.js"></script>
<%@ include file="../common/IncludeBottom.jsp"%>