package com.zhan.aoyoustore.beans;

import java.util.List;

/**
 * 作者：伍岳 on 2016/7/12 15:17
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
public class GetArticleCategoryResponseBean {

    /**
     * res : 1
     * categorys : [{"categoryId":7,"categoryName":"商城资讯","categoryContent":"商城资讯分类介绍"},{"categoryId":8,"categoryName":"商品资讯","categoryContent":"商品资讯介绍"}]
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
        /**
         * categoryId : 7
         * categoryName : 商城资讯
         * categoryContent : 商城资讯分类介绍
         */

        private List<CategorysEntity> categorys;

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public List<CategorysEntity> getCategorys() {
            return categorys;
        }

        public void setCategorys(List<CategorysEntity> categorys) {
            this.categorys = categorys;
        }

        public static class CategorysEntity {
            private int categoryId;
            private String categoryName;
            private String categoryContent;

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
        }
    }
}
