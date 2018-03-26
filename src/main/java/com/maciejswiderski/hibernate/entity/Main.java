package com.maciejswiderski.hibernate.entity;

import com.maciejswiderski.hibernate.entity.create.CreateNewRecord;
import com.maciejswiderski.hibernate.entity.display.Display;
import com.maciejswiderski.hibernate.entity.search.SearchPhoneNumber;
import com.maciejswiderski.hibernate.entity.delete.DeleteRecord;
import com.maciejswiderski.hibernate.entity.update.UpdatePhoneBook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static final String BLUE = "\033[1;34m";
    public static final String RESET = "\033[0m";

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

    public static void mainMenu() {
        Transaction txt;
        Session session = getSession();

        System.out.println(BLUE + "Welcome to the Phones Book on-line service\n" + RESET +
                "1. Add new record to the phone book\n" +
                "2. Look up for a phone number\n3. Update a phone book\n4. Delete record (admin password required)\n5. Show all records\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        try {
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    CreateNewRecord createNewRecordObj = new CreateNewRecord();
                    createNewRecordObj.createNewRecord();
                    mainMenu();
                    break;
                case 2:
                    SearchPhoneNumber searchPhoneNumber = new SearchPhoneNumber();
                    searchPhoneNumber.searchForNumber();
                    mainMenu();
                    break;
                case 3:
                    UpdatePhoneBook updatePhoneBook = new UpdatePhoneBook();
                    updatePhoneBook.updateEntry();
                    break;
                case 4:
                    DeleteRecord deleteRecord = new DeleteRecord();
                    deleteRecord.searchToDeleteRecord();
                    break;
                case 5:
                    txt = session.beginTransaction();
                    Display display = new Display();
                    display.diplayDB(session);
                    System.out.println("type anything to come back to the menu");
                    String typeYes = scanner.next();
                    txt.commit();
                    Main.mainMenu();
                    break;
                case 6:
                    System.out.println("Good bye");
                     session.close();
                     session.getSessionFactory().close();
                    break;
                default:
                    System.out.println("you have not chosen anything from the menu");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input mismatch");
        }
    }

    public static void main(String[] args) {

        mainMenu();
    }
}
