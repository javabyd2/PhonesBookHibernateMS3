package com.maciejswiderski.hibernate.entity.display;

import com.maciejswiderski.hibernate.entity.PhonesBookEntity;

import java.util.List;

import static com.maciejswiderski.hibernate.entity.display.Display.BLUE;
import static com.maciejswiderski.hibernate.entity.display.Display.RESET;

public class DisplayUpdated {


    public void displayByID(List<PhonesBookEntity> phonesBookEntityList, int idNumber) {

        System.out.printf("%-2s | %-14s | %-13s | %-16s | %-18s | %-18s", "ID", "NAME", "LAST NAME", "ADDRESS", "PHONE NUMBER", "EMAIL ADDRESS");
        System.out.println("\n----------------------------------------------------------------------------------------------");
        for (PhonesBookEntity sp : phonesBookEntityList) {
            if (sp.getId() == idNumber) {
                System.out.printf(BLUE + "%-2s | %-14s | %-13s | %-16s | %-18s | %-18s", sp.getId(), sp.getName(), sp.getLastName(), sp.getAddress(), sp.getPhoneNumber(), sp.getEmail() + RESET);
                System.out.println();
            }
        }
    }
}
