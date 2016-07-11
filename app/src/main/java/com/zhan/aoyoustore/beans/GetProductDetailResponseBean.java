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

        private DefaultSkuBean defaultSku;
        private int stock;
        private List<String> pics;
        private List<?> SkuItem;

        private List<SkusBean> skus;

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

        public DefaultSkuBean getDefaultSku() {
            return defaultSku;
        }

        public void setDefaultSku(DefaultSkuBean defaultSku) {
            this.defaultSku = defaultSku;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
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

            public List<?> getSkuItems() {
                return SkuItems;
            }

            public void setSkuItems(List<?> SkuItems) {
                this.SkuItems = SkuItems;
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

            public List<?> getSkuItems() {
                return SkuItems;
            }

            public void setSkuItems(List<?> SkuItems) {
                this.SkuItems = SkuItems;
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
