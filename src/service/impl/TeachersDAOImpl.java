package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Students;
import entity.Teachers;
import service.TeachersDAO;

public class TeachersDAOImpl implements TeachersDAO{

	@Override
	public List<Teachers> queryAllTeachers() {
		// TODO Auto-generated method stub
		List<Teachers> list = null;
		String hql="from Teachers";
		Transaction tx=null;
		try{
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx=session.beginTransaction();//开启事务
		Query query = session.createQuery(hql);//执行数据库查询
		list = query.list();
		tx.commit();
		  return list;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			return list;
		}finally{
			//关闭事务
			if(tx!=null){
				tx=null;
			}
			
		}
		//return null;
	}

	@Override
	public boolean addTeachers(Teachers t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyTeachers(Teachers t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTeachers(String tid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Students getTeachers(Teachers t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
