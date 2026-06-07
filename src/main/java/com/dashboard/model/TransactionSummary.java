package com.dashboard.model;

public class TransactionSummary {

    private long npsbSuccess;
    private long npsbFailure;
    private long beftnSuccess;
    private long beftnFailure;
    private String date;

    public TransactionSummary() {}

    public TransactionSummary(long npsbSuccess, long npsbFailure,
                               long beftnSuccess, long beftnFailure, String date) {
        this.npsbSuccess  = npsbSuccess;
        this.npsbFailure  = npsbFailure;
        this.beftnSuccess = beftnSuccess;
        this.beftnFailure = beftnFailure;
        this.date         = date;
    }

    /* ── derived ── */
    public long   getNpsbTotal()         { return npsbSuccess + npsbFailure; }
    public long   getBeftnTotal()        { return beftnSuccess + beftnFailure; }
    public long   getGrandTotal()        { return getNpsbTotal() + getBeftnTotal(); }

    public double getNpsbSuccessRate() {
        return getNpsbTotal() == 0 ? 0 :
               Math.round(npsbSuccess * 1000.0 / getNpsbTotal()) / 10.0;
    }
    public double getNpsbFailureRate()  { return Math.round((100 - getNpsbSuccessRate()) * 10.0) / 10.0; }
    public double getBeftnSuccessRate() {
        return getBeftnTotal() == 0 ? 0 :
               Math.round(beftnSuccess * 1000.0 / getBeftnTotal()) / 10.0;
    }
    public double getBeftnFailureRate() { return Math.round((100 - getBeftnSuccessRate()) * 10.0) / 10.0; }

    /* ── getters / setters ── */
    public long   getNpsbSuccess()                    { return npsbSuccess; }
    public void   setNpsbSuccess(long npsbSuccess)    { this.npsbSuccess = npsbSuccess; }
    public long   getNpsbFailure()                    { return npsbFailure; }
    public void   setNpsbFailure(long npsbFailure)    { this.npsbFailure = npsbFailure; }
    public long   getBeftnSuccess()                   { return beftnSuccess; }
    public void   setBeftnSuccess(long beftnSuccess)  { this.beftnSuccess = beftnSuccess; }
    public long   getBeftnFailure()                   { return beftnFailure; }
    public void   setBeftnFailure(long beftnFailure)  { this.beftnFailure = beftnFailure; }
    public String getDate()                           { return date; }
    public void   setDate(String date)                { this.date = date; }
}
