<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>购物车</title>
        <%-- 静态包含base标签，css样式，jQuery文件--%>
        <%@include file="/pages/common/head.jsp" %>
        <script type="text/javascript">
            $(function () {
                $("a.deleteItem").click(function () {
                    //给删除绑定单击事件
                    return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() +"】吗(＃°Д°)？")
                })
                $("#clearCart").click(function () {
                    //给清空购物车绑定单击事件
                    if(confirm("你确定要删除清空购物车吗(＃°Д°)？")){
                        if(confirm("你真的确定要清空购物车吗ಥ_ಥ")){
                            if(confirm("好吧，算你狠(╬▔皿▔)╯")){
                                return true;
                            }else{
                                alert("你真好(❤ ω ❤)");
                                return false;
                            }

                        }
                    }
                })
                $(".updateCount").change(function () {
                    var name = $(this).parent().parent().find("td:first").text();
                    var id = $(this).attr("bookId");
                    var count = this.value;
                    if(confirm("你确定要将【" + name + "】数量修改为" + count + "吗？")){
                        //发起请求。给服务器保存修改

                        location.href = "http://" + document.domain +":8080/FuckBook/cartServlet?action=updateCount&count="+count+"&id="+id;
                        // location.href = "http://localhost:8080/FuckBook/cartServlet?action=updateCount&count="+count+"&id="+id;
                    }else{
                        // defaultValue属性是表单项Dom对象的属性。它表示默认的value值。
                        this.value = this.defaultValue;
                    }
                });
            })
        </script>
    </head>
    <body>

        <div id="header">
            <img class="logo_img" alt="" src="static/img/logo.gif">
            <span class="wel_word">购物车</span>

            <%-- 静态包含，登陆成功之后的菜单 --%>
            <%@include file="/pages/common/login_success_menu.jsp" %>

        </div>

        <div id="main">

            <table>
                <tr>
                    <td>商品名称</td>
                    <td>数量</td>
                    <td>单价</td>
                    <td>金额</td>
                    <td>操作</td>
                </tr>
                <c:if test="${empty sessionScope.cart.items}">
                    <%--如果购物车为空--%>
                    <tr>
                        <td colspan="5"><a href="index.jsp">穷逼，你的购物车竟然是空的，快滚去添加几件商品(づ￣ 3￣)づ</a></td>
                    </tr>
                </c:if>
                <c:if test="${not empty sessionScope.cart.items}">
                    <%--如果购物车不为空--%>
                    <c:forEach items="${sessionScope.cart.items}" var="entry">
                        <tr>
                            <td>${entry.value.name}</td>
                            <td>
                                <input class="updateCount" type="text" style="width: 50px;"
                                       bookId = "${entry.value.id}"
                                       value="${entry.value.count}">
                            </td>
                            <td>${entry.value.price}</td>
                            <td>${entry.value.totalPrice}</td>
                            <td> <a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                        </tr>
                    </c:forEach>
                </c:if>


            </table>

            <%--如果购物车非空--%>
            <c:if test="${not empty sessionScope.cart.items}">
                <div class="cart_info">
                    <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
                    <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
                    <span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
                    <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
                </div>
            </c:if>


        </div>

        <%-- 静态包含页脚内容 --%>
        <%@include file="/pages/common/footer.jsp" %>
    </body>
</html>