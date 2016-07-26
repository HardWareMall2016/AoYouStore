package com.zhan.aoyoustore.beans;

/**
 * 作者：keke on 2016/7/26 11:06
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
public class RegisterResponseBean {
    /**
     * res : 1
     * token : 6b771354-38c3-46c3-bc81-74094d5bc047
     * jpush : ddcc17a1_4d03_42c4_813d_67a1be755bf3
     * uid : 0
     * userName : hehehe1
     * gradeName : 普通会员
     * nickName :
     * realName : hehehe1
     * waitPayCount : 0
     * waitFinishCount : 0
     * orderNumber : 0
     * expenditure : 0
     * points : 0
     * gender : 0
     * picture :
     * couponsCount : 0
     * IsOpenBalance : false
     * balance : 0
     * hasBalance : 0
     * frozenBalance : 0
     * userType :
     * ReferralStatus : 0
     */

    private ResultEntity result;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        private int res;
        private String token;
        private String jpush;
        private int uid;
        private String userName;
        private String gradeName;
        private String nickName;
        private String realName;
        private int waitPayCount;
        private int waitFinishCount;
        private int orderNumber;
        private int expenditure;
        private int points;
        private int gender;
        private String picture;
        private int couponsCount;
        private boolean IsOpenBalance;
        private int balance;
        private int hasBalance;
        private int frozenBalance;
        private String userType;
        private String ReferralStatus;

        public void setRes(int res) {
            this.res = res;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setJpush(String jpush) {
            this.jpush = jpush;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public void setWaitPayCount(int waitPayCount) {
            this.waitPayCount = waitPayCount;
        }

        public void setWaitFinishCount(int waitFinishCount) {
            this.waitFinishCount = waitFinishCount;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        public void setExpenditure(int expenditure) {
            this.expenditure = expenditure;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public void setCouponsCount(int couponsCount) {
            this.couponsCount = couponsCount;
        }

        public void setIsOpenBalance(boolean IsOpenBalance) {
            this.IsOpenBalance = IsOpenBalance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public void setHasBalance(int hasBalance) {
            this.hasBalance = hasBalance;
        }

        public void setFrozenBalance(int frozenBalance) {
            this.frozenBalance = frozenBalance;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public void setReferralStatus(String ReferralStatus) {
            this.ReferralStatus = ReferralStatus;
        }

        public int getRes() {
            return res;
        }

        public String getToken() {
            return token;
        }

        public String getJpush() {
            return jpush;
        }

        public int getUid() {
            return uid;
        }

        public String getUserName() {
            return userName;
        }

        public String getGradeName() {
            return gradeName;
        }

        public String getNickName() {
            return nickName;
        }

        public String getRealName() {
            return realName;
        }

        public int getWaitPayCount() {
            return waitPayCount;
        }

        public int getWaitFinishCount() {
            return waitFinishCount;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public int getExpenditure() {
            return expenditure;
        }

        public int getPoints() {
            return points;
        }

        public int getGender() {
            return gender;
        }

        public String getPicture() {
            return picture;
        }

        public int getCouponsCount() {
            return couponsCount;
        }

        public boolean isIsOpenBalance() {
            return IsOpenBalance;
        }

        public int getBalance() {
            return balance;
        }

        public int getHasBalance() {
            return hasBalance;
        }

        public int getFrozenBalance() {
            return frozenBalance;
        }

        public String getUserType() {
            return userType;
        }

        public String getReferralStatus() {
            return ReferralStatus;
        }
    }
}
