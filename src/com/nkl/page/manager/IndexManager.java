package com.nkl.page.manager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.Param;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.CollectDao;
import com.nkl.page.dao.GoodsDao;
import com.nkl.page.dao.GoodsPicDao;
import com.nkl.page.dao.LogisticsDao;
import com.nkl.page.dao.OrdersDao;
import com.nkl.page.dao.SblogDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Collect;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsPic;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;

public class IndexManager {

	UserDao userDao = new UserDao();
	GoodsDao goodsDao = new GoodsDao();
	GoodsPicDao goodsPicDao = new GoodsPicDao();
	SblogDao sblogDao = new SblogDao();
	OrdersDao ordersDao = new OrdersDao();
	LogisticsDao logisticsDao = new LogisticsDao();
	CollectDao collectDao = new CollectDao();
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}
	 
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询产品信息
	 * @param goods
	 * @return List<Goods>
	 */
	public List<Goods>  listGoodss(Goods goods,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = goodsDao.listGoodssCount(goods, conn);
		}
		List<Goods> goodss = goodsDao.listGoodss(goods,conn);
		BaseDao.closeDB(null, null, conn);
		return goodss;
	}
	
	public List<GoodsPic>  listGoodsPics(GoodsPic goodsPic,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = goodsPicDao.listGoodsPicsCount(goodsPic, conn);
		}
		List<GoodsPic> goodsPics = goodsPicDao.listGoodsPics(goodsPic,conn);
		BaseDao.closeDB(null, null, conn);
		return goodsPics;
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 产品查询
	 * @param goods
	 * @return Goods
	 */
	public Goods queryGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		Goods _goods = goodsDao.getGoods(goods, conn);
		_goods.setGoods_click(_goods.getGoods_click()+1);
		goodsDao.updateGoods(_goods, conn);
		BaseDao.closeDB(null, null, conn);
		return _goods;
	}
	
	/**
	 * @Title: addGoods
	 * @Description: 添加商品
	 * @param goods
	 * @return void
	 */
	public void addGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			goods.setGoods_desc(Transcode.htmlEncode(goods.getGoods_desc()));
		}
		goods.setGoods_date(DateUtil.dateToDateString(new Date()));
		if (goods.getGoods_flag()==1) {
			goods.setGoods_status(1);//待审核
		}else {
			goods.setGoods_status(2);//无需审核
		}
		
		goodsDao.addGoods(goods, conn);
		
		//处理图片
		String goods_pics = goods.getGoods_pics();
		if (!StringUtil.isEmptyString(goods_pics)) {
			User userFront = (User)Param.getSession("userFront");
			int goods_id = goodsDao.getGoodsId(userFront.getUser_id(), conn);
			String[] pics = goods_pics.split(",");
			for (String pic : pics) {
				GoodsPic goodsPic = new GoodsPic();
				goodsPic.setGoods_id(goods_id);
				goodsPic.setGoods_pic(pic);
				goodsPicDao.addGoodsPic(goodsPic, conn);
			}
		}
		
		BaseDao.closeDB(null, null, conn);
		
	}

	/**
	 * @Title: updateGoods
	 * @Description: 更新商品信息
	 * @param goods
	 * @return void
	 */
	public void updateGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			goods.setGoods_desc(Transcode.htmlEncode(goods.getGoods_desc()));
		}
		goodsDao.updateGoods(goods, conn);
		
		//处理图片
		String goods_pics = goods.getGoods_pics();
		if (!StringUtil.isEmptyString(goods_pics)) {
			int goods_id = goods.getGoods_id();
			goodsPicDao.delGoodsPicByGoodsId(goods_id+"", conn);
			String[] pics = goods_pics.split(",");
			for (String pic : pics) {
				GoodsPic goodsPic = new GoodsPic();
				goodsPic.setGoods_id(goods_id);
				goodsPic.setGoods_pic(pic);
				goodsPicDao.addGoodsPic(goodsPic, conn);
			}
		}
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delGoods
	 * @Description: 删除商品信息
	 * @param goods
	 * @return void
	 */
	public void delGoodss(Goods goods) {
		Connection conn = BaseDao.getConnection();
		goodsDao.delGoodss(goods.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询评论
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog>  listSblogs(Sblog sblog,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = sblogDao.listSblogsCount(sblog, conn);
		}
		List<Sblog> sblogs = sblogDao.listSblogs(sblog, conn);
		BaseDao.closeDB(null, null, conn);
		return sblogs;
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增评论
	 * @param sblog
	 * @return void
	 */
	public void  addSblog(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		sblog.setSblog_date(DateUtil.dateToDateString(new Date()));
		sblogDao.addSblog(sblog, conn);
		BaseDao.closeDB(null, null, conn);
		
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 添加商品订单
	 * @param orders
	 * @return Orders
	 */
	public void addOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		//生成订单号
		String orders_no = DateUtil.dateToDateString(new Date(), "yyyyMMddHHmmss")+orders.getUser_id();
		orders.setOrders_no(orders_no);
		//订单日期
		orders.setOrders_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd"));
		//1：待发货
		orders.setOrders_flag(1);
		//保存订单
		ordersDao.addOrders(orders, conn);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 商品订单查询
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders>  listOrderss(Orders orders,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = ordersDao.listOrderssCount(orders, conn);
		}
		List<Orders> orderss = ordersDao.listOrderss(orders,conn);
		
		BaseDao.closeDB(null, null, conn);
		return orderss;
	}
	
	/**
	 * @Title: queryOrders
	 * @Description: 商品订单查询
	 * @param orders
	 * @return Orders
	 */
	public Orders  queryOrders(Orders orders){
		Connection conn = BaseDao.getConnection();
		Orders _orders = ordersDao.getOrders(orders, conn);
		BaseDao.closeDB(null, null, conn);
		return _orders;
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 查询商品物流信息
	 * @param logistics
	 * @return List<Logistics>
	 */
	public List<Logistics>  listLogisticss(Logistics logistics,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = logisticsDao.listLogisticssCount(logistics, conn);
		}
		List<Logistics> logisticss = logisticsDao.listLogisticss(logistics,conn);
		BaseDao.closeDB(null, null, conn);
		return logisticss;
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @param Orders
	 * @return void
	 */
	public void finishOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		//确认收货
		orders.setOrders_flag(3);
		ordersDao.updateOrders(orders, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listCollects
	 * @Description: 查询收藏信息
	 * @param collect
	 * @return List<Collect>
	 */
	public List<Collect>  listCollects(Collect collect,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = collectDao.listCollectsCount(collect, conn);
		}
		List<Collect> collects = collectDao.listCollects(collect, conn);

		BaseDao.closeDB(null, null, conn);
		return collects;
	}
	
	/**
	 * @Title: addCollect
	 * @Description: 添加收藏
	 * @param report
	 * @return void
	 */
	public void addCollect(Collect collect) {
		Connection conn = BaseDao.getConnection();
		//添加收藏
		Collect _collect = collectDao.getCollect(collect, conn);
		if (_collect==null) {
			collect.setCollect_date(DateUtil.getCurDateTime());
			collectDao.addCollect(collect, conn);
		}
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delCollect
	 * @Description: 删除商品收藏
	 * @param collect
	 * @return void
	 */
	public void delCollects(Collect collect) {
		Connection conn = BaseDao.getConnection();
		collectDao.delCollects(collect.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
}
