<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

<table>
	<tr>
		<th align="center" colspan="2">Order #${sessionScope.order.getOrderId()}
		<fmt:formatDate value="${sessionScope.order.getOrderDate()}"
			pattern="yyyy/MM/dd hh:mm:ss" /></th>
	</tr>
	<tr>
		<th colspan="2">Payment Details</th>
	</tr>
	<tr>
		<td>Card Type:</td>
		<td><c:out value="${sessionScope.order.getCardType()}" /></td>
	</tr>
	<tr>
		<td>Card Number:</td>
		<td><c:out value="${sessionScope.order.getCreditCard()}" /> * Fake
		number!</td>
	</tr>
	<tr>
		<td>Expiry Date (MM/YYYY):</td>
		<td><c:out value="${sessionScope.order.getExpiryDate()}" /></td>
	</tr>
	<tr>
		<th colspan="2">Billing Address</th>
	</tr>
	<tr>
		<td>First name:</td>
		<td><c:out value="${sessionScope.order.getBillToFirstName()}" /></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><c:out value="${sessionScope.order.getBillToLastName()}" /></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><c:out value="${sessionScope.order.getBillAddress1()}" /></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><c:out value="${sessionScope.order.getBillAddress2()}" /></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><c:out value="${sessionScope.order.getBillCity()}" /></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><c:out value="${sessionScope.order.getBillState()}" /></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><c:out value="${sessionScope.order.getBillZip()}" /></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><c:out value="${sessionScope.order.getBillCountry()}" /></td>
	</tr>
	<tr>
		<th colspan="2">Shipping Address</th>
	</tr>
	<tr>
		<td>First name:</td>
		<td><c:out value="${sessionScope.order.getShipToFirstName()}" /></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><c:out value="${sessionScope.order.getShipToLastName()}" /></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><c:out value="${sessionScope.order.getShipAddress1()}" /></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><c:out value="${sessionScope.order.getShipAddress2()}" /></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><c:out value="${sessionScope.order.getShipCity()}" /></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><c:out value="${sessionScope.order.getShipState()}" /></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><c:out value="${sessionScope.order.getShipZip()}" /></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><c:out value="${sessionScope.order.getShipCountry()}" /></td>
	</tr>
	<tr>
		<td>Courier:</td>
		<td><c:out value="${sessionScope.order.getCourier()}" /></td>
	</tr>
	<tr>
		<td colspan="2">Status: <c:out value="${sessionScope.order.getStatus()}" /></td>
	</tr>
	<tr>
		<td colspan="2">
		<table>
			<tr>
				<th>Item ID</th>
				<th>Description</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Total Cost</th>
			</tr>
			<c:forEach var="lineItem" items="${sessionScope.order.getLineItems()}">
				<tr>
					<td>
						<a href="VeiwItem?itemId=${lineItem.item.itemId}">${lineItem.item.itemId}</a>
					</td>
					<td><c:if test="${lineItem.item != null}">
						${lineItem.item.attribute1}
						${lineItem.item.attribute2}
						${lineItem.item.attribute3}
						${lineItem.item.attribute4}
						${lineItem.item.attribute5}
						${lineItem.item.product.name}
					</c:if>
						<c:if test="${lineItem.item == null}">
							<i>{description unavailable}</i>
						</c:if>
					</td>

					<td>${lineItem.quantity}</td>
					<td><fmt:formatNumber value="${lineItem.unitPrice}"
						pattern="$#,##0.00" /></td>
					<td><fmt:formatNumber value="${lineItem.total}"
						pattern="$#,##0.00" /></td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="5">Total: <fmt:formatNumber
					value="${sessionScope.order.getTotalPrice()}" pattern="$#,##0.00" /></th>
			</tr>
		</table>
		</td>
	</tr>

</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>
