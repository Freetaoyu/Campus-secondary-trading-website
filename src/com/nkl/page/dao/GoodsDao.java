package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Goods;

public class GoodsDao {

	public int addGoods(Goods goods, Connection conn){
		String sql = "INSERT INTO goods(goods_id,user_id,goods_name,goods_type1,goods_type2,goods_price,goods_desc,goods_sale,goods_click,user_phone,user_qq,goods_address,goods_date,goods_flag,goods_status) values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			goods.getUser_id(),
			goods.getGoods_name(),
			goods.getGoods_type1(),
			goods.getGoods_type2(),
			goods.getGoods_price(),
			goods.getGoods_desc(),
			goods.getGoods_sale(),
			goods.getGoods_click(),
			goods.getUser_phone(),
			goods.getUser_qq(),
			goods.getGoods_address(),
			goods.getGoods_date(),
			goods.getGoods_flag(),
			goods.getGoods_status()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delGoods(String goods_id, Connection conn){
		String sql = "DELETE FROM goods WHERE goods_id=?";

		Object[] params = new Object[] { new Integer(goods_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delGoodss(String[] goods_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <goods_ids.length; i++) {
			sBuilder.append("?");
			if (i !=goods_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM goods WHERE goods_id IN(" +sBuilder.toString()+")";

		Object[] params = goods_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateGoods(Goods goods, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE goods SET goods_id = " + goods.getGoods_id() +" ");
		if (!StringUtil.isEmptyString(goods.getGoods_name())) {
			sBuilder.append(" ,goods_name = '" + goods.getGoods_name() +"' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_type1())) {
			sBuilder.append(" ,goods_type1 = '" + goods.getGoods_type1() +"' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_type2())) {
			sBuilder.append(" ,goods_type2 = '" + goods.getGoods_type2() +"' ");
		}
		if (goods.getGoods_price()!=0) {
			sBuilder.append(" ,goods_price = " + goods.getGoods_price() +" ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			sBuilder.append(" ,goods_desc = '" + goods.getGoods_desc() +"' ");
		}
		if (goods.getGoods_sale()!=0) {
			sBuilder.append(" ,goods_sale = " + goods.getGoods_sale() +" ");
		}
		if (goods.getGoods_click()!=0) {
			sBuilder.append(" ,goods_click = " + goods.getGoods_click() +" ");
		}
		if (!StringUtil.isEmptyString(goods.getUser_phone())) {
			sBuilder.append(" ,user_phone = '" + goods.getUser_phone() +"' ");
		}
		if (!StringUtil.isEmptyString(goods.getUser_qq())) {
			sBuilder.append(" ,user_qq = '" + goods.getUser_qq() +"' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_address())) {
			sBuilder.append(" ,goods_address = '" + goods.getGoods_address() +"' ");
		}
		if (goods.getGoods_flag()!=0) {
			sBuilder.append(" ,goods_flag = " + goods.getGoods_flag() +" ");
		}
		if (goods.getGoods_status()!=0) {
			sBuilder.append(" ,goods_status = " + goods.getGoods_status() +" ");
		}
		sBuilder.append("where goods_id = " + goods.getGoods_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Goods getGoods(Goods goods, Connection conn){
		Goods _goods=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT g.*,u.nick_name,t.goods_pic FROM goods g join user u on g.user_id=u.user_id ");
		sBuilder.append("  left join (select p.goods_id,min(p.goods_pic) goods_pic from goods_pic p group by p.goods_id) t on g.goods_id=t.goods_id WHERE 1=1");
		if (goods.getGoods_id()!=0) {
			sBuilder.append(" and g.goods_id = " + goods.getGoods_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Goods.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _goods = (Goods)list.get(0);
		}
		return _goods;
	}

	public List<Goods>  listGoodss(Goods goods, Connection conn){
		List<Goods> goodss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT g.*,u.nick_name,t.goods_pic FROM goods g join user u on g.user_id=u.user_id ");
		sBuilder.append("  left join (select p.goods_id,min(p.goods_pic) goods_pic from goods_pic p group by p.goods_id) t on g.goods_id=t.goods_id WHERE 1=1");

		if (goods.getGoods_id()!=0) {
			sBuilder.append(" and g.goods_id = " + goods.getGoods_id() +" ");
		}
		if (goods.getUser_id()!=0) {
			sBuilder.append(" and g.user_id = " + goods.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + goods.getGoods_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_type1())) {
			sBuilder.append(" and g.goods_type1 like '%" + goods.getGoods_type1() +"%' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_type2())) {
			sBuilder.append(" and g.goods_type2 like '%" + goods.getGoods_type2() +"%' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_type())) {
			sBuilder.append(" and (g.goods_type1 like '%" + goods.getGoods_type() +"%' ");
			sBuilder.append("   or g.goods_type2 like '%" + goods.getGoods_type() +"%') ");
		}
		if (goods.getGoods_flag()!=0) {
			sBuilder.append(" and g.goods_flag = " + goods.getGoods_flag() +" ");
		}
		if (goods.getGoods_status()!=0) {
			sBuilder.append(" and goods_status = " + goods.getGoods_status() +" ");
		}
		sBuilder.append(" order by g.goods_date desc,g.goods_id asc) t");

		if (goods.getStart() != -1) {
			sBuilder.append(" limit " + goods.getStart() + "," + goods.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Goods.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			goodss = new ArrayList<Goods>();
			for (Object object : list) {
				goodss.add((Goods)object);
			}
		}
		return goodss;
	}

	public int  listGoodssCount(Goods goods, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM goods g join user u on g.user_id=u.user_id ");
		sBuilder.append("  left join (select p.goods_id,min(p.goods_pic) goods_pic from goods_pic p group by p.goods_id) t on g.goods_id=t.goods_id WHERE 1=1");

		if (goods.getGoods_id()!=0) {
			sBuilder.append(" and g.goods_id = " + goods.getGoods_id() +" ");
		}
		if (goods.getUser_id()!=0) {
			sBuilder.append(" and g.user_id = " + goods.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + goods.getGoods_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_type1())) {
			sBuilder.append(" and g.goods_type1 like '%" + goods.getGoods_type1() +"%' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_type2())) {
			sBuilder.append(" and g.goods_type2 like '%" + goods.getGoods_type2() +"%' ");
		}
		if (!StringUtil.isEmptyString(goods.getGoods_type())) {
			sBuilder.append(" and (g.goods_type1 like '%" + goods.getGoods_type() +"%' ");
			sBuilder.append("   or g.goods_type2 like '%" + goods.getGoods_type() +"%') ");
		}
		if (goods.getGoods_flag()!=0) {
			sBuilder.append(" and g.goods_flag = " + goods.getGoods_flag() +" ");
		}
		if (goods.getGoods_status()!=0) {
			sBuilder.append(" and goods_status = " + goods.getGoods_status() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	public int  getGoodsId(int user_id, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT max(goods_id) FROM goods g WHERE 1=1");
		sBuilder.append(" and g.user_id = " + user_id +" ");
		 
		Integer count = (Integer)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
