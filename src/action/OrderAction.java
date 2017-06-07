package action;
import model.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class OrderAction extends ActionSupport{
	private DaoManager dm=new DaoManager();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session =request.getSession();
//	private ActionContext ac = ActionContext.getContext(); 
//	Map session = actionContext.getSession();			  
//	session.put("USER_NAME", "Test User");	
//	HttpServletRequest request = ServletActionContext.getRequest();
//	request.setAttribute("username","zhangsan");
	
	// �����ѯ�����Ժ�鿴���ж���,����orderList
    public String checkAllOrder() throws Exception 
    {
		Staff staff=(Staff) session.getAttribute("staff");
		if(staff==null) return "noLogin";
		List<Buy> list=dm.checkMydOrder(staff);
		request.setAttribute("orderList", list);
		return "checkAllOrderOK";		
    }
    
    //	��JSP��request�������Buy�࣬����ΪaddBuy�������
    public String addBuy() throws Exception 
    {
    	Buy buy=(Buy) request.getAttribute("addBuy");
    	dm.addOrder(buy);
    	return "addBuyOK";
    }
    
    //	��JSP��request�������Buy�࣬����ΪupdateBuy�����޸�
    public String updateBuy() throws Exception 
    {
    	Buy buy=(Buy) request.getAttribute("updateBuy");
    	dm.updateOrder(buy);
    	return "updateBuyOK";
    }
    
    //	��JSP��request�������Buy_id������ΪremoveBuy����ɾ��
    public String removeBuy() throws Exception 
    {
    	int buy_id=(int) request.getAttribute("removeBuy");
    	dm.removeOrder(buy_id);
    	return "removeBuyOK";
    }
}
