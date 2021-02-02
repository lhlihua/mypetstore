$(document).ready(function () {
    $(".cartItemUpdate").each(function () {
        var cartItemid = $(this).attr("id");
        $("#" + cartItemid + " input").on('blur',function () {
            var quantity = $(this).val();
            $.ajax({
                type     :       "GET",
                url      :       "cartUpdate?cartItemid="+cartItemid+"&quantity="+quantity,
                success  :       function () {
                    alert("You have change the quantity!");
                    var singlePrice = $("#" + cartItemid + " td.singlePrice").text();
                    var index = singlePrice.lastIndexOf("$");
                    singlePrice = singlePrice.substring(index+1,singlePrice.length);
                    var single = parseFloat(singlePrice);
                    var quantityInt = parseInt(quantity);
                    var listPrice = single * quantityInt;
                    console.log(listPrice);
                    var lastlistPrice = $("#" + cartItemid + " td.listTotalPrice").text();
                    index = lastlistPrice.lastIndexOf("$");
                    lastlistPrice = lastlistPrice.substring(index+1,lastlistPrice.length);
                    var lastlistPriceFloat = parseFloat(lastlistPrice);
                    console.log(lastlistPrice);
                    $("#" + cartItemid + " td.listTotalPrice").html("<b>$"+listPrice.toString()+"</b>");
                    var lastTotal = $(".subtotal").text();
                    index = lastTotal.lastIndexOf("$");
                    lastTotal = lastTotal.substring(index+1,lastTotal.length);
                    var lastTotalFloat = parseFloat(lastTotal);
                    console.log(lastTotalFloat);
                    lastTotalFloat = lastTotalFloat - lastlistPriceFloat + listPrice;
                    $(".subtotal").html("<b>$"+lastTotalFloat.toString()+"</b>");
                }
            });
        });
    });
});