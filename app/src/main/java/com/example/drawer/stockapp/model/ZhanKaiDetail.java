package com.example.drawer.stockapp.model;

/**
 * Created by Administrator on 2017/2/25 0025.
 */

public class ZhanKaiDetail {
    private String Code;//股票代码
    private String Name;//股票名称
    private double TradePrice;//成交价
    private double Volumn ;//交易量
    private String TradeTime;//成交时间
    private int BuyType;//买入卖出 0 买 1卖

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getVolumn() {
        return Volumn;
    }

    public void setVolumn(double volumn) {
        Volumn = volumn;
    }

    public double getTradePrice() {
        return TradePrice;
    }

    public void setTradePrice(double tradePrice) {
        TradePrice = tradePrice;
    }

    public String getTradeTime() {
        return TradeTime;
    }

    public void setTradeTime(String tradeTime) {
        TradeTime = tradeTime;
    }

    public int getBuyType() {
        return BuyType;
    }

    public void setBuyType(int buyType) {
        BuyType = buyType;
    }

    @Override
    public String toString() {
        return "ZhanKaiDetail{" +
                "Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", TradePrice=" + TradePrice +
                ", Volumn=" + Volumn +
                ", TradeTime='" + TradeTime + '\'' +
                ", BuyType=" + BuyType +
                '}';
    }
}
