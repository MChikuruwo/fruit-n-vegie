package com.fruitnvegie.fruitnvegieapi.dao.Impl;

/*import com.fruitnvegie.fruitnvegieapi.dao.CustomerRepository;
import com.fruitnvegie.fruitnvegieapi.models.Customer;
import com.fruitnvegie.fruitnvegieapi.models.ShoppingCart;
import com.fruitnvegie.fruitnvegieapi.models.User;
import com.fruitnvegie.fruitnvegieapi.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    private SessionFactory sessionFactory;
    private UserService userService;

    @Autowired
    public CustomerRepositoryImpl(SessionFactory sessionFactory,UserService userService){
        this.sessionFactory = sessionFactory;
        this.userService = userService;
    }

    //SessionFactory session = HibernateUtil.getInstance().getSessionFactory();

    @Transactional
    public void addCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User();
        userService.getOne(user.getId());
        customer.setContactEmail(user.getEmailAddress());
        customer.setPassword(user.getPassword());
        customer.getShippingDetails().setCustomer(customer);
        customer.setActive(true);
        user.setCustomerId(customer.getId());


        session.saveOrUpdate(customer);

        session.saveOrUpdate(customer.getShippingDetails());

        /*User user = new User();
        userService.getOne(user.getId());
        user.setCustomerId(customer.getId());
        //newUser.setEmailAddress(customer.getContactEmail());
        //newUser.setPassword(customer.getPassword());
        customer.setContactEmail(user.getEmailAddress());
        customer.setPassword(user.getPassword());



        Role newAuthority = new Role();
        newAuthority.setName("CUSTOMER");
        //newAuthority.setUsername(customer.getUsername());



        session.saveOrUpdate(user);
        //session.saveOrUpdate(newAuthority);




        ShoppingCart newCart = new ShoppingCart();
        newCart.setCustomer(customer);

        customer.setShoppingCart(newCart);
        session.saveOrUpdate(customer);
        session.saveOrUpdate(newCart);

        session.flush();
        session.getTransaction().commit();

    }

    public Customer getCustomerById(long customerId) {
        return (Customer) sessionFactory.getCurrentSession().get(Customer.class, customerId);
    }

    public List<Customer> getAllCustomers() {
        return sessionFactory.getCurrentSession().createQuery("from Customer")
                .list();
    }

    public Customer getCustomerByUsername(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where firstName = ?");
        query.setString(0, username);
        return (Customer) query.uniqueResult();
    }

}

 */

