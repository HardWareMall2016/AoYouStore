package com.zhan.aoyoustore.beans;

import java.util.List;

public class GetArticleCategoryResponseBean {

    /**
     * res : 1
     * categoryId : 7
     * categoryName : 常用查询
     * categoryContent : 常用查询
     * helps : [{"helpId":14,"title":"常用贴片电阻阻值表","descriptions":"常用贴片电阻阻值表","createdAt":"1477030799"},{"helpId":13,"title":"色环电阻表","descriptions":"色环电阻表","createdAt":"1477030772"}]
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
        private int categoryId;
        private String categoryName;
        private String categoryContent;
        /**
         * helpId : 14
         * title : 常用贴片电阻阻值表
         * descriptions : 常用贴片电阻阻值表
         * createdAt : 1477030799
         */

        private List<HelpsBean> helps;

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryContent() {
            return categoryContent;
        }

        public void setCategoryContent(String categoryContent) {
            this.categoryContent = categoryContent;
        }

        public List<HelpsBean> getHelps() {
            return helps;
        }

        public void setHelps(List<HelpsBean> helps) {
            this.helps = helps;
        }

        public static class HelpsBean {
            private int helpId;
            private String title;
            private String descriptions;
            private String createdAt;

            public int getHelpId() {
                return helpId;
            }

            public void setHelpId(int helpId) {
                this.helpId = helpId;
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

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }
        }
    }
}
