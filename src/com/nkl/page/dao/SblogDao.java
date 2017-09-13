package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Sblog;

public class SblogDao {

	public int addSblog(Sblog sblog, Connection conn){
		String sql = "INSERT INTO sblog(sblog_id,goods_id,user_id,sblog_title,sblog_content,sblog_date,sblog_pic) values(null,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			sblog.getGoods_id(),
			sblog.getUser_id(),
			sblog.getSblog_title(),
			sblog.getSblog_content(),
			sblog.getSblog_date(),
			sblog.getSblog_pic()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSblog(String sblog_id, Connection conn){
		String sql = "DELETE FROM sblog WHERE sblog_id=?";

		Object[] params = new Object[] { new Integer(sblog_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSblogs(String[] sblog_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <sblog_ids.length; i++) {
			sBuilder.append("?");
			if (i !=sblog_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sblog WHERE sblog_id IN(" +sBuilder.toString()+")";

		Object[] params = sblog_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateSblog(Sblog sblog, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sblog SET sblog_id = " + sblog.getSblog_id() +" ");

		sBuilder.append("where sblog_id = " + sblog.getSblog_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Sblog getSblog(Sblog sblog, Connection conn){
		Sblog _sblog=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT s.*,u.nick_name FROM sblog s join user u on s.user_id=u.user_id WHERE 1=1");
		if (sblog.getSblog_id()!=0) {
			sBuilder.append(" and s.sblog_id = " + sblog.getSblog_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Sblog.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sblog = (Sblog)list.get(0);
		}
		return _sblog;
	}

	public List<Sblog>  listSblogs(Sblog sblog, Connection conn){
		List<Sblog> sblogs = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT s.*,u.nick_name FROM sblog s join user u on s.user_id=u.user_id WHERE 1=1");

		if (sblog.getSblog_id()!=0) {
			sBuilder.append(" and s.sblog_id = " + sblog.getSblog_id() +" ");
		}
		if (!StringUtil.isEmptyString(sblog.getSblog_title())) {
			sBuilder.append(" and s.sblog_title like '%" + sblog.getSblog_title() +"%'");
		}
		if (!StringUtil.isEmptyString(sblog.getSblog_content())) {
			sBuilder.append(" and s.sblog_content like '%" + sblog.getSblog_content() +"%'");
		}
		if (sblog.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + sblog.getUser_id() );
		}
		if (sblog.getGoods_id()!=0) {
			sBuilder.append(" and s.goods_id = " + sblog.getGoods_id() );
		}
		sBuilder.append(" order by s.sblog_date desc,s.sblog_id asc) t");

		if (sblog.getStart() != -1) {
			sBuilder.append(" limit " + sblog.getStart() + "," + sblog.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Sblog.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sblogs = new ArrayList<Sblog>();
			for (Object object : list) {
				sblogs.add((Sblog)object);
			}
		}
		return sblogs;
	}

	public int  listSblogsCount(Sblog sblog, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM sblog s join user u on s.user_id=u.user_id WHERE 1=1");

		if (sblog.getSblog_id()!=0) {
			sBuilder.append(" and s.sblog_id = " + sblog.getSblog_id() +" ");
		}
		if (!StringUtil.isEmptyString(sblog.getSblog_title())) {
			sBuilder.append(" and s.sblog_title like '%" + sblog.getSblog_title() +"%'");
		}
		if (!StringUtil.isEmptyString(sblog.getSblog_content())) {
			sBuilder.append(" and s.sblog_content like '%" + sblog.getSblog_content() +"%'");
		}
		if (sblog.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + sblog.getUser_id() );
		}
		if (sblog.getGoods_id()!=0) {
			sBuilder.append(" and s.goods_id = " + sblog.getGoods_id() );
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
