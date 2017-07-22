package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by 欢大哥 on 2017/2/25.
 */
public class UserAttention {
    private UserAttention.HeadBean headBean;
    private UserAttention.ResultBean resultBean;

    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }

    public HeadBean getHeadBean() {
        return headBean;
    }

    public void setHeadBean(HeadBean headBean) {
        this.headBean = headBean;
    }

    public static class HeadBean {

        private int Status;
        private String Msg;

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String msg) {
            Msg = msg;
        }
    }

    public static class ResultBean {


        private List<UserAttention.ResultBean.DataBean> dataBeanList;

        public List<DataBean> getDataBeanList() {
            return dataBeanList;
        }

        public void setDataBeanList(List<DataBean> dataBeanList) {
            this.dataBeanList = dataBeanList;
        }

        public static class DataBean {

            private String Id;
            private String NickName;
            private String Avatar;
            private String CreateTime;
            private boolean IsConcerns;
            private double AverageCumulativeReturn;
            private double WinRate;
            private int RcmdStock;

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


        }

    }

}
