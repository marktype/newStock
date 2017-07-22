package com.example.drawer.stockapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 欢大哥 on 2016/7/14.
 */
public class DynamicsInfo implements Parcelable {


    /**
     * Status : 0
     * Msg : Success
     */

    private HeadBean Head;
    /**
     * PageInfo : {"PageIndex":0,"PageCount":8,"PageSize":10}
     * Share : [{"Id":"129","Title":"a3144b71850bf31f","NickName":"我我我我我我我我我","UserName":null,"Imgs":"http://filewebpath.matrix.lab.supwin.com:8899/20160825162147007.jpg","Comments":0,"Forward":0,"Likes":0,"UpdateTime":"2016-08-25 16:48:20","Content":"我","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"117","Title":"29641ff588ebed5c","NickName":null,"UserName":"15018400882","Imgs":null,"Comments":2,"Forward":0,"Likes":0,"UpdateTime":"2016-08-25 15:15:08","Content":"这是什么鬼","Imgs":["http://filewebpath.matrix.lab.supwin.com:8899/20160825151508950.jpg","http://filewebpath.matrix.lab.supwin.com:8899/20160825151508959.jpg","http://filewebpath.matrix.lab.supwin.com:8899/20160825151508968.jpg"],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"116","Title":"571c2de6825c519b","NickName":"zoe","UserName":null,"Imgs":"http://filewebpath.matrix.lab.supwin.com:8899/20160820165337747.jpg","Comments":0,"Forward":2,"Likes":0,"UpdateTime":"2016-08-25 15:14:49","Content":"哈哈哈","Imgs":["http://filewebpath.matrix.lab.supwin.com:8899/20160825151449177.jpg"],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"88","Title":"571c2de6825c519b","NickName":"zoe","UserName":null,"Imgs":"http://filewebpath.matrix.lab.supwin.com:8899/20160820165337747.jpg","Comments":3,"Forward":3,"Likes":0,"UpdateTime":"2016-08-24 15:54:21","Content":"string","Imgs":["http://filewebpath.matrix.lab.supwin.com:8899/20160824155421524.jpg"],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"86","Title":"571c2de6825c519b","NickName":"zoe","UserName":null,"Imgs":"http://filewebpath.matrix.lab.supwin.com:8899/20160820165337747.jpg","Comments":1,"Forward":0,"Likes":0,"UpdateTime":"2016-08-24 15:35:03","Content":"测试5555555。。。。","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"85","Title":"e0c35cc5309d9c63","NickName":"测","UserName":null,"Imgs":"http://filewebpath.matrix.lab.supwin.com:8899/20160824152845988.jpg","Comments":1,"Forward":0,"Likes":0,"UpdateTime":"2016-08-24 15:34:33","Content":"测试5555555666","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"81","Title":"e0c35cc5309d9c63","NickName":"测","UserName":null,"Imgs":"http://filewebpath.matrix.lab.supwin.com:8899/20160824152845988.jpg","Comments":0,"Forward":2,"Likes":0,"UpdateTime":"2016-08-24 10:17:12","Content":"测试数据","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null},{"Id":"80","Title":null,"NickName":null,"UserName":null,"Imgs":null,"Comments":0,"Forward":1,"Likes":0,"UpdateTime":"2016-08-24 10:05:20","Content":"hello你好。。。。。。。","Imgs":[],"HasFavorites":false,"HasForward":false,"HasLike":false,"IsForward":false,"ForwardInfo":null}]
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

    public static class HeadBean implements Parcelable {
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
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.Status);
            dest.writeString(this.Msg);
        }

        public HeadBean() {
        }

        protected HeadBean(Parcel in) {
            this.Status = in.readInt();
            this.Msg = in.readString();
        }

        public static final Creator<HeadBean> CREATOR = new Creator<HeadBean>() {
            @Override
            public HeadBean createFromParcel(Parcel source) {
                return new HeadBean(source);
            }

            @Override
            public HeadBean[] newArray(int size) {
                return new HeadBean[size];
            }
        };
    }

    public static class ResultBean implements Parcelable {
        /**
         * Id : 129
         * Title : a3144b71850bf31f
         * NickName : 我我我我我我我我我
         * UserName : null
         * Imgs : http://filewebpath.matrix.lab.supwin.com:8899/20160825162147007.jpg
         * Comments : 0
         * Forward : 0
         * Likes : 0
         * UpdateTime : 2016-08-25 16:48:20
         * Content : 我
         * Imgs : []
         * HasFavorites : false
         * HasForward : false
         * HasLike : false
         * IsForward : false
         * ForwardInfo : null
         */

        private List<DataBean> Share;

        public List<DataBean> getShare() {
            return Share;
        }

        public void setShare(List<DataBean> Share) {
            this.Share = Share;
        }

