package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/9/26.
 */
public class DynamicDetialInfo {


    /**
     * Status : 0
     * Msg : success
     */

    private HeadBean Head;
    /**
     * Id : 576
     * UserId : e0c35cc5309d9c63
     * NickName : 许，测试
     * UserName : null
     * ImgUrl : http://filewebpath.matrix.lab.supwin.com:8899/20160824152845988.jpg
     * Comments : 14
     * Forwards : 0
     * Likes : 2
     * UpdateTime : 2016-09-19 17:16:37
     * Content : (・_・ヾ测试数据。应该是很不错的(〃･ิ∀･ิ)ゞ((유∀유|||))��������
     * Imgs : []
     * IsFavorite : false
     * HasForward : false
     * IsLike : false
     * IsForward : false
     * <p/>
     * <p/>
     * "Id": "string",
     * "Title": "string",
     * "Summary": "string",
     * "Imgs": [
     * "string"
     * ],
     * "TargetUrl": "string",
     * "UpdateTime": "string",
     * "Source": "string",
     * "Content": "string",
     * "Comments": 0,
     * "Forwards": 0,
     * "Likes": 0,
     * "Favorites": 0,
     * "Shares": 0,
     * "IsComment": true,
     * "IsForward": true,
     * "IsLike": true,
     * "IsFavorite": true,
     * "UserId": "string",
     * "NickName": "string",
     * "Avatar": "string"
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
        private String Id;
        private String UserId;
        private String NickName;
        private Object UserName;
        private String ImgUrl;
        private int Comments;
        private int Forwards;
        private int Likes;
        private int Favorites;
        private int Shares;
        private String UpdateTime;
        private String Content;
        private boolean IsFavorite;
        private boolean IsLike;
        private boolean IsForward;
        private List<String> Imgs;

        public boolean isForward() {
            return IsForward;
        }

        public void setForward(boolean forward) {
            IsForward = forward;
        }

        public int getShares() {
            return Shares;
        }

        public void setShares(int shares) {
            Shares = shares;
        }
        public int getFavorites() {
            return Favorites;
        }

        public void setFavorites(int favorites) {
            Favorites = favorites;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public Object getUserName() {
            return UserName;
        }

        public void setUserName(Object UserName) {
            this.UserName = UserName;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public int getComments() {
            return Comments;
        }

        public void setComments(int Comments) {
            this.Comments = Comments;
        }

        public int getForwards() {
            return Forwards;
        }

        public void setForwards(int Forward) {
            this.Forwards = Forward;
        }

        public int getLikes() {
            return Likes;
        }

        public void setLikes(int Likes) {
            this.Likes = Likes;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public boolean isFavorite() {
            return IsFavorite;
        }

        public void setFavorite(boolean HasFavorites) {
            this.IsFavorite = HasFavorites;
        }


        public boolean isLike() {
            return IsLike;
        }

        public void setLike(boolean HasLike) {
            this.IsLike = HasLike;
        }

        public boolean isIsForward() {
            return IsForward;
        }

        public void setIsForward(boolean IsForward) {
            this.IsForward = IsForward;
        }

        public List<String> getImgs() {
            return Imgs;
        }

        public void setImgs(List<String> Imgs) {
            this.Imgs = Imgs;
        }
    }
}
