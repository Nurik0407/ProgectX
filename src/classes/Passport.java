package classes;

import enums.Country;
import enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Passport {
    private static Scanner scannerInt = new Scanner(System.in);
    private static Scanner scannerStr = new Scanner(System.in);

    private int id;
    private String lastName;
    private String firstName;

    private LocalDate dateOfBirth;
    private Gender gender;
    private Country country;


    public Passport(int id, String lastName, String firstName, LocalDate localDate, Gender gender, Country country) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = localDate;
        this.gender = gender;
        this.country = country;
    }

    public Passport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "        Person{" +
                "\nid: " + id +
                "\nlastName: " + lastName +
                "\nfirstName: " + firstName +
                "\ndateOfBirth: " + dateOfBirth +
                "\ngender: " + gender +
                "\ncountry: " + country + "\n";
    }

    /**
     * methods
     **/

    public String createPassport(ArrayList<Passport> passports) {

        try {
            System.out.println("Fill in the form");
            System.out.println("ID person: ");
            int id = scannerInt.nextInt();
            for (Passport passport : passports) {
                if (passport.getId() == id) return "This id already exists";
            }
            if (id <= 0) {
                throw new MyException();
            }
            System.out.println("Last name: ");
            String last = scannerStr.nextLine();
            System.out.println("First name: ");
            String first = scannerStr.nextLine();
            System.out.println("Data of birth: (yyyy-mm-dd)");
            String data = scannerStr.nextLine();
            System.out.println("Enter gender: ");
            String gen = scannerStr.nextLine();
            System.out.println("Enter country: ");
            String country = scannerStr.nextLine();
            String[] dataOFBirth = data.trim().split("-");
            int a = Integer.parseInt(dataOFBirth[0]);
            int b = Integer.parseInt(dataOFBirth[1]);
            int c = Integer.parseInt(dataOFBirth[2]);
            LocalDate date = LocalDate.of(a, b, c);

            passports.add(new Passport(id, last, first, date, Gender.valueOf(gen.toUpperCase()), Country.valueOf(country.toUpperCase())));
            System.out.println(passports.get(passports.size() - 1));
        } catch (MyException e) {
            return "\nID cannot be zero!!!\n";
        } catch (InputMismatchException e) {
            return "ID may contain (1-9)";
        } catch (IllegalArgumentException e) {
            return "Wrong gender or city entered!!!";
        }

        return "Passport received successfully";
    }

    public String searchByName(ArrayList<Passport> person) {
        System.out.println("Enter first name: ");
        String firstName = scannerStr.nextLine();
        if (person.size() == 0) return "\nIt's not in the database!\n";
        for (Passport passport : person) {
            if (passport.getFirstName().toUpperCase().equals(firstName.toUpperCase())) {
                System.out.println(passport);
                return "\nSuccessfully found\n";
            } else return "\nIt's not in the database!\n";
        }
        return null;
    }

    public ArrayList<Passport> passportsBaza(ArrayList<Passport> passports) {
        System.out.println("People: " + passports.size());
        return passports;
    }

    public Country[] getAllCountry() {
        return Country.values();
    }

    public Gender[] getAllGender() {
        return Gender.values();
    }
}
