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
		//�޸����һѧ���޷�ɾ����BUG
		if(list!=null&&list.size()>0){
			//list����Ԫ��
			//��ѧ������session
			session.setAttribute("students_list", list);
			
		}else{//����list�ÿ�
			session.setAttribute("students_list", null);
		}
		return "query_success";
	}
	//ɾ��ѧ������
	public String delete(){
		StudentsDAO sdao = new StudentsDAOImpl();
		String sid =request.getParameter("sid");
		sdao.deleteStudents(sid);//����ɾ������
		return "delete_success";
	}
	
	//���ѧ��,ģ������
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
	
	//ѧ�������޸Ķ�����û�иı�����ֻ�ǵ��ҳ��
	public String modify(){
		//modfyҳ����ô�ACTION
		String sid =request.getParameter("sid");
		StudentsDAO sdao = new StudentsDAOImpl();
		//sdao.queryStudentsBySid(sid);//ͨ��id����	
		Students s = sdao.queryStudentsBySid(sid);//��
		
		session.setAttribute("modify_students",s);//��ѡ�е�ѧ�����ϴ���session,ȡ��Ϊmodify_students
		//����������modifyҳ����
		return "modify_success";
	}
	
	//�����޸ĺ�ѧ�����ϵĶ���
	
	public String save(){
		StudentsDAO sdao = new StudentsDAOImpl();
		//Students s = new Students();
		//��������ģ��
		sdao.modifyStudents(stu);//�������ݿ�
		return "save_success";
	}
	
	public String search(){
		String condition = request.getParameter("condition");
		String command = request.getParameter("command");
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students> list=sdao.searchStudents(condition,command);
		if(list!=null&&list.size()>0){//list����Ԫ��
			//��ѧ������session
			session.setAttribute("students_searched", list);
			
		}else{//����list�ÿ�
			session.setAttribute("students_searched", null);
		}
		return "search_success";
		
	}
	
	

}
