<html>

<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <!-- 边栏sidebar -->
    <#include "../common/nav.ftl">

    <!-- 主要内容 -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed table-hover">
                        <thead>
                        <tr>
                            <th style='text-align: center;'>
                                商品Id
                            </th>
                            <th style='text-align: center;'>
                                名称
                            </th>
                            <th style='text-align: center;'>
                                图片
                            </th>
                            <th style='text-align: center;'>
                                单价
                            </th>
                            <th style='text-align: center;'>
                                库存
                            </th>
                            <th style='text-align: center;'>
                                描述
                            </th>
                            <th style='text-align: center;'>
                                类目
                            </th>
                            <th style='text-align: center;'>
                                创建时间
                            </th>
                            <th style='text-align: center;'>
                                修改时间
                            </th>

                            <th colspan="2" style='text-align: center;'>
                                操作
                            </th>

                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as productInfo>
                            <tr>
                                <td>
                                    ${productInfo.productId}
                                </td>
                                <td>
                                    ${productInfo.productName}
                                </td>
                                <td>
                                    <img height="100" width="100" src="${productInfo.productIcon}" alt="找不到该图片">
                                </td>
                                <td>
                                    ${productInfo.productPrice}
                                </td>
                                <td>
                                    ${productInfo.productStock}
                                </td>
                                <td>
                                    ${productInfo.productDescription}
                                </td>
                                <td>
                                    ${productInfo.categoryType}
                                </td>
                                <td>
                                    ${productInfo.createTime}
                                </td>
                                <td>
                                    ${productInfo.updateTime}
                                </td>
                                <td>
                                    <a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a>
                                </td>
                                <td>
                                    <#if productInfo.getProductStatusEnum().message == "在架">
                                        <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                    <#else>
                                        <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <!-- 分页 -->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">

                        <li >
                            <a href="/sell/seller/product/list?page=1&size=${size}">首页</a>
                        </li>
                        <#if currentPage lte 1>
                            <li class="disabled">
                                <a href="#">上一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/product/list?page=${currentPage-1}&size=${size}">上一页</a>
                            </li>
                        </#if>

                        <#--                <#list 1..orderDTOPage.getTotalPages() as index>-->
                        <#--                    <#if currentPage == index>-->
                        <#--                        <li class="disabled"><a href="#">${index}</a></li>-->
                        <#--                    <#else>-->
                        <#--                        <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>-->
                        <#--                    </#if>-->
                        <#--                </#list>-->

                        <#-- 列表 -->
                        <#-- 期望的页码标签长度 -->
                        <#assign expectSize = 7>
                        <#-- 期望的当前页码标签前展示的页码标签数量 -->
                        <#assign expectBefore = 2>
                        <#-- 初始化长度计数, 用于统计实际的页码标签个数 -->
                        <#assign listSize = 0>
                        <#-- 遍历 -->
                        <#list 1..productInfoPage.getTotalPages() as index>
                        <#-- 页码标签长度小于期望值才继续 -->
                            <#if listSize lt expectSize>
                            <#-- 索引 大于 (当前页码 - 当前页码前的个数), 也就是说当前标签前只有expectBefore个页码标签 -->
                                <#if index gte (currentPage - expectBefore)>
                                    <#assign listSize = listSize + 1><#-- 每渲染一个页码标签, 计数加一 -->
                                    <!-- 渲染页码标签 -->
                                    <#if index == currentPage>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                                    </#if>

                                <#else>
                                <#-- 处理页码末尾页码数不足的情况, 其实就是保持页码标签数量一直等于expectSize, 是对上面条件生成的便签数不足时的补充 -->
                                    <#if index gt (productInfoPage.getTotalPages() - expectSize)>
                                        <#assign listSize = listSize + 1><#-- 每渲染一个页码标签, 计数加一 -->
                                        <!-- 渲染页码标签, 一段重复代码 -->
                                        <#if index == currentPage>
                                            <li class="disabled"><a href="#">${index}</a></li>
                                        <#else>
                                            <li><a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                                        </#if>
                                    </#if>
                                </#if>
                            </#if>
                        </#list>

                        <#if currentPage gte productInfoPage.getTotalPages()>
                            <li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/product/list?page=${currentPage+1}&size=${size}">下一页</a>
                            </li>
                        </#if>
                        <li >
                            <a href="/sell/seller/product/list?page=${productInfoPage.getTotalPages()}&size=${size}">尾页</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
