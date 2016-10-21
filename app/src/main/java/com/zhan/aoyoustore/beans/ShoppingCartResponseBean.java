package com.zhan.aoyoustore.beans;

import java.util.List;

/**
 * 作者：伍岳 on 2016/10/21 23:51
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
public class ShoppingCartResponseBean {
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
        private boolean isOpenSale;
        private int recordCount;
        private double amount;
        private int point;
        private double totalAmount;
        private boolean isFreightFree;
        private boolean isReduced;
        private boolean isSendGift;
        private boolean isSendTimesPoint;
        private double reducedPromotionAmount;
        private int reducedPromotionId;
        private String reducedPromotionName;
        private int sendGiftPromotionId;
        private String sendGiftPromotionName;
        private int sentTimesPointPromotionId;
        private String sentTimesPointPromotionName;
        private double timesPoint;
        private double totalWeight;
        private double weight;
        private int freightFreePromotionId;
        private String freightFreePromotionName;
        private List<CartItemInfoBean> cartItemInfo;
        private List<?> giftInfo;

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

        public boolean isIsOpenSale() {
            return isOpenSale;
        }

        public void setIsOpenSale(boolean isOpenSale) {
            this.isOpenSale = isOpenSale;
        }

        public int getRecordCount() {
            return recordCount;
        }

        public void setRecordCount(int recordCount) {
            this.recordCount = recordCount;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public boolean isIsFreightFree() {
            return isFreightFree;
        }

        public void setIsFreightFree(boolean isFreightFree) {
            this.isFreightFree = isFreightFree;
        }

        public boolean isIsReduced() {
            return isReduced;
        }

        public void setIsReduced(boolean isReduced) {
            this.isReduced = isReduced;
        }

        public boolean isIsSendGift() {
            return isSendGift;
        }

        public void setIsSendGift(boolean isSendGift) {
            this.isSendGift = isSendGift;
        }

        public boolean isIsSendTimesPoint() {
            return isSendTimesPoint;
        }

        public void setIsSendTimesPoint(boolean isSendTimesPoint) {
            this.isSendTimesPoint = isSendTimesPoint;
        }

        public double getReducedPromotionAmount() {
            return reducedPromotionAmount;
        }

        public void setReducedPromotionAmount(double reducedPromotionAmount) {
            this.reducedPromotionAmount = reducedPromotionAmount;
        }

        public int getReducedPromotionId() {
            return reducedPromotionId;
        }

        public void setReducedPromotionId(int reducedPromotionId) {
            this.reducedPromotionId = reducedPromotionId;
        }

        public String getReducedPromotionName() {
            return reducedPromotionName;
        }

        public void setReducedPromotionName(String reducedPromotionName) {
            this.reducedPromotionName = reducedPromotionName;
        }

        public int getSendGiftPromotionId() {
            return sendGiftPromotionId;
        }

        public void setSendGiftPromotionId(int sendGiftPromotionId) {
            this.sendGiftPromotionId = sendGiftPromotionId;
        }

        public String getSendGiftPromotionName() {
            return sendGiftPromotionName;
        }

        public void setSendGiftPromotionName(String sendGiftPromotionName) {
            this.sendGiftPromotionName = sendGiftPromotionName;
        }

        public int getSentTimesPointPromotionId() {
            return sentTimesPointPromotionId;
        }

        public void setSentTimesPointPromotionId(int sentTimesPointPromotionId) {
            this.sentTimesPointPromotionId = sentTimesPointPromotionId;
        }

        public String getSentTimesPointPromotionName() {
            return sentTimesPointPromotionName;
        }

        public void setSentTimesPointPromotionName(String sentTimesPointPromotionName) {
            this.sentTimesPointPromotionName = sentTimesPointPromotionName;
        }

        public double getTimesPoint() {
            return timesPoint;
        }

        public void setTimesPoint(double timesPoint) {
            this.timesPoint = timesPoint;
        }

        public double getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(double totalWeight) {
            this.totalWeight = totalWeight;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public int getFreightFreePromotionId() {
            return freightFreePromotionId;
        }

        public void setFreightFreePromotionId(int freightFreePromotionId) {
            this.freightFreePromotionId = freightFreePromotionId;
        }

        public String getFreightFreePromotionName() {
            return freightFreePromotionName;
        }

        public void setFreightFreePromotionName(String freightFreePromotionName) {
            this.freightFreePromotionName = freightFreePromotionName;
        }

        public List<CartItemInfoBean> getCartItemInfo() {
            return cartItemInfo;
        }

        public void setCartItemInfo(List<CartItemInfoBean> cartItemInfo) {
            this.cartItemInfo = cartItemInfo;
        }

        public List<?> getGiftInfo() {
            return giftInfo;
        }

        public void setGiftInfo(List<?> giftInfo) {
            this.giftInfo = giftInfo;
        }

        public static class CartItemInfoBean {
            private String skuID;
            private int quantity;
            private int shippQuantity;
            private boolean isfreeShipping;
            private boolean isSendGift;
            private double memberPrice;
            private String name;
            private int productId;
            private String promoteType;
            private int promotionId;
            private String promotionName;
            private String sku;
            private String skuContent;
            private double subTotal;
            private String thumbnailUrl100;
            private String thumbnailUrl40;
            private String thumbnailUrl60;
            private double weight;
            private int stock;
            private String productCode;
            private String brand;
            private String shortDescription;

            public String getSkuID() {
                return skuID;
            }

            public void setSkuID(String skuID) {
                this.skuID = skuID;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getShippQuantity() {
                return shippQuantity;
            }

            public void setShippQuantity(int shippQuantity) {
                this.shippQuantity = shippQuantity;
            }

            public boolean isIsfreeShipping() {
                return isfreeShipping;
            }

            public void setIsfreeShipping(boolean isfreeShipping) {
                this.isfreeShipping = isfreeShipping;
            }

            public boolean isIsSendGift() {
                return isSendGift;
            }

            public void setIsSendGift(boolean isSendGift) {
                this.isSendGift = isSendGift;
            }

            public double getMemberPrice() {
                return memberPrice;
            }

            public void setMemberPrice(double memberPrice) {
                this.memberPrice = memberPrice;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getPromoteType() {
                return promoteType;
            }

            public void setPromoteType(String promoteType) {
                this.promoteType = promoteType;
            }

            public int getPromotionId() {
                return promotionId;
            }

            public void setPromotionId(int promotionId) {
                this.promotionId = promotionId;
            }

            public String getPromotionName() {
                return promotionName;
            }

            public void setPromotionName(String promotionName) {
                this.promotionName = promotionName;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public String getSkuContent() {
                return skuContent;
            }

            public void setSkuContent(String skuContent) {
                this.skuContent = skuContent;
            }

            public double getSubTotal() {
                return subTotal;
            }

            public void setSubTotal(double subTotal) {
                this.subTotal = subTotal;
            }

            public String getThumbnailUrl100() {
                return thumbnailUrl100;
            }

            public void setThumbnailUrl100(String thumbnailUrl100) {
                this.thumbnailUrl100 = thumbnailUrl100;
            }

            public String getThumbnailUrl40() {
                return thumbnailUrl40;
            }

            public void setThumbnailUrl40(String thumbnailUrl40) {
                this.thumbnailUrl40 = thumbnailUrl40;
            }

            public String getThumbnailUrl60() {
                return thumbnailUrl60;
            }

            public void setThumbnailUrl60(String thumbnailUrl60) {
                this.thumbnailUrl60 = thumbnailUrl60;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getShortDescription() {
                return shortDescription;
            }

            public void setShortDescription(String shortDescription) {
                this.shortDescription = shortDescription;
            }
        }
    }
}
