package service;

import java.util.List;

import entity.Students;

public interface StudentsDAO {
	
	
	public List<Students> queryAllStudents();
	
	public List<Students> searchStudents(String condition,String command);
	
	public Students queryStudentsBySid(String sid);
	
	public boolean addStudents(Students s);
	
	public boolean modifyStudents(Students s);
	
	public boolean deleteStudents(String sid);
	
	public Students getStudents(Students s);
	
	

}
