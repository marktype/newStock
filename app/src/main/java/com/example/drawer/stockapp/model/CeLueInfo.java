package com.example.drawer.stockapp.model;

/**
 * 策略组合信息
 */
public class CeLueInfo {
    private int id;
    private int Type;
    private  String Desc ;

    private  String UserName;
    private double AlongCount; //多少人跟投
    private double CumulativeReturn; //累计收益率
    private double MonthProfitRate; //近一月收益率
    private double ProfitLossProportion; //交易胜率

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public double getAlongCount() {
        return AlongCount;
    }

    public void setAlongCount(double alongCount) {
        AlongCount = alongCount;
    }

    public double getCumulativeReturn() {
        return CumulativeReturn;
    }

    public void setCumulativeReturn(double cumulativeReturn) {
        CumulativeReturn = cumulativeReturn;
    }

    public double getMonthProfitRate() {
        return MonthProfitRate;
    }

    public void setMonthProfitRate(double monthProfitRate) {
        MonthProfitRate = monthProfitRate;
    }

    public double getProfitLossProportion() {
        return ProfitLossProportion;
    }

    public void setProfitLossProportion(double profitLossProportion) {
        ProfitLossProportion = profitLossProportion;
    }

    @Override
    public String toString() {
        return "CeLueInfo{" +
                "id=" + id +
                ", Type=" + Type +
                ", Desc='" + Desc + '\'' +
                ", UserName='" + UserName + '\'' +
                ", AlongCount=" + AlongCount +
                ", CumulativeReturn=" + CumulativeReturn +
                ", MonthProfitRate=" + MonthProfitRate +
                ", ProfitLossProportion=" + ProfitLossProportion +
                '}';
    }
}

