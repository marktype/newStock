package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/18 0018.
 */

public class MyzuheThree {

    /**
     * Head : {"Status":0,"Msg":"success"}
     * Result : {"PageInfo":{"PageIndex":0,"PageCount":1,"PageSize":10},"Data":[{"Id":"1189","Type":2,"Status":0,"IsMyPortfolio":true,"UserId":"5dd39abb4ccd80c2","UserNickName":"我的名字","UserAvatar":"https://newapi.imaozhua.com:4451/20170316173604323.jpg","Name":"我的小金库","Desc":"哈哈😄","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":0,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":0,"CreateTime":"2017-03-16 17:26:55","PorfolioAttribute":{"MinFollow":0,"LimtAmount":0},"PorfolioOther":{"CumulativeReturn":-4.28,"CumulativeProfit":-4278.57,"ActualRunDay":2,"NetValue":95797.13,"Return":-0.08,"Position":64.12,"MaxDrawDownRate":4.2,"HoldCount":2,"Cash":34372.13,"MonthProfitRate":-4.28,"ProfitLossProportion":0,"CloseMaxLossReturn":-5.3,"CloseMaxProfitReturn":0,"MonthTradeCount":2}}]}
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
         * PageInfo : {"PageIndex":0,"PageCount":1,"PageSize":10}
         * Data : [{"Id":"1189","Type":2,"Status":0,"IsMyPortfolio":true,"UserId":"5dd39abb4ccd80c2","UserNickName":"我的名字","UserAvatar":"https://newapi.imaozhua.com:4451/20170316173604323.jpg","Name":"我的小金库","Desc":"哈哈😄","IsAlong":false,"IsTracking":false,"IsSubscribe":false,"IsReward":false,"IsSmsNotic":false,"RewardCount":0,"AlongCount":0,"TrackingCount":0,"ShareCount":0,"Subscribe":0,"CommentCount":0,"CreateTime":"2017-03-16 17:26:55","PorfolioAttribute":{"MinFollow":0,"LimtAmount":0},"PorfolioOther":{"CumulativeReturn":-4.28,"CumulativeProfit":-4278.57,"ActualRunDay":2,"NetValue":95797.13,"Return":-0.08,"Position":64.12,"MaxDrawDownRate":4.2,"HoldCount":2,"Cash":34372.13,"MonthProfitRate":-4.28,"ProfitLossProportion":0,"CloseMaxLossReturn":-5.3,"CloseMaxProfitReturn":0,"MonthTradeCount":2}}]
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

        public static class PageInfoBean {
            /**
             * PageIndex : 0
             * PageCount : 1
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
             * Id : 1189
             * Type : 2
             * Status : 0
             * IsMyPortfolio : true
             * UserId : 5dd39abb4ccd80c2
             * UserNickName : 我的名字
             * UserAvatar : https://newapi.imaozhua.com:4451/20170316173604323.jpg
             * Name : 我的小金库
             * Desc : 哈哈😄
             * IsAlong : false
             * IsTracking : false
             * IsSubscribe : false
             * IsReward : false
             * IsSmsNotic : false
             * RewardCount : 0
             * AlongCount : 0
             * TrackingCount : 0
             * ShareCount : 0
             * Subscribe : 0
             * CommentCount : 0
             * CreateTime : 2017-03-16 17:26:55
             * PorfolioAttribute : {"MinFollow":0,"LimtAmount":0}
             * PorfolioOther : {"CumulativeReturn":-4.28,"CumulativeProfit":-4278.57,"ActualRunDay":2,"NetValue":95797.13,"Return":-0.08,"Position":64.12,"MaxDrawDownRate":4.2,"HoldCount":2,"Cash":34372.13,"MonthProfitRate":-4.28,"ProfitLossProportion":0,"CloseMaxLossReturn":-5.3,"CloseMaxProfitReturn":0,"MonthTradeCount":2}
             */

            private String Id;
            private int Type;
            private int Status;
            private boolean IsMyPortfolio;
            private String UserId;
            private String UserNickName;
            private String UserAvatar;
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

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
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

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getUserNickName() {
                return UserNickName;
            }

            public void setUserNickName(String UserNickName) {
                this.UserNickName = UserNickName;
            }

            public String getUserAvatar() {
                return UserAvatar;
            }

            public void setUserAvatar(String UserAvatar) {
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

            public static class PorfolioAttributeBean {
                /**
                 * MinFollow : 0
                 * LimtAmount : 0
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
                 * CumulativeReturn : -4.28
                 * CumulativeProfit : -4278.57
                 * ActualRunDay : 2
                 * NetValue : 95797.13
                 * Return : -0.08
                 * Position : 64.12
                 * MaxDrawDownRate : 4.2
                 * HoldCount : 2
                 * Cash : 34372.13
                 * MonthProfitRate : -4.28
                 * ProfitLossProportion : 0
                 * CloseMaxLossReturn : -5.3
                 * CloseMaxProfitReturn : 0
                 * MonthTradeCount : 2
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
                private double MonthTradeCount;

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

                public void setProfitLossProportion(double profitLossProportion) {
                    ProfitLossProportion = profitLossProportion;
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

                public void setCloseMaxProfitReturn(double closeMaxProfitReturn) {
                    CloseMaxProfitReturn = closeMaxProfitReturn;
                }

                public double getMonthTradeCount() {
                    return MonthTradeCount;
                }

                public void setMonthTradeCount(double monthTradeCount) {
                    MonthTradeCount = monthTradeCount;
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

            @Override
            public String toString() {
                return "DataBean{" +
                        "Id='" + Id + '\'' +
                        ", Type=" + Type +
                        ", Status=" + Status +
                        ", IsMyPortfolio=" + IsMyPortfolio +
                        ", UserId='" + UserId + '\'' +
                        ", UserNickName='" + UserNickName + '\'' +
                        ", UserAvatar='" + UserAvatar + '\'' +
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
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "PageInfo=" + PageInfo +
                    ", Data=" + Data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyzuheThree{" +
                "Head=" + Head +
                ", Result=" + Result +
                '}';
    }
}
