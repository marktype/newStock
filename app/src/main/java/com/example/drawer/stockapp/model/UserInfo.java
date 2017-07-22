package com.example.drawer.stockapp.model;

/**
 * Created by 欢大哥 on 2016/7/8.
 */
public class UserInfo {
//    /**
//     * Head : {"Status":0,"Msg":"success"}
//     * Result : {"Id":"5dd39abb4ccd80c2","NickName":"我的名字","Avatar":"https://newapi.imaozhua.com:4451/20170316173604323.jpg","Desc":null,"Sex":0,"RcmdStock":0,"Concerns":0,"Fans":0,"Favorites":0,"Credit":1006,"TotalCredit":1006,"AverageCumulativeReturn":0,"WinRate":0,"IsPush":true,"IsConcerns":true,"IsInitPassword":true}
//     */
//
//    private HeadBean Head;
//    private ResultBean Result;
//
//    public HeadBean getHead() {
//        return Head;
//    }
//
//    public void setHead(HeadBean Head) {
//        this.Head = Head;
//    }
//
//    public ResultBean getResult() {
//        return Result;
//    }
//
//    public void setResult(ResultBean Result) {
//        this.Result = Result;
//    }
//
//    @Override
//    public String toString() {
//        return "UserInfo{" +
//                "Head=" + Head +
//                ", Result=" + Result +
//                '}';
//    }
//
//    public static class HeadBean {
//        /**
//         * Status : 0
//         * Msg : success
//         */
//
//        private int Status;
//        private String Msg;
//
//        public int getStatus() {
//            return Status;
//        }
//
//        public void setStatus(int Status) {
//            this.Status = Status;
//        }
//
//        public String getMsg() {
//            return Msg;
//        }
//
//        public void setMsg(String Msg) {
//            this.Msg = Msg;
//        }
//
//        @Override
//        public String toString() {
//            return "HeadBean{" +
//                    "Status=" + Status +
//                    ", Msg='" + Msg + '\'' +
//                    '}';
//        }
//    }
//
//    public static class ResultBean {
//        /**
//         * Id : 5dd39abb4ccd80c2
//         * NickName : 我的名字
//         * Avatar : https://newapi.imaozhua.com:4451/20170316173604323.jpg
//         * Desc : null
//         * Sex : 0
//         * RcmdStock : 0
//         * Concerns : 0
//         * Fans : 0
//         * Favorites : 0
//         * Credit : 1006
//         * TotalCredit : 1006
//         * AverageCumulativeReturn : 0
//         * WinRate : 0
//         * IsPush : true
//         * IsConcerns : true
//         * IsInitPassword : true
//         */
//
//        private String Id;
//        private String NickName;
//        private String Avatar;
//        private Object Desc;
//        private int Sex;
//        private int RcmdStock;
//        private int Concerns;
//        private int Fans;
//        private int Favorites;
//        private int Credit;
//        private int TotalCredit;
//        private int AverageCumulativeReturn;
//        private int WinRate;
//        private boolean IsPush;
//        private boolean IsConcerns;
//        private boolean IsInitPassword;
//
//        public String getId() {
//            return Id;
//        }
//
//        public void setId(String Id) {
//            this.Id = Id;
//        }
//
//        public String getNickName() {
//            return NickName;
//        }
//
//        public void setNickName(String NickName) {
//            this.NickName = NickName;
//        }
//
//        public String getAvatar() {
//            return Avatar;
//        }
//
//        public void setAvatar(String Avatar) {
//            this.Avatar = Avatar;
//        }
//
//        public Object getDesc() {
//            return Desc;
//        }
//
//        public void setDesc(Object Desc) {
//            this.Desc = Desc;
//        }
//
//        public int getSex() {
//            return Sex;
//        }
//
//        public void setSex(int Sex) {
//            this.Sex = Sex;
//        }
//
//        public int getRcmdStock() {
//            return RcmdStock;
//        }
//
//        public void setRcmdStock(int RcmdStock) {
//            this.RcmdStock = RcmdStock;
//        }
//
//        public int getConcerns() {
//            return Concerns;
//        }
//
//        public void setConcerns(int Concerns) {
//            this.Concerns = Concerns;
//        }
//
//        public int getFans() {
//            return Fans;
//        }
//
//        public void setFans(int Fans) {
//            this.Fans = Fans;
//        }
//
//        public int getFavorites() {
//            return Favorites;
//        }
//
//        public void setFavorites(int Favorites) {
//            this.Favorites = Favorites;
//        }
//
//        public int getCredit() {
//            return Credit;
//        }
//
//        public void setCredit(int Credit) {
//            this.Credit = Credit;
//        }
//
//        public int getTotalCredit() {
//            return TotalCredit;
//        }
//
//        public void setTotalCredit(int TotalCredit) {
//            this.TotalCredit = TotalCredit;
//        }
//
//        public int getAverageCumulativeReturn() {
//            return AverageCumulativeReturn;
//        }
//
//        public void setAverageCumulativeReturn(int AverageCumulativeReturn) {
//            this.AverageCumulativeReturn = AverageCumulativeReturn;
//        }
//
//        public int getWinRate() {
//            return WinRate;
//        }
//
//        public void setWinRate(int WinRate) {
//            this.WinRate = WinRate;
//        }
//
//        public boolean isIsPush() {
//            return IsPush;
//        }
//
//        public void setIsPush(boolean IsPush) {
//            this.IsPush = IsPush;
//        }
//
//        public boolean isIsConcerns() {
//            return IsConcerns;
//        }
//
//        public void setIsConcerns(boolean IsConcerns) {
//            this.IsConcerns = IsConcerns;
//        }
//
//        public boolean isIsInitPassword() {
//            return IsInitPassword;
//        }
//
//        public void setIsInitPassword(boolean IsInitPassword) {
//            this.IsInitPassword = IsInitPassword;
//        }
//
//        @Override
//        public String toString() {
//            return "ResultBean{" +
//                    "Id='" + Id + '\'' +
//                    ", NickName='" + NickName + '\'' +
//                    ", Avatar='" + Avatar + '\'' +
//                    ", Desc=" + Desc +
//                    ", Sex=" + Sex +
//                    ", RcmdStock=" + RcmdStock +
//                    ", Concerns=" + Concerns +
//                    ", Fans=" + Fans +
//                    ", Favorites=" + Favorites +
//                    ", Credit=" + Credit +
//                    ", TotalCredit=" + TotalCredit +
//                    ", AverageCumulativeReturn=" + AverageCumulativeReturn +
//                    ", WinRate=" + WinRate +
//                    ", IsPush=" + IsPush +
//                    ", IsConcerns=" + IsConcerns +
//                    ", IsInitPassword=" + IsInitPassword +
//                    '}';
//        }
//    }


    /**
     * Status : 0
     * Msg : Success
     */

    private HeadBean Head;
    /**
     * RealName : null
     * Avatar : null
     * Title : null
     * Token : 3dfd8d5dc48f79e5
     * Id : 67b0ea55bb432229
     * NickName : null
     * Level : 0
     * Score : 0.0
     * BankCards : null
     * PhoneNum : 18228753625
     * Fans : 0
     * Follower : 0
     * Address : null
     * Sex : 0
     * SetPayPwd : false
     * UserName : null
     */

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

        @Override
        public String toString() {
            return "HeadBean{" +
                    "Status=" + Status +
                    ", Msg='" + Msg + '\'' +
                    '}';
        }
    }

    public static class ResultBean {
        private Object RealName;
        private Object Avatar;
        private String Token;
        private String Id;
        private String NickName;
        private String Level;
        private double Score;
        private Object BankCards;
        private String PhoneNum;
        private int Fans;
        private int Follower;
        private Object Address;
        private int Sex;
        private boolean SetPayPwd;
        private Object UserName;


        private String Desc; //简介
        private double RcmdStock;
        private int Concerns; //关注数
        private int Favorites; //收藏数
        private double Credit; //积分
        private double TotalCredit;
        private double AverageCumulativeReturn;
        private double WinRate;
        private boolean IsPush;
        private boolean IsConcerns;
        private boolean IsInitPassword;


        public String getDesc() {
            return Desc;
        }

        public void setDesc(String desc) {
            Desc = desc;
        }

        public double getRcmdStock() {
            return RcmdStock;
        }

        public void setRcmdStock(double rcmdStock) {
            RcmdStock = rcmdStock;
        }

        public int getConcerns() {
            return Concerns;
        }

        public void setConcerns(int concerns) {
            Concerns = concerns;
        }

        public int getFavorites() {
            return Favorites;
        }

        public void setFavorites(int favorites) {
            Favorites = favorites;
        }

        public double getCredit() {
            return Credit;
        }

        public void setCredit(double credit) {
            Credit = credit;
        }

        public double getTotalCredit() {
            return TotalCredit;
        }

        public void setTotalCredit(double totalCredit) {
            TotalCredit = totalCredit;
        }

        public double getAverageCumulativeReturn() {
            return AverageCumulativeReturn;
        }

        public void setAverageCumulativeReturn(double averageCumulativeReturn) {
            AverageCumulativeReturn = averageCumulativeReturn;
        }

        public double getWinRate() {
            return WinRate;
        }

        public void setWinRate(double winRate) {
            WinRate = winRate;
        }

        public boolean isPush() {
            return IsPush;
        }

        public void setPush(boolean push) {
            IsPush = push;
        }

        public boolean isConcerns() {
            return IsConcerns;
        }

        public void setConcerns(boolean concerns) {
            IsConcerns = concerns;
        }

        public boolean isInitPassword() {
            return IsInitPassword;
        }

        public void setInitPassword(boolean initPassword) {
            IsInitPassword = initPassword;
        }

        public Object getRealName() {
            return RealName;
        }

        public void setRealName(Object RealName) {
            this.RealName = RealName;
        }

        public Object getAvatar() {
            return Avatar;
        }

        public void setAvatar(Object Avatar) {
            this.Avatar = Avatar;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getLevel() {
            return Level;
        }

        public void setLevel(String Level) {
            this.Level = Level;
        }

        public double getScore() {
            return Score;
        }

        public void setScore(double Score) {
            this.Score = Score;
        }

        public Object getBankCards() {
            return BankCards;
        }

        public void setBankCards(Object BankCards) {
            this.BankCards = BankCards;
        }

        public String getPhoneNum() {
            return PhoneNum;
        }

        public void setPhoneNum(String PhoneNum) {
            this.PhoneNum = PhoneNum;
        }

        public int getFans() {
            return Fans;
        }

        public void setFans(int Fans) {
            this.Fans = Fans;
        }

        public int getFollower() {
            return Follower;
        }

        public void setFollower(int Follower) {
            this.Follower = Follower;
        }

        public Object getAddress() {
            return Address;
        }

        public void setAddress(Object Address) {
            this.Address = Address;
        }

        public int getSex() {
            return Sex;
        }

        public void setSex(int Sex) {
            this.Sex = Sex;
        }

        public boolean isSetPayPwd() {
            return SetPayPwd;
        }

        public void setSetPayPwd(boolean SetPayPwd) {
            this.SetPayPwd = SetPayPwd;
        }

        public Object getUserName() {
            return UserName;
        }

        public void setUserName(Object UserName) {
            this.UserName = UserName;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "RealName=" + RealName +
                    ", Avatar=" + Avatar +
                    ", Token='" + Token + '\'' +
                    ", Id='" + Id + '\'' +
                    ", NickName='" + NickName + '\'' +
                    ", Level='" + Level + '\'' +
                    ", Score=" + Score +
                    ", BankCards=" + BankCards +
                    ", PhoneNum='" + PhoneNum + '\'' +
                    ", Fans=" + Fans +
                    ", Follower=" + Follower +
                    ", Address=" + Address +
                    ", Sex=" + Sex +
                    ", SetPayPwd=" + SetPayPwd +
                    ", UserName=" + UserName +
                    ", Desc='" + Desc + '\'' +
                    ", RcmdStock=" + RcmdStock +
                    ", Concerns=" + Concerns +
                    ", Favorites=" + Favorites +
                    ", Credit=" + Credit +
                    ", TotalCredit=" + TotalCredit +
                    ", AverageCumulativeReturn=" + AverageCumulativeReturn +
                    ", WinRate=" + WinRate +
                    ", IsPush=" + IsPush +
                    ", IsConcerns=" + IsConcerns +
                    ", IsInitPassword=" + IsInitPassword +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "Head=" + Head +
                ", Result=" + Result +
                '}';
    }



}
