package lk.ijse.supermarketfx;

import lk.ijse.supermarketfx.config.FactoryConfiguration;
import lk.ijse.supermarketfx.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LifeCycle {
    public static void main(String[] args) {
        //Transient
        Customer customer = new Customer();
        customer.setName("Jhon");
        //no session
        //no db
        //hibernate no track changes

        //Persistent
        //object is now managed by hibernate when obj gets this state
        //have session
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(customer);//give obj to session
        transaction.commit();

        //detached
        session.close();//save wela commit unoth session ekt dunna obj eke data gets saved in db
                        //so even though session is closed the obj is stored in memory
        //customer - db
        customer.setName("Doe");//mukuth wenne na db eke obj ekt yet

        Session session1 = FactoryConfiguration.getInstance().getSession();
        //persistent
        session1.merge(customer);//db customer name get updated in db so modified obj gets persisted

        Transaction transaction1 = session1.beginTransaction();

        //Removed
        session1.remove(customer);//delete wenna ready wela inna aya
        transaction1.commit();//db eken ayin wela ynw
    }
}
