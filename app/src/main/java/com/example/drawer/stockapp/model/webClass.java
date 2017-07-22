package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class webClass {

    /**
     * Head : {"Status":0,"Msg":"success"}
     * Result : {"PageInfo":{"PageIndex":0,"PageCount":6,"PageSize":10},"Data":[{"Id":"3","Type":1,"Status":0,"IsMyPortfolio":false,"UserId":null,"UserNickName":null,"UserAvatar":null,"Name":"激进型短线一号","Desc":"本策略是通过DDE 大单净额、换手率、以及最近5日的涨跌幅来筛选股票，优先选择小市值个股，通常持股周期为2天。","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":8,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":2,"CreateTime":"2017-01-19 15:03:00","PorfolioAttribute":{"MinFollow":10000,"LimtAmount":100000},"PorfolioOther":{"CumulativeReturn":17.32,"CumulativeProfit":173190.1,"ActualRunDay":56,"NetValue":1174387.23,"Return":-0.01,"Position":94.91,"MaxDrawDownRate":2.4,"HoldCount":1,"Cash":59827.23,"MonthProfitRate":7.48,"ProfitLossProportion":81.82,"CloseMaxLossReturn":-1.31,"CloseMaxProfitReturn":6.72,"MonthTradeCount":6}},{"Id":"6","Type":1,"Status":0,"IsMyPortfolio":false,"UserId":null,"UserNickName":null,"UserAvatar":null,"Name":"激进型短线二号","Desc":"本策略是通过近几日的资金流入、振幅和量比来筛选股票，优先选择小市值个股，通常持股周期为2天.","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":8,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":1,"CreateTime":"2017-02-02 15:39:00","PorfolioAttribute":{"MinFollow":10000,"LimtAmount":100000},"PorfolioOther":{"CumulativeReturn":17.57,"CumulativeProfit":175706.17,"ActualRunDay":42,"NetValue":1176913.57,"Return":-0.71,"Position":95.23,"MaxDrawDownRate":3.1,"HoldCount":1,"Cash":56113.57,"MonthProfitRate":5.45,"ProfitLossProportion":85.71,"CloseMaxLossReturn":-0.04,"CloseMaxProfitReturn":5.95,"MonthTradeCount":4}},{"Id":"8","Type":1,"Status":0,"IsMyPortfolio":false,"UserId":null,"UserNickName":null,"UserAvatar":null,"Name":"稳健型中线 BBI","Desc":"本策略是通过BBI、量比和换手率来筛选股票，优先选择小市值个股，通常持股周期为20天,通常持股数量2-3只。","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":3,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":0,"CreateTime":"2016-12-12 16:14:00","PorfolioAttribute":{"MinFollow":10000,"LimtAmount":200000},"PorfolioOther":{"CumulativeReturn":19.21,"CumulativeProfit":192119.34,"ActualRunDay":93,"NetValue":1192841.53,"Return":-0.49,"Position":55.63,"MaxDrawDownRate":2.57,"HoldCount":2,"Cash":529241.53,"MonthProfitRate":-2.77,"ProfitLossProportion":80,"CloseMaxLossReturn":-0.75,"CloseMaxProfitReturn":18.79,"MonthTradeCount":2}},{"Id":"20","Type":1,"Status":0,"IsMyPortfolio":false,"UserId":null,"UserNickName":null,"UserAvatar":null,"Name":"激进型中线 BBI","Desc":"本策略是通过BBI、量比和换手率来筛选股票，优先选择小市值个股，通常持股周期为20天，通常持股数1-2只。","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":8,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":2,"CreateTime":"2016-11-27 17:27:00","PorfolioAttribute":{"MinFollow":10000,"LimtAmount":100000},"PorfolioOther":{"CumulativeReturn":19.77,"CumulativeProfit":197707.85,"ActualRunDay":108,"NetValue":1198929.96,"Return":-0.6,"Position":94.21,"MaxDrawDownRate":6.36,"HoldCount":1,"Cash":69429.96,"MonthProfitRate":2.65,"ProfitLossProportion":100,"CloseMaxLossReturn":0,"CloseMaxProfitReturn":8.77,"MonthTradeCount":2}}]}
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

    @Override
    public String toString() {
        return "webClass{" +
                "Head=" + Head +
                ", Result=" + Result +
                '}';
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

        @Override
        public String toString() {
            return "HeadBean{" +
                    "Status=" + Status +
                    ", Msg='" + Msg + '\'' +
                    '}';
        }
    }

    public static class ResultBean {
        /**
         * PageInfo : {"PageIndex":0,"PageCount":6,"PageSize":10}
         * Data : [{"Id":"3","Type":1,"Status":0,"IsMyPortfolio":false,"UserId":null,"UserNickName":null,"UserAvatar":null,"Name":"激进型短线一号","Desc":"本策略是通过DDE 大单净额、换手率、以及最近5日的涨跌幅来筛选股票，优先选择小市值个股，通常持股周期为2天。","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":8,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":2,"CreateTime":"2017-01-19 15:03:00","PorfolioAttribute":{"MinFollow":10000,"LimtAmount":100000},"PorfolioOther":{"CumulativeReturn":17.32,"CumulativeProfit":173190.1,"ActualRunDay":56,"NetValue":1174387.23,"Return":-0.01,"Position":94.91,"MaxDrawDownRate":2.4,"HoldCount":1,"Cash":59827.23,"MonthProfitRate":7.48,"ProfitLossProportion":81.82,"CloseMaxLossReturn":-1.31,"CloseMaxProfitReturn":6.72,"MonthTradeCount":6}},{"Id":"6","Type":1,"Status":0,"IsMyPortfolio":false,"UserId":null,"UserNickName":null,"UserAvatar":null,"Name":"激进型短线二号","Desc":"本策略是通过近几日的资金流入、振幅和量比来筛选股票，优先选择小市值个股，通常持股周期为2天.","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":8,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":1,"CreateTime":"2017-02-02 15:39:00","PorfolioAttribute":{"MinFollow":10000,"LimtAmount":100000},"PorfolioOther":{"CumulativeReturn":17.57,"CumulativeProfit":175706.17,"ActualRunDay":42,"NetValue":1176913.57,"Return":-0.71,"Position":95.23,"MaxDrawDownRate":3.1,"HoldCount":1,"Cash":56113.57,"MonthProfitRate":5.45,"ProfitLossProportion":85.71,"CloseMaxLossReturn":-0.04,"CloseMaxProfitReturn":5.95,"MonthTradeCount":4}},{"Id":"8","Type":1,"Status":0,"IsMyPortfolio":false,"UserId":null,"UserNickName":null,"UserAvatar":null,"Name":"稳健型中线 BBI","Desc":"本策略是通过BBI、量比和换手率来筛选股票，优先选择小市值个股，通常持股周期为20天,通常持股数量2-3只。","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":3,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":0,"CreateTime":"2016-12-12 16:14:00","PorfolioAttribute":{"MinFollow":10000,"LimtAmount":200000},"PorfolioOther":{"CumulativeReturn":19.21,"CumulativeProfit":192119.34,"ActualRunDay":93,"NetValue":1192841.53,"Return":-0.49,"Position":55.63,"MaxDrawDownRate":2.57,"HoldCount":2,"Cash":529241.53,"MonthProfitRate":-2.77,"ProfitLossProportion":80,"CloseMaxLossReturn":-0.75,"CloseMaxProfitReturn":18.79,"MonthTradeCount":2}},{"Id":"20","Type":1,"Status":0,"IsMyPortfolio":false,"UserId":null,"UserNickName":null,"UserAvatar":null,"Name":"激进型中线 BBI","Desc":"本策略是通过BBI、量比和换手率来筛选股票，优先选择小市值个股，通常持股周期为20天，通常持股数1-2只。","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":8,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":2,"CreateTime":"2016-11-27 17:27:00","PorfolioAttribute":{"MinFollow":10000,"LimtAmount":100000},"PorfolioOther":{"CumulativeReturn":19.77,"CumulativeProfit":197707.85,"ActualRunDay":108,"NetValue":1198929.96,"Return":-0.6,"Position":94.21,"MaxDrawDownRate":6.36,"HoldCount":1,"Cash":69429.96,"MonthProfitRate":2.65,"ProfitLossProportion":100,"CloseMaxLossReturn":0,"CloseMaxProfitReturn":8.77,"MonthTradeCount":2}}]
         */

        private PageInfoBean PageInfo;
        private List<DataBean> Data;

        public PageInfoBean getPageInfo() {
            return PageInfo;
        }

        public void setPageInfo(PageInfoBean PageInfo) {
            this.PageInfo = PageInfo;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "PageInfo=" + PageInfo +
                    ", Data=" + Data +
                    '}';
        }

        public static class PageInfoBean {
            /**
             * PageIndex : 0
             * PageCount : 6
             * PageSize : 10
             */

            private int PageIndex;
            private int PageCount;
            private int PageSize;

            public int getPageIndex() {
                return PageIndex;
            }

            public void setPageIndex(int PageIndex) {
                this.PageIndex = PageIndex;
            }

            public int getPageCount() {
                return PageCount;
            }

            public void setPageCount(int PageCount) {
                this.PageCount = PageCount;
            }

            public int getPageSize() {
                return PageSize;
            }

            public void setPageSize(int PageSize) {
                this.PageSize = PageSize;
            }

            @Override
            public String toString() {
                return "PageInfoBean{" +
                        "PageIndex=" + PageIndex +
                        ", PageCount=" + PageCount +
                        ", PageSize=" + PageSize +
                        '}';
            }
        }

        public static class DataBean {
            /**
             * Id : 3
             * Type : 1
             * Status : 0
             * IsMyPortfolio : false
             * UserId : null
             * UserNickName : null
             * UserAvatar : null
             * Name : 激进型短线一号
             * Desc : 本策略是通过DDE 大单净额、换手率、以及最近5日的涨跌幅来筛选股票，优先选择小市值个股，通常持股周期为2天。
             * IsAlong : false
             * IsTracking : false
             * IsSubscribe : false
             * IsReward : false
             * IsSmsNotic : false
             * RewardCount : 0
             * AlongCount : 8
             * TrackingCount : 0
             * ShareCount : 0
             * Subscribe : 0
             * CommentCount : 2
             * CreateTime : 2017-01-19 15:03:00
             * PorfolioAttribute : {"MinFollow":10000,"LimtAmount":100000}
             * PorfolioOther : {"CumulativeReturn":17.32,"CumulativeProfit":173190.1,"ActualRunDay":56,"NetValue":1174387.23,"Return":-0.01,"Position":94.91,"MaxDrawDownRate":2.4,"HoldCount":1,"Cash":59827.23,"MonthProfitRate":7.48,"ProfitLossProportion":81.82,"CloseMaxLossReturn":-1.31,"CloseMaxProfitReturn":6.72,"MonthTradeCount":6}
             */

            private int Id;
            private int Type;
            private int Status;
            private boolean IsMyPortfolio;
            private Object UserId;
            private Object UserNickName;
            private Object UserAvatar;
            private String Name;
            private String Desc;
            private boolean IsAlong;
            private boolean IsTracking;
            private boolean IsSubscribe;
            private boolean IsReward;
            private boolean IsSmsNotic;
            private int RewardCount;
            private int AlongCount;
            private int TrackingCount;
            private int ShareCount;
            private int Subscribe;
            private int CommentCount;
            private String CreateTime;
            private PorfolioAttributeBean PorfolioAttribute;
            private PorfolioOtherBean PorfolioOther;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public boolean isIsMyPortfolio() {
                return IsMyPortfolio;
            }

            public void setIsMyPortfolio(boolean IsMyPortfolio) {
                this.IsMyPortfolio = IsMyPortfolio;
            }

            public Object getUserId() {
                return UserId;
            }

            public void setUserId(Object UserId) {
                this.UserId = UserId;
            }

            public Object getUserNickName() {
                return UserNickName;
            }

            public void setUserNickName(Object UserNickName) {
                this.UserNickName = UserNickName;
            }

            public Object getUserAvatar() {
                return UserAvatar;
            }

            public void setUserAvatar(Object UserAvatar) {
                this.UserAvatar = UserAvatar;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getDesc() {
                return Desc;
            }

            public void setDesc(String Desc) {
                this.Desc = Desc;
            }

            public boolean isIsAlong() {
                return IsAlong;
            }

            public void setIsAlong(boolean IsAlong) {
                this.IsAlong = IsAlong;
            }

            public boolean isIsTracking() {
                return IsTracking;
            }

            public void setIsTracking(boolean IsTracking) {
                this.IsTracking = IsTracking;
            }

            public boolean isIsSubscribe() {
                return IsSubscribe;
            }

            public void setIsSubscribe(boolean IsSubscribe) {
                this.IsSubscribe = IsSubscribe;
            }

            public boolean isIsReward() {
                return IsReward;
            }

            public void setIsReward(boolean IsReward) {
                this.IsReward = IsReward;
            }

            public boolean isIsSmsNotic() {
                return IsSmsNotic;
            }

            public void setIsSmsNotic(boolean IsSmsNotic) {
                this.IsSmsNotic = IsSmsNotic;
            }

            public int getRewardCount() {
                return RewardCount;
            }

            public void setRewardCount(int RewardCount) {
                this.RewardCount = RewardCount;
            }

            public int getAlongCount() {
                return AlongCount;
            }

            public void setAlongCount(int AlongCount) {
                this.AlongCount = AlongCount;
            }

            public int getTrackingCount() {
                return TrackingCount;
            }

            public void setTrackingCount(int TrackingCount) {
                this.TrackingCount = TrackingCount;
            }

            public int getShareCount() {
                return ShareCount;
            }

            public void setShareCount(int ShareCount) {
                this.ShareCount = ShareCount;
            }

            public int getSubscribe() {
                return Subscribe;
            }

            public void setSubscribe(int Subscribe) {
                this.Subscribe = Subscribe;
            }

            public int getCommentCount() {
                return CommentCount;
            }

            public void setCommentCount(int CommentCount) {
                this.CommentCount = CommentCount;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public PorfolioAttributeBean getPorfolioAttribute() {
                return PorfolioAttribute;
            }

            public void setPorfolioAttribute(PorfolioAttributeBean PorfolioAttribute) {
                this.PorfolioAttribute = PorfolioAttribute;
            }

            public PorfolioOtherBean getPorfolioOther() {
                return PorfolioOther;
            }

            public void setPorfolioOther(PorfolioOtherBean PorfolioOther) {
                this.PorfolioOther = PorfolioOther;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "Id=" + Id +
                        ", Type=" + Type +
                        ", Status=" + Status +
                        ", IsMyPortfolio=" + IsMyPortfolio +
                        ", UserId=" + UserId +
                        ", UserNickName=" + UserNickName +
                        ", UserAvatar=" + UserAvatar +
                        ", Name='" + Name + '\'' +
                        ", Desc='" + Desc + '\'' +
                        ", IsAlong=" + IsAlong +
                        ", IsTracking=" + IsTracking +
                        ", IsSubscribe=" + IsSubscribe +
                        ", IsReward=" + IsReward +
                        ", IsSmsNotic=" + IsSmsNotic +
                        ", RewardCount=" + RewardCount +
                        ", AlongCount=" + AlongCount +
                        ", TrackingCount=" + TrackingCount +
                        ", ShareCount=" + ShareCount +
                        ", Subscribe=" + Subscribe +
                        ", CommentCount=" + CommentCount +
                        ", CreateTime='" + CreateTime + '\'' +
                        ", PorfolioAttribute=" + PorfolioAttribute +
                        ", PorfolioOther=" + PorfolioOther +
                        '}';
            }

            public static class PorfolioAttributeBean {
                /**
                 * MinFollow : 10000
                 * LimtAmount : 100000
                 */

                private int MinFollow;
                private int LimtAmount;

                public int getMinFollow() {
                    return MinFollow;
                }

                public void setMinFollow(int MinFollow) {
                    this.MinFollow = MinFollow;
                }

                public int getLimtAmount() {
                    return LimtAmount;
                }

                public void setLimtAmount(int LimtAmount) {
                    this.LimtAmount = LimtAmount;
                }

                @Override
                public String toString() {
                    return "PorfolioAttributeBean{" +
                            "MinFollow=" + MinFollow +
                            ", LimtAmount=" + LimtAmount +
                            '}';
                }
            }

            public static class PorfolioOtherBean {
                /**
                 * CumulativeReturn : 17.32
                 * CumulativeProfit : 173190.1
                 * ActualRunDay : 56
                 * NetValue : 1174387.23
                 * Return : -0.01
                 * Position : 94.91
                 * MaxDrawDownRate : 2.4
                 * HoldCount : 1
                 * Cash : 59827.23
                 * MonthProfitRate : 7.48
                 * ProfitLossProportion : 81.82
                 * CloseMaxLossReturn : -1.31
                 * CloseMaxProfitReturn : 6.72
                 * MonthTradeCount : 6
                 */

                private double CumulativeReturn;
                private double CumulativeProfit;
                private int ActualRunDay;
                private double NetValue;
                private double Return;
                private double Position;
                private double MaxDrawDownRate;
                private int HoldCount;
                private double Cash;
                private double MonthProfitRate;
                private double ProfitLossProportion;
                private double CloseMaxLossReturn;
                private double CloseMaxProfitReturn;
                private int MonthTradeCount;

                public double getCumulativeReturn() {
                    return CumulativeReturn;
                }

                public void setCumulativeReturn(double CumulativeReturn) {
                    this.CumulativeReturn = CumulativeReturn;
                }

                public double getCumulativeProfit() {
                    return CumulativeProfit;
                }

                public void setCumulativeProfit(double CumulativeProfit) {
                    this.CumulativeProfit = CumulativeProfit;
                }

                public int getActualRunDay() {
                    return ActualRunDay;
                }

                public void setActualRunDay(int ActualRunDay) {
                    this.ActualRunDay = ActualRunDay;
                }

                public double getNetValue() {
                    return NetValue;
                }

                public void setNetValue(double NetValue) {
                    this.NetValue = NetValue;
                }

                public double getReturn() {
                    return Return;
                }

                public void setReturn(double Return) {
                    this.Return = Return;
                }

                public double getPosition() {
                    return Position;
                }

                public void setPosition(double Position) {
                    this.Position = Position;
                }

                public double getMaxDrawDownRate() {
                    return MaxDrawDownRate;
                }

                public void setMaxDrawDownRate(double MaxDrawDownRate) {
                    this.MaxDrawDownRate = MaxDrawDownRate;
                }

                public int getHoldCount() {
                    return HoldCount;
                }

                public void setHoldCount(int HoldCount) {
                    this.HoldCount = HoldCount;
                }

                public double getCash() {
                    return Cash;
                }

                public void setCash(double Cash) {
                    this.Cash = Cash;
                }

                public double getMonthProfitRate() {
                    return MonthProfitRate;
                }

                public void setMonthProfitRate(double MonthProfitRate) {
                    this.MonthProfitRate = MonthProfitRate;
                }

                public double getProfitLossProportion() {
                    return ProfitLossProportion;
                }

                public void setProfitLossProportion(double ProfitLossProportion) {
                    this.ProfitLossProportion = ProfitLossProportion;
                }

                public double getCloseMaxLossReturn() {
                    return CloseMaxLossReturn;
                }

                public void setCloseMaxLossReturn(double CloseMaxLossReturn) {
                    this.CloseMaxLossReturn = CloseMaxLossReturn;
                }

                public double getCloseMaxProfitReturn() {
                    return CloseMaxProfitReturn;
                }

                public void setCloseMaxProfitReturn(double CloseMaxProfitReturn) {
                    this.CloseMaxProfitReturn = CloseMaxProfitReturn;
                }

                public int getMonthTradeCount() {
                    return MonthTradeCount;
                }

                public void setMonthTradeCount(int MonthTradeCount) {
                    this.MonthTradeCount = MonthTradeCount;
                }

                @Override
                public String toString() {
                    return "PorfolioOtherBean{" +
                            "CumulativeReturn=" + CumulativeReturn +
                            ", CumulativeProfit=" + CumulativeProfit +
                            ", ActualRunDay=" + ActualRunDay +
                            ", NetValue=" + NetValue +
                            ", Return=" + Return +
                            ", Position=" + Position +
                            ", MaxDrawDownRate=" + MaxDrawDownRate +
                            ", HoldCount=" + HoldCount +
                            ", Cash=" + Cash +
                            ", MonthProfitRate=" + MonthProfitRate +
                            ", ProfitLossProportion=" + ProfitLossProportion +
                            ", CloseMaxLossReturn=" + CloseMaxLossReturn +
                            ", CloseMaxProfitReturn=" + CloseMaxProfitReturn +
                            ", MonthTradeCount=" + MonthTradeCount +
                            '}';
                }
            }
        }
    }
}
