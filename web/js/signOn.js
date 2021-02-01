var xhr;
function checkUsername() {
    var username = document.getElementById('username').value;
    console.log(username);
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = process;
    xhr.open("GET","UsernameIsExist?username="+username,true);
    //不使用post，即send null
    xhr.send(null);
}
//处理函数
function process(){
    //当异步处理完成时
    if(xhr.readyState == 4){
        //服务器处理完成
        if(xhr.status == 200){
            var responseInfo = xhr.responseText;
            var msg = document.getElementById("isExistInfo");
            console.log(msg);
            //如果用户名存在
            if(responseInfo === "Not Exist"){
                msg.className = "okmsg";
                msg.innerText = "用户名可用";
            }else{
                msg.className = "errormsg";
                msg.innerText = "用户名已存在";
            }
        }
    }
}
//安装事件监听器
username.addEventListener('blur',checkUsername,false);
