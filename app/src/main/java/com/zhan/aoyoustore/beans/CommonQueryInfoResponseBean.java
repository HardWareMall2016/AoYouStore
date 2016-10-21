package com.zhan.aoyoustore.beans;

/**
 * 作者：蒲柯柯 on 2016/10/21 17:20
 * 邮箱：983198505@qq.com
 * 介绍:
 */
public class CommonQueryInfoResponseBean {
    /**
     * res : 1
     * articleId : 13
     * title : 色环电阻表
     * descriptions : 色环电阻表
     * content : <p>
     色环电阻表
     </p>
     <p>
     色环电阻表
     </p>
     * createdAt : 1477030772
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int res;
        private int articleId;
        private String title;
        private String descriptions;
        private String content;
        private String createdAt;

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescriptions() {
            return descriptions;
        }

        public void setDescriptions(String descriptions) {
            this.descriptions = descriptions;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }
}
