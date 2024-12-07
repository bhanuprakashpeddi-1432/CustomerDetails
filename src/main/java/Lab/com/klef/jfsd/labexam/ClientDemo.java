package Lab.com.klef.jfsd.labexam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
    	
        // Create SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        // I. Insert records manually
        Customer c1 = new Customer();
        c1.setName("John Doe");
        c1.setEmail("john.doe@example.com");
        c1.setAge(25);
        c1.setLocation("New York");

        Customer c2 = new Customer();
        c2.setName("Jane Smith");
        c2.setEmail("jane.smith@example.com");
        c2.setAge(30);
        c2.setLocation("California");

        session.save(c1);
        session.save(c2);

        transaction.commit();

        // II. Apply restrictions using Criteria
        Criteria criteria = session.createCriteria(Customer.class);

        // Equal
        criteria.add(Restrictions.eq("location", "New York"));
        List<Customer> resultList = criteria.list();
        System.out.println("Customers from New York: " + resultList);

        // Not Equal
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.ne("location", "California"));
        resultList = criteria.list();
        System.out.println("Customers not from California: " + resultList);

        // Less Than
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.lt("age", 30));
        resultList = criteria.list();
        System.out.println("Customers with age less than 30: " + resultList);

        // Greater Than
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.gt("age", 25));
        resultList = criteria.list();
        System.out.println("Customers with age greater than 25: " + resultList);

        // Between
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.between("age", 20, 35));
        resultList = criteria.list();
        System.out.println("Customers with age between 20 and 35: " + resultList);

        // Like
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("name", "J%"));
        resultList = criteria.list();
        System.out.println("Customers whose names start with 'J': " + resultList);

        session.close();
        factory.close();
    }
}
