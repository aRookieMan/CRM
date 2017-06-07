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
	//	��¼�á�ѡ����������session����
	public Staff selectStaff(int staff_id,String password)
	{
		cr = session.createCriteria(Staff.class);

	    cr.add(Restrictions.eq("staff_id", staff_id))  
	            .add(Restrictions.eq("password",password));
	    return (Staff)cr.uniqueResult();
	}
	
	//	�ҳ�����ĳ�����ŵ�����Ա�������ھ������Լ����ŵ���
	public List<Staff> selectStaffDepartment(String staff_department)
	{
//		crit.addOrder(Order.desc("id"));  
//	    List<Conft> list = crit.list();  
		cr = session.createCriteria(Staff.class);

	    cr.add(Restrictions.eq("staff_department", staff_department))
	    		.addOrder(Order.desc("staff_post"));
	    return (List<Staff>)cr.list();
	}

	//	���ڻ�ȡ����Ա������Ϣ���Ա��ڷ���ͻ���ʱ����������û���
	public List<Staff> selectAllStaff()
	{
//		crit.addOrder(Order.desc("id"));  
//	    List<Conft> list = crit.list();  
		cr = session.createCriteria(Staff.class);
		return (List<Staff>)cr.list();
	}

	//	���ڻ�ȡ���пͻ�����Ϣ
	public List<Client> selectAllClient()
	{
//		crit.addOrder(Order.desc("id"));  
//	    List<Conft> list = crit.list();  
		cr = session.createCriteria(Client.class);
		return (List<Client>)cr.list();
	}

	//	���ݿͻ���ID��ȡ�ͻ�����Ϣ
	public Client selectClientById(int client_id)
	{
		cr = session.createCriteria(Client.class);

	    cr.add(Restrictions.eq("client_id", client_id));
	    return (Client)cr.uniqueResult();
	}

	//	����Ա����ID��ȡԱ��
	public Staff selectStaffById(int Staff_id)
	{
		cr = session.createCriteria(Staff.class);

	    cr.add(Restrictions.eq("Staff_id", Staff_id));
	    return (Staff)cr.uniqueResult();
	}

	//	���ͻ��������ݿ�
	public void insertClient(Client cus)
	{
		session.save(cus);
		tx.commit();
		session.flush();
	}
	
	//	��Ա��������ݿ�
	public void insertStaff(Staff cus)
	{
		session.save(cus);
		tx.commit();
		session.flush();
	}

	//	���ݿͻ������ֲ�ѯ�ͻ����أ�List<Client>������ͬ�������򷵻�null 
	public List<Client> selectClientByName(String client_name)
	{
		cr = session.createCriteria(Client.class);

	    cr.add(Restrictions.eq("client_name", client_name));
	    List<Client> emps=cr.list();
	    if(emps.isEmpty())return null;
	    return  emps;
	}

	//	���¸ÿͻ�
	public void updateClient(Client cus)
	{
		session.update(cus);
		tx.commit();
		session.flush();
	}

	//	�޸�һ��Ա��
	public void updateStaff(Staff staff)
	{
		session.update(staff);
		tx.commit();
		session.flush();
	}
	
	//	���һ��Ա��
	public void addStaff(Staff staff)
	{
		session.save(staff);
		tx.commit();
		session.flush();
	}
	
	//	ɾ��һ��Ա��
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
	
	//	Ա���ҵ��Լ����еĹ˿�
	public Set<Client> staffFindClient(Staff staff)
	{
//		 for (int x : arr) { 
//             System.out.println(x); //����������Ԫ�ص�ֵ 
//         } 
		return staff.getMyClients();
	}
	
	//	ĳԱ�������Լ� �� �� ��
	public int clientNumber(Staff staff)
	{
		return staff.getMyClients().size();
	}
	
	
	//	Ա�����������г���
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
	
	//	Ա������������û�й˿����գ��о���ʾ��Ŀ��û�о�Ϊ0
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
	
	//	�ϸ�API�������0������������鿴���������յĹ˿���Ϣ
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
	
	//	Ա���鿴��û��δ����� �� �� ��
	public int uncompletedOrder(Staff staff)
	{
//		Set<Buy> buyList=new HashSet<Buy>();
		int count=0;
		for(Client tempC:staff.getMyClients())
		{
			for(Buy tempB:tempC.getMyBuys())
			{
				if(tempB.getbuy_falg().equals("δ���"))	count++;//buyList.add(tempB);
			}
		}
		
	    return  count;
	}
		
	//	Ա���鿴�����Լ��������г̣���ʱ������
	public List<Journey> checkMyJourneyByTime(Staff staff)
	{
		cr = session.createCriteria(Journey.class);
		
		cr.add(Restrictions.in("myStaff", staff));
		cr.addOrder(Order.asc("journey_date"));
		List<Journey> list=cr.list();
	    return  list;
	}
	
	//	Ա���鿴�����Լ��������г̣���״̬����
	public List<Journey> checkMyJourneyByRemark(Staff staff)
	{
		cr = session.createCriteria(Journey.class);
		
		cr.add(Restrictions.in("myStaff", staff));
		cr.addOrder(Order.desc("journey_remark")).addOrder(Order.asc("journey_date"));
		List<Journey> list=cr.list();
	    return  list;
	}
	
	//	����г�
	public void addJourney(Journey jour)
	{
			session.save(jour);
			tx.commit();
			session.flush();
	}
	
	//	�޸��г�
	public void updateJourney(Journey jour)
	{
			session.update(jour);
			tx.commit();
			session.flush();
	}
	
	//	ɾ���г�
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
	
	//	Ա���鿴�����Լ��Ķ������������״̬��ʱ������
	public List<Buy> checkMydOrder(Staff staff)
	{
		cr = session.createCriteria(Buy.class);
		
		cr.add(Restrictions.in("myClient", staff.getMyClients()));
		cr.addOrder(Order.desc("buy_falg")).addOrder(Order.asc("create_date"));
		List<Buy> list=cr.list();
	    return  list;
	}
	
	//	�鿴δ��ɶ�����
	public int unCompletedOrderNumber(Staff staff)
	{
		cr = session.createCriteria(Buy.class);
		
		cr.add(Restrictions.eq("buy_falg", "δ���"));
		int count=cr.list().size();
	    return  count;
	}
	
	//	����ж�����������ӿͻ�����ȡ���ͻ��๹�충����
	public void addOrder(Buy buy)
	{
			session.save(buy);
			tx.commit();
			session.flush();
	}
	
	//	�޸Ķ���
	public void updateOrder(Buy buy)
	{
			session.update(buy);
			tx.commit();
			session.flush();
	}
	
	//	ɾ������
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
	
	//	����֪ͨ
	public void addNotice(Notice not)
	{
		session.save(not);
		tx.commit();
		session.flush();
	}
	
	//	�鿴����֪ͨ
	public List<Notice> checkAllNotice()
	{
		cr=session.createCriteria(Notice.class);
		cr.addOrder(Order.desc("notice_date"));
		return (List<Notice>)cr.list();
	}
	
	//	ɾ��֪ͨ
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
	
	//	�ͻ�ת�Ƶ���һ��Ա������
	public void transportClient(Client client,Staff oldStaff,int newStaff_id)
	{
		oldStaff.getMyClients().remove(client);
		cr.add(Restrictions.eq("staff_id", newStaff_id));
		Staff newStaff=(Staff)cr.uniqueResult();
		newStaff.getMyClients().add(client);
	}
}
