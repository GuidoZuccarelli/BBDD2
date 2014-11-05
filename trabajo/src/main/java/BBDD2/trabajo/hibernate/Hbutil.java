package BBDD2.trabajo.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Hbutil {

    private static SessionFactory sessionFactory;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());     
        sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
        	configureSessionFactory();
        return sessionFactory;
    }
}
