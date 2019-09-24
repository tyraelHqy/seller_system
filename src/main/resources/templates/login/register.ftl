<html>

<#include "../common/header.ftl">


<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-center">
                卖家后台管理注册系统
            </h3>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" method="get" action="/sell/login/registerInfo">
                <div class="form-group">
                    <label for="exampleInputEmail1">Username</label><input name="username" type="text" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label><input name="password" type="password" class="form-control" id="exampleInputPassword1" />
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Openid</label><input name="openid" type="text" class="form-control"  />
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" />Check me out</label>
                </div> <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</div>


</body>
</html>