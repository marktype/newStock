package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by 欢大哥 on 2017/2/25.
 */
public class UserAttentionBean implements Parcelable {
    private String Id;
    private String NickName;
    private String Avatar;
    private String CreateTime;
    private boolean IsConcerns;
    private double AverageCumulativeReturn;
    private double WinRate;
    private int RcmdStock;

    public UserAttentionBean() {
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

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public boolean isConcerns() {
        return IsConcerns;
    }

    public void setConcerns(boolean concerns) {
        IsConcerns = concerns;
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

    public int getRcmdStock() {
        return RcmdStock;
    }

    public void setRcmdStock(int rcmdStock) {
        RcmdStock = rcmdStock;
    }

    protected UserAttentionBean(Parcel in) {
        Id = in.readString();
        NickName = in.readString();
        Avatar = in.readString();
        CreateTime = in.readString();
        IsConcerns = in.readByte() != 0;
        AverageCumulativeReturn = in.readDouble();
        WinRate = in.readDouble();
        RcmdStock = in.readInt();
    }

    public static final Creator<UserAttentionBean> CREATOR = new Creator<UserAttentionBean>() {
        @Override
        public UserAttentionBean createFromParcel(Parcel in) {
            return new UserAttentionBean(in);
        }

        @Override
        public UserAttentionBean[] newArray(int size) {
            return new UserAttentionBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Id);
        parcel.writeString(NickName);
        parcel.writeString(Avatar);
        parcel.writeString(CreateTime);
        parcel.writeByte((byte) (IsConcerns ? 1 : 0));
        parcel.writeDouble(AverageCumulativeReturn);
        parcel.writeDouble(WinRate);
        parcel.writeInt(RcmdStock);
    }
}
