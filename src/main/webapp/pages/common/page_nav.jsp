<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/2/15
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%--处理分页条的代码复用--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">

    <%--大于首页才显示--%>
    <c:if test="${requestScope.page.pageNo > 1 && requestScope.page.pageNo <= requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <%--            &lt;%&ndash;只有当当前页不为第一页时才显示前一页&ndash;%&gt;--%>
    <%--            <c:if test="${ requestScope.page.pageNo-1 > 0 && requestScope.page.pageNo <= requestScope.page.pageTotal}">--%>
    <%--                <a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">${ requestScope.page.pageNo-1 }</a>--%>
    <%--            </c:if>--%>

    <%--            【${ requestScope.page.pageNo }】--%>

    <%--            &lt;%&ndash;只有当当前页不为最后一页时才显示后一页&ndash;%&gt;--%>
    <%--            <c:if test="${ requestScope.page.pageNo < requestScope.page.pageTotal && requestScope.page.pageNo > 0}">--%>
    <%--                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">${ requestScope.page.pageNo+1 }</a>--%>
    <%--            </c:if>--%>
    <%--&lt;%&ndash;            上面是自己写的总页码只有五页而且同时显示3页的情况&ndash;%&gt;--%>
    <%--            &lt;%&ndash;小于末页才显示&ndash;%&gt;--%>

    <%--            处理页码开始--%>
    <%--             总页码小于5的情况 页码范围是 1-- 总页码--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i == requestScope.page.pageNo}">
                    [${i}]
                </c:if>
                <c:if test="${i != requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%-- 总页码大于5的情况  --%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo <=3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3}">
                    <c:forEach begin="${requestScope.page.pageTotal - 4}" end="${requestScope.page.pageTotal}"
                               var="i">
                        <c:if test="${i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}"
                               var="i">
                        <c:if test="${i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <%--处理页码结束--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal && requestScope.page.pageNo > 0}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">

        $(function () {
            //跳到指定的页码
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                var pageTotal = ${requestScope.page.pageTotal};

                //在前端页面进行数据校验可能会被用户在地址栏输入地址直接跳转从而导致校验失败，所以要在后端代码进行校验
                if (pageTotal < pageNo || pageNo < 1) {
                    alert("请输入正确页码");
                } else {
                    // javascript语言中提供了一个Location地址栏对象
                    // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
                    // href属性可读，可写
                    <%--alert(${pageScope.basePath});--%>
                    <%--alert(${requestScope.page.url});--%>
                    location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
                }
            });
        });

    </script>
</div>