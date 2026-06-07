<%-- ═══════════════════════════════════════════════════════════
     views/dashboard.jsp
     Main dashboard page.
     Assembles components via <jsp:include>.
     All layout lives here; individual component logic/style
     belongs in their own files under /WEB-INF/components/.
     ═══════════════════════════════════════════════════════════ --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"    uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt"  uri="jakarta.tags.fmt"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Transaction Dashboard</title>

    <%-- ── Google Fonts ── --%>
    <link href="https://fonts.googleapis.com/css2?family=Sora:wght@300;400;500;600;700&family=JetBrains+Mono:wght@400;500&display=swap" rel="stylesheet"/>

    <%-- ── Bootstrap 5 ── --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <%-- ── Bootstrap Icons ── --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet"/>

    <%-- ── Component CSS files ── --%>
    <link rel="stylesheet" href="/static/css/dashboard.css"/>
    <link rel="stylesheet" href="/static/css/navbar.css"/>
    <link rel="stylesheet" href="/static/css/card.css"/>
    <link rel="stylesheet" href="/static/css/graph.css"/>
</head>
<body>

<%-- ══════════════════════════════════════════════
     COMPONENT 1 : Navbar
     Reads:  pageDate  (set by controller)
     ══════════════════════════════════════════════ --%>
<jsp:include page="/WEB-INF/components/navbar.jsp"/>

<%-- ══════════════════════════════════════════════
     MAIN CONTENT
     ══════════════════════════════════════════════ --%>
<div class="main-content">

    <%-- page heading --%>
    <div class="page-title">
        <i class="bi bi-activity"></i>
        Daily Transaction Overview &mdash; ${summary.date}
    </div>

    <%-- ──────────────────────────────────────────
         SECTION : STAT CARDS
         Each card is rendered by card.jsp.
         We set the "card" request attribute before
         each include so the component knows what to render.
         ────────────────────────────────────────── --%>
    <div class="section-label">Summary Cards</div>

    <div class="row g-3 mb-4">
        <c:forEach var="cardItem" items="${cards}">
            <div class="col-12 col-sm-6 col-xl-3">
                <%-- pass current card into the component via request scope --%>
                <c:set var="card" value="${cardItem}" scope="request"/>
                <jsp:include page="/WEB-INF/components/card.jsp"/>
            </div>
        </c:forEach>
    </div>

    <%-- ──────────────────────────────────────────
         SECTION : CHARTS
         Each chart panel is rendered by graph.jsp.
         Set the "graph" request attribute before each include.
         ────────────────────────────────────────── --%>
    <div class="section-label">Charts</div>

    <div class="row g-3">

        <%-- Pie / Doughnut Chart --%>
        <div class="col-12 col-lg-4">
            <c:set var="graph" value="${pieChart}" scope="request"/>
            <jsp:include page="/WEB-INF/components/graph.jsp"/>
        </div>

        <%-- Line Chart --%>
        <div class="col-12 col-lg-8">
            <c:set var="graph" value="${lineChart}" scope="request"/>
            <jsp:include page="/WEB-INF/components/graph.jsp"/>
        </div>

    </div><%-- /charts row --%>

</div><%-- /main-content --%>

<%-- ══════════════════════════════════════════════
     SCRIPTS
     Order matters:
       1. Bootstrap JS
       2. Chart.js (UMD global — required before graph.js)
       3. Component JS files
     ══════════════════════════════════════════════ --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.3/dist/chart.umd.min.js"></script>

<%-- navbar clock --%>
<script src="/static/js/navbar.js"></script>

<%-- chart initialisation — reads window.CHART_CONFIG populated by graph.jsp includes above --%>
<script src="/static/js/graph.js"></script>

</body>
</html>
