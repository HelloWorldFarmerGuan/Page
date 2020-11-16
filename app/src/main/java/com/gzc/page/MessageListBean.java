package com.gzc.page;

import java.util.List;
import java.util.Objects;

/**
 * author：gzc
 * date：2020/11/16
 * describe：
 */
public class MessageListBean {
    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 100185527
         * title : 您收到一条微博订单！
         * wapurl :
         * content : 您有一条微博订单，请尽快接单。
         * linkpage : 18
         * createtime : 1594139406
         * isread : 1
         * msg_type : newmsg
         */

        private String id;
        private String title;
        private String wapurl;
        private String content;
        private String linkpage;
        private long createtime;
        private int isread;
        private String msg_type;
        private String taskid;
        private String idstr;//订单id  空的话跳列表  非空的话跳详情

        public String getIdstr() {
            return idstr;
        }

        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWapurl() {
            return wapurl;
        }

        public void setWapurl(String wapurl) {
            this.wapurl = wapurl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLinkpage() {
            return linkpage;
        }

        public void setLinkpage(String linkpage) {
            this.linkpage = linkpage;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public int getIsread() {
            return isread;
        }

        public void setIsread(int isread) {
            this.isread = isread;
        }

        public String getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(String msg_type) {
            this.msg_type = msg_type;
        }

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            DataBean dataBean = (DataBean) o;
            return createtime == dataBean.createtime &&
                    isread == dataBean.isread &&
                    Objects.equals(id, dataBean.id) &&
                    Objects.equals(title, dataBean.title) &&
                    Objects.equals(wapurl, dataBean.wapurl) &&
                    Objects.equals(content, dataBean.content) &&
                    Objects.equals(linkpage, dataBean.linkpage) &&
                    Objects.equals(msg_type, dataBean.msg_type) &&
                    Objects.equals(taskid, dataBean.taskid) &&
                    Objects.equals(idstr, dataBean.idstr);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, wapurl, content, linkpage, createtime, isread, msg_type, taskid, idstr);
        }
    }
}
