package com.zhan.aoyoustore.beans;

import java.util.List;

/**
 * 作者：伍岳 on 2016/7/12 15:40
 * 邮箱：wuyue8512@163.com
 * //
 * //         .............................................
 * //                  美女坐镇                  BUG辟易
 * //         .............................................
 * //
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` ':.          ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 * //
 */
public class getArticlesByCategoryIdResponseBean {

    /**
     * res : 1
     * categoryId : 7
     * categoryName : 商城资讯
     * categoryContent : 商城资讯分类介绍
     * articles : [{"articleId":12,"title":"京东成立智能音频联盟","descriptions":"京东携飞利浦、漫步者、猫王、索爱等影音硬件生产商及喜马拉雅、百度音乐等影音内容供应商成立了京东智能音频联盟。该联盟中的多款WiFi智能音箱产品已在京东上线，包含DingDong智能音箱、DingDong青春版以及与哈曼联合推出的JBL Go Smart等多款产品。","createdAt":"1460984550"},{"articleId":11,"title":"庆祝京东全球购一周年","descriptions":"4月13日，京东全球购一周年庆典活动在京东集团总部举行，京东商城CEO沈皓瑜、京东商城消费品事业部总裁冯轶、驻华17国参赞、a2、Swisse等品牌商代表共同出席并现场为京东全球购庆生。","createdAt":"1460984468"}]
     */

    private ResultEntity result;

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {
        private int res;
        private int categoryId;
        private String categoryName;
        private String categoryContent;
        /**
         * articleId : 12
         * title : 京东成立智能音频联盟
         * descriptions : 京东携飞利浦、漫步者、猫王、索爱等影音硬件生产商及喜马拉雅、百度音乐等影音内容供应商成立了京东智能音频联盟。该联盟中的多款WiFi智能音箱产品已在京东上线，包含DingDong智能音箱、DingDong青春版以及与哈曼联合推出的JBL Go Smart等多款产品。
         * createdAt : 1460984550
         */

        private List<ArticlesEntity> articles;

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

        public List<ArticlesEntity> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesEntity> articles) {
            this.articles = articles;
        }

        public static class ArticlesEntity {
            private int articleId;
            private String title;
            private String descriptions;
            private String createdAt;

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

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }
        }
    }
}
