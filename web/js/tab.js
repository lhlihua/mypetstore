$(document).ready(function() {
    var billToFirstName = $("#billToFirstName").val();
    var billToLastName = $("#billToLastName").val();
    var billAddress1 = $("#billAddress1").val();
    var billAddress2 = $("#billAddress2").val();
    var billCity = $("#billCity").val();
    var billState = $("#billState").val();
    var billZip = $("#billZip").val();
    var billCountry = $("#billCountry").val();

    $.ajax({
        type        :       "GET",
        url         :       "shippingForm?billToFirstName="+
            billToFirstName+ "&billToLastName"+billToLastName+
            "&billAddress1"+billAddress1+"&billAddress2"+billAddress2+"&billCity"+billCity+"&billState"
            +billState+"&billZip"+billZip+"&billCountry"+billCountry,
        success     :       function () {
            $('.tab h3').mouseover(function(){$(this).attr("class","over");})
                .click(function() {
                    $('.tab div').hide();
                    $(this).attr("class","active").next("div").show();
                });
            $('.tab h3').mouseleave(function () {
                $(this).attr("class","origin_lab");
            });
        }
    });
});
// function viewConfirm() {
//     var billToFirstName = $("#billToFirstName").val();
//     var billToLastName = $("#billToLastName").val();
//     var billAddress1 = $("#billAddress1").val();
//     var billAddress2 = $("#billAddress2").val();
//     var billCity = $("#billCity").val();
//     var billState = $("#billState").val();
//     var billZip = $("#billZip").val();
//     var billCountry = $("#billCountry").val();
//
//     var shipToFirstName = $("#shipToFirstName").val();
//     var shipToLastName = $("#shipToLastName").val();
//     var shipAddress1 = $("#shipAddress1").val();
//     var shipAddress2 = $("#shipAddress2").val();
//     var shipCity = $("#shipCity").val();
//     var shipState = $("#shipState").val();
//     var shipZip = $("#shipZip").val();
//     var shipCountry = $("#shipCountry").val();
//
//     window.location.href = "viewConfirmOrder?billToFirstName="+billToFirstName+ "&billToLastName"+billToLastName+
//         "&billAddress1"+billAddress1+"&billAddress2"+billAddress2+"&billCity"+billCity+"&billState"+
//         billState+"&billZip"+billZip+"&billCountry"+billCountry+"&shipToFirstName"+shipToFirstName+"&shipToLastName"+shipToLastName
//         +"&shipAddress1"+shipAddress1+"&shipAddress2"+shipAddress2+"&shipCity"+shipCity+"&shipState"+shipState
//         +"&shipZip"+shipZip+"&shipCountry"+shipCountry;
//     console.log("success");
// }