package com.zhan.aoyoustore.beans;

import java.util.List;

/**
 * 作者：伍岳 on 2016/7/9 23:48
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
public class GetShopCategoriesResponseBean {


    private ResultEntity result;

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {
        private int res;

        private List<CategoryEntity> category;

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public List<CategoryEntity> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryEntity> category) {
            this.category = category;
        }

        public static class CategoryEntity {
            private int cid;
            private String name;
            private String icon;
            private boolean hasChildren;
            private String descriptions;
            /**
             * cid : 44
             * name : 晶体
             * icon : null
             * descriptions :
             * proCount : 1
             */

            private List<SubsEntity> subs;

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public boolean isHasChildren() {
                return hasChildren;
            }

            public void setHasChildren(boolean hasChildren) {
                this.hasChildren = hasChildren;
            }

            public String getDescriptions() {
                return descriptions;
            }

            public void setDescriptions(String descriptions) {
                this.descriptions = descriptions;
            }

            public List<SubsEntity> getSubs() {
                return subs;
            }

            public void setSubs(List<SubsEntity> subs) {
                this.subs = subs;
            }

            public static class SubsEntity {
                private int cid;
                private String name;
                private String icon;
                private String descriptions;
                private int proCount;

                public int getCid() {
                    return cid;
                }

                public void setCid(int cid) {
                    this.cid = cid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getDescriptions() {
                    return descriptions;
                }

                public void setDescriptions(String descriptions) {
                    this.descriptions = descriptions;
                }

                public int getProCount() {
                    return proCount;
                }

                public void setProCount(int proCount) {
                    this.proCount = proCount;
                }
            }
        }
    }
}
