package action;
import model.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class NoticrAction extends ActionSupport{
	private DaoManager dm=new DaoManager();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session =request.getSession();
//	private ActionContext ac = ActionContext.getContext(); 
//	Map session = actionContext.getSession();			  
//	session.put("USER_NAME", "Test User");	
//	HttpServletRequest request = ServletActionContext.getRequest();
//	request.setAttribute("username","zhangsan");
	
	// ���֪ͨ�Ժ�鿴����֪ͨ,����noticeList
    public String checkAllNotice() throws Exception 
    {
		List<Notice> list=dm.checkAllNotice();
		request.setAttribute("noticeList", list);
		return "checkAllNoticeOK";		
    }
    
    //	��JSP��request�������notice�࣬����ΪaddNotice�������
    public String addNotice() throws Exception 
    {
    	Notice notice=(Notice) request.getAttribute("addNotice");
    	dm.addNotice(notice);
    	return "addNoticeOK";
    }
    
//	��JSP��request�������notice_id������ΪremoveNotice����ɾ��
    public String removeNotice() throws Exception 
    {
    	int notice_id=(int) request.getAttribute("removeNotice");
    	dm.removeNotice(notice_id);
    	return "removeNoticeOK";
    }
}
