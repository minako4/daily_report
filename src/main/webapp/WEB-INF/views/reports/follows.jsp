<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="actFollow" value="${ForwardConst.ACT_FOLLOW.getValue()}" />
<c:set var="commFollow" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>フォロー中の従業員 一覧</h2>
        <table id="follows_list">
            <tbody>
                <tr>
                    <th class="follow_name">氏名</th>



                    <th class="report_action">操作</th>
                </tr>
               <c:forEach var="follow" items="${follows}" varStatus="status">

                    <tr class="row${status.count % 2}">
                        <td class="follows_name"><c:out value="${follow.followee.name}" />
                        <td class="follows_name">
                        <form method="POST" action="<c:url value='/?action=${actFollow}&command=${commDel}&id=${follow.followee.id}' />">
                         <input type="hidden" name="destroy" id="id"  value="${follow.followee.id}" />
                                <button type="submit">フォローを外す</button>
                         </form>

                        </td>


                    </tr>
                </c:forEach>
            </tbody>
        </table>

    <div id="pagination">
         <br />
        <c:forEach var="i" begin="1" end="${((reports_count - 1) / maxRow) + 1}" step="1">
            <c:choose>
                <c:when test="${i == page}">
                    <c:out value="${i}" />&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='?action=${actRep}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    <p><a href="<c:url value='?action=${actRep}&command=${commNew}' />">新規日報の登録</a></p>

    </c:param>
</c:import>