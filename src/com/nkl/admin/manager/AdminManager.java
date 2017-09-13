package com.nkl.admin.manager;

import java.sql.Connection;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.page.dao.GoodsDao;
import com.nkl.page.dao.GoodsPicDao;
import com.nkl.page.dao.LogisticsDao;
import com.nkl.page.dao.OrdersDao;
import com.nkl.page.dao.SblogDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.User;

public class AdminManager {

	UserDao userDao = new UserDao();
	GoodsDao goodsDao = new GoodsDao();
	GoodsPicDao goodsPicDao = new GoodsPicDao();
	SblogDao sblogDao = new SblogDao();
	OrdersDao ordersDao = new OrdersDao();
	LogisticsDao logisticsDao = new LogisticsDao();
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return users;
	}
	
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
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
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
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
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
	
	/**
	 * @Title: queryGoods
	 * @Description: 产品查询
	 * @param goods
	 * @return Goods
	 */
	public Goods queryGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		Goods _goods = goodsDao.getGoods(goods, conn);
		BaseDao.closeDB(null, null, conn);
		return _goods;
	}
	
	/**
	 * @Title: updateGoods
	 * @Description: 更新产品
	 * @param goods
	 * @return void
	 */
	public void updateGoods(Goods goods) {
		Connection conn = BaseDao.getConnection();
		goodsDao.updateGoods(goods,conn);
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
	 * @Title: delOrderss
	 * @Description: 删除商品订单信息
	 * @param orders
	 * @return void
	 */
	public void  delOrderss(Orders orders){
		Connection conn = BaseDao.getConnection();
		ordersDao.delOrderss(orders.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @param orders
	 * @return void
	 */
	public void sendOrders(Orders orders) {
		Connection conn = BaseDao.getConnection();
		//确认订单信息
		orders.setOrders_flag(2);//2-已发货 
		ordersDao.updateOrders(orders,conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	
	/**
	 * @Title: listLogisticss
	 * @Description: 商品订单物流信息查询
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
	 * @Title: addLogistics
	 * @Description: 新增物流信息
	 * @param logistics
	 * @return void
	 */
	public void addLogistics(Logistics logistics) {
		Connection conn = BaseDao.getConnection();
		logisticsDao.addLogistics(logistics, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
}
