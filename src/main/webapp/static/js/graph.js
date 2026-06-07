/**
 * graph.js
 * Initialises Chart.js instances for the dashboard.
 *
 * Expects the following global variables to be set by the
 * calling JSP (graph.jsp) before this script runs:
 *
 *   window.CHART_CONFIG  — an object keyed by canvas id:
 *   {
 *     "pieChart":  { type: "doughnut", labels: [...], datasets: [...] },
 *     "lineChart": { type: "line",     labels: [...], datasets: [...], options: {...} }
 *   }
 */
(function () {
    "use strict";

    /* ── shared Chart.js defaults ── */
    Chart.defaults.color       = "#7d8590";
    Chart.defaults.borderColor = "#30363d";
    Chart.defaults.font.family = "'Sora', sans-serif";
    Chart.defaults.font.size   = 11;

    /* ── default options per chart type ── */
    const DEFAULT_OPTIONS = {

        doughnut: {
            cutout: "65%",
            plugins: {
                legend: { display: false },
                tooltip: {
                    backgroundColor: "#1c2333",
                    borderColor:     "#30363d",
                    borderWidth:     1,
                    padding:         10,
                    callbacks: {
                        label: function (ctx) {
                            return " " + ctx.label + ": " + ctx.parsed.toLocaleString() + " txns";
                        }
                    }
                }
            }
        },

        line: {
            responsive: true,
            interaction: { mode: "index", intersect: false },
            plugins: {
                legend: { display: false },
                tooltip: {
                    backgroundColor: "#1c2333",
                    borderColor:     "#30363d",
                    borderWidth:     1,
                    padding:         10,
                    callbacks: {
                        label: function (ctx) {
                            return " " + ctx.dataset.label + ": " + ctx.parsed.y.toLocaleString();
                        }
                    }
                }
            },
            scales: {
                x: {
                    grid:  { color: "rgba(48,54,61,0.5)" },
                    ticks: { maxTicksLimit: 12, font: { size: 10, family: "'JetBrains Mono', monospace" } }
                },
                y: {
                    grid:  { color: "rgba(48,54,61,0.5)" },
                    ticks: { font: { size: 10 } }
                }
            }
        }
    };

    /**
     * Deep-merges two plain objects (non-recursive arrays are replaced).
     */
    function mergeDeep(target, source) {
        const out = Object.assign({}, target);
        for (const key of Object.keys(source)) {
            if (source[key] && typeof source[key] === "object" && !Array.isArray(source[key])) {
                out[key] = mergeDeep(target[key] || {}, source[key]);
            } else {
                out[key] = source[key];
            }
        }
        return out;
    }

    /* ── initialise all charts declared in CHART_CONFIG ── */
    document.addEventListener("DOMContentLoaded", function () {
        if (typeof window.CHART_CONFIG === "undefined") return;

        Object.keys(window.CHART_CONFIG).forEach(function (canvasId) {
            const cfg     = window.CHART_CONFIG[canvasId];
            const canvas  = document.getElementById(canvasId);
            if (!canvas) return;

            const baseOpts   = DEFAULT_OPTIONS[cfg.type] || {};
            const mergedOpts = cfg.options ? mergeDeep(baseOpts, cfg.options) : baseOpts;

            new Chart(canvas, {
                type:    cfg.type,
                data:    { labels: cfg.labels, datasets: cfg.datasets },
                options: mergedOpts
            });
        });
    });
})();
