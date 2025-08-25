package lk.ijse.supermarketfx;

import lk.ijse.supermarketfx.config.FactoryConfiguration;
import lk.ijse.supermarketfx.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class QueryTest {

    public static void main(String[] args) {
        //Native SQL,HQL,JPQL with Hibernate
        FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
        Session session = factoryConfiguration.getSession();

        //HQL / JPQL with Where case-att eke name eken
//        Query<Customer> query = session.createQuery("from Customer where name='Jhon Doe'", Customer.class);//returns a query object, we can take out a list from this
//        List<Customer> list = query.list();
//        list.forEach(customer->{
//            System.out.println(customer.toString());
//        });

        //Native SQL with Where clause-tbl column name eken
        NativeQuery<Customer> nativeQuery = session.createNativeQuery(
                "select * from customers where customer_name:cus_name", Customer.class);
        nativeQuery.setParameter("cus_name","John Doe");
        List<Customer> customerList = nativeQuery.list();
        customerList.forEach(customer->{
            System.out.println(customer.toString());
        });

        //HQL where case with parameter
//        session.createQuery("",entity class) - HQL/JPQL ->query and then class name
//        session.createNativeQuery("",entity class) - SQL
       Query<Customer> query = session.createQuery("from Customer cus where cus.name: cus_name",Customer.class);//using cus as a ref name for Customer entity
       query.setParameter("cus_name","John Doe");
       List<Customer>list = query.list();

       for (Customer customer : customerList) {
           System.out.println(customer.toString());
       }

    }
}
