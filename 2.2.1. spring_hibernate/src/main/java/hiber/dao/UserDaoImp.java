package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void addUser(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User getUser(Car car) {
       TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User u where u.car.model = :model and u.car.series = :series");
       query.setParameter("model", car.getModel()).setParameter("series", car.getSeries());
       return query.setMaxResults(1).getSingleResult();
   }

   @Override
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
