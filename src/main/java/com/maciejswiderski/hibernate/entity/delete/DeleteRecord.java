package com.maciejswiderski.hibernate.entity.delete;

import com.maciejswiderski.hibernate.entity.Main;
import com.maciejswiderski.hibernate.entity.PhonesBookEntity;
import com.maciejswiderski.hibernate.entity.display.DisplayUpdated;
import com.maciejswiderski.hibernate.entity.search.SearchPhoneNumber;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class DeleteRecord {

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

    public void searchToDeleteRecord() {

        Transaction tx = null;
        Session session = getSession();

        Scanner scannerDelete = new Scanner(System.in);
        System.out.println("provide password: ");
        String password = scannerDelete.next();

        if (password.equalsIgnoreCase("kot")) {
            SearchPhoneNumber searchPhoneNumber = new SearchPhoneNumber();
            searchPhoneNumber.searchForNumber();

            System.out.println("provide id for the record you want to delete");

            int idNumber = scannerDelete.nextInt();

            System.out.println("confirm y/n");
            String confrirmation = scannerDelete.next();

            if (confrirmation.equalsIgnoreCase("y")){
                List<PhonesBookEntity> phonesBookEntityList =
                        session.createQuery("From " + PhonesBookEntity.class.getName()).list();

            DisplayUpdated displayUpdated = new DisplayUpdated();
            displayUpdated.displayByID(phonesBookEntityList, idNumber);

            tx = session.beginTransaction();
            String hql = "delete from phonesBook " +
                    " where id = :id";
            Query query1 = session.createNativeQuery(hql);
            //query1.setParameter("name", newName);
            query1.setParameter("id", idNumber);

            int result = query1.executeUpdate();
            tx.commit();

            System.out.println("The above record has been deleted from the phones book service ");
            System.out.println("Rows affected: " + result);

        }

            System.out.println("to come back to the main menu type y/n");
            String comeBackToMain = scannerDelete.next();

            if (comeBackToMain.equalsIgnoreCase("y")) {
                Main.mainMenu();
            } else {
                searchToDeleteRecord();
            }
        }else{
            System.out.println("wrong password! try again or come back to the menu y/n");
            String tryAgain = scannerDelete.next();
            if(tryAgain.equalsIgnoreCase("y")||(tryAgain.equalsIgnoreCase("yes"))){
                searchToDeleteRecord();
            }else{
                Main.mainMenu();
            }
        }
    }
}
