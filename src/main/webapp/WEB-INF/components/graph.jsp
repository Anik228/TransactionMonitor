<%-- ═══════════════════════════════════════════════════════════
     components/graph.jsp
     Reusable chart-panel component (pie OR line).
     Renders a Chart.js canvas panel and registers the chart
     config into window.CHART_CONFIG for graph.js to pick up.

     How to include from a parent JSP:
       <c:set var="graph" value="${pieChart}" scope="request"/>
       <jsp:include page="/WEB-INF/components/graph.jsp"/>

     Required request attribute:
       graph  (com.dashboard.model.ChartData)

     graph fields used:
       chartId       — unique <canvas> id
       title         — panel heading
       subtitle      — panel sub-heading
       chartType     — PIE | LINE
       labelsJson    — JSON array string of labels
       datasetsJson  — JSON array string of dataset objects
     ═══════════════════════════════════════════════════════════ --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="chart-panel">

    <%-- ── header ── --%>
    <div class="chart-panel-title">
        <c:choose>
            <c:when test="${graph.chartType == 'PIE'}">
                <i class="bi bi-pie-chart-fill"></i>
            </c:when>
            <c:otherwise>
                <i class="bi bi-graph-up"></i>
            </c:otherwise>
        </c:choose>
        ${graph.title}
    </div>
    <div class="chart-panel-subtitle">${graph.subtitle}</div>

    <%-- ── legend (colour dots) ── --%>
    <div class="chart-legend">
        <div class="legend-item"><div class="legend-dot" style="background:#10b981;"></div>NPSB Success</div>
        <div class="legend-item"><div class="legend-dot" style="background:#ef4444;"></div>NPSB Failure</div>
        <div class="legend-item"><div class="legend-dot" style="background:#3b82f6;"></div>BEFTN Success</div>
        <div class="legend-item"><div class="legend-dot" style="background:#f97316;"></div>BEFTN Failure</div>
    </div>

    <%-- ── canvas ── --%>
    <c:choose>
        <c:when test="${graph.chartType == 'PIE'}">
            <canvas id="${graph.chartId}" height="260"></canvas>
        </c:when>
        <c:otherwise>
            <canvas id="${graph.chartId}" height="200"></canvas>
        </c:otherwise>
    </c:choose>

</div>

<%-- ── register chart config for graph.js ── --%>
<script>
    (function () {
        window.CHART_CONFIG = window.CHART_CONFIG || {};

        var chartType = "${graph.chartType}" === "PIE" ? "doughnut" : "line";

        window.CHART_CONFIG["${graph.chartId}"] = {
            type:     chartType,
            labels:   ${graph.labelsJson},
            datasets: ${graph.datasetsJson}
        };
    })();
</script>
