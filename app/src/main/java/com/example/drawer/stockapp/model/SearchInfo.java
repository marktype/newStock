package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/8/19.
 */
public class SearchInfo {


    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    private ResultBean Result;

    public HeadBean getHead() {
        return Head;
    }

    public void setHead(HeadBean Head) {
        this.Head = Head;
    }

    public ResultBean getResult() {
        return Result;
    }

    public void setResult(ResultBean Result) {
        this.Result = Result;
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

    public static class ResultBean {
        /**
         * Id : 0
         * Name : 上证指数
         * Code : 000001
         * Points : 0.0
         * VariabilityPoints : 0.0
         * VariabilityRate : 0.0
         * UpdateTime : null
         */

        private List<MarketDataBean> MarketData;

        public List<MarketDataBean> getMarketData() {
            return MarketData;
        }

        public void setMarketData(List<MarketDataBean> MarketData) {
            this.MarketData = MarketData;
        }

        public static class MarketDataBean {
//            private String Id;
            private String Name;
            private String Code;
            private double Price;
            private double VariabilityPrice;
            private double VariabilityRate;
            private String UpdateTime;

//            public String getId() {
//                return Id;
//            }
//
//            public void setId(String Id) {
//                this.Id = Id;
//            }

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
        }
    }
}
