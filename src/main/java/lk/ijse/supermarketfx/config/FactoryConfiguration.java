package lk.ijse.supermarketfx.config;

import lk.ijse.supermarketfx.entity.Customer;
import lk.ijse.supermarketfx.entity.Item;
import lk.ijse.supermarketfx.entity.Order;
import lk.ijse.supermarketfx.entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//Private nisa obj hdnnth ba and instance hdnna ba meke
public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        //1 load (xml) load configuration
//        configuration.configure("hibernate.cfg.xml");//dennm oni file eka read krnna / must give the path file name ek wenas kroth
        configuration.configure();

        //2 add entity classes
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(OrderDetail.class);

        //3 create session factory
        sessionFactory = configuration.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ?
                factoryConfiguration = new FactoryConfiguration() :
                factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();// gets sessions
        //Session session = sessionFactory.openSession();
        //return session;
    }

    //session is not thread safe
    //sessionFactory is thread safe and immutable(doesnt change, first time initialize krt passe aye wenas krnna ba)

}
