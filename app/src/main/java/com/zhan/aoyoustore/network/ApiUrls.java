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
    public static final String LOGIN = "uploadImg.do";

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
     *  商城-文章资讯
     */
    //获取商城分类
    public static final String GET_ARTICLE_CATEGORYS = "getArticleCategorys.ashx";
    //获取分类下所有文章内容
    public static final String GET_ARTICLES_BY_CATEGORY_ID = "getArticlesByCategoryId.ashx";
    //获取文章详细内容
    public static final String GET_ARTICLE_INFO = "getArticleInfoById.ashx";
}
