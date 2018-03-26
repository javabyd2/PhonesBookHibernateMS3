package com.maciejswiderski.hibernate.entity.display;

import com.maciejswiderski.hibernate.entity.PhonesBookEntity;
import com.maciejswiderski.hibernate.entity.inputFromUser.FromUser;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;


public class Display {

    public static final String BLUE = "\033[1;34m";
    public static final String RESET = "\033[0m";

    public void diplayDB(Session session) {

        List<PhonesBookEntity> phonesBookEntityList = session.createQuery("From " + PhonesBookEntity.class.getName()).list();

        System.out.printf("%-2s | %-14s | %-13s | %-16s | %-18s | %-18s", "ID", "NAME", "LAST NAME", "ADDRESS", "PHONE NUMBER", "EMAIL ADDRESS");
        System.out.println("\n----------------------------------------------------------------------------------------------");
        for (PhonesBookEntity sp : phonesBookEntityList) {
            System.out.printf("%-2s | %-14s | %-13s | %-16s | %-18s | %-18s", sp.getId(), sp.getName(), sp.getLastName(), sp.getAddress(), sp.getPhoneNumber(), sp.getEmail());
            System.out.println();
        }
    }

    public void displayLastAdded(int count, List<Integer> myList, Session session) {
        System.out.println("\nAdded new records: " + count + "\n");

        for (int i : myList) {
            Query query = session.createQuery("from PhonesBookEntity where id=" + i);
            List<PhonesBookEntity> phoneNumList = query.getResultList();
            for (PhonesBookEntity sp : phoneNumList) {
                System.out.printf(BLUE + "%-2s | %-14s | %-13s | %-16s | %-18s | %-18s", sp.getId(), sp.getName(), sp.getLastName(), sp.getAddress(), sp.getPhoneNumber(), sp.getEmail() + RESET);
                System.out.println();
            }
        }
    }

    public void displaySearchedPhoneNumber(Session session) {

        PhonesBookEntity s = FromUser.findNumber();
        String name = s.getName();
        String lastName = s.getLastName();
        name = name.replaceAll("[\\W+\\s+]", "");
        lastName = lastName.replaceAll("[\\W+\\s+]", "");

        List<PhonesBookEntity> phonesBookEntityList = session.createQuery("From " + PhonesBookEntity.class.getName()).list();

        int count = 0;
        System.out.printf("%-2s | %-14s | %-13s | %-16s | %-18s | %-18s", "ID", "NAME", "LAST NAME", "ADDRESS", "PHONE NUMBER", "EMAIL ADDRESS");
        System.out.println("\n----------------------------------------------------------------------------------------------");
        for (PhonesBookEntity sp : phonesBookEntityList) {
            count=+count;
            if (sp.getName().equalsIgnoreCase(name) && (sp.getLastName().equalsIgnoreCase(lastName))) {
                System.out.printf(BLUE + "%-2s | %-14s | %-13s | %-16s | %-18s | %-18s", sp.getId(), sp.getName(), sp.getLastName(), sp.getAddress(), sp.getPhoneNumber(), sp.getEmail() + RESET);
                count++;
                System.out.println();
            }
        }System.out.println("found records: "+count);
    }
}
