package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Collect;

public class CollectDao {

	public int addCollect(Collect collect, Connection conn){
		String sql = "INSERT INTO collect(collect_id,goods_id,user_id,collect_date) values(null,?,?,?)";
		Object[] params = new Object[] {
			collect.getGoods_id(),
			collect.getUser_id(),
			collect.getCollect_date()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delCollect(String collect_id, Connection conn){
		String sql = "DELETE FROM collect WHERE collect_id=?";

		Object[] params = new Object[] { new Integer(collect_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delCollects(String[] collect_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <collect_ids.length; i++) {
			sBuilder.append("?");
			if (i !=collect_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM collect WHERE collect_id IN(" +sBuilder.toString()+")";

		Object[] params = collect_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateCollect(Collect collect, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE collect SET collect_id = " + collect.getCollect_id() +" ");

		sBuilder.append("where collect_id = " + collect.getCollect_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Collect getCollect(Collect collect, Connection conn){
		Collect _collect=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT s.*,g.goods_name FROM collect s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join goods g on s.goods_id=g.goods_id WHERE 1=1");
		if (collect.getCollect_id()!=0) {
			sBuilder.append(" and s.collect_id = " + collect.getCollect_id() +" ");
		}
		if (collect.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + collect.getUser_id() );
		}
		if (collect.getGoods_id()!=0) {
			sBuilder.append(" and s.goods_id = " + collect.getGoods_id() );
		}

		List<Object> list = BaseDao.executeQuery(Collect.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _collect = (Collect)list.get(0);
		}
		return _collect;
	}

	public List<Collect>  listCollects(Collect collect, Connection conn){
		List<Collect> collects = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT s.*,g.goods_name FROM collect s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join goods g on s.goods_id=g.goods_id WHERE 1=1");

		if (collect.getCollect_id()!=0) {
			sBuilder.append(" and s.collect_id = " + collect.getCollect_id() +" ");
		}
		if (collect.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + collect.getUser_id() );
		}
		if (collect.getGoods_id()!=0) {
			sBuilder.append(" and s.goods_id = " + collect.getGoods_id() );
		}
		if (!StringUtil.isEmptyString(collect.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + collect.getGoods_name() +"%'");
		}
		sBuilder.append(" order by s.collect_date desc,s.collect_id asc) t");

		if (collect.getStart() != -1) {
			sBuilder.append(" limit " + collect.getStart() + "," + collect.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Collect.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			collects = new ArrayList<Collect>();
			for (Object object : list) {
				collects.add((Collect)object);
			}
		}
		return collects;
	}

	public int  listCollectsCount(Collect collect, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM collect s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join goods g on s.goods_id=g.goods_id WHERE 1=1");

		if (collect.getCollect_id()!=0) {
			sBuilder.append(" and s.collect_id = " + collect.getCollect_id() +" ");
		}
		if (collect.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + collect.getUser_id() );
		}
		if (collect.getGoods_id()!=0) {
			sBuilder.append(" and s.goods_id = " + collect.getGoods_id() );
		}
		if (!StringUtil.isEmptyString(collect.getGoods_name())) {
			sBuilder.append(" and g.goods_name like '%" + collect.getGoods_name() +"%'");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
