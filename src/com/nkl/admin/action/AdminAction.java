package com.nkl.admin.action;

import java.util.Date;
import java.util.List;

import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsPic;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();

	//抓取页面参数
	User paramsUser;
	Goods paramsGoods;
	GoodsPic paramsGoodsPic;
	Sblog paramsSblog;
	Orders paramsOrders;
	Logistics paramsLogistics;
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			Param.setSession("admin", admin);
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		setSuccessTip("编辑成功", "modifyInfo.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		setSuccessTip("修改成功", "modifyPwd.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询注册用户
			paramsUser.setUser_type(1);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			 //添加用户
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("添加用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("删除用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询产品
	 * @return String
	 */
	public String listGoodss(){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//设置分页信息
			setPagination(paramsGoods);
			int[] sum={0};
			List<Goods> goodss = adminManager.listGoodss(paramsGoods,sum); 
			
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询产品异常", "main.jsp");
			return "infoTip";
		}
		
		return "goodsShow";
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 查询产品详情
	 * @return String
	 */
	public String queryGoods(){
		try {
			 //得到产品
			Goods goods = adminManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
		} catch (Exception e) {
			setErrorTip("查询产品异常", "Admin_listGoodss.action");
			return "infoTip";
		}
		
		return "goodsDetail";
	}
	
	/**
	 * @Title: assessGoods
	 * @Description: 审核产品
	 * @return String
	 */
	public String assessGoods(){
		try {
			 //审核产品
			paramsGoods.setGoods_status(2);
			adminManager.updateGoods(paramsGoods);

			setSuccessTip("审核成功", "Admin_listGoodss.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("审核异常", "Admin_listGoodss.action");
		}
		return "infoTip";
	}
	 
	/**
	 * @Title: delGoodss
	 * @Description: 删除产品
	 * @return String
	 */
	public String delGoodss(){
		try {
			 //删除产品
			adminManager.delGoodss(paramsGoods);

			setSuccessTip("删除产品成功", "Admin_listGoodss.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("删除产品异常", "Admin_listGoodss.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 查询商品订单
	 * @return String
	 */
	public String listOrderss(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品订单列表
			List<Orders> orderss = adminManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询商品订单异常", "main.jsp");
			return "infoTip";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @return String
	 */
	public String sendOrders(){
		try {
			 //订单发货
			adminManager.sendOrders(paramsOrders);
			
			setSuccessTip("订单发货成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("订单发货异常", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delOrderss
	 * @Description: 删除商品订单
	 * @return String
	 */
	public String delOrderss(){
		try {
			 //删除商品订单
			adminManager.delOrderss(paramsOrders);
			
			setSuccessTip("删除商品订单成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("删除商品订单异常", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 查询商品订单物流信息
	 * @return String
	 */
	public String listLogisticss(){
		try {
			if (paramsLogistics==null) {
				paramsLogistics = new Logistics();
			}
			//设置分页信息不分页
			paramsLogistics.setStart(-1);
			//查询商品订单物流信息
			List<Logistics> logisticss = adminManager.listLogisticss(paramsLogistics,null); 
			Param.setAttribute("logisticss", logisticss);
			
			Param.setAttribute("orders_no", paramsLogistics.getOrders_no());
			
		} catch (Exception e) {
			setErrorTip("查询商品订单物流信息异常", "main.jsp");
			return "infoTip";
		}
		
		return "logisticsShow";
	}
	
	/**
	 * @Title: addLogisticsShow
	 * @Description: 显示添加物流信息页面
	 * @return String
	 */
	public String addLogisticsShow(){
		Param.setAttribute("orders_no", paramsLogistics.getOrders_no());
		return "logisticsEdit";
	}
	
	/**
	 * @Title: addLogistics
	 * @Description: 添加物流信息
	 * @return String
	 */
	public String addLogistics(){
		try {
			 //添加物流信息
			adminManager.addLogistics(paramsLogistics);
			
		} catch (Exception e) {
			setErrorTip("添加物流信息异常", "Admin_listLogisticss.action?paramsLogistics.orders_no="+paramsLogistics.getOrders_no());
		}
		setSuccessTip("添加物流信息成功", "Admin_listLogisticss.action?paramsLogistics.orders_no="+paramsLogistics.getOrders_no());
		return "infoTip";
	}
	
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Goods getParamsGoods() {
		return paramsGoods;
	}

	public void setParamsGoods(Goods paramsGoods) {
		this.paramsGoods = paramsGoods;
	}

	public GoodsPic getParamsGoodsPic() {
		return paramsGoodsPic;
	}

	public void setParamsGoodsPic(GoodsPic paramsGoodsPic) {
		this.paramsGoodsPic = paramsGoodsPic;
	}

	public Sblog getParamsSblog() {
		return paramsSblog;
	}

	public void setParamsSblog(Sblog paramsSblog) {
		this.paramsSblog = paramsSblog;
	}

	public Orders getParamsOrders() {
		return paramsOrders;
	}

	public Logistics getParamsLogistics() {
		return paramsLogistics;
	}

	public void setParamsOrders(Orders paramsOrders) {
		this.paramsOrders = paramsOrders;
	}

	public void setParamsLogistics(Logistics paramsLogistics) {
		this.paramsLogistics = paramsLogistics;
	}


}
