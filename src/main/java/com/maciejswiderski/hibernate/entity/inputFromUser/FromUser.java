package com.maciejswiderski.hibernate.entity.inputFromUser;

import com.maciejswiderski.hibernate.entity.PhonesBookEntity;
import com.maciejswiderski.hibernate.entity.validation.EmailValidation;
import com.maciejswiderski.hibernate.entity.validation.PhoneValidation;

import java.util.Scanner;

public class FromUser {

    public static PhonesBookEntity fromUser() {

        Scanner scanner = new Scanner(System.in);
        PhonesBookEntity phonesBookEntity = new PhonesBookEntity();
        System.out.println("provide name");
        String newName = scanner.nextLine();
        newName = newName.replaceAll("\\s", "");
        phonesBookEntity.setName(newName);
        System.out.println("provide last name");
        String newLastName = scanner.nextLine();
        newLastName = newLastName.replaceAll("\\s", "");
        phonesBookEntity.setLastName(newLastName);
        System.out.println("provide address");
        String newAddress = scanner.nextLine();
        phonesBookEntity.setAddress(newAddress);

        PhoneValidation phoneValidation = new PhoneValidation();
        phoneValidation.provideName(phonesBookEntity);

        EmailValidation emailValidation = new EmailValidation();
        emailValidation.provideEmail(phonesBookEntity);

        return phonesBookEntity;
    }

    public static PhonesBookEntity findNumber() {

        Scanner scanner = new Scanner(System.in);
        PhonesBookEntity phonesBookEntity = new PhonesBookEntity();
        System.out.println("Provide a name you are looking for");
        String newName = scanner.nextLine();
        phonesBookEntity.setName(newName);
        System.out.println("Provide a last name you are looking for");
        String newLastName = scanner.nextLine();
        phonesBookEntity.setLastName(newLastName);

        return phonesBookEntity;
    }
}
