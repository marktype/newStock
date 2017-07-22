package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class SearchInfoSecond {

    /**
     * Head : {"Status":0,"Msg":"success"}
     * Result : [{"Id":null,"Name":"浦发银行","Code":"600000.SH","Price":16.14,"VariabilityPrice":-0.05999999999999872,"VariabilityRate":-0.37037037037036247,"UpdateTime":"2017-03-20 15:00:04","InceptionDate":"1999-11-10 00:00:00","ExpirationDate":"3015-11-03 00:00:00","IsInception":true},{"Id":null,"Name":"邯郸钢铁(退市)","Code":"600001.SH","Price":0,"VariabilityPrice":0,"VariabilityRate":0,"UpdateTime":"2017-03-20 00:00:00","InceptionDate":"1998-01-22 00:00:00","ExpirationDate":"2009-12-29 00:00:00","IsInception":false},{"Id":null,"Name":"齐鲁石化(退市)","Code":"600002.SH","Price":0,"VariabilityPrice":0,"VariabilityRate":0,"UpdateTime":"2017-03-20 00:00:00","InceptionDate":"1998-04-08 00:00:00","ExpirationDate":"2006-04-24 00:00:00","IsInception":false},{"Id":null,"Name":"ST东北高(退市)","Code":"600003.SH","Price":0,"VariabilityPrice":0,"VariabilityRate":0,"UpdateTime":"2017-03-20 00:00:00","InceptionDate":"1999-08-10 00:00:00","ExpirationDate":"2010-02-26 00:00:00","IsInception":false},{"Id":null,"Name":"白云机场","Code":"600004.SH","Price":16.22,"VariabilityPrice":0.05999999999999872,"VariabilityRate":0.3712871287128634,"UpdateTime":"2017-03-20 15:00:04","InceptionDate":"2003-04-28 00:00:00","ExpirationDate":"3015-11-03 00:00:00","IsInception":true},{"Id":null,"Name":"武钢股份(退市)","Code":"600005.SH","Price":0,"VariabilityPrice":0,"VariabilityRate":0,"UpdateTime":"2017-03-20 00:00:00","InceptionDate":"1999-08-03 00:00:00","ExpirationDate":"2017-02-14 00:00:00","IsInception":false},{"Id":null,"Name":"东风汽车","Code":"600006.SH","Price":7.12,"VariabilityPrice":0.03000000000000025,"VariabilityRate":0.423131170662909,"UpdateTime":"2017-03-20 15:00:02","InceptionDate":"1999-07-27 00:00:00","ExpirationDate":"3015-11-03 00:00:00","IsInception":true},{"Id":null,"Name":"中国国贸","Code":"600007.SH","Price":20.69,"VariabilityPrice":0.4800000000000004,"VariabilityRate":2.3750618505690273,"UpdateTime":"2017-03-20 15:00:02","InceptionDate":"1999-03-12 00:00:00","ExpirationDate":"3017-02-10 12:00:05","IsInception":true},{"Id":null,"Name":"首创股份","Code":"600008.SH","Price":4.3100000000000005,"VariabilityPrice":0.010000000000000675,"VariabilityRate":0.23255813953489943,"UpdateTime":"2017-03-20 15:00:04","InceptionDate":"2000-04-27 00:00:00","ExpirationDate":"3015-11-03 00:00:00","IsInception":true},{"Id":null,"Name":"上海机场","Code":"600009.SH","Price":28.54,"VariabilityPrice":0.0799999999999983,"VariabilityRate":0.28109627547434396,"UpdateTime":"2017-03-20 15:00:04","InceptionDate":"1998-02-18 00:00:00","ExpirationDate":"3015-11-03 00:00:00","IsInception":true}]
     */

    private HeadBean Head;
    private List<ResultBean> Result;

    public HeadBean getHead() {
        return Head;
    }

    public void setHead(HeadBean Head) {
        this.Head = Head;
    }

    public List<ResultBean> getResult() {
        return Result;
    }

    public void setResult(List<ResultBean> Result) {
        this.Result = Result;
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
         * Id : null
         * Name : 浦发银行
         * Code : 600000.SH
         * Price : 16.14
         * VariabilityPrice : -0.05999999999999872
         * VariabilityRate : -0.37037037037036247
         * UpdateTime : 2017-03-20 15:00:04
         * InceptionDate : 1999-11-10 00:00:00
         * ExpirationDate : 3015-11-03 00:00:00
         * IsInception : true
         */

        private Object Id;
        private String Name;
        private String Code;
        private double Price;
        private double VariabilityPrice;
        private double VariabilityRate;
        private String UpdateTime;
        private String InceptionDate;
        private String ExpirationDate;
        private boolean IsInception;

        public Object getId() {
            return Id;
        }

        public void setId(Object Id) {
            this.Id = Id;
        }

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

        public double getVariabilityPrice() {
            return VariabilityPrice;
        }

        public void setVariabilityPrice(double VariabilityPrice) {
            this.VariabilityPrice = VariabilityPrice;
        }

        public double getVariabilityRate() {
            return VariabilityRate;
        }

        public void setVariabilityRate(double VariabilityRate) {
            this.VariabilityRate = VariabilityRate;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getInceptionDate() {
            return InceptionDate;
        }

        public void setInceptionDate(String InceptionDate) {
            this.InceptionDate = InceptionDate;
        }

        public String getExpirationDate() {
            return ExpirationDate;
        }

        public void setExpirationDate(String ExpirationDate) {
            this.ExpirationDate = ExpirationDate;
        }

        public boolean isIsInception() {
            return IsInception;
        }

        public void setIsInception(boolean IsInception) {
            this.IsInception = IsInception;
        }
    }
}
