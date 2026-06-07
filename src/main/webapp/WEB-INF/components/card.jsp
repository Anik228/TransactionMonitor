<%-- ═══════════════════════════════════════════════════════════
     components/card.jsp
     Reusable stat-card component.

     How to include from a parent JSP:
       set the CardData bean first
       <c:set var="card" value="${npsbSuccessCard}" scope="request"/>
       <jsp:include page="/WEB-INF/components/card.jsp"/>

     Required request attribute:
       card  (com.dashboard.model.CardData)

     card fields used:
       label          — heading text
       value          — large numeric value
       subText        — small descriptive line
       rate           — percentage string  e.g. "93.9%"
       rateDirection  — "up" | "down"
       themeClass     — CSS class  e.g. "card-npsb-success"
       iconClass      — Bootstrap icon class e.g. "bi-check-circle-fill"
     ═══════════════════════════════════════════════════════════ --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"  %>

<div class="stat-card ${card.themeClass}">

    <!-- <div>Hello World</div> -->

    <%-- decorative background icon --%>
    <i class="bi ${card.iconClass} card-bg-icon"></i>

    <%-- label --%>
    <div class="card-label">${card.label}</div>

    <%-- main value, formatted with thousand separators --%>
    <div class="card-value">
        <fmt:formatNumber value="${card.value}" type="number" groupingUsed="true"/>
    </div>

    <%-- sub-text --%>
    <div class="card-sub">${card.subText}</div>

    <%-- rate badge with directional arrow --%>
    <div class="card-rate">
        <c:choose>
            <c:when test="${card.rateDirection == 'up'}">
                <i class="bi bi-arrow-up-right"></i>
            </c:when>
            <c:otherwise>
                <i class="bi bi-arrow-down-right"></i>
            </c:otherwise>
        </c:choose>
        ${card.rate}
    </div>

</div>
