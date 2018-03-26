package com.maciejswiderski.hibernate.entity.validation;

import com.maciejswiderski.hibernate.entity.PhonesBookEntity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneValidation {

    public static final String BLUE = "\033[1;34m";
    public static final String RESET = "\033[0m";

    public PhonesBookEntity provideName(PhonesBookEntity phonesBookEntity){

        Scanner scanner = new Scanner(System.in);
        System.out.println("provide phone number");
        String newPhoneNumber = scanner.nextLine();

        if ( Pattern.matches("((\\+\\d{2})\\s?\\d{3})(-|\\s)?\\d{3}(-|\\s)?\\d{3}", newPhoneNumber)) {
        //if ( Pattern.matches("(\\+\\d{2})?\\s?\\d{3}(-|\\s)?\\d{3}(-|\\s)?\\d{3}", newPhoneNumber)) {  // allows both options +xx or without
            phonesBookEntity.setPhoneNumber(newPhoneNumber);
        } else {
            System.out.println(BLUE+"wrong format!\nThe correct format is: +XX XXX XXX XXX"+RESET);
            PhoneValidation phoneValidation = new PhoneValidation();
            phoneValidation.provideName(phonesBookEntity);

        }
        return phonesBookEntity;
    }

    public String updatePhoneValidation(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("provide phone number");
        String newPhoneNumber = scanner.nextLine();

        if ( Pattern.matches("((\\+\\d{2})\\s?\\d{3})(-|\\s)?\\d{3}(-|\\s)?\\d{3}", newPhoneNumber)) {
            return newPhoneNumber;
        } else {
            System.out.println(BLUE+"wrong format!\nThe correct format is: +XX XXX XXX XXX"+RESET);
            PhoneValidation phoneValidation = new PhoneValidation();
            return phoneValidation.updatePhoneValidation();
        }
    }
}
