<%-- ═══════════════════════════════════════════════════════════
     components/navbar.jsp
     Reusable top navigation bar component.

     Required request attributes (set by controller or parent JSP):
       pageDate  (String)  — e.g. "07 Jun 2025"
     ═══════════════════════════════════════════════════════════ --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="navbar-custom d-flex align-items-center justify-content-between">

    <%-- ── Brand / logo ── --%>
    <div class="d-flex align-items-center gap-3">
        <a href="/dashboard" class="navbar-brand-custom">
            <div class="brand-icon">
                <i class="bi bi-bar-chart-fill text-white"></i>
            </div>
            TxnMonitor
        </a>
        <span class="nav-live-badge">LIVE</span>
    </div>

    <%-- ── Right-side meta: date + live clock ── --%>
    <div class="navbar-meta">
        <div class="navbar-meta-item">
            <div class="live-dot"></div>
            <i class="bi bi-calendar3"></i>
            <span>${pageDate}</span>
        </div>
        <div class="navbar-meta-item">
            <i class="bi bi-clock"></i>
            <span id="navbar-clock">--:--:--</span>
        </div>
    </div>

</nav>
