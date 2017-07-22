package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class CeLueListInfo {


    /**
     * Status : 0
     * Msg :
     */

    private HeadBean Head;
    private ResultBean Result;

    /**
     * PageInfo : {"PageIndex":0,"PageCount":7,"PageSize":10}
     * Strategies : [{"Id":"1593","Recruitment":0,"Name":"爱猫爪2号","Desc":null,"TargetReturns":10,"MaxDay":4,"ShareRatio":0,"MinFollow":10000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":5,"RecuitmentStartTime":"2016-08-22 06:08:00","RecuitmentEndTime":"2016-08-24 06:08:00","RunStartDay":"2016-08-24 06:09:00","RunEndDay":null},{"Id":"1592","Recruitment":1,"Name":"实盈量化第二期","Desc":"这是一个量化策略组合","TargetReturns":10,"MaxDay":5,"ShareRatio":0,"MinFollow":10000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":false,"IsNotStartRecruit":false,"IsStartInvestment":false,"IsStartRecruit":true,"IsStartRun":false,"StopLoss":5,"RecuitmentStartTime":"2016-08-25 06:02:00","RecuitmentEndTime":"2016-08-26 06:02:00","RunStartDay":"2016-08-26 04:55:00","RunEndDay":null},{"Id":"1590","Recruitment":100,"Name":"爱猫爪1号","Desc":"测试一下","TargetReturns":5,"MaxDay":1,"ShareRatio":0,"MinFollow":10000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":false,"IsNotStartRecruit":false,"IsStartInvestment":false,"IsStartRecruit":true,"IsStartRun":false,"StopLoss":0.9,"RecuitmentStartTime":"2016-08-25 02:19:00","RecuitmentEndTime":"2016-08-26 02:19:00","RunStartDay":"2016-08-26 02:20:00","RunEndDay":null},{"Id":"1578","Recruitment":0,"Name":"测试","Desc":"123","TargetReturns":1,"MaxDay":8,"ShareRatio":10,"MinFollow":1,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":10,"RecuitmentStartTime":"2016-08-22 09:26:00","RecuitmentEndTime":"2016-08-23 09:26:00","RunStartDay":"2016-08-23 09:27:00","RunEndDay":null},{"Id":"1577","Recruitment":20,"Name":"猫猫","Desc":"猫猫猫猫","TargetReturns":1,"MaxDay":5,"ShareRatio":1,"MinFollow":100,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":2,"RecuitmentStartTime":"2016-08-19 09:21:00","RecuitmentEndTime":"2016-08-20 09:21:00","RunStartDay":"2016-08-21 09:21:00","RunEndDay":"2016-08-23 05:02:51"},{"Id":"1566","Recruitment":0.45,"Name":"爱猫爪1","Desc":"爱猫爪1--发发发","TargetReturns":5,"MaxDay":3,"ShareRatio":10,"MinFollow":1000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":4.78,"RecuitmentStartTime":"2016-08-22 08:24:00","RecuitmentEndTime":"2016-08-23 08:24:00","RunStartDay":"2016-08-23 07:17:00","RunEndDay":"2016-08-25 02:16:43"},{"Id":"1565","Recruitment":4,"Name":"实盈量化第一期","Desc":"这一是一个测试的组合，请慎用","TargetReturns":15,"MaxDay":6,"ShareRatio":0,"MinFollow":10000,"UserName":null,"UserImgUrl":null,"IsEndInvestment":false,"IsEndRecruit":true,"IsNotStartRecruit":false,"IsStartInvestment":true,"IsStartRecruit":false,"IsStartRun":true,"StopLoss":5,"RecuitmentStartTime":"2016-08-21 03:59:00","RecuitmentEndTime":"2016-08-22 15:59:00","RunStartDay":"2016-08-22 17:59:00","RunEndDay":null}]
     */

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
        return "CeLueListInfo{" +
                "Head=" + Head +
                ", Result=" + Result +
                '}';
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
         * Id : 1593
         * Recruitment : 0.0
         * Name : 爱猫爪2号
         * Desc : null
         * TargetReturns : 10.0
         * MaxDay : 4
         * ShareRatio : 0.0
         * MinFollow : 10000.0
         * UserName : null
         * UserImgUrl : null
         * IsEndInvestment : false
         * IsEndRecruit : true
         * IsNotStartRecruit : false
         * IsStartInvestment : true
         * IsStartRecruit : false
         * IsStartRun : true
         * StopLoss : 5.0
         * RecuitmentStartTime : 2016-08-22 06:08:00
         * RecuitmentEndTime : 2016-08-24 06:08:00
         * RunStartDay : 2016-08-24 06:09:00
         * RunEndDay : null
         */



        /**新增  **/

        private PageInfo PageInfo;
        private List<b> Data;

        public List<b> getData() {
            return Data;
        }

        public void setData(List<b> data) {
            Data = data;
        }

        public ResultBean.PageInfo getPageInfo() {
            return PageInfo;
        }

        public void setPageInfo(ResultBean.PageInfo pageInfo) {
            PageInfo = pageInfo;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "PageInfo=" + PageInfo +
                    ", Data=" + Data +
                    '}';
        }

        public static class PageInfo{
            private int PageIndex;
            private int PageCount;
            private int PageSize;

            public int getPageIndex() {
                return PageIndex;
            }

            public void setPageIndex(int pageIndex) {
                PageIndex = pageIndex;
            }

            public int getPageCount() {
                return PageCount;
            }

            public void setPageCount(int pageCount) {
                PageCount = pageCount;
            }

            public int getPageSize() {
                return PageSize;
            }

            public void setPageSize(int pageSize) {
                PageSize = pageSize;
            }

            @Override
            public String toString() {
                return "PageInfo{" +
                        "PageIndex=" + PageIndex +
                        ", PageCount=" + PageCount +
                        ", PageSize=" + PageSize +
                        '}';
        }
        }

        public static class b{
            private    String  Id ;
            private    int Type ;
            private     int Status ;
            private    boolean IsMyPortfolio;
            private     String UserId            ;
            private     String  UserNickName;
            private  String  UserAvatar;
            private   String Name;
            private  String Desc ;
            private  Boolean IsAlong ;
            private  boolean IsTracking ;
            private  boolean IsSubscribe ;
            private   boolean IsReward;
            private  boolean IsSmsNotic;
            private int  RewardCount ;
            private int   AlongCount;
            private int   TrackingCount;
            private int   ShareCount;
            private int   Subscribe;
            private int   CommentCount;
            private String CreateTime;
            private  static profol PorfolioAttribute;
            private  static profolother PorfolioOther;

            public boolean isSubscribe() {
                return IsSubscribe;
            }

            public void setSubscribe(boolean subscribe) {
                IsSubscribe = subscribe;
            }

            public boolean isTracking() {
                return IsTracking;
            }

            public void setTracking(boolean tracking) {
                IsTracking = tracking;
            }

            public Boolean getAlong() {
                return IsAlong;
            }

            public void setAlong(Boolean along) {
                IsAlong = along;
            }

            public String getDesc() {
                return Desc;
            }

            public void setDesc(String desc) {
                Desc = desc;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getUserAvatar() {
                return UserAvatar;
            }

            public void setUserAvatar(String userAvatar) {
                UserAvatar = userAvatar;
            }

            public String getUserNickName() {
                return UserNickName;
            }

            public void setUserNickName(String userNickName) {
                UserNickName = userNickName;
            }

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

            public int getStatus() {
                return Status;
            }

            public void setStatus(int status) {
                Status = status;
            }

            public int getType() {
                return Type;
            }

            public void setType(int type) {
                Type = type;
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public boolean isSmsNotic() {
                return IsSmsNotic;
            }

            public void setSmsNotic(boolean smsNotic) {
                IsSmsNotic = smsNotic;
            }

            public boolean isReward() {
                return IsReward;
            }

            public void setReward(boolean reward) {
                IsReward = reward;
            }

            public int getRewardCount() {
                return RewardCount;
            }

            public void setRewardCount(int rewardCount) {
                RewardCount = rewardCount;
            }

            public int getAlongCount() {
                return AlongCount;
            }

            public void setAlongCount(int alongCount) {
                AlongCount = alongCount;
            }

            public int getTrackingCount() {
                return TrackingCount;
            }

            public void setTrackingCount(int trackingCount) {
                TrackingCount = trackingCount;
            }

            public int getShareCount() {
                return ShareCount;
            }

            public void setShareCount(int shareCount) {
                ShareCount = shareCount;
            }

            public int getSubscribe() {
                return Subscribe;
            }

            public void setSubscribe(int subscribe) {
                Subscribe = subscribe;
            }

            public int getCommentCount() {
                return CommentCount;
            }

            public void setCommentCount(int commentCount) {
                CommentCount = commentCount;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String createTime) {
                CreateTime = createTime;
            }

            public profol getPorfolioAttribute() {
                return PorfolioAttribute;
            }

            public void setPorfolioAttribute(profol porfolioAttribute) {
                PorfolioAttribute = porfolioAttribute;
            }

            public profolother getPorfolioOther() {
                return PorfolioOther;
            }

            public void setPorfolioOther(profolother porfolioOther) {
                PorfolioOther = porfolioOther;
            }

            @Override
            public String toString() {
                return "b{" +
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

        public static class profol{
            private int MinFollow;
            private int LimtAmount;

            public int getMinFollow() {
                return MinFollow;
            }

            public void setMinFollow(int minFollow) {
                MinFollow = minFollow;
            }

            public int getLimtAmount() {
                return LimtAmount;
            }

            public void setLimtAmount(int limtAmount) {
                LimtAmount = limtAmount;
            }

            @Override
            public String toString() {
                return "profol{" +
                        "MinFollow=" + MinFollow +
                        ", LimtAmount=" + LimtAmount +
                        '}';
            }
        }
        public static class profolother{
            private double  CumulativeReturn;
            private double  CumulativeProfit;
            private int  ActualRunDay;
            private double  NetValue;
            private double  Return;
            private double  Position;
            private double  MaxDrawDownRate;
            private int  HoldCount;
            private double  Cash;
            private double  MonthProfitRate;
            private double  ProfitLossProportion;
            private double  CloseMaxLossReturn ;
            private double  CloseMaxProfitReturn;
            private double  MonthTradeCount;



            public double getCumulativeReturn() {
                return CumulativeReturn;
            }

            public void setCumulativeReturn(double cumulativeReturn) {
                CumulativeReturn = cumulativeReturn;
            }

            public double getCumulativeProfit() {
                return CumulativeProfit;
            }

            public void setCumulativeProfit(double cumulativeProfit) {
                CumulativeProfit = cumulativeProfit;
            }

            public int getActualRunDay() {
                return ActualRunDay;
            }

            public void setActualRunDay(int actualRunDay) {
                ActualRunDay = actualRunDay;
            }

            public double getNetValue() {
                return NetValue;
            }

            public void setNetValue(double netValue) {
                NetValue = netValue;
            }

            public double getReturn() {
                return Return;
            }

            public void setReturn(double aReturn) {
                Return = aReturn;
            }

            public double getPosition() {
                return Position;
            }

            public void setPosition(double position) {
                Position = position;
            }

            public double getMaxDrawDownRate() {
                return MaxDrawDownRate;
            }

            public void setMaxDrawDownRate(double maxDrawDownRate) {
                MaxDrawDownRate = maxDrawDownRate;
            }

            public int getHoldCount() {
                return HoldCount;
            }

            public void setHoldCount(int holdCount) {
                HoldCount = holdCount;
            }

            public double getCash() {
                return Cash;
            }

            public void setCash(double cash) {
                Cash = cash;
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

            public double getCloseMaxLossReturn() {
                return CloseMaxLossReturn;
            }

            public void setCloseMaxLossReturn(double closeMaxLossReturn) {
                CloseMaxLossReturn = closeMaxLossReturn;
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
                return "profolother{" +
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

//        private List<StrategiesBean> Strategies;
//
//        public List<StrategiesBean> getStrategies() {
//            return Strategies;
//        }
//
//        public void setStrategies(List<StrategiesBean> Strategies) {
//            this.Strategies = Strategies;
//        }
//
//        public static class StrategiesBean {
//            private String Id;
//            private double Recruitment;
//            private String Name;
//            private Object Desc;
//            private double TargetReturns;
//            private int MaxDay;
//            private double ShareRatio;
//            private double MinFollow;
//            private Object UserName;
//            private Object UserImgUrl;
//            private boolean IsEndInvestment;
//            private boolean IsEndRecruit;
//            private boolean IsNotStartRecruit;
//            private boolean IsStartInvestment;
//            private boolean IsStartRecruit;
//            private boolean IsStartRun;
//            private double StopLoss;
//            private String RecuitmentStartTime;
//            private String RecuitmentEndTime;
//            private String RunStartDay;
//            private Object RunEndDay;
//            private double TotalReturn;
//            private String RunEndState;
//
//            public String getRunEndState() {
//                return RunEndState;
//            }
//
//            public void setRunEndState(String runEndState) {
//                RunEndState = runEndState;
//            }
//
//            public double getTotalReturn() {
//                return TotalReturn;
//            }
//
//            public void setTotalReturn(double totalReturn) {
//                TotalReturn = totalReturn;
//            }
//
//            public String getId() {
//                return Id;
//            }
//
//            public void setId(String Id) {
//                this.Id = Id;
//            }
//
//            public double getRecruitment() {
//                return Recruitment;
//            }
//
//            public void setRecruitment(double Recruitment) {
//                this.Recruitment = Recruitment;
//            }
//
//            public String getName() {
//                return Name;
//            }
//
//            public void setName(String Name) {
//                this.Name = Name;
//            }
//
//            public Object getDesc() {
//                return Desc;
//            }
//
//            public void setDesc(Object Desc) {
//                this.Desc = Desc;
//            }
//
//            public double getTargetReturns() {
//                return TargetReturns;
//            }
//
//            public void setTargetReturns(double TargetReturns) {
//                this.TargetReturns = TargetReturns;
//            }
//
//            public int getMaxDay() {
//                return MaxDay;
//            }
//
//            public void setMaxDay(int MaxDay) {
//                this.MaxDay = MaxDay;
//            }
//
//            public double getShareRatio() {
//                return ShareRatio;
//            }
//
//            public void setShareRatio(double ShareRatio) {
//                this.ShareRatio = ShareRatio;
//            }
//
//            public double getMinFollow() {
//                return MinFollow;
//            }
//
//            public void setMinFollow(double MinFollow) {
//                this.MinFollow = MinFollow;
//            }
//
//            public Object getUserName() {
//                return UserName;
//            }
//
//            public void setUserName(Object UserName) {
//                this.UserName = UserName;
//            }
//
//            public Object getUserImgUrl() {
//                return UserImgUrl;
//            }
//
//            public void setUserImgUrl(Object UserImgUrl) {
//                this.UserImgUrl = UserImgUrl;
//            }
//
//            public boolean isIsEndInvestment() {
//                return IsEndInvestment;
//            }
//
//            public void setIsEndInvestment(boolean IsEndInvestment) {
//                this.IsEndInvestment = IsEndInvestment;
//            }
//
//            public boolean isIsEndRecruit() {
//                return IsEndRecruit;
//            }
//
//            public void setIsEndRecruit(boolean IsEndRecruit) {
//                this.IsEndRecruit = IsEndRecruit;
//            }
//
//            public boolean isIsNotStartRecruit() {
//                return IsNotStartRecruit;
//            }
//
//            public void setIsNotStartRecruit(boolean IsNotStartRecruit) {
//                this.IsNotStartRecruit = IsNotStartRecruit;
//            }
//
//            public boolean isIsStartInvestment() {
//                return IsStartInvestment;
//            }
//
//            public void setIsStartInvestment(boolean IsStartInvestment) {
//                this.IsStartInvestment = IsStartInvestment;
//            }
//
//            public boolean isIsStartRecruit() {
//                return IsStartRecruit;
//            }
//
//            public void setIsStartRecruit(boolean IsStartRecruit) {
//                this.IsStartRecruit = IsStartRecruit;
//            }
//
//            public boolean isIsStartRun() {
//                return IsStartRun;
//            }
//
//            public void setIsStartRun(boolean IsStartRun) {
//                this.IsStartRun = IsStartRun;
//            }
//
//            public double getStopLoss() {
//                return StopLoss;
//            }
//
//            public void setStopLoss(double StopLoss) {
//                this.StopLoss = StopLoss;
//            }
//
//            public String getRecuitmentStartTime() {
//                return RecuitmentStartTime;
//            }
//
//            public void setRecuitmentStartTime(String RecuitmentStartTime) {
//                this.RecuitmentStartTime = RecuitmentStartTime;
//            }
//
//            public String getRecuitmentEndTime() {
//                return RecuitmentEndTime;
//            }
//
//            public void setRecuitmentEndTime(String RecuitmentEndTime) {
//                this.RecuitmentEndTime = RecuitmentEndTime;
//            }
//
//            public String getRunStartDay() {
//                return RunStartDay;
//            }
//
//            public void setRunStartDay(String RunStartDay) {
//                this.RunStartDay = RunStartDay;
//            }
//
//            public Object getRunEndDay() {
//                return RunEndDay;
//            }
//
//            public void setRunEndDay(Object RunEndDay) {
//                this.RunEndDay = RunEndDay;
//            }
//        }
   }
}
