package com.nkl.page.action;

import java.util.List;

import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Collect;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsPic;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;
import com.nkl.page.manager.IndexManager;

public class IndexAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	IndexManager indexManager = new IndexManager();

	//抓取页面参数
	User paramsUser;
	Goods paramsGoods;
	GoodsPic paramsGoodsPic;
	Sblog paramsSblog;
	Orders paramsOrders;
	Logistics paramsLogistics;
	Collect paramsCollect;
	String tip;
	
	/**
	 * @Title: index
	 * @Description: 查询发布商品信息集合
	 * @return String
	 */
	public String index(){
		try {
			//查询发布商品集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			setPagination(paramsGoods);
			int[] sum = {0};
			//商品类别
			paramsGoods.setGoods_flag(1);
			paramsGoods.setGoods_status(2);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "index";
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 查询产品详情
	 * @return String
	 */
	public String queryGoods(){
		try {
			 //得到产品
			Goods goods = indexManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			Param.setAttribute("goodsPics", goodsPics);
			
			//查询商品评论
			if (paramsSblog==null) {
				paramsSblog  = new Sblog();
				paramsSblog.setGoods_id(paramsGoods.getGoods_id());
			}
			//分页信息设置
			setPagination(paramsSblog);
			int[] sum = {0};
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog, sum);
			Param.setAttribute("sblogs", sblogs);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsDetail";
	}
	
	/**
	 * @Title: addCollect
	 * @Description: 收藏商品
	 * @return String
	 */
	public String addCollect(){
		try {
			//收藏商品
			indexManager.addCollect(paramsCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("收藏商品失败！");
			return "error2";
		}
		
		return "success";
	}
	
	
	/**
	 * @Title: goodsBuy
	 * @Description: 查询商品求购信息集合
	 * @return String
	 */
	public String goodsBuy(){
		try {
			//查询商品求购集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			setPagination(paramsGoods);
			int[] sum = {0};
			//商品类别
			paramsGoods.setGoods_flag(2);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
			//查询评论信息
			if (goodss!=null && goodss.size()>0) {
				for (Goods goods : goodss) {
					Sblog sblog = new Sblog();
					sblog.setStart(-1);
					sblog.setGoods_id(goods.getGoods_id());
					List<Sblog> sblogs = indexManager.listSblogs(sblog, null);
					goods.setSblogs(sblogs);
					goods.setGoods_head((goods.getGoods_id()%5==0?5:goods.getGoods_id()%5)+"");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsBuy";
	}
	
	/**
	 * @Title: goodsExchange
	 * @Description: 查询商品交换信息集合
	 * @return String
	 */
	public String goodsExchange(){
		try {
			//查询商品交换集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			setPagination(paramsGoods);
			int[] sum = {0};
			//商品类别
			paramsGoods.setGoods_flag(3);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
			//查询评论信息
			if (goodss!=null && goodss.size()>0) {
				for (Goods goods : goodss) {
					Sblog sblog = new Sblog();
					sblog.setStart(-1);
					sblog.setGoods_id(goods.getGoods_id());
					List<Sblog> sblogs = indexManager.listSblogs(sblog, null);
					goods.setSblogs(sblogs);
					goods.setGoods_head((goods.getGoods_id()%5==0?5:goods.getGoods_id()%5)+"");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsExchange";
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增评论
	 * @return String
	 */
	public String addSblog(){
		try {
			//新增评论
			indexManager.addSblog(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 新增订单
	 * @return String
	 */
	public String addOrders(){
		try {
			//新增订单
			indexManager.addOrders(paramsOrders);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("提交订单失败！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: listMyGoodss
	 * @Description: 查询我的商品发布
	 * @return String
	 */
	public String listMyGoodss(){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//获取用户,用户只能查询自己的
			User userFront = (User)Param.getSession("userFront");
			if (userFront.getUser_type()==1) {
				paramsGoods.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			setPagination(paramsGoods);
			int[] sum={0};
			paramsGoods.setGoods_flag(1);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsShow";
	}
	
	/**
	 * @Title: listMyGoodsBuys
	 * @Description: 商品求购信息集合
	 * @return String
	 */
	public String listMyGoodsBuys(){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//获取用户,用户只能查询自己的
			User userFront = (User)Param.getSession("userFront");
			if (userFront.getUser_type()==1) {
				paramsGoods.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			setPagination(paramsGoods);
			int[] sum={0};
			paramsGoods.setGoods_flag(2);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsBuyShow";
	}
	
	/**
	 * @Title: listMyGoodsExchanges
	 * @Description: 商品交换信息集合
	 * @return String
	 */
	public String listMyGoodsExchanges(){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//获取用户,用户只能查询自己的
			User userFront = (User)Param.getSession("userFront");
			if (userFront.getUser_type()==1) {
				paramsGoods.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			setPagination(paramsGoods);
			int[] sum={0};
			paramsGoods.setGoods_flag(3);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsExchangeShow";
	}
	
	/**
	 * @Title: queryMyGoods
	 * @Description: 查看商品
	 * @return String
	 */
	public String queryMyGoods(){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			Param.setAttribute("goodsPics", goodsPics);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsDetail";
	}
	
	/**
	 * @Title: queryMyGoodsBuy
	 * @Description: 查看商品求购信息
	 * @return String
	 */
	public String queryMyGoodsBuy(){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			Param.setAttribute("goodsPics", goodsPics);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsBuyDetail";
	}
	
	/**
	 * @Title: queryMyGoodsExchange
	 * @Description: 查看商品交换信息
	 * @return String
	 */
	public String queryMyGoodsExchange(){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			Param.setAttribute("goodsPics", goodsPics);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsExchangeDetail";
	}
	
	/**
	 * @Title: addGoodsShow
	 * @Description: 显示添加商品页面
	 * @return String
	 */
	public String addGoodsShow(){
		return "myGoodsEdit";
	}
	
	public String addGoodsBuyShow(){
		return "myGoodsBuyEdit";
	}
	
	public String addGoodsExchangeShow(){
		return "myGoodsExchangeEdit";
	}
	
	/**
	 * @Title: addGoods
	 * @Description: 添加商品
	 * @return String
	 */
	public String addGoods(){
		try {
			 //添加商品
			indexManager.addGoods(paramsGoods);
			
		} catch (Exception e) {
			setErrorReason("添加商品异常");
			return "error2";
		}
		return "success";
	}
	
	/**
	 * @Title: editGoods
	 * @Description: 编辑商品
	 * @return String
	 */
	public String editGoods(){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			Param.setAttribute("goodsPics", goodsPics);
			
			String goods_pics = "";
			for (GoodsPic goodsPic2 : goodsPics) {
				goods_pics += "," + goodsPic2.getGoods_pic();
			}
			goods.setGoods_pics(goods_pics.substring(1));
			Param.setAttribute("goods", goods);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsEdit";
	}
	
	public String editGoodsBuy(){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsBuyEdit";
	}
	
	public String editGoodsExchange(){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsExchangeEdit";
	}
	
	
	/**
	 * @Title: saveGoods
	 * @Description: 保存编辑商品
	 * @return String
	 */
	public String saveGoods(){
		try {
			 //保存编辑商品
			indexManager.updateGoods(paramsGoods);
			
		} catch (Exception e) {
			setErrorReason("编辑商品异常");
			return "error2";
		}
		return "success";
	}
	
	/**
	 * @Title: delGoodss
	 * @Description: 删除商品
	 * @return String
	 */
	public String delGoodss(){
		try {
			 //删除商品
			indexManager.delGoodss(paramsGoods);
			
		} catch (Exception e) {
			setErrorReason("删除商品异常");
			return "error2";
		}
		return "success";
	}
	
	/**
	 * @Title: listMyCollects
	 * @Description: 查询我的商品收藏
	 * @return String
	 */
	public String listMyCollects(){
		try {
			if (paramsCollect==null) {
				paramsCollect = new Collect();
			}
			//获取用户,用户只能查询自己的商品收藏
			User userFront = (User)Param.getSession("userFront");
			if (userFront==null) {
				Param.setAttribute("collects", null);
				return "collectShow";
			}
			paramsCollect.setUser_id(userFront.getUser_id());
			//设置分页信息
			setPagination(paramsCollect);
			//总的条数
			int[] sum={0};
			//查询商品列表
			List<Collect> collects = indexManager.listCollects(paramsCollect,sum); 
			
			Param.setAttribute("collects", collects);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "collectShow";
	}
	
	/**
	 * @Title: delCollects
	 * @Description: 删除商品收藏
	 * @return String
	 */
	public String delCollects(){
		try {
			 //删除商品
			indexManager.delCollects(paramsCollect);
			
		} catch (Exception e) {
			setErrorReason("删除商品异常");
			return "error2";
		}
		return "success";
	}
	
	/**
	 * @Title: saveUserFront
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveUserFront(){
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User userFront = new User();
			userFront.setUser_id(paramsUser.getUser_id());
			userFront = indexManager.getUser(userFront);
			Param.setSession("userFront", userFront);
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userInfo";
		}
		tip = "修改成功";
		return "userInfo";
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveUserFrontPass(){
		try {
			 //保存修改个人密码
			indexManager.updateUser(paramsUser);
			//更新session
			User UserFront = (User)Param.getSession("UserFront");
			if (UserFront!=null) {
				UserFront.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("UserFront", UserFront);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userPwd";
		}
		tip = "修改成功";
		return "userPwd";
	}
	
	/**
	 * @Title: listMyOrderss1
	 * @Description: 查询我的卖出订单
	 * @return String
	 */
	public String listMyOrderss1(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//获取用户,用户只能查询自己的订单
			User userFront = (User)Param.getSession("userFront");
			if (userFront.getUser_type()==1) {
				paramsOrders.setSell_id(userFront.getUser_id());
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Orders> orderss = indexManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersShow1";
	}
	
	/**
	 * @Title: listMyOrderss2
	 * @Description: 查询我的购买订单
	 * @return String
	 */
	public String listMyOrderss2(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//获取用户,用户只能查询自己的订单
			User userFront = (User)Param.getSession("userFront");
			if (userFront.getUser_type()==1) {
				paramsOrders.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Orders> orderss = indexManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersShow2";
	}
	 
	/**
	 * @Title: listLogisticss
	 * @Description: 展示物流信息列表
	 * @return String
	 */
	public String listLogisticss(){
		try {
			//查询物流信息集合
			if (paramsLogistics==null) {
				paramsLogistics = new Logistics();
			}
			//设置分页信息
			paramsLogistics.setStart(-1);
			List<Logistics> logisticss = indexManager.listLogisticss(paramsLogistics,null); 
			Param.setAttribute("logisticss", logisticss);
			
			//订单信息
			Param.setAttribute("orders_no", paramsLogistics.getOrders_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "logisticsShow";
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @return String
	 */
	public String finishOrders(){
		try {
			//确认收货
			indexManager.finishOrders(paramsOrders);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("确认收货失败！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	public String myInfo(){
		return "userInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	public String myPwd(){
		return "userPwd";
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

	public Collect getParamsCollect() {
		return paramsCollect;
	}

	public void setParamsCollect(Collect paramsCollect) {
		this.paramsCollect = paramsCollect;
	}

}
