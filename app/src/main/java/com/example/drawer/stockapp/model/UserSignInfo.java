package com.example.drawer.stockapp.model;

import java.io.Serializable;

/**
 * Created by 欢大哥 on 2017/2/22.
 */
public class UserSignInfo implements Serializable{
    private HeadBean Head;


    public HeadBean getHead() {
        return Head;
    }

    public void setHead(HeadBean Head) {
        this.Head = Head;
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

    private ResultBean resultBean;

    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }


    /**
     * "Result": {
     * "ChcekingVolumn": 0, //签到总天数
     * "MonthVolumn": 0,    //本月签到天数
     * "Credit": 0,         //用户拥有可用积分
     * "IsChecking": true,  //用户是否已经签到
     * "CloseInfoVolumn": 0,//完成课程数
     * "TotalCredit": 0     //用户总积分
     * }
     */
    public static class ResultBean {
        private double ChcekingVolumn;
        private double MonthVolumn;
        private double Credit;
        private Boolean IsChecking;
        private double CloseInfoVolumn;
        private double TotalCredit;

        public double getChcekingVolumn() {
            return ChcekingVolumn;
        }

        public void setChcekingVolumn(double chcekingVolumn) {
            ChcekingVolumn = chcekingVolumn;
        }

        public double getMonthVolumn() {
            return MonthVolumn;
        }

        public void setMonthVolumn(double monthVolumn) {
            MonthVolumn = monthVolumn;
        }

        public double getCredit() {
            return Credit;
        }

        public void setCredit(double credit) {
            Credit = credit;
        }

        public Boolean getChecking() {
            return IsChecking;
        }

        public void setChecking(Boolean checking) {
            IsChecking = checking;
        }

        public double getCloseInfoVolumn() {
            return CloseInfoVolumn;
        }

        public void setCloseInfoVolumn(double closeInfoVolumn) {
            CloseInfoVolumn = closeInfoVolumn;
        }

        public double getTotalCredit() {
            return TotalCredit;
        }

        public void setTotalCredit(double totalCredit) {
            TotalCredit = totalCredit;
        }


        @Override
        public String toString() {
            return "ResultBean{" +
                    "ChcekingVolumn=" + ChcekingVolumn +
                    ", MonthVolumn=" + MonthVolumn +
                    ", Credit=" + Credit +
                    ", IsChecking=" + IsChecking +
                    ", CloseInfoVolumn=" + CloseInfoVolumn +
                    ", TotalCredit=" + TotalCredit +
                    '}';
        }
    }

}
