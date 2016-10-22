package com.zhan.aoyoustore.base;

/**
 * Created by Wuyue on 2015/10/14.
 */
public class UserInfo extends BasePersistObject {
    private boolean isLogin =false;

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
    private double expenditure;
    private int points;
    private int gender;
    private String picture;
    private int couponsCount;
    private boolean IsOpenBalance;
    private double balance;
    private double hasBalance;
    private double frozenBalance;
    private String email;
    private String mobile;
    private String companyName;
    private String companyType;
    private String companyJob;
    private String companySite;
    private String telPhone;
    private String fax;
    private String ReferralStatus;
    private String province;
    private String city;
    private String district;
    private String address;
    private String qq;
    private String msn;
    private String wangWang;
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }


    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setOpenBalance(boolean openBalance) {
        IsOpenBalance = openBalance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyJob() {
        return companyJob;
    }

    public void setCompanyJob(String companyJob) {
        this.companyJob = companyJob;
    }

    public String getCompanySite() {
        return companySite;
    }

    public void setCompanySite(String companySite) {
        this.companySite = companySite;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getWangWang() {
        return wangWang;
    }

    public void setWangWang(String wangWang) {
        this.wangWang = wangWang;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJpush() {
        return jpush;
    }

    public void setJpush(String jpush) {
        this.jpush = jpush;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getWaitPayCount() {
        return waitPayCount;
    }

    public void setWaitPayCount(int waitPayCount) {
        this.waitPayCount = waitPayCount;
    }

    public int getWaitFinishCount() {
        return waitFinishCount;
    }

    public void setWaitFinishCount(int waitFinishCount) {
        this.waitFinishCount = waitFinishCount;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCouponsCount() {
        return couponsCount;
    }

    public void setCouponsCount(int couponsCount) {
        this.couponsCount = couponsCount;
    }

    public boolean isOpenBalance() {
        return IsOpenBalance;
    }

    public void setIsOpenBalance(boolean isOpenBalance) {
        IsOpenBalance = isOpenBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getHasBalance() {
        return hasBalance;
    }

    public void setHasBalance(double hasBalance) {
        this.hasBalance = hasBalance;
    }

    public double getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(double frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public String getReferralStatus() {
        return ReferralStatus;
    }

    public void setReferralStatus(String referralStatus) {
        ReferralStatus = referralStatus;
    }

    public static UserInfo getsUserInfo() {
        return sUserInfo;
    }

    public static void setsUserInfo(UserInfo sUserInfo) {
        UserInfo.sUserInfo = sUserInfo;
    }

    private static UserInfo sUserInfo=null;

    public static UserInfo getCurrentUser() {
        if(sUserInfo==null){
            sUserInfo=getPersisObject(UserInfo.class);
        }
        return sUserInfo;
    }

    public static void saveLoginUserInfo(UserInfo user) {
        //如果登录的不是同一个用户就清除相关数据
        /*if(getCurrentUser()==null||!getCurrentUser().getUserID().equals(user.getUserID())){
            PushMessageInfo.clearPushMessageInfo();
        }*/
        persisObject(user);
        sUserInfo=user;
    }

    public static void logout() {
        if(sUserInfo!=null){
            sUserInfo.setIsLogin(false);
            persisObject(sUserInfo);
        }
        //PushMessageInfo.clearPushMessageInfo();
        /*deletePersistObject(UserInfo.class);
        sUserInfo=null;*/
    }

}
