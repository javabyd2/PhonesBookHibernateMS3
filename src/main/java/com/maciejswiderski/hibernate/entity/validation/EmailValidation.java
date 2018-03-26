package com.maciejswiderski.hibernate.entity.validation;

import com.maciejswiderski.hibernate.entity.PhonesBookEntity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class EmailValidation {

    public static final String BLUE = "\033[1;34m";
    public static final String RESET = "\033[0m";

    public PhonesBookEntity provideEmail(PhonesBookEntity phonesBookEntity) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("provide email address");
        String newEmail = scanner.nextLine();

        if (Pattern.matches("([a-z]([.\\-|+])?[a-z]+)+@[a-z]+\\.[a-z]{2,3}", newEmail)) {

            phonesBookEntity.setEmail(newEmail);
        } else {
            System.out.println(BLUE + "wrong format!\nThe correct formats are: xxxx.xxx@ / xxx-xxx@ / xxxxxx@" + RESET);
            EmailValidation emailValidation = new EmailValidation();
            emailValidation.provideEmail(phonesBookEntity);
        }
        return phonesBookEntity;
    }

    public String updatedEmailValidation() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("provide email address");
        String newEmail = scanner.next();

        if (Pattern.matches("([a-z]([.\\-|+])?[a-z]+)+@[a-z]+\\.[a-z]{2,3}", newEmail)) {
            return newEmail;
        } else {
            System.out.println(BLUE + "wrong format!\nThe correct formats are: xxxx.xxx@ / xxx-xxx@ / xxxxxx@" + RESET);
            EmailValidation emailValidation1 = new EmailValidation();
            return emailValidation1.updatedEmailValidation();
        }
    }
}
