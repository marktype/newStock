package com.example.drawer.stockapp.model;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class MyZuHe {
    private double CumulativeReturn;//累计收益率
    private double CumulativeProfit;//累计收益
    private String name;
    private String id;
    private String UserId;
    private int Type;
    private boolean IsMyPortfolio;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public boolean isMyPortfolio() {
        return IsMyPortfolio;
    }

    public void setMyPortfolio(boolean myPortfolio) {
        IsMyPortfolio = myPortfolio;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCumulativeProfit() {
        return CumulativeProfit;
    }

    public void setCumulativeProfit(double cumulativeProfit) {
        CumulativeProfit = cumulativeProfit;
    }

    public double getCumulativeReturn() {
        return CumulativeReturn;
    }

    public void setCumulativeReturn(double cumulativeReturn) {
        CumulativeReturn = cumulativeReturn;
    }

    @Override
    public String toString() {
        return "MyZuHe{" +
                "CumulativeReturn=" + CumulativeReturn +
                ", CumulativeProfit=" + CumulativeProfit +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", UserId='" + UserId + '\'' +
                ", Type=" + Type +
                ", IsMyPortfolio=" + IsMyPortfolio +
                '}';
    }
}
