package action;
import model.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class JourneyAction extends ActionSupport{
	private DaoManager dm=new DaoManager();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session =request.getSession();
//	private ActionContext ac = ActionContext.getContext(); 
//	Map session = actionContext.getSession();			  
//	session.put("USER_NAME", "Test User");	
//	HttpServletRequest request = ServletActionContext.getRequest();
//	request.setAttribute("username","zhangsan");
	
	// ����г��Ժ�鿴�����г�,����journeyList
    public String checkAllJourney() throws Exception 
    {
		Staff staff=(Staff) session.getAttribute("staff");
		if(staff==null) return "noLogin";
		List<Journey> list=dm.checkMyJourneyByRemark(staff);
		request.setAttribute("journeyList", list);
		return "checkAllJourneyOK";		
    }
    
    //	��JSP��request�������Journey�࣬����ΪaddJourney�������
    public String addJourney() throws Exception 
    {
    	Journey journey=(Journey) request.getAttribute("addJourney");
    	dm.addJourney(journey);
    	return "addJourneyOK";
    }
    
//	��JSP��request�������Journey�࣬����ΪupdateJourney�����޸�
    public String updateJourney() throws Exception 
    {
    	Journey journey=(Journey) request.getAttribute("updateJourney");
    	dm.updateJourney(journey);
    	return "updateJourneyOK";
    }
    
//	��JSP��request�������journey_id������ΪremoveJourney����ɾ��
    public String removeJourney() throws Exception 
    {
    	int journey_id=(int) request.getAttribute("removeJourney");
    	dm.removeJourney(journey_id);
    	return "removeJourneyOK";
    }
}
