package action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import entity.Students;
import service.StudentsDAO;
import service.impl.StudentsDAOImpl;

public class StudentsAction extends SuperAction implements ModelDriven<Students>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Students stu = new Students();
	
	public String query(){
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students> list =sdao.queryAllStudents();
		//修复最后一学生无法删除的BUG
		if(list!=null&&list.size()>0){
			//list内有元素
			//将学生存入session
			session.setAttribute("students_list", list);
			
		}else{//否则list置空
			session.setAttribute("students_list", null);
		}
		return "query_success";
	}
	//删除学生动作
	public String delete(){
		StudentsDAO sdao = new StudentsDAOImpl();
		String sid =request.getParameter("sid");
		sdao.deleteStudents(sid);//调用删除方法
		return "delete_success";
	}
	
	//添加学生,模型驱动
	@Override
	public Students getModel() {
		// TODO Auto-generated method stub
		return this.stu;
	}
	public String add(){
		StudentsDAO sdao = new StudentsDAOImpl();
		sdao.addStudents(stu);
		return "add_success";
		
	}
	
	//学生资料修改动作，没有改变资料只是点进页面
	public String modify(){
		//modfy页面调用此ACTION
		String sid =request.getParameter("sid");
		StudentsDAO sdao = new StudentsDAOImpl();
		//sdao.queryStudentsBySid(sid);//通过id查找	
		Students s = sdao.queryStudentsBySid(sid);//马虎
		
		session.setAttribute("modify_students",s);//将选中的学生资料存入session,取名为modify_students
		//用来呈现在modify页面上
		return "modify_success";
	}
	
	//保存修改后学生资料的动作
	
	public String save(){
		StudentsDAO sdao = new StudentsDAOImpl();
		//Students s = new Students();
		//试用驱动模型
		sdao.modifyStudents(stu);//存入数据库
		return "save_success";
	}
	
	public String search(){
		String condition = request.getParameter("condition");
		String command = request.getParameter("command");
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students> list=sdao.searchStudents(condition,command);
		if(list!=null&&list.size()>0){//list内有元素
			//将学生存入session
			session.setAttribute("students_searched", list);
			
		}else{//否则list置空
			session.setAttribute("students_searched", null);
		}
		return "search_success";
		
	}
	
	

}
