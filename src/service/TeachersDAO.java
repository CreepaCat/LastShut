package service;

import java.util.List;

import entity.Students;
import entity.Teachers;

public interface TeachersDAO {
	//��ʾ��ʦ
	public List<Teachers> queryAllTeachers();
	//��ӽ�ʦ
	public boolean addTeachers(Teachers t);
	//public boolean addTeachers();
   // public boolean addStudents(Students s);
	
	public boolean modifyTeachers(Teachers t);
	
	public boolean deleteTeachers(String tid);
	
	public Students getTeachers(Teachers t);
	
	

}
