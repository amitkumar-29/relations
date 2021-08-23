package examples;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

public class Operation {
	public static void main(String[] args) {
		Session session = HibernateUtil2.getSessionFactory().openSession();
		session.beginTransaction();
		
		//oneToMany(session);
		
		Subscription sub1 = new Subscription();
		sub1.setSubscriptionName("Friction");
		
		Subscription sub2 = new Subscription();
		sub2.setSubscriptionName("Biography");
		
		Set<Subscription> subs = new HashSet<Subscription>();
		subs.add(sub1);
		subs.add(sub2);
		
		//Readers
		Reader read1 = new Reader();
		read1.setName("Amitkumar Bhatt");
		
		Reader read2 = new Reader();
		read2.setName("Eminen");
		
		read1.setSubscriptions(subs);
		read2.setSubscriptions(subs);
		
		session.save(read1);
		session.save(read2);
		
		session.getTransaction().commit();
		HibernateUtil2.shutdown();
	}

	private static void oneToMany(Session session) {
		//Creating Account objects
		AccountEntity account1 = new AccountEntity();
		account1.setAccountNumber("abc123");
		account1.setAccountType("Current");
		
		AccountEntity account2 = new AccountEntity();
		account2.setAccountNumber("xyz123");
		account2.setAccountType("Current");
		
		//Creating Employee Object
		EmployeeEntity employee = new EmployeeEntity();
		employee.setName("Amitkumar Bhatt");
		employee.setEmail("amitkumar@gmail.com");
		
		//Sets
		Set<AccountEntity> accountsOfEmployee = new HashSet<AccountEntity>();
		accountsOfEmployee.add(account1);
		accountsOfEmployee.add(account2);
		
		//Setting Accounts for employee
		employee.setAccounts(accountsOfEmployee);
		
		session.save(employee);
	}
}