        public static class DataBean implements Parcelable {
            private String Id;
            private String Title;
            private String Summary;
            private ArrayList<String> Imgs;
            private String UpdateTime;
            private String Source;
            private int Comments;
            private int Forwards;
            private int Likes;
            private int Favorites;
            private int Shares;
            private boolean IsComment;
            private boolean IsForward;
            private boolean IsLike;
            private boolean IsFavorite;
            private String UserId;
            private String NickName;
            private String Avatar;
            private int InfoType;

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String title) {
                Title = title;
            }

            public String getSummary() {
                return Summary;
            }

            public void setSummary(String summary) {
                Summary = summary;
            }

            public ArrayList<String> getImgs() {
                return Imgs;
            }

            public void setImgs(ArrayList<String> imgs) {
                Imgs = imgs;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String updateTime) {
                UpdateTime = updateTime;
            }

            public String getSource() {
                return Source;
            }

            public void setSource(String source) {
                Source = source;
            }

            public int getComments() {
                return Comments;
            }

            public void setComments(int comments) {
                Comments = comments;
            }

            public int getForwards() {
                return Forwards;
            }

            public void setForwards(int forwards) {
                Forwards = forwards;
            }

            public int getLikes() {
                return Likes;
            }

            public void setLikes(int likes) {
                Likes = likes;
            }

            public int getFavorites() {
                return Favorites;
            }

            public void setFavorites(int favorites) {
                Favorites = favorites;
            }

            public int getShares() {
                return Shares;
            }

            public void setShares(int shares) {
                Shares = shares;
            }

            public boolean isComment() {
                return IsComment;
            }

            public void setComment(boolean comment) {
                IsComment = comment;
            }

            public boolean isForward() {
                return IsForward;
            }

            public void setForward(boolean forward) {
                IsForward = forward;
            }

            public boolean isLike() {
                return IsLike;
            }

            public void setLike(boolean like) {
                IsLike = like;
            }

            public boolean isFavorite() {
                return IsFavorite;
            }

            public void setFavorite(boolean favorite) {
                IsFavorite = favorite;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String userId) {
                UserId = userId;
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

            public int getInfoType() {
                return InfoType;
            }

            public void setInfoType(int infoType) {
                InfoType = infoType;
            }



            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {


                dest.writeString(this.Id);
                dest.writeString(this.Title);
                dest.writeString(this.Summary);
                dest.writeList(this.Imgs);
                dest.writeString(this.UpdateTime);
                dest.writeString(this.Source);
                dest.writeInt(this.Comments);
                dest.writeInt(this.Forwards);
                dest.writeInt(this.Likes);
                dest.writeInt(this.Favorites);
                dest.writeInt(this.Shares);
                dest.writeByte(this.IsComment ? (byte) 1 : (byte) 0);
                dest.writeByte(this.IsForward ? (byte) 1 : (byte) 0);
                dest.writeByte(this.IsLike ? (byte) 1 : (byte) 0);
                dest.writeByte(this.IsFavorite ? (byte) 1 : (byte) 0);
                dest.writeString(this.UserId);
                dest.writeString(this.NickName);
                dest.writeString(this.Avatar);
                dest.writeInt(this.InfoType);

            }

            public DataBean() {
            }

            protected DataBean(Parcel in) {


                this.Id = in.readString();
                this.Title = in.readString();
                this.Summary= in.readString();
                this.Imgs = new ArrayList<String>();
                in.readList(this.Imgs, String.class.getClassLoader());
                this.UpdateTime= in.readString();
                this.Source= in.readString();
                this.Comments= in.readInt();
                this.Forwards= in.readInt();
                this.Likes= in.readInt();
                this.Favorites= in.readInt();
                this.Shares= in.readInt();
                this.IsComment = in.readByte() != 0;
                this.IsForward = in.readByte() != 0;
                this.IsLike = in.readByte() != 0;
                this.IsFavorite = in.readByte() != 0;
                this.UserId= in.readString();
                this.NickName= in.readString();
                this.Avatar= in.readString();
                this.InfoType= in.readInt();


            }

            public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel source) {
                    return new DataBean(source);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.Share);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.Share = new ArrayList<DataBean>();
            in.readList(this.Share, DataBean.class.getClassLoader());
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel source) {
                return new ResultBean(source);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.Head, flags);
        dest.writeParcelable(this.Result, flags);
    }

    public DynamicsInfo() {
    }

    protected DynamicsInfo(Parcel in) {
        this.Head = in.readParcelable(HeadBean.class.getClassLoader());
        this.Result = in.readParcelable(ResultBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<DynamicsInfo> CREATOR = new Parcelable.Creator<DynamicsInfo>() {
        @Override
        public DynamicsInfo createFromParcel(Parcel source) {
            return new DynamicsInfo(source);
        }

        @Override
        public DynamicsInfo[] newArray(int size) {
            return new DynamicsInfo[size];
        }
    };
}
