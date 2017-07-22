package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/7.
 * 指数信息
 */
public class IndexData {


    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;


    public HeadBean getHead() {
        return Head;
    }

    public void setHead(HeadBean Head) {
        this.Head = Head;
    }


    public static class HeadBean {
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


    private List<ResultBean> resultBeanList;

    public List<ResultBean> getResultBeanList() {
        return resultBeanList;
    }
    public void setResultBeanList(List<ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    public static class ResultBean {


        /**
         * "Id": null,
         * "Name": "上证综指",
         * "Code": "000001.SH",
         * "Price": 3202.08,
         * "VariabilityPrice": -27.539999999999964,
         * "VariabilityRate": -0.8527319003474082,
         * "UpdateTime": "2017-02-17 15:01:03",
         * "InceptionDate": "1899-12-30 00:00:00",
         * "ExpirationDate": "3015-11-03 00:00:00",
         * "IsInception": true
         */
        private String Id;
        private String Name;
        private String Code;
        private double Price;
        private double VariabilityPrice;
        private double VariabilityRate;
        private String UpdateTime;
        private String InceptionDate;
        private String ExpirationDate;
        private boolean IsInception;


        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String code) {
            Code = code;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double price) {
            Price = price;
        }

        public double getVariabilityPrice() {
            return VariabilityPrice;
        }

        public void setVariabilityPrice(double variabilityPrice) {
            VariabilityPrice = variabilityPrice;
        }

        public double getVariabilityRate() {
            return VariabilityRate;
        }

        public void setVariabilityRate(double variabilityRate) {
            VariabilityRate = variabilityRate;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            UpdateTime = updateTime;
        }

        public String getInceptionDate() {
            return InceptionDate;
        }

        public void setInceptionDate(String inceptionDate) {
            InceptionDate = inceptionDate;
        }

        public String getExpirationDate() {
            return ExpirationDate;
        }

        public void setExpirationDate(String expirationDate) {
            ExpirationDate = expirationDate;
        }

        public boolean isInception() {
            return IsInception;
        }

        public void setInception(boolean inception) {
            IsInception = inception;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "Id='" + Id + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Code='" + Code + '\'' +
                    ", Price=" + Price +
                    ", VariabilityPrice=" + VariabilityPrice +
                    ", VariabilityRate=" + VariabilityRate +
                    ", UpdateTime='" + UpdateTime + '\'' +
                    ", InceptionDate='" + InceptionDate + '\'' +
                    ", ExpirationDate='" + ExpirationDate + '\'' +
                    ", IsInception=" + IsInception +
                    '}';
        }
    }
}
