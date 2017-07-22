package com.example.drawer.stockapp.model;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class NiuRenInfoS {
    private String name;
    private String UserAvatar;//头像
    private double ProfitLossProportion;//胜率
    private int HoldCount;//持仓股票数
    private double Position;//仓位
    private double MonthProfitRate;//月收益
    private double CumulativeReturn;//总收益率

    public int getHoldCount() {
        return HoldCount;
    }

    public void setHoldCount(int holdCount) {
        HoldCount = holdCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserAvatar() {
        return UserAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        UserAvatar = userAvatar;
    }

    public double getProfitLossProportion() {
        return ProfitLossProportion;
    }

    public void setProfitLossProportion(double profitLossProportion) {
        ProfitLossProportion = profitLossProportion;
    }

    public double getPosition() {
        return Position;
    }

    public void setPosition(double position) {
        Position = position;
    }

    public double getMonthProfitRate() {
        return MonthProfitRate;
    }

    public void setMonthProfitRate(double monthProfitRate) {
        MonthProfitRate = monthProfitRate;
    }

    public double getCumulativeReturn() {
        return CumulativeReturn;
    }

    public void setCumulativeReturn(double cumulativeReturn) {
        CumulativeReturn = cumulativeReturn;
    }

    @Override
    public String toString() {
        return "NiuRenInfoS{" +
                "name='" + name + '\'' +
                ", UserAvatar='" + UserAvatar + '\'' +
                ", ProfitLossProportion=" + ProfitLossProportion +
                ", HoldCount=" + HoldCount +
                ", Position=" + Position +
                ", MonthProfitRate=" + MonthProfitRate +
                ", CumulativeReturn=" + CumulativeReturn +
                '}';
    }
}
