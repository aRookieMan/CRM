package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class DaoManager {
	private Session session;
	private Transaction tx;
	private Criteria cr;
	private SessionFactory sf;
	public DaoManager()
	{
		StandardServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().configure().build();
		sf=new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

		this.session=sf.openSession();
		this.tx=session.beginTransaction();
	}
	public void close()
	{
		session.close();
		sf.close();
	}
	//	登录用。选择出来后放入session即可
	public Staff selectStaff(int staff_id,String password)
	{
		cr = session.createCriteria(Staff.class);

	    cr.add(Restrictions.eq("staff_id", staff_id))  
	            .add(Restrictions.eq("password",password));
	    return (Staff)cr.uniqueResult();
	}
	
	//	找出属于某个部门的所有员工，用于经理找自己部门的人
	public List<Staff> selectStaffDepartment(String staff_department)
	{
//		crit.addOrder(Order.desc("id"));  
//	    List<Conft> list = crit.list();  
		cr = session.createCriteria(Staff.class);

	    cr.add(Restrictions.eq("staff_department", staff_department))
	    		.addOrder(Order.desc("staff_post"));
	    return (List<Staff>)cr.list();
	}

	//	用于获取所有员工的信息，以便在分配客户的时候遍历所有用户。
	public List<Staff> selectAllStaff()
	{
//		crit.addOrder(Order.desc("id"));  
//	    List<Conft> list = crit.list();  
		cr = session.createCriteria(Staff.class);
		return (List<Staff>)cr.list();
	}

	//	用于获取所有客户的信息
	public List<Client> selectAllClient()
	{
//		crit.addOrder(Order.desc("id"));  
//	    List<Conft> list = crit.list();  
		cr = session.createCriteria(Client.class);
		return (List<Client>)cr.list();
	}

	//	根据客户的ID获取客户的信息
	public Client selectClientById(int client_id)
	{
		cr = session.createCriteria(Client.class);

	    cr.add(Restrictions.eq("client_id", client_id));
	    return (Client)cr.uniqueResult();
	}

	//	根据员工的ID获取员工
	public Staff selectStaffById(int Staff_id)
	{
		cr = session.createCriteria(Staff.class);

	    cr.add(Restrictions.eq("Staff_id", Staff_id));
	    return (Staff)cr.uniqueResult();
	}

	//	将客户插入数据库
	public void insertClient(Client cus)
	{
		session.save(cus);
		tx.commit();
		session.flush();
	}
	
	//	将员工添加数据库
	public void insertStaff(Staff cus)
	{
		session.save(cus);
		tx.commit();
		session.flush();
	}

	//	根据客户的名字查询客户返回：List<Client>（可能同名）否则返回null 
	public List<Client> selectClientByName(String client_name)
	{
		cr = session.createCriteria(Client.class);

	    cr.add(Restrictions.eq("client_name", client_name));
	    List<Client> emps=cr.list();
	    if(emps.isEmpty())return null;
	    return  emps;
	}

	//	更新该客户
	public void updateClient(Client cus)
	{
		session.update(cus);
		tx.commit();
		session.flush();
	}

	//	修改一个员工
	public void updateStaff(Staff staff)
	{
		session.update(staff);
		tx.commit();
		session.flush();
	}
	
	//	添加一个员工
	public void addStaff(Staff staff)
	{
		session.save(staff);
		tx.commit();
		session.flush();
	}
	
	//	删除一个员工
	public void deleteStaff(Staff staff)
	{
		session.remove(staff);
		tx.commit();
		session.flush();
	}
	public void deleteStaff(int staff_id)
	{
			cr.add(Restrictions.eq("staff_id", staff_id));
			Staff staff=(Staff)cr.uniqueResult();
			session.remove(staff);
			tx.commit();
			session.flush();
	}	
	
	//	员工找到自己所有的顾客
	public Set<Client> staffFindClient(Staff staff)
	{
//		 for (int x : arr) { 
//             System.out.println(x); //逐个输出数组元素的值 
//         } 
		return staff.getMyClients();
	}
	
	//	某员工看看自己 顾 客 数
	public int clientNumber(Staff staff)
	{
		return staff.getMyClients().size();
	}
	
	
	//	员工看看今天行程数
	public int journeyNumber(Staff staff)
	{
//		Date d = new Date();  
//      System.out.println(d);  
//      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//      String dateNowStr = sdf.format(d);
		
		int count=0;
		Date tempD=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String timeNow = sdf.format(tempD);
		for(Journey tempJ:staff.getMyJourneys())
		{
			if(sdf.format(tempJ.getJourney_date()).equals(timeNow)) count++;
		}
		return count;
	}
	
	//	员工看看今天有没有顾客生日，有就显示数目，没有就为0
	public int birthdayNumber(Staff staff)
	{
//		Date d = new Date();  
//      System.out.println(d);  
//      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//      String dateNowStr = sdf.format(d);
		
		int count=0;
		Date tempD=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String timeNow = sdf.format(tempD);
		for(Client tempC:staff.getMyClients())
		{
			if(sdf.format(tempC.getClient_birthday()).equals(timeNow)) count++;
		}
		return count;
	}
	
	//	上个API如果不是0，可以用这个查看看今天生日的顾客信息
	public List<Client> birthdayClient(Staff staff)
	{
//		Date d = new Date();  
//      System.out.println(d);  
//      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//      String dateNowStr = sdf.format(d);
		
		int count=0;
		Date tempD=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String timeNow = sdf.format(tempD);
		List<Client> list=new ArrayList<Client>();
		for(Client tempC:staff.getMyClients())
		{
			if(sdf.format(tempC.getClient_birthday()).equals(timeNow)) list.add(tempC);
		}
		return list;
	}
	
	//	员工查看有没有未处理的 订 单 数
	public int uncompletedOrder(Staff staff)
	{
//		Set<Buy> buyList=new HashSet<Buy>();
		int count=0;
		for(Client tempC:staff.getMyClients())
		{
			for(Buy tempB:tempC.getMyBuys())
			{
				if(tempB.getbuy_falg().equals("未完成"))	count++;//buyList.add(tempB);
			}
		}
		
	    return  count;
	}
		
	//	员工查看属于自己的所有行程，按时间排序
	public List<Journey> checkMyJourneyByTime(Staff staff)
	{
		cr = session.createCriteria(Journey.class);
		
		cr.add(Restrictions.in("myStaff", staff));
		cr.addOrder(Order.asc("journey_date"));
		List<Journey> list=cr.list();
	    return  list;
	}
	
	//	员工查看属于自己的所有行程，按状态排序
	public List<Journey> checkMyJourneyByRemark(Staff staff)
	{
		cr = session.createCriteria(Journey.class);
		
		cr.add(Restrictions.in("myStaff", staff));
		cr.addOrder(Order.desc("journey_remark")).addOrder(Order.asc("journey_date"));
		List<Journey> list=cr.list();
	    return  list;
	}
	
	//	添加行程
	public void addJourney(Journey jour)
	{
			session.save(jour);
			tx.commit();
			session.flush();
	}
	
	//	修改行程
	public void updateJourney(Journey jour)
	{
			session.update(jour);
			tx.commit();
			session.flush();
	}
	
	//	删除行程
	public void removeJourney(Journey jour)
	{
			session.remove(jour);
			tx.commit();
			session.flush();
	}
	public void removeJourney(int journey_id)
	{
			cr.add(Restrictions.eq("journey_id", journey_id));
			Journey jour=(Journey)cr.uniqueResult();
			session.remove(jour);
			tx.commit();
			session.flush();
	}
	
	//	员工查看属于自己的订单，按完成先状态后时间排序
	public List<Buy> checkMydOrder(Staff staff)
	{
		cr = session.createCriteria(Buy.class);
		
		cr.add(Restrictions.in("myClient", staff.getMyClients()));
		cr.addOrder(Order.desc("buy_falg")).addOrder(Order.asc("create_date"));
		List<Buy> list=cr.list();
	    return  list;
	}
	
	//	查看未完成订单数
	public int unCompletedOrderNumber(Staff staff)
	{
		cr = session.createCriteria(Buy.class);
		
		cr.add(Restrictions.eq("buy_falg", "未完成"));
		int count=cr.list().size();
	    return  count;
	}
	
	//	添加行订单，必须添加客户，先取出客户类构造订单类
	public void addOrder(Buy buy)
	{
			session.save(buy);
			tx.commit();
			session.flush();
	}
	
	//	修改订单
	public void updateOrder(Buy buy)
	{
			session.update(buy);
			tx.commit();
			session.flush();
	}
	
	//	删除订单
	public void removeOrder(Buy buy)
	{
			session.remove(buy);
			tx.commit();
			session.flush();
	}
	public void removeOrder(int buy_id)
	{
			cr.add(Restrictions.eq("buy_id", buy_id));
			Buy buy=(Buy)cr.uniqueResult();
			session.remove(buy);
			tx.commit();
			session.flush();
	}
	
	//	发布通知
	public void addNotice(Notice not)
	{
		session.save(not);
		tx.commit();
		session.flush();
	}
	
	//	查看所有通知
	public List<Notice> checkAllNotice()
	{
		cr=session.createCriteria(Notice.class);
		cr.addOrder(Order.desc("notice_date"));
		return (List<Notice>)cr.list();
	}
	
	//	删除通知
	public void removeNotice(Notice not)
	{
		session.remove(not);
		tx.commit();
		session.flush();
	}
	public void removeNotice(int notice_id)
	{
			cr.add(Restrictions.eq("notice_id", notice_id));
			Notice notice=(Notice)cr.uniqueResult();
			session.remove(notice);
			tx.commit();
			session.flush();
	}
	
	//	客户转移到另一个员工名下
	public void transportClient(Client client,Staff oldStaff,int newStaff_id)
	{
		oldStaff.getMyClients().remove(client);
		cr.add(Restrictions.eq("staff_id", newStaff_id));
		Staff newStaff=(Staff)cr.uniqueResult();
		newStaff.getMyClients().add(client);
	}
}
