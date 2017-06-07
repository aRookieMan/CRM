package action;
import model.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
public class LeaderAction extends ActionSupport{
	private DaoManager dm=new DaoManager();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session =request.getSession();
//	private ActionContext ac = ActionContext.getContext(); 
//	Map session = actionContext.getSession();			  
//	session.put("USER_NAME", "Test User");	
//	HttpServletRequest request = ServletActionContext.getRequest();
//	request.setAttribute("username","zhangsan");
	
	//	看自己部门下的员工，放到myStaff中
    public String checkMyStaff() throws Exception 
    {
    	Staff staff=(Staff) session.getAttribute("staff");
		if(staff==null) return "noLogin";
		String department=staff.getStaff_department();
		List<Staff> list=dm.selectStaffDepartment(department);
		request.setAttribute("myStaff", list);
		return "checkMyStaffOK";		
    }
    
    //	添加通知在NotcieAction中，查看员工业绩可以用上面这个函数。
    
    //	从JSP的request里面接受Staff类，名字为addStaff并且添加员工
    public String addStaff() throws Exception 
    {
    	Staff staff=(Staff) request.getAttribute("addStaff");
    	dm.addStaff(staff);
    	return "addStaffOK";
    }
    
    //	从JSP的request里面接受Staff类，名字为updateStaff并且修改
    public String updateStaff() throws Exception 
    {
    	Staff staff=(Staff) request.getAttribute("updateStaff");
    	dm.updateStaff(staff);
    	return "updateStaffOK";
    }
    
    //	从JSP的request里面接受Staff_id，名字为removeStaff并且删除
    public String removeStaff() throws Exception 
    {
    	int staff_id=(int) request.getAttribute("removeStaff");
    	dm.deleteStaff(staff_id);
    	return "removeStaffOK";
    }

}
