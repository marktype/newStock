package com.example.drawer.stockapp.model;

/**
 * Created by 欢大哥 on 2016/7/8.
 */
public class UserOuthreInfo {

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


    }


}
