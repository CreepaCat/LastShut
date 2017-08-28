package service;

import java.util.List;

import entity.Students;
import entity.Teachers;

public interface TeachersDAO {
	//显示教师
	public List<Teachers> queryAllTeachers();
	//添加教师
	public boolean addTeachers(Teachers t);
	//public boolean addTeachers();
   // public boolean addStudents(Students s);
	
	public boolean modifyTeachers(Teachers t);
	
	public boolean deleteTeachers(String tid);
	
	public Students getTeachers(Teachers t);
	
	

}
