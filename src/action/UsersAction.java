package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UsersDAO;
import service.impl.UsersDAOImpl;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

public class UsersAction extends SuperAction implements ModelDriven<Users> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user = new Users();
	
	public String login(){
		UsersDAO udao = new UsersDAOImpl();
		if(udao.usersLogin(user)){
            session.setAttribute("loginUserName", user.getUsername());
			
			
			return "login_success";	
			
		}else{
			return "login_failure";
        
		}
	}
	//ע��
	@SkipValidation
	public String logout(){
		if(session.getAttribute("loginUserName")!=null){
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
	


	@Override
	public void validate() {
		// TODO Auto-generated method stub
		//super.validate();
		if("".equals(user.getUsername().trim())){
			this.addFieldError("userNameError", "�û�������Ϊ��");
		}
		if(user.getPassword().length()<6){
			this.addFieldError("passwordError", "���볤�Ȳ���С��6");
		}
	}
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}
	

}