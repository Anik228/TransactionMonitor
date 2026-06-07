package com.dashboard.model;

public class HourlyTransaction {

    private String hour;
    private long   npsbSuccess;
    private long   npsbFailure;
    private long   beftnSuccess;
    private long   beftnFailure;

    public HourlyTransaction() {}

    public HourlyTransaction(String hour, long npsbSuccess, long npsbFailure,
                              long beftnSuccess, long beftnFailure) {
        this.hour         = hour;
        this.npsbSuccess  = npsbSuccess;
        this.npsbFailure  = npsbFailure;
        this.beftnSuccess = beftnSuccess;
        this.beftnFailure = beftnFailure;
    }

    public String getHour()                         { return hour; }
    public void   setHour(String hour)              { this.hour = hour; }
    public long   getNpsbSuccess()                  { return npsbSuccess; }
    public void   setNpsbSuccess(long v)            { this.npsbSuccess = v; }
    public long   getNpsbFailure()                  { return npsbFailure; }
    public void   setNpsbFailure(long v)            { this.npsbFailure = v; }
    public long   getBeftnSuccess()                 { return beftnSuccess; }
    public void   setBeftnSuccess(long v)           { this.beftnSuccess = v; }
    public long   getBeftnFailure()                 { return beftnFailure; }
    public void   setBeftnFailure(long v)           { this.beftnFailure = v; }
}
