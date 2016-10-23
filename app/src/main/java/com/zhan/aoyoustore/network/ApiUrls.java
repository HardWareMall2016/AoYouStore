package com.zhan.aoyoustore.network;

/**
 * 作者：伍岳 on 2016/3/7 17:34
 * 邮箱：wuyue8512@163.com
 //
 //         .............................................
 //                  美女坐镇                  BUG辟易
 //         .............................................
 //
 //                       .::::.
 //                     .::::::::.
 //                    :::::::::::
 //                 ..:::::::::::'
 //              '::::::::::::'
 //                .::::::::::
 //           '::::::::::::::..
 //                ..::::::::::::.
 //              ``::::::::::::::::
 //               ::::``:::::::::'        .:::.
 //              ::::'   ':::::'       .::::::::.
 //            .::::'      ::::     .:::::::'::::.
 //           .:::'       :::::  .:::::::::' ':::::.
 //          .::'        :::::.:::::::::'      ':::::.
 //         .::'         ::::::::::::::'         ``::::.
 //     ...:::           ::::::::::::'              ``::.
 //    ```` ':.          ':::::::::'                  ::::..
 //                       '.:::::'                    ':'````..
 //
 */
public class ApiUrls {
    //User - 用户登录
    public static final String LOGIN = "login.ashx";
    //User - 用户注册
    public static final String REGISTER = "register.ashx";
    //获取注册短信验证码
    public static final String GET_LOGIN_CAPTCHA = "getLoginCaptcha.ashx";

    /***
     *  商城-商品信息
     */
    //获取商城分类
    public static final String GET_SHOP_CATEGORIES = "getShopCategories.ashx";
    //获取商品列表
    public static final String GET_PRODUCT_LIST = "getProductList.ashx";
    //获取商品详情
    public static final String GET_PRODUCT_DETAIL = "getProductDetail.ashx";

    /***
     *  会员中心
     */
    //获取收货地址
    public static final String GET_ADDRE_LIST = "getUserAddrList.ashx";

    /***
     *  商城-常用查询
     */
    //获取分类下所有帮助信息
    public static final String GET_HELPS_BY_CATEGORYS_ID = "getHelpsByCategoryId.ashx";
    //获取帮助详情内容
    public static final String GET_HELPS_INFO = "getHelpInfoById.ashx";

    //获取商城分类
    public static final String GET_ARTICLE_CATEGORYS = "getArticleCategorys.ashx";
    //获取分类下所有文章内容
    public static final String GET_ARTICLES_BY_CATEGORY_ID = "getArticlesByCategoryId.ashx";
    //获取文章详细内容
    public static final String GET_ARTICLE_INFO = "getArticleInfoById.ashx";

    /***
     *  商城- 购物车
     */
    //获取购物车
    public static final String GET_SHOPPING_CART = "getShoppingCart.ashx";
    //加入购物车
    public static final String  ADD_TO_CART = "addToCart.ashx";
    // 购物车数量修改
    public static final String SET_SHOPPING_CART_QUANTITY = "setShoppingCartQuantity.ashx";
    //删除购物车商品
    public static final String DEL_CART_ITEM = "delCartItem.ashx";

}
