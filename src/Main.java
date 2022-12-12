import classes.Contacts;
import classes.MyException;
import classes.Passport;
import classes.WhatsApp;
import enums.Country;
import enums.Gender;
import enums.Status;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static Scanner scannerInt = new Scanner(System.in);
    public static Scanner scannerStr = new Scanner(System.in);
    public static Passport passport = new Passport();
    public static WhatsApp whatsApp = new WhatsApp();
    public static ArrayList<WhatsApp> whatsAppsDataBaza = new ArrayList<>();
    public static ArrayList<Passport> passports = new ArrayList<>();

    public static void main(String[] args) {


        passports.add(new Passport(1, "Zholdoshov", "Nuradil", LocalDate.of(2004, 3, 26), Gender.MALE, Country.TALAS));
        passports.add(new Passport(2, "Shabdanov", "Ilim", LocalDate.of(2003, 5, 4), Gender.MALE, Country.TALAS));

        whatsAppsDataBaza.add(new WhatsApp(1, "+996702817203", "Ilim", "ilim", Status.HELLO_I_AM_USING_WHATSAPP));
        whatsAppsDataBaza.add(new WhatsApp(2, "+996999322332", "Nurik", "nurik", Status.HELLO_I_AM_USING_WHATSAPP));
        try {
            while (true) {
                System.out.println("\n>>>>>>>>>>>>>> Commands <<<<<<<<<<<<<<\n");
                System.out.println("1 - Search by name passport");
                System.out.println("2 - Change status");
                System.out.println("3 - Get passport");
                System.out.println("4 - Get all passport");
                System.out.println("5 - Registration whatsapp");
                System.out.println("6 - Sign in");
                System.out.println("7 - All registered people in whatsapp");
                System.out.println("8 - Get all whatsapp status");
                System.out.println("9 - Get all country");
                System.out.println("10 - Get all gender");
                System.out.println("11 - Remove WhatsApp");
                System.out.println("12 - Save contacts");
                System.out.println("13 - Delete whatsapp account");
                System.out.println("14 - Message");
                int i = scannerInt.nextInt();
                if (i == 1) {
                    System.out.println(passport.searchByName(passports));
                }
                if (i == 2) {
                    System.out.println(whatsApp.changeStatus(whatsAppsDataBaza));
                }
                if (i == 3) {
                    System.out.println(passport.createPassport(passports));
                }
                if (i == 4) {
                    System.out.println(passport.passportsBaza(passports));
                }
                if (i == 5) {
                    System.out.println(whatsApp.registrationWhatsapp(whatsAppsDataBaza, passport.passportsBaza(passports)));
                }
                if (i == 6) {
                    System.out.println(whatsApp.signIn(whatsAppsDataBaza));
                }
                if (i == 7) {
                    System.out.println(whatsApp.getAllWhatsApp(whatsAppsDataBaza));
                }
                if (i == 8) {
                    System.out.println(Arrays.toString(whatsApp.getAllStatus()));
                }
                if (i == 9) {
                    System.out.println(Arrays.toString(passport.getAllCountry()));
                }
                if (i == 10) {
                    System.out.println(Arrays.toString(passport.getAllGender()));
                }
                if (i == 11) {
                    System.out.println(whatsApp.removeWhatsApp(whatsAppsDataBaza));
                }
                if (12 == i) {
                    System.out.println(whatsApp.saveContacts(whatsAppsDataBaza));
                }
                if (13 == i) {
                    whatsApp.removeContact(whatsAppsDataBaza);
                }
                if (14 == i) {
                    System.out.println(whatsApp.message(whatsAppsDataBaza));
                }
            }
        } catch (Exception e) {
            System.err.println("!!!NUMBER!!!");
        }
    }
}