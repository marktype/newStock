package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 欢大哥 on 2017/2/25.
 */
public class UserOutherInfoBean implements Parcelable{
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

    public UserOutherInfoBean() {
    }

    protected UserOutherInfoBean(Parcel in) {
        Token = in.readString();
        Id = in.readString();
        NickName = in.readString();
        Level = in.readString();
        Score = in.readDouble();
        PhoneNum = in.readString();
        Fans = in.readInt();
        Follower = in.readInt();
        Sex = in.readInt();
        SetPayPwd = in.readByte() != 0;
        Desc = in.readString();
        RcmdStock = in.readDouble();
        Concerns = in.readInt();
        Favorites = in.readInt();
        Credit = in.readDouble();
        TotalCredit = in.readDouble();
        AverageCumulativeReturn = in.readDouble();
        WinRate = in.readDouble();
        IsPush = in.readByte() != 0;
        IsConcerns = in.readByte() != 0;
        IsInitPassword = in.readByte() != 0;
    }

    public static final Creator<UserOutherInfoBean> CREATOR = new Creator<UserOutherInfoBean>() {
        @Override
        public UserOutherInfoBean createFromParcel(Parcel in) {
            return new UserOutherInfoBean(in);
        }

        @Override
        public UserOutherInfoBean[] newArray(int size) {
            return new UserOutherInfoBean[size];
        }
    };

    public Object getRealName() {
        return RealName;
    }

    public void setRealName(Object realName) {
        RealName = realName;
    }

    public Object getAvatar() {
        return Avatar;
    }

    public void setAvatar(Object avatar) {
        Avatar = avatar;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public Object getBankCards() {
        return BankCards;
    }

    public void setBankCards(Object bankCards) {
        BankCards = bankCards;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public int getFans() {
        return Fans;
    }

    public void setFans(int fans) {
        Fans = fans;
    }

    public int getFollower() {
        return Follower;
    }

    public void setFollower(int follower) {
        Follower = follower;
    }

    public Object getAddress() {
        return Address;
    }

    public void setAddress(Object address) {
        Address = address;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public boolean isSetPayPwd() {
        return SetPayPwd;
    }

    public void setSetPayPwd(boolean setPayPwd) {
        SetPayPwd = setPayPwd;
    }

    public Object getUserName() {
        return UserName;
    }

    public void setUserName(Object userName) {
        UserName = userName;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Token);
        parcel.writeString(Id);
        parcel.writeString(NickName);
        parcel.writeString(Level);
        parcel.writeDouble(Score);
        parcel.writeString(PhoneNum);
        parcel.writeInt(Fans);
        parcel.writeInt(Follower);
        parcel.writeInt(Sex);
        parcel.writeByte((byte) (SetPayPwd ? 1 : 0));
        parcel.writeString(Desc);
        parcel.writeDouble(RcmdStock);
        parcel.writeInt(Concerns);
        parcel.writeInt(Favorites);
        parcel.writeDouble(Credit);
        parcel.writeDouble(TotalCredit);
        parcel.writeDouble(AverageCumulativeReturn);
        parcel.writeDouble(WinRate);
        parcel.writeByte((byte) (IsPush ? 1 : 0));
        parcel.writeByte((byte) (IsConcerns ? 1 : 0));
        parcel.writeByte((byte) (IsInitPassword ? 1 : 0));
    }
}
