package action;

import java.util.List;

import entity.Teachers;
import service.TeachersDAO;
import service.impl.TeachersDAOImpl;


/**
 * @author ghost
 *
 */
public class TeachersAction extends SuperAction{
	private static final long serialVersionUID = 1L;
	public String query(){
		TeachersDAO tdao =new TeachersDAOImpl();
		List<Teachers> list=tdao.queryAllTeachers();
		if(list!=null&&list.size()>0){//list内有元素
			//将学生存入session
			session.setAttribute("teachers_list", list);
			
		}else{//否则list置空
			session.setAttribute("teachers_list", null);
		}
		
		return "tquery_success";
	}
}
