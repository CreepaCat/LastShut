package service.impl;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import entity.Students;
import service.StudentsDAO;

public class TestStudentDAOImpl {
	@Test
	public void testQueryAllStudents(){
		StudentsDAO sdao=new StudentsDAOImpl();
		List<Students> list=sdao.queryAllStudents();
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
	}
	
	
	
	@Test
	public void testqueryStudentsBySid(){
		
		StudentsDAO sdao = new StudentsDAOImpl();
		
		//s.setSid("S0000002");

		Students s=sdao.queryStudentsBySid("S0000001");//dao方法没问题
		System.out.println(s.getSname());
		
	}

}
