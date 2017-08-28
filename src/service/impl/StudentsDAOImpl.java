package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import service.StudentsDAO;
import entity.Students;

//ѧ���ӿ�ʵ����
public class StudentsDAOImpl implements StudentsDAO {

	@Override
	public List<Students> queryAllStudents() {
		// TODO Auto-generated method stub
		Transaction tx = null;//�������
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
	public Students queryStudentsBySid(String sid) {//ͨ��id����ѧ��
		// TODO Auto-generated method stub
		Transaction tx = null;//�������
		Students s = null;
		//String hql="from Students";
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();//��ȡ��ǰ�Ự
			tx = session.beginTransaction();//��������
			//hql = "from Students";
			//Query query = session.createQuery(hql);
			s = (Students)session.get(Students.class,sid);
		   //s=session.get
			//session.get
			tx.commit();//�ύ����
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
	//���մ���
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
		tx.commit();//�ύ����
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
	public boolean modifyStudents(Students s) {//����ı���Ϣ���ѧ������
		// TODO Auto-generated method stub
		//hibernate�Ĺ�������
		Transaction tx = null;
		//String hql = "";
		try{
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		//session.
		//session.save(s);
		session.update(s);
		tx.commit();//�ύ����
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
		Transaction tx =null;//��Ϣ�������
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
	//����������ѧ��
	@Override
	public List<Students> searchStudents(String condition,String command){
		Transaction tx = null;
		List<Students> list = null;
		//String com=command;//ģ����ѯ
		String  hql = "";
		try{
		Session	session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx=session.beginTransaction();
		hql="from Students where "+condition+" like '"+command+"%'";
		Query query = session.createQuery(hql);//ִ�в�ѯ����
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
	
	//���ѧ�����ѧ��
	public String getNewSid(){
		Transaction tx =null;//�������
		String hql="";
		String sid = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//tx = session.getTransaction();
			tx = session.beginTransaction();//��������
			//��õ�ǰ���ѧ��
			hql="select max(sid) from Students";
			Query query = session.createQuery(hql);//ִ���������
			sid = (String)query.uniqueResult();//ȡ��id�ַ���
			if(sid==null||"".equals(sid)){
				//Ĭ�������
				sid="S00000001";
			}else{
				String temp =sid.substring(1);//ȡ����λ�ַ���
				int num = Integer.parseInt(temp);
				num++;
				temp = String.valueOf(num);//ת�����ַ���
				
				int len =temp.length();//���temp�ַ�����
				//�չ���λ
				for(int j=0;j<7-len;j++){
					temp="0"+temp;
				}
				//����S
				sid="S"+temp;
	
			}	
			tx.commit();//�ύ������һ����û�ύ����һ�����޷�ִ��
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
