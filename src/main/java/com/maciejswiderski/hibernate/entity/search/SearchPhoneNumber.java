package com.maciejswiderski.hibernate.entity.search;

import com.maciejswiderski.hibernate.entity.display.Display;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class SearchPhoneNumber {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public void searchForNumber(){
        Scanner scanner = new Scanner(System.in);

        Session session = getSession();

        Display display2 = new Display();
        display2.displaySearchedPhoneNumber(session);

        System.out.println("do you want to search for a contact again? y/n");
        String yes = scanner.next();
        if(yes.equalsIgnoreCase("y")||(yes.equalsIgnoreCase("yes"))){
            searchForNumber();
        }
    }
}
