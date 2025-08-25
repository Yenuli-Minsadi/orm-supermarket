package lk.ijse.supermarketfx;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
//        NativeQuery<Customer> nativeQuery = session.createNativeQuery(
//                "select * from customers where customer_name:cus_name", Customer.class);
//        nativeQuery.setParameter("cus_name","John Doe");
//        List<Customer> customerList = nativeQuery.list();
//        customerList.forEach(customer->{
//            System.out.println(customer.toString());
//        });

        //HQL where case with parameter
//        session.createQuery("",entity class) - HQL/JPQL ->query and then class name
//        session.createNativeQuery("",entity class) - SQL
//       Query<Customer> query = session.createQuery("from Customer cus where cus.name: cus_name",Customer.class);//using cus as a ref name for Customer entity
//       query.setParameter("cus_name","John Doe");
//       List<Customer>list = query.list();
//
//       for (Customer customer : customerList) {
//           System.out.println(customer.toString());
//       }

        //Criteria API
        //Type Safe Programmatic way build queries in HQL/JPQL
        //Avoid writing HQL/JPQL as a string
        //use for dynamic queries

////      1.  create CriteriaBuilder Object
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
////        2. create CriteriaQuery Object
//        CriteriaQuery<Customer> customerQuery = criteriaBuilder.createQuery(Customer.class);// query ek hdgnnw
////        3. Set up Root entity
//        Root<Customer> root = customerQuery.from(Customer.class);
////        4. add where condition
//        customerQuery.select(root).where(criteriaBuilder.equal(root.get("name"),"John Doe"));
////        5. Run Query
//        Query<Customer> query = session.createQuery(customerQuery);
//        List<Customer> customerList = query.list();

//        Join Query
//        select * from customers c left join orders o on c.id=o.cus_id where cid='C001';
        Query<Object[]> query = session.createQuery("FROM Customer c left join Order o on c.id = o.customer WHERE c.id='C001'",
                Object[].class);//return obj array since an obj is need to be returned for each column
        //list eke tiyenne table eke data rows therefore obj array eka list ekk htiyt gnna

        List<Object[]> dataList = query.list();
        for (Object[] object: dataList) {
            System.out.println(object[0]);//column 1
        }


    }
}
