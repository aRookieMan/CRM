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
	
	// 点击行程以后查看所有行程,放入journeyList
    public String checkAllJourney() throws Exception 
    {
		Staff staff=(Staff) session.getAttribute("staff");
		if(staff==null) return "noLogin";
		List<Journey> list=dm.checkMyJourneyByRemark(staff);
		request.setAttribute("journeyList", list);
		return "checkAllJourneyOK";		
    }
    
    //	从JSP的request里面接受Journey类，名字为addJourney并且添加
    public String addJourney() throws Exception 
    {
    	Journey journey=(Journey) request.getAttribute("addJourney");
    	dm.addJourney(journey);
    	return "addJourneyOK";
    }
    
//	从JSP的request里面接受Journey类，名字为updateJourney并且修改
    public String updateJourney() throws Exception 
    {
    	Journey journey=(Journey) request.getAttribute("updateJourney");
    	dm.updateJourney(journey);
    	return "updateJourneyOK";
    }
    
//	从JSP的request里面接受journey_id，名字为removeJourney并且删除
    public String removeJourney() throws Exception 
    {
    	int journey_id=(int) request.getAttribute("removeJourney");
    	dm.removeJourney(journey_id);
    	return "removeJourneyOK";
    }
}
