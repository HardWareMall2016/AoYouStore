package com.zhan.aoyoustore.beans;

import java.util.List;

/**
 * 作者：伍岳 on 2016/7/11 20:47
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
public class GetProductDetailResponseBean {

    /**
     * res : 1
     * isOpenSale : true
     * productid : 5889
     * productName : TAJE107M025RNJ
     * productCode : 064-TAJE107M025RNJ
     * shortDescription : CAP TANT 100UF 25V 20% 2917
     * descriptions :
     * saleCounts : 0
     * weight : 0.0
     * vistiCounts : 2
     * costPrice : 0.0
     * marketPrice : 0.0
     * isfreeShipping : false
     * maxSalePrice : 4.5
     * minSalePrice : 4.5
     * isFavorite : false
     * pics : ["http://121.43.111.133:51055/Storage/master/product/images/201609011636499698221.png"]
     * stock : 4000
     * brand : AVX Corporation
     * minBuyNum : 1
     * skuBook : http://121.43.111.133:51055/Storage/master/product/File/TAJ.pdf
     * defaultSku : {"CostPrice":0,"LadderPrices":[],"MemberPrices":[],"Ladders":[{"minCount":1,"maxCont":9,"ladderPrice":4.5},{"minCount":10,"maxCont":29,"ladderPrice":3.9},{"minCount":30,"maxCont":100,"ladderPrice":3.65},{"minCount":100,"maxCont":4000,"ladderPrice":3.4}],"ProductId":5889,"SKU":"62824","SalePrice":4.5,"SkuId":"5889_0","SkuItems":[],"Stock":4000,"StoreStock":4000,"WarningStock":200,"Weight":0}
     * SkuItem : []
     * skus : [{"CostPrice":0,"LadderPrices":[],"MemberPrices":[],"Ladders":[{"minCount":1,"maxCont":9,"ladderPrice":4.5},{"minCount":10,"maxCont":29,"ladderPrice":3.9},{"minCount":30,"maxCont":100,"ladderPrice":3.65},{"minCount":100,"maxCont":4000,"ladderPrice":3.4}],"ProductId":5889,"SKU":"62824","SalePrice":4.5,"SkuId":"5889_0","SkuItems":[],"Stock":4000,"StoreStock":4000,"WarningStock":200,"Weight":0}]
     * info : [{"infoName":"特性","infoValue":"通用"},{"infoName":"制造商尺寸代码","infoValue":"E"},{"infoName":"引线间距","infoValue":"-"},{"infoName":"高度-安装（最大值）","infoValue":"4.30mm"},{"infoName":"大小/尺寸","infoValue":"7.30mm x 4.30mm"},{"infoName":"封装/外壳","infoValue":"2917（7343 公制）"},{"infoName":"安装类型","infoValue":"表面贴装"},{"infoName":"工作温度","infoValue":"-55°C ~ 125°C"},{"infoName":"类型","infoValue":"模制"},{"infoName":"ESR(等效串联电阻）","infoValue":"300毫欧"},{"infoName":"电压-额定","infoValue":"25V"},{"infoName":"容差","infoValue":"20%"},{"infoName":"电容","infoValue":"100µF"},{"infoName":"系列","infoValue":"TAJ"}]
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
        private boolean isOpenSale;
        private int productid;
        private String productName;
        private String productCode;
        private String shortDescription;
        private String descriptions;
        private int saleCounts;
        private double weight;
        private int vistiCounts;
        private double costPrice;
        private double marketPrice;
        private boolean isfreeShipping;
        private double maxSalePrice;
        private double minSalePrice;
        private boolean isFavorite;
        private int stock;
        private String brand;
        private int minBuyNum;
        private String skuBook;
        /**
         * CostPrice : 0.0
         * LadderPrices : []
         * MemberPrices : []
         * Ladders : [{"minCount":1,"maxCont":9,"ladderPrice":4.5},{"minCount":10,"maxCont":29,"ladderPrice":3.9},{"minCount":30,"maxCont":100,"ladderPrice":3.65},{"minCount":100,"maxCont":4000,"ladderPrice":3.4}]
         * ProductId : 5889
         * SKU : 62824
         * SalePrice : 4.5
         * SkuId : 5889_0
         * SkuItems : []
         * Stock : 4000
         * StoreStock : 4000
         * WarningStock : 200
         * Weight : 0.0
         */

        private DefaultSkuBean defaultSku;
        private List<String> pics;
        private List<?> SkuItem;
        /**
         * CostPrice : 0.0
         * LadderPrices : []
         * MemberPrices : []
         * Ladders : [{"minCount":1,"maxCont":9,"ladderPrice":4.5},{"minCount":10,"maxCont":29,"ladderPrice":3.9},{"minCount":30,"maxCont":100,"ladderPrice":3.65},{"minCount":100,"maxCont":4000,"ladderPrice":3.4}]
         * ProductId : 5889
         * SKU : 62824
         * SalePrice : 4.5
         * SkuId : 5889_0
         * SkuItems : []
         * Stock : 4000
         * StoreStock : 4000
         * WarningStock : 200
         * Weight : 0.0
         */

        private List<SkusBean> skus;
        /**
         * infoName : 特性
         * infoValue : 通用
         */

        private List<InfoBean> info;

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public boolean isIsOpenSale() {
            return isOpenSale;
        }

        public void setIsOpenSale(boolean isOpenSale) {
            this.isOpenSale = isOpenSale;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getDescriptions() {
            return descriptions;
        }

        public void setDescriptions(String descriptions) {
            this.descriptions = descriptions;
        }

        public int getSaleCounts() {
            return saleCounts;
        }

        public void setSaleCounts(int saleCounts) {
            this.saleCounts = saleCounts;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public int getVistiCounts() {
            return vistiCounts;
        }

        public void setVistiCounts(int vistiCounts) {
            this.vistiCounts = vistiCounts;
        }

        public double getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(double costPrice) {
            this.costPrice = costPrice;
        }

        public double getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(double marketPrice) {
            this.marketPrice = marketPrice;
        }

        public boolean isIsfreeShipping() {
            return isfreeShipping;
        }

        public void setIsfreeShipping(boolean isfreeShipping) {
            this.isfreeShipping = isfreeShipping;
        }

        public double getMaxSalePrice() {
            return maxSalePrice;
        }

        public void setMaxSalePrice(double maxSalePrice) {
            this.maxSalePrice = maxSalePrice;
        }

        public double getMinSalePrice() {
            return minSalePrice;
        }

        public void setMinSalePrice(double minSalePrice) {
            this.minSalePrice = minSalePrice;
        }

        public boolean isIsFavorite() {
            return isFavorite;
        }

        public void setIsFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getMinBuyNum() {
            return minBuyNum;
        }

        public void setMinBuyNum(int minBuyNum) {
            this.minBuyNum = minBuyNum;
        }

        public String getSkuBook() {
            return skuBook;
        }

        public void setSkuBook(String skuBook) {
            this.skuBook = skuBook;
        }

        public DefaultSkuBean getDefaultSku() {
            return defaultSku;
        }

        public void setDefaultSku(DefaultSkuBean defaultSku) {
            this.defaultSku = defaultSku;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }

        public List<?> getSkuItem() {
            return SkuItem;
        }

        public void setSkuItem(List<?> SkuItem) {
            this.SkuItem = SkuItem;
        }

        public List<SkusBean> getSkus() {
            return skus;
        }

        public void setSkus(List<SkusBean> skus) {
            this.skus = skus;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class DefaultSkuBean {
            private double CostPrice;
            private int ProductId;
            private String SKU;
            private double SalePrice;
            private String SkuId;
            private int Stock;
            private int StoreStock;
            private int WarningStock;
            private double Weight;
            private List<?> LadderPrices;
            private List<?> MemberPrices;
            /**
             * minCount : 1
             * maxCont : 9
             * ladderPrice : 4.5
             */

            private List<LaddersBean> Ladders;
            private List<?> SkuItems;

            public double getCostPrice() {
                return CostPrice;
            }

            public void setCostPrice(double CostPrice) {
                this.CostPrice = CostPrice;
            }

            public int getProductId() {
                return ProductId;
            }

            public void setProductId(int ProductId) {
                this.ProductId = ProductId;
            }

            public String getSKU() {
                return SKU;
            }

            public void setSKU(String SKU) {
                this.SKU = SKU;
            }

            public double getSalePrice() {
                return SalePrice;
            }

            public void setSalePrice(double SalePrice) {
                this.SalePrice = SalePrice;
            }

            public String getSkuId() {
                return SkuId;
            }

            public void setSkuId(String SkuId) {
                this.SkuId = SkuId;
            }

            public int getStock() {
                return Stock;
            }

            public void setStock(int Stock) {
                this.Stock = Stock;
            }

            public int getStoreStock() {
                return StoreStock;
            }

            public void setStoreStock(int StoreStock) {
                this.StoreStock = StoreStock;
            }

            public int getWarningStock() {
                return WarningStock;
            }

            public void setWarningStock(int WarningStock) {
                this.WarningStock = WarningStock;
            }

            public double getWeight() {
                return Weight;
            }

            public void setWeight(double Weight) {
                this.Weight = Weight;
            }

            public List<?> getLadderPrices() {
                return LadderPrices;
            }

            public void setLadderPrices(List<?> LadderPrices) {
                this.LadderPrices = LadderPrices;
            }

            public List<?> getMemberPrices() {
                return MemberPrices;
            }

            public void setMemberPrices(List<?> MemberPrices) {
                this.MemberPrices = MemberPrices;
            }

            public List<LaddersBean> getLadders() {
                return Ladders;
            }

            public void setLadders(List<LaddersBean> Ladders) {
                this.Ladders = Ladders;
            }

            public List<?> getSkuItems() {
                return SkuItems;
            }

            public void setSkuItems(List<?> SkuItems) {
                this.SkuItems = SkuItems;
            }

            public static class LaddersBean {
                private int minCount;
                private int maxCont;
                private double ladderPrice;

                public int getMinCount() {
                    return minCount;
                }

                public void setMinCount(int minCount) {
                    this.minCount = minCount;
                }

                public int getMaxCont() {
                    return maxCont;
                }

                public void setMaxCont(int maxCont) {
                    this.maxCont = maxCont;
                }

                public double getLadderPrice() {
                    return ladderPrice;
                }

                public void setLadderPrice(double ladderPrice) {
                    this.ladderPrice = ladderPrice;
                }
            }
        }

        public static class SkusBean {
            private double CostPrice;
            private int ProductId;
            private String SKU;
            private double SalePrice;
            private String SkuId;
            private int Stock;
            private int StoreStock;
            private int WarningStock;
            private double Weight;
            private List<?> LadderPrices;
            private List<?> MemberPrices;
            /**
             * minCount : 1
             * maxCont : 9
             * ladderPrice : 4.5
             */

            private List<LaddersBean> Ladders;
            private List<?> SkuItems;

            public double getCostPrice() {
                return CostPrice;
            }

            public void setCostPrice(double CostPrice) {
                this.CostPrice = CostPrice;
            }

            public int getProductId() {
                return ProductId;
            }

            public void setProductId(int ProductId) {
                this.ProductId = ProductId;
            }

            public String getSKU() {
                return SKU;
            }

            public void setSKU(String SKU) {
                this.SKU = SKU;
            }

            public double getSalePrice() {
                return SalePrice;
            }

            public void setSalePrice(double SalePrice) {
                this.SalePrice = SalePrice;
            }

            public String getSkuId() {
                return SkuId;
            }

            public void setSkuId(String SkuId) {
                this.SkuId = SkuId;
            }

            public int getStock() {
                return Stock;
            }

            public void setStock(int Stock) {
                this.Stock = Stock;
            }

            public int getStoreStock() {
                return StoreStock;
            }

            public void setStoreStock(int StoreStock) {
                this.StoreStock = StoreStock;
            }

            public int getWarningStock() {
                return WarningStock;
            }

            public void setWarningStock(int WarningStock) {
                this.WarningStock = WarningStock;
            }

            public double getWeight() {
                return Weight;
            }

            public void setWeight(double Weight) {
                this.Weight = Weight;
            }

            public List<?> getLadderPrices() {
                return LadderPrices;
            }

            public void setLadderPrices(List<?> LadderPrices) {
                this.LadderPrices = LadderPrices;
            }

            public List<?> getMemberPrices() {
                return MemberPrices;
            }

            public void setMemberPrices(List<?> MemberPrices) {
                this.MemberPrices = MemberPrices;
            }

            public List<LaddersBean> getLadders() {
                return Ladders;
            }

            public void setLadders(List<LaddersBean> Ladders) {
                this.Ladders = Ladders;
            }

            public List<?> getSkuItems() {
                return SkuItems;
            }

            public void setSkuItems(List<?> SkuItems) {
                this.SkuItems = SkuItems;
            }

            public static class LaddersBean {
                private int minCount;
                private int maxCont;
                private double ladderPrice;

                public int getMinCount() {
                    return minCount;
                }

                public void setMinCount(int minCount) {
                    this.minCount = minCount;
                }

                public int getMaxCont() {
                    return maxCont;
                }

                public void setMaxCont(int maxCont) {
                    this.maxCont = maxCont;
                }

                public double getLadderPrice() {
                    return ladderPrice;
                }

                public void setLadderPrice(double ladderPrice) {
                    this.ladderPrice = ladderPrice;
                }
            }
        }

        public static class InfoBean {
            private String infoName;
            private String infoValue;

            public String getInfoName() {
                return infoName;
            }

            public void setInfoName(String infoName) {
                this.infoName = infoName;
            }

            public String getInfoValue() {
                return infoValue;
            }

            public void setInfoValue(String infoValue) {
                this.infoValue = infoValue;
            }
        }
    }
}
