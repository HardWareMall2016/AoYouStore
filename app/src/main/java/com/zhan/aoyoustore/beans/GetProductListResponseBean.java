package com.zhan.aoyoustore.beans;

import java.util.List;

/**
 * 作者：伍岳 on 2016/7/10 20:07
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
public class GetProductListResponseBean {

    /**
     * res : 1
     * totals : 1
     * products : [{"pid":156,"name":"9C-8.000MAAJ-T","pic":"http://121.43.111.133:51055/Storage/master/product/thumbs310/310_201605162007159119820.jpg","price":1,"marketPrice":0,"discount":"","saleCounts":0,"url":"http://121.43.111.133:51055/AppShop/ProductDetails.aspx?productId=156"}]
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
        private int totals;
        /**
         * pid : 156
         * name : 9C-8.000MAAJ-T
         * pic : http://121.43.111.133:51055/Storage/master/product/thumbs310/310_201605162007159119820.jpg
         * price : 1.0
         * marketPrice : 0.0
         * discount :
         * saleCounts : 0
         * url : http://121.43.111.133:51055/AppShop/ProductDetails.aspx?productId=156
         */

        private List<ProductsBean> products;

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public int getTotals() {
            return totals;
        }

        public void setTotals(int totals) {
            this.totals = totals;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            private int pid;
            private String name;
            private String pic;
            private double price;
            private double marketPrice;
            private String discount;
            private int saleCounts;
            private String url;

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(double marketPrice) {
                this.marketPrice = marketPrice;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public int getSaleCounts() {
                return saleCounts;
            }

            public void setSaleCounts(int saleCounts) {
                this.saleCounts = saleCounts;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
