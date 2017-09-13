package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.page.domain.GoodsPic;

public class GoodsPicDao {

	public int addGoodsPic(GoodsPic goodsPic, Connection conn){
		String sql = "INSERT INTO goods_pic(pic_id,goods_id,goods_pic) values(null,?,?)";
		Object[] params = new Object[] {
			goodsPic.getGoods_id(),
			goodsPic.getGoods_pic()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delGoodsPic(String pic_id, Connection conn){
		String sql = "DELETE FROM goods_pic WHERE pic_id=?";

		Object[] params = new Object[] { new Integer(pic_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}
	
	public int delGoodsPicByGoodsId(String goods_id, Connection conn){
		String sql = "DELETE FROM goods_pic WHERE goods_id=?";

		Object[] params = new Object[] { new Integer(goods_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delGoodsPics(String[] pic_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <pic_ids.length; i++) {
			sBuilder.append("?");
			if (i !=pic_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM goods_pic WHERE pic_id IN(" +sBuilder.toString()+")";

		Object[] params = pic_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateGoodsPic(GoodsPic goodsPic, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE goods_pic SET pic_id = " + goodsPic.getPic_id() +" ");

		sBuilder.append("where pic_id = " + goodsPic.getPic_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public GoodsPic getGoodsPic(GoodsPic goodsPic, Connection conn){
		GoodsPic _goodsPic=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM goods_pic WHERE 1=1");
		if (goodsPic.getPic_id()!=0) {
			sBuilder.append(" and pic_id = " + goodsPic.getPic_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(GoodsPic.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _goodsPic = (GoodsPic)list.get(0);
		}
		return _goodsPic;
	}

	public List<GoodsPic>  listGoodsPics(GoodsPic goodsPic, Connection conn){
		List<GoodsPic> goodsPics = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM goods_pic WHERE 1=1");

		if (goodsPic.getPic_id()!=0) {
			sBuilder.append(" and pic_id = " + goodsPic.getPic_id() +" ");
		}
		if (goodsPic.getGoods_id()!=0) {
			sBuilder.append(" and goods_id = " + goodsPic.getGoods_id() +" ");
		}
		sBuilder.append(" order by pic_id asc) t");

		if (goodsPic.getStart() != -1) {
			sBuilder.append(" limit " + goodsPic.getStart() + "," + goodsPic.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(GoodsPic.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			goodsPics = new ArrayList<GoodsPic>();
			for (Object object : list) {
				goodsPics.add((GoodsPic)object);
			}
		}
		return goodsPics;
	}

	public int  listGoodsPicsCount(GoodsPic goodsPic, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM goods_pic WHERE 1=1");

		if (goodsPic.getPic_id()!=0) {
			sBuilder.append(" and pic_id = " + goodsPic.getPic_id() +" ");
		}
		if (goodsPic.getGoods_id()!=0) {
			sBuilder.append(" and goods_id = " + goodsPic.getGoods_id() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
