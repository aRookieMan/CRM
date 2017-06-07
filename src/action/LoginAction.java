package action;
import model.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class LoginAction extends ActionSupport{
//	ǰ�������������ʺ����룬�ʺž���staff_id
//	��¼��session����Ա���࣬���ݽ����г̣�δ��ɶ���������������
	private DaoManager dm=new DaoManager();
//	private ActionContext ac = ActionContext.getContext(); 
//	Map session = actionContext.getSession();			  
//	session.put("USER_NAME", "Test User");	
//	HttpServletRequest request = ServletActionContext.getRequest();
//	request.setAttribute("username","zhangsan");
	@Override  
    public String execute() throws Exception 
    {
		HttpServletRequest request = ServletActionContext.getRequest();
		//post�����ʺ�����
		int staff_id=Integer.parseInt(request.getParameter("staff_id"));
		String password=request.getParameter("password");
		
		Staff staff=dm.selectStaff(staff_id, password);
		if(staff==null) return "loginError";
		else 
		{
			HttpSession session=request.getSession();
			session.setAttribute("staff", staff);
			session.setAttribute("birthdayNumber", dm.birthdayNumber(staff));
			session.setAttribute("journeyNumber",dm.journeyNumber(staff));
			session.setAttribute("unCompletedOrderNumber",dm.unCompletedOrderNumber(staff));
			
			return "loginSuccess";
		}
		
    }
}
