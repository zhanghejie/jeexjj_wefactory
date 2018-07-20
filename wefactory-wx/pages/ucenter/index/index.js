var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var user = require('../../../utils/user.js');
var app = getApp();

Page({
  data: {
    userInfo: {
      nickName: '点击登录',
      avatarUrl: api.rootPath +"/files/default/no-login.png"
    }
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
  },
  onReady: function () {

  },
  onShow: function () {
    console.info("ucenter/index.js.onShow-app.globalData-");
    console.info(app.globalData);
    //获取用户的登录信息
    if (app.globalData.hasLogin){
      let userInfo = wx.getStorageSync('userInfo');
      this.setData({
        userInfo: userInfo,
      });
    }

  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭
  },
  goLogin(){
    if (!app.globalData.hasLogin) {
      wx.navigateTo({ url: "/pages/auth/login/login" });
    }
  },
  goOrder() {

    console.info("ucenter/index.js.goOrder-app.globalData-");
    console.info(app.globalData);

    if (app.globalData.hasLogin) {
      wx.navigateTo({ url: "/pages/ucenter/order/order" });
    }
    else {
      wx.navigateTo({ url: "/pages/auth/login/login" });
    }
  },
  goCoupon() {
    if (app.globalData.hasLogin) {
      wx.navigateTo({ url: "/pages/ucenter/coupon/coupon" });
    }
    else {
      wx.navigateTo({ url: "/pages/auth/login/login" });
    };

  },
  goCollect() {
    if (app.globalData.hasLogin) {
      wx.navigateTo({ url: "/pages/ucenter/collect/collect" });
    }
    else {
      wx.navigateTo({ url: "/pages/auth/login/login" });
    };
  },
  goFootprint() {
    if (app.globalData.hasLogin) {
      wx.navigateTo({ url: "/pages/ucenter/footprint/footprint" });
    }
    else {
      wx.navigateTo({ url: "/pages/auth/login/login" });
    };
  },
  goAddress() {
    if (app.globalData.hasLogin) {
      wx.navigateTo({ url: "/pages/ucenter/address/address" });
    }
    else {
      wx.navigateTo({ url: "/pages/auth/login/login" });
    };
  },
  exitLogin: function () {
    wx.showModal({
      title: '',
      confirmColor: '#b4282d',
      content: '退出登录？',
      success: function (res) {
        if (res.confirm) {
          wx.removeStorageSync('token');
          wx.removeStorageSync('userInfo');
          wx.switchTab({
            url: '/pages/index/index'
          });
        }
      }
    })

  }
})