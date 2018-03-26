package com.maciejswiderski.hibernate.entity.create;

import com.maciejswiderski.hibernate.entity.inputFromUser.FromUser;
import com.maciejswiderski.hibernate.entity.PhonesBookEntity;
import com.maciejswiderski.hibernate.entity.display.Display;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateNewRecord {

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

    public void createNewRecord() {

        Transaction txt;
         Session session = getSession();

        Scanner scanner = new Scanner(System.in);
        String answer;
        int count = 0;
        int number;
        List<Integer> myList = new ArrayList<>();

        do {
            System.out.println("Do you want to add more records to the table? y/n");
            answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                PhonesBookEntity s = FromUser.fromUser();
                txt = session.beginTransaction();
                session.save(s);
                txt.commit();
                count = +count;
                count++;
                number = s.getId();
                myList.add(number);
            } else {
                System.out.println("you have not added to the table PhonesBook");
            }
        } while (answer.equalsIgnoreCase("y"));

        Display displayObj = new Display();
        displayObj.diplayDB(session);
        displayObj.displayLastAdded(count, myList, session);
        session.close();
        session.getSessionFactory().close();
    }
}
