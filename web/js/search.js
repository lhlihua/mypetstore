$(document).ready(function () {
    $("#SearchInput").autocomplete({
        source:function (request , response) {
            $.ajax({
                type    :   "post",
                url     :   "autoComplete?keyword="+$("#SearchInput").val(),
                success :   function (jsonData) {
                    var data = eval(jsonData);
                    var context = "";
                    response($.map(data,function (item) {
                        var name = item.name;
                        context = context + "<div class='append_div'><span class='append_span'" +
                            " onclick=select('"+name+"')>"+name+"</span></div>";
                    }));
                    if(context != ""){
                        $(".showResults").html("");
                        $(".showResults").append(context);
                        $(".showResults").show();
                    }else{
                        $(".showResults").hide();
                    }
                }
            });
        }
    });

    $("body")._mouseUp(function () {
        $(".showResults").hide();
    })
    
});

function select(name) {
    console.log("Enter");
    $("#SearchInput").val(name);
    $(".showResults").hide();
}