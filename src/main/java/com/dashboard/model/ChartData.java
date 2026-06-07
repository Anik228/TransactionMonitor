package com.dashboard.model;

/**
 * Data object passed to the graph.jsp component.
 * Carries Chart.js-ready JSON strings and metadata.
 */
public class ChartData {

    public enum ChartType { PIE, LINE }

    private ChartType chartType;
    private String    chartId;       // unique HTML id for the canvas element
    private String    title;
    private String    subtitle;

    /* JSON arrays (serialised by controller, injected via JSP EL) */
    private String labelsJson;
    private String datasetsJson;

    public ChartData() {}

    public ChartData(ChartType chartType, String chartId,
                     String title, String subtitle,
                     String labelsJson, String datasetsJson) {
        this.chartType    = chartType;
        this.chartId      = chartId;
        this.title        = title;
        this.subtitle     = subtitle;
        this.labelsJson   = labelsJson;
        this.datasetsJson = datasetsJson;
    }

    public ChartType getChartType()               { return chartType; }
    public void      setChartType(ChartType t)    { this.chartType = t; }

    public String    getChartId()                 { return chartId; }
    public void      setChartId(String id)        { this.chartId = id; }

    public String    getTitle()                   { return title; }
    public void      setTitle(String t)           { this.title = t; }

    public String    getSubtitle()                { return subtitle; }
    public void      setSubtitle(String s)        { this.subtitle = s; }

    public String    getLabelsJson()              { return labelsJson; }
    public void      setLabelsJson(String j)      { this.labelsJson = j; }

    public String    getDatasetsJson()            { return datasetsJson; }
    public void      setDatasetsJson(String j)    { this.datasetsJson = j; }
}
