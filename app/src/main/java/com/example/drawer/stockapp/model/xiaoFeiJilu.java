package com.example.drawer.stockapp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class xiaoFeiJilu {

    /**
     * Head : {"Status":0,"Msg":"success"}
     * Result : {"PageInfo":{"PageIndex":0,"PageCount":4,"PageSize":10},"Data":[{"Id":1181,"Num":50,"Remark":"2017-04-17签到","CreateTime":"2017-04-17 18:06:24"},{"Id":1101,"Num":50,"Remark":"2017-03-24签到","CreateTime":"2017-03-24 09:24:38"},{"Id":1097,"Num":50,"Remark":"2017-03-22签到","CreateTime":"2017-03-22 21:29:18"},{"Id":1076,"Num":50,"Remark":"2017-03-20签到","CreateTime":"2017-03-20 10:22:55"}]}
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
         * PageInfo : {"PageIndex":0,"PageCount":4,"PageSize":10}
         * Data : [{"Id":1181,"Num":50,"Remark":"2017-04-17签到","CreateTime":"2017-04-17 18:06:24"},{"Id":1101,"Num":50,"Remark":"2017-03-24签到","CreateTime":"2017-03-24 09:24:38"},{"Id":1097,"Num":50,"Remark":"2017-03-22签到","CreateTime":"2017-03-22 21:29:18"},{"Id":1076,"Num":50,"Remark":"2017-03-20签到","CreateTime":"2017-03-20 10:22:55"}]
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
             * PageCount : 4
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
        }

        public static class DataBean {
            /**
             * Id : 1181
             * Num : 50
             * Remark : 2017-04-17签到
             * CreateTime : 2017-04-17 18:06:24
             */

            private int Id;
            private int Num;
            private String Remark;
            private String CreateTime;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getNum() {
                return Num;
            }

            public void setNum(int Num) {
                this.Num = Num;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }
        }
    }
}
