$(function () {
    $('#username').on('blur',function () {
        $.ajax({
            type        :   "GET",
            url         :   "UsernameIsExist?username="+this.value,
            success     :   function (data) {
                if(data.msg === "Exist"){
                    $('#isExistInfo').attr("class","errormsg").text("用户名已存在");
                }else{
                    $('#isExistInfo').attr("class","okmsg").text("用户名可用");
                }
            }
        });
    });
});
