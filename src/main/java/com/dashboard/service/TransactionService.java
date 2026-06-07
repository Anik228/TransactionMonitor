package com.dashboard.service;

import com.dashboard.model.HourlyTransaction;
import com.dashboard.model.TransactionSummary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    /**
     * Returns today's overall transaction counts.
     * ──────────────────────────────────────────
     * TODO: Replace hardcoded values with real DB queries, e.g.:
     *
     *   @Autowired JdbcTemplate jdbc;
     *
     *   Map<String,Object> row = jdbc.queryForMap("""
     *       SELECT
     *         SUM(CASE WHEN txn_type='NPSB'  AND status='SUCCESS' THEN 1 ELSE 0 END) npsb_s,
     *         SUM(CASE WHEN txn_type='NPSB'  AND status='FAILURE' THEN 1 ELSE 0 END) npsb_f,
     *         SUM(CASE WHEN txn_type='BEFTN' AND status='SUCCESS' THEN 1 ELSE 0 END) bftn_s,
     *         SUM(CASE WHEN txn_type='BEFTN' AND status='FAILURE' THEN 1 ELSE 0 END) bftn_f
     *       FROM transactions
     *       WHERE DATE(created_at) = CURDATE()
     *   """);
     */
    public TransactionSummary getTodaySummary() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return new TransactionSummary(4823, 312, 3150, 198, today);
    }

    /**
     * Returns hourly breakdown for the line chart.
     * ──────────────────────────────────────────────
     * TODO: Replace with real hourly-aggregate DB query, e.g.:
     *
     *   SELECT HOUR(created_at) hr,
     *          SUM(CASE WHEN txn_type='NPSB'  AND status='SUCCESS' THEN 1 ELSE 0 END) npsb_s,
     *          ...
     *   FROM transactions
     *   WHERE DATE(created_at) = CURDATE()
     *   GROUP BY hr ORDER BY hr
     */
    public List<HourlyTransaction> getHourlyBreakdown() {
        int[] ns = {120,95,60,40,55,110,280,420,510,490,460,430,400,380,360,340,300,270,230,200,170,150,130,110};
        int[] nf = { 8, 5, 3, 2, 4,  7, 18, 28, 35, 30, 25, 22, 20, 18, 17, 15, 13, 12, 10,  9,  8,  7,  6,  5};
        int[] bs = { 80,60,40,25,35, 70,180,270,340,320,300,280,260,240,220,210,190,170,150,130,110, 90, 80, 70};
        int[] bf = {  5, 3, 2, 1, 2,  4, 10, 16, 20, 18, 15, 13, 12, 11, 10,  9,  8,  7,  6,  5,  4,  3,  3,  2};

        List<HourlyTransaction> list = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            list.add(new HourlyTransaction(String.format("%02d:00", i), ns[i], nf[i], bs[i], bf[i]));
        }
        return list;
    }
}
