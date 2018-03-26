package com.maciejswiderski.hibernate.entity.update;

import com.maciejswiderski.hibernate.entity.Main;
import com.maciejswiderski.hibernate.entity.PhonesBookEntity;
import com.maciejswiderski.hibernate.entity.display.DisplayUpdated;
import com.maciejswiderski.hibernate.entity.validation.EmailValidation;
import com.maciejswiderski.hibernate.entity.validation.PhoneValidation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class UpdatePhoneBook {

    //public static final String BLUE = "\033[1;34m";
    //public static final String RESET = "\033[0m";

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

    public void updateEntry() {

        Scanner scannerUpdate = new Scanner(System.in);

        Transaction tx = null;
        Session session = getSession();

        System.out.println("Provide id for entry you would like to upate: ");
        Scanner scanner = new Scanner(System.in);
        int idNumber = scanner.nextInt();

        List<PhonesBookEntity> phonesBookEntityList =
                session.createQuery("From " + PhonesBookEntity.class.getName()).list();

        DisplayUpdated displayUpdated = new DisplayUpdated();
        displayUpdated.displayByID(phonesBookEntityList, idNumber);

        updateMenu(scannerUpdate, session, idNumber, displayUpdated, scanner);
    }

    public void updateMenu(Scanner scannerUpdate, Session session, int idNumber, DisplayUpdated displayUpdated, Scanner scanner) {
        System.out.println("Tp update choose from the list below\n" +
                "1. first name\n2. last name\n3. address\n4. phone number\n5. email\n6. Exit to the  main Manu");

        int number1 = scanner.nextInt();

        Transaction tx;
        switch (number1) {
            case 1:
                System.out.println("provide new name");
                String newName = scannerUpdate.nextLine();

                Session session1 = getSession();
                tx = session.beginTransaction();
                String hql = "update phonesBook set name = :name" +
                        " where id = :id";
                Query query1 = session.createNativeQuery(hql);
                query1.setParameter("name", newName);
                query1.setParameter("id", idNumber);

                int result = query1.executeUpdate();
                tx.commit();

                System.out.println("Rows affected: " + result);

                List<PhonesBookEntity> phonesBookEntityListAfterUpdate1 =
                        session1.createQuery("From " + PhonesBookEntity.class.getName()).list();
                displayUpdated.displayByID(phonesBookEntityListAfterUpdate1, idNumber);

                updateMenu(scannerUpdate, session, idNumber, displayUpdated, scanner);
                break;
            case 2:

                System.out.println("provide new last name");
                String newLastName = scannerUpdate.nextLine();

                Session session2 = getSession();
                tx = session.beginTransaction();
                String hq2 = "update phonesBook set lastName = :lastName" +
                        " where id = :id";
                Query query2 = session.createNativeQuery(hq2);
                query2.setParameter("lastName", newLastName);
                query2.setParameter("id", idNumber);

                int result2 = query2.executeUpdate();
                tx.commit();

                System.out.println("Rows affected: " + result2);

                List<PhonesBookEntity> phonesBookEntityListAfterUpdate2 =
                        session2.createQuery("From " + PhonesBookEntity.class.getName()).list();
                displayUpdated.displayByID(phonesBookEntityListAfterUpdate2, idNumber);

                updateMenu(scannerUpdate, session, idNumber, displayUpdated, scanner);
                break;
            case 3:

                System.out.println("provide new address");
                String address = scannerUpdate.nextLine();

                Session session3 = getSession();
                tx = session.beginTransaction();
                String hq3 = "update phonesBook set address = :address" +
                        " where id = :id";
                Query query3 = session.createNativeQuery(hq3);
                query3.setParameter("address", address);
                query3.setParameter("id", idNumber);

                int result3 = query3.executeUpdate();
                tx.commit();

                System.out.println("Rows affected: " + result3);

                List<PhonesBookEntity> phonesBookEntityListAfterUpdate3 =
                        session3.createQuery("From " + PhonesBookEntity.class.getName()).list();
                displayUpdated.displayByID(phonesBookEntityListAfterUpdate3, idNumber);

                updateMenu(scannerUpdate, session, idNumber, displayUpdated, scanner);

                break;
            case 5:
                EmailValidation emailValidation = new EmailValidation();
                String email = emailValidation.updatedEmailValidation();
                Session session4 = getSession();
                tx = session.beginTransaction();
                String hq4 = "update phonesBook set email = :email" +
                        " where id = :id";
                Query query4 = session.createNativeQuery(hq4);
                query4.setParameter("email", email);

                query4.setParameter("id", idNumber);

                int result4 = query4.executeUpdate();
                tx.commit();

                System.out.println("Rows affected: " + result4);

                List<PhonesBookEntity> phonesBookEntityListAfterUpdate4 =
                        session4.createQuery("From " + PhonesBookEntity.class.getName()).list();
                displayUpdated.displayByID(phonesBookEntityListAfterUpdate4, idNumber);

                updateMenu(scannerUpdate, session, idNumber, displayUpdated, scanner);
                break;
            case 4:

                PhoneValidation phoneValidation = new PhoneValidation();
                String phoneNumber = phoneValidation.updatePhoneValidation();
                Session session5 = getSession();
                tx = session.beginTransaction();
                String hq5 = "update phonesBook set phoneNumber = :phoneNumber" +
                        " where id = :id";
                Query query5 = session.createNativeQuery(hq5);
                query5.setParameter("phoneNumber", phoneNumber);

                query5.setParameter("id", idNumber);

                int result5 = query5.executeUpdate();
                tx.commit();
                System.out.println("Rows affected: " + result5);

                List<PhonesBookEntity> phonesBookEntityListAfterUpdate5 = session5.createQuery("From " + PhonesBookEntity.class.getName()).list();
                displayUpdated.displayByID(phonesBookEntityListAfterUpdate5, idNumber);

                updateMenu(scannerUpdate, session, idNumber, displayUpdated, scanner);

                break;
            case 6:
                Main.mainMenu();
                break;
            default:
                System.out.println("You have not chosen anything");
                break;
        }
    }
}
