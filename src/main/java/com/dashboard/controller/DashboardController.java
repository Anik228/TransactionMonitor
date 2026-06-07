package com.dashboard.controller;

import com.dashboard.model.*;
import com.dashboard.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private TransactionService transactionService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) throws JsonProcessingException {

        // ── summary data ──────────────────────────────────────────────
        TransactionSummary s = transactionService.getTodaySummary();
        model.addAttribute("summary", s);
        model.addAttribute("pageDate", s.getDate());

        // ── 4 stat cards ─────────────────────────────────────────────
        List<CardData> cards = new ArrayList<>();

        cards.add(new CardData(
                "NPSB — Success",
                s.getNpsbSuccess(),
                "of " + s.getNpsbTotal() + " total NPSB",
                s.getNpsbSuccessRate() + "%", "up",
                "card-npsb-success", "bi-check-circle-fill"
        ));
        cards.add(new CardData(
                "NPSB — Failure",
                s.getNpsbFailure(),
                "of " + s.getNpsbTotal() + " total NPSB",
                s.getNpsbFailureRate() + "%", "down",
                "card-npsb-failure", "bi-x-circle-fill"
        ));
        cards.add(new CardData(
                "BEFTN — Success",
                s.getBeftnSuccess(),
                "of " + s.getBeftnTotal() + " total BEFTN",
                s.getBeftnSuccessRate() + "%", "up",
                "card-beftn-success", "bi-check2-all"
        ));
        cards.add(new CardData(
                "BEFTN — Failure",
                s.getBeftnFailure(),
                "of " + s.getBeftnTotal() + " total BEFTN",
                s.getBeftnFailureRate() + "%", "down",
                "card-beftn-failure", "bi-exclamation-triangle-fill"
        ));

        model.addAttribute("cards", cards);

        // ── pie / doughnut chart ──────────────────────────────────────
        List<HourlyTransaction> hourly = transactionService.getHourlyBreakdown();

        long totalNS = hourly.stream().mapToLong(HourlyTransaction::getNpsbSuccess).sum();
        long totalNF = hourly.stream().mapToLong(HourlyTransaction::getNpsbFailure).sum();
        long totalBS = hourly.stream().mapToLong(HourlyTransaction::getBeftnSuccess).sum();
        long totalBF = hourly.stream().mapToLong(HourlyTransaction::getBeftnFailure).sum();

        String pieLabels   = mapper.writeValueAsString(
                List.of("NPSB Success", "NPSB Failure", "BEFTN Success", "BEFTN Failure"));
        String pieDatasets = mapper.writeValueAsString(List.of(Map.of(
                "data",            List.of(totalNS, totalNF, totalBS, totalBF),
                "backgroundColor", List.of("#10b981","#ef4444","#3b82f6","#f97316"),
                "borderColor",     "#161b22",
                "borderWidth",     3,
                "hoverOffset",     8
        )));

        model.addAttribute("pieChart", new ChartData(
                ChartData.ChartType.PIE, "pieChart",
                "Transaction Distribution",
                "Today's breakdown by type & status",
                pieLabels, pieDatasets
        ));

        // ── line chart ────────────────────────────────────────────────
        List<String> hours = hourly.stream()
                .map(HourlyTransaction::getHour).collect(Collectors.toList());
        List<Long> ns = hourly.stream().map(HourlyTransaction::getNpsbSuccess).collect(Collectors.toList());
        List<Long> nf = hourly.stream().map(HourlyTransaction::getNpsbFailure).collect(Collectors.toList());
        List<Long> bs = hourly.stream().map(HourlyTransaction::getBeftnSuccess).collect(Collectors.toList());
        List<Long> bf = hourly.stream().map(HourlyTransaction::getBeftnFailure).collect(Collectors.toList());

        String lineLabels   = mapper.writeValueAsString(hours);
        String lineDatasets = mapper.writeValueAsString(List.of(
                lineDataset("NPSB Success",  ns, "#10b981", false),
                lineDataset("NPSB Failure",  nf, "#ef4444", false),
                lineDataset("BEFTN Success", bs, "#3b82f6", true),
                lineDataset("BEFTN Failure", bf, "#f97316", true)
        ));

        model.addAttribute("lineChart", new ChartData(
                ChartData.ChartType.LINE, "lineChart",
                "Hourly Transaction Trend",
                "Success & Failure count per hour — 00:00 to 23:00",
                lineLabels, lineDatasets
        ));

        return "dashboard";
    }

    // ── helpers ────────────────────────────────────────────────────────
    private Map<String, Object> lineDataset(String label, List<Long> data,
                                             String color, boolean dashed) {
        String rgba = hexToRgba(color, 0.08);
        Map<String, Object> ds = new java.util.LinkedHashMap<>();
        ds.put("label",           label);
        ds.put("data",            data);
        ds.put("borderColor",     color);
        ds.put("backgroundColor", rgba);
        ds.put("borderWidth",     2);
        ds.put("pointRadius",     0);
        ds.put("pointHoverRadius",5);
        ds.put("fill",            true);
        ds.put("tension",         0.4);
        if (dashed) ds.put("borderDash", List.of(5, 3));
        return ds;
    }

    private String hexToRgba(String hex, double alpha) {
        int r = Integer.parseInt(hex.substring(1,3),16);
        int g = Integer.parseInt(hex.substring(3,5),16);
        int b = Integer.parseInt(hex.substring(5,7),16);
        return "rgba(" + r + "," + g + "," + b + "," + alpha + ")";
    }
}
