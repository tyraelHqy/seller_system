<html>

<#include "../common/header.ftl">


<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-center">
                卖家后台管理验证系统
            </h3>
        </div>
    </div>
    <br>
    <br>
    <br>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" class="form-horizontal" method="get" action="/sell/seller/login" >

                <div class="form-group">
                    <label  class="col-sm-2 control-label">Openid</label>
                    <div class="col-sm-10">
                        <input type="text" name="openid" class="form-control" id="inputPassword3" placeholder="openid" required autofocus/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary btn-default">验证</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="/sell/login/register" type="button" class="btn btn-link btn-primary">注册</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>