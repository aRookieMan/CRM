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
	
	//	���Լ������µ�Ա�����ŵ�myStaff��
    public String checkMyStaff() throws Exception 
    {
    	Staff staff=(Staff) session.getAttribute("staff");
		if(staff==null) return "noLogin";
		String department=staff.getStaff_department();
		List<Staff> list=dm.selectStaffDepartment(department);
		request.setAttribute("myStaff", list);
		return "checkMyStaffOK";		
    }
    
    //	���֪ͨ��NotcieAction�У��鿴Ա��ҵ���������������������
    
    //	��JSP��request�������Staff�࣬����ΪaddStaff�������Ա��
    public String addStaff() throws Exception 
    {
    	Staff staff=(Staff) request.getAttribute("addStaff");
    	dm.addStaff(staff);
    	return "addStaffOK";
    }
    
    //	��JSP��request�������Staff�࣬����ΪupdateStaff�����޸�
    public String updateStaff() throws Exception 
    {
    	Staff staff=(Staff) request.getAttribute("updateStaff");
    	dm.updateStaff(staff);
    	return "updateStaffOK";
    }
    
    //	��JSP��request�������Staff_id������ΪremoveStaff����ɾ��
    public String removeStaff() throws Exception 
    {
    	int staff_id=(int) request.getAttribute("removeStaff");
    	dm.deleteStaff(staff_id);
    	return "removeStaffOK";
    }

}
