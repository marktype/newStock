package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class chiCangLieBiaoEntitySecond {
    private List<chiCangLieBiaoEntitySecond.ResultBean> Result;

    public List<ResultBean> getResult() {
        return Result;
    }

    public void setResult(List<ResultBean> result) {
        Result = result;
    }

    public static class HeadBean {
        /**
         * Status : 0
         * Msg : success
         */

        private int Status;
        private String Msg;

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }
    }

    public static class ResultBean {
        /**
         * Name : 渝三峡A
         * Code : 000565.SZ
         * Price : 12.92
         * TradePrice : 12.780000000000001
         * Volume : 2900
         * RiseFallProfit : 1.1
         * CumulativeRiseFallReturn : 1.1
         * Positions : 37.55
         * TradeTime : 2017-03-27 17:49:08
         * IsInception : true
         */

        private String Name;
        private String Code;
        private double Price;
        private double TradePrice;
        private int Volume;
        private double RiseFallProfit;
        private double CumulativeRiseFallReturn;
        private double Positions;
        private String TradeTime;
        private boolean IsInception;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public double getTradePrice() {
            return TradePrice;
        }

        public void setTradePrice(double TradePrice) {
            this.TradePrice = TradePrice;
        }

        public int getVolume() {
            return Volume;
        }

        public void setVolume(int Volume) {
            this.Volume = Volume;
        }

        public double getRiseFallProfit() {
            return RiseFallProfit;
        }

        public void setRiseFallProfit(double RiseFallProfit) {
            this.RiseFallProfit = RiseFallProfit;
        }

        public double getCumulativeRiseFallReturn() {
            return CumulativeRiseFallReturn;
        }

        public void setCumulativeRiseFallReturn(double CumulativeRiseFallReturn) {
            this.CumulativeRiseFallReturn = CumulativeRiseFallReturn;
        }

        public double getPositions() {
            return Positions;
        }

        public void setPositions(double Positions) {
            this.Positions = Positions;
        }

        public String getTradeTime() {
            return TradeTime;
        }

        public void setTradeTime(String TradeTime) {
            this.TradeTime = TradeTime;
        }

        public boolean isIsInception() {
            return IsInception;
        }

        public void setIsInception(boolean IsInception) {
            this.IsInception = IsInception;
        }
    }
}

