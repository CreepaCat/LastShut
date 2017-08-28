package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import service.StudentsDAO;
import entity.Students;

//学生接口实现类
public class StudentsDAOImpl implements StudentsDAO {

	@Override
	public List<Students> queryAllStudents() {
		// TODO Auto-generated method stub
		Transaction tx = null;//事务对象
		List<Students> list = null;
		String hql="from Students";
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			//hql = "from Students";
			Query query = session.createQuery(hql);
			
			list = query.list();
			tx.commit();
			return list;
			
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return list;
		}finally{
			if(tx!=null){
				tx=null;
				
			}
		}
		
	}

	@Override
	public Students queryStudentsBySid(String sid) {//通过id查找学生
		// TODO Auto-generated method stub
		Transaction tx = null;//事务对象
		Students s = null;
		//String hql="from Students";
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();//获取当前会话
			tx = session.beginTransaction();//开启事务
			//hql = "from Students";
			//Query query = session.createQuery(hql);
			s = (Students)session.get(Students.class,sid);
		   //s=session.get
			//session.get
			tx.commit();//提交事务
			return s;
			
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return s;
		}finally{
			if(tx!=null){
				tx=null;

			}
		}
		//return null;
	}
	
	@Override
	//接收传参
	public Students getStudents(Students s){
		//Students stu = new Students;
		
		return null;
		
	}

	@Override
	public boolean addStudents(Students s) {
		// TODO Auto-generated method stub
		s.setSid(getNewSid());
		Transaction tx = null;
		try{
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		session.save(s);
		tx.commit();//提交事务
		return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
		
	}

	@Override
	public boolean modifyStudents(Students s) {//传入改变信息后的学生资料
		// TODO Auto-generated method stub
		//hibernate的工作流程
		Transaction tx = null;
		//String hql = "";
		try{
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		//session.
		//session.save(s);
		session.update(s);
		tx.commit();//提交事务
		return true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			return false;
			
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
		
		
		//return false;
	}

	@Override
	public boolean deleteStudents(String sid) {
		// TODO Auto-generated method stub
		Transaction tx =null;//信息传输对象
		//String hql="";
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Students s=(Students)session.get(Students.class,sid);
			session.delete(s);
			tx.commit();
			return true;
			
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return false;
			
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
		
	}
	//按条件查找学生
	@Override
	public List<Students> searchStudents(String condition,String command){
		Transaction tx = null;
		List<Students> list = null;
		//String com=command;//模糊查询
		String  hql = "";
		try{
		Session	session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx=session.beginTransaction();
		hql="from Students where "+condition+" like '"+command+"%'";
		Query query = session.createQuery(hql);//执行查询操作
		list = query.list();
		tx.commit();
		return list;
			
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			return null;
		}finally{
			if(tx!=null){
				tx=null;
			}
			
		}
		
	}
	
	//获得学生最大学号
	public String getNewSid(){
		Transaction tx =null;//事务对象
		String hql="";
		String sid = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//tx = session.getTransaction();
			tx = session.beginTransaction();//激活事务
			//获得当前最大学号
			hql="select max(sid) from Students";
			Query query = session.createQuery(hql);//执行搜索语句
			sid = (String)query.uniqueResult();//取得id字符串
			if(sid==null||"".equals(sid)){
				//默认最大编号
				sid="S00000001";
			}else{
				String temp =sid.substring(1);//取后七位字符串
				int num = Integer.parseInt(temp);
				num++;
				temp = String.valueOf(num);//转换成字符串
				
				int len =temp.length();//获得temp字符长度
				//凑够七位
				for(int j=0;j<7-len;j++){
					temp="0"+temp;
				}
				//补上S
				sid="S"+temp;
	
			}	
			tx.commit();//提交事务，上一事务没提交，下一事务无法执行
			return sid;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return null;
			
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
		//return temp;
		
		
	}
	
}
