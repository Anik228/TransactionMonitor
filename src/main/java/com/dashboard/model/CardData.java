package com.dashboard.model;

/**
 * Data object passed to the card.jsp component.
 * Each card on the dashboard is rendered by including card.jsp
 * and setting a CardData instance as a request attribute.
 */
public class CardData {

    private String label;        // e.g. "NPSB — Success"
    private long   value;        // e.g. 4823
    private String subText;      // e.g. "of 5,135 total NPSB"
    private String rate;         // e.g. "93.9%"
    private String rateDirection;// "up" or "down"
    private String themeClass;   // CSS class: card-npsb-success | card-npsb-failure | etc.
    private String iconClass;    // Bootstrap icon class: bi-check-circle-fill | bi-x-circle-fill | etc.

    public CardData() {}

    public CardData(String label, long value, String subText,
                    String rate, String rateDirection,
                    String themeClass, String iconClass) {
        this.label         = label;
        this.value         = value;
        this.subText       = subText;
        this.rate          = rate;
        this.rateDirection = rateDirection;
        this.themeClass    = themeClass;
        this.iconClass     = iconClass;
    }

    public String getLabel()         { return label; }
    public void   setLabel(String l) { this.label = l; }

    public long   getValue()         { return value; }
    public void   setValue(long v)   { this.value = v; }

    public String getSubText()          { return subText; }
    public void   setSubText(String s)  { this.subText = s; }

    public String getRate()             { return rate; }
    public void   setRate(String r)     { this.rate = r; }

    public String getRateDirection()              { return rateDirection; }
    public void   setRateDirection(String d)      { this.rateDirection = d; }

    public String getThemeClass()                 { return themeClass; }
    public void   setThemeClass(String t)         { this.themeClass = t; }

    public String getIconClass()                  { return iconClass; }
    public void   setIconClass(String i)          { this.iconClass = i; }
}
