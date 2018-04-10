<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ajax重名检测</title>
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript">
    // 检查表单元素的值是否为空
    function check() {
        var myform = document.getElementById("myform");
        for ( var i = 0; i < myform.length; i++) {
            if (myform.elements[i].value == "") {
                alert(myform.elements[i].title + "不能为空");
                myform.elements[i].focus();
                return;
            }
        }
        myform.submit(); // 表单中最后input提交标签用的是button类型，首先不提交表单，在js判断表单项都不为空时 再提交表单。
    }

    $(function(){
        $(":input[name='name']").blur(function(){
            var val = $(this).val();
            val = $.trim(val);

            if(val != ""){
                var url = "${pageContext.request.contextPath }/ajax1";   // 将前端的业务转到后端Servlet来处理。最后Servlet再将结果返回给前端JSP页面
                var args = {"userName":val, "time":new Date()};

                $.post(url, args, function(data){  // URL中处理的结果都保存在data数据中，而data中的格式是Servlet中返回的结果格式，即为html
                    $("#message").html(data);      //将data结果附加到div中
                });
            }
        });
    });
</script>
</head>
<body>
    <form action="ajax1" method="post" id="myform">
        <table align="center">
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" name="name" id="username" title="用户名" />
                    <div id="message"></div>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="pwd" id="userpwd" title="密码" />
                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td><input type="radio" name="sex" value="男" title="性别" />男 <input
                    type="radio" name="sex" value="女" title="性别" />女</td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="text" name="age" title="年龄" />
                </td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" title="Email" />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="button" value="注 册"
                    onclick="check()" /> <input type="reset" value="重 置" /></td>
            </tr>
        </table>
    </form>
</body>
</html>