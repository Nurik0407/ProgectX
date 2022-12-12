package classes;

import com.sun.tools.javac.Main;
import enums.Status;

import java.util.*;

public class WhatsApp {

    private static Scanner scannerInt = new Scanner(System.in);
    private static Scanner scannerStr = new Scanner(System.in);
    private String phoneNumber;
    private String userName;
    private String password;
    private Status status;
    private int id;
    private Map<String, String> contacts = new TreeMap<>();
    private Map<String, String> message = new LinkedHashMap<>();

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }

    public WhatsApp(int id, String phoneNumber, String userName, String password, Status status) {
        try {
            if (id < 0) {
                throw new MyException();
            } else {
                this.id = id;
            }
            this.phoneNumber = phoneNumber;

            this.userName = userName;
            this.password = password;
            this.status = status;
        } catch (MyException ex) {
            System.err.println("id must not be less than zero");
        }
    }

    public WhatsApp(String password) {
        this.password = password;
    }

    public WhatsApp() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, String> contacts) {
        this.contacts = contacts;
    }

    /**
     * methods
     **/

    public String registrationWhatsapp(ArrayList<WhatsApp> whatsAppsDataBaza, ArrayList<Passport> passports) throws Exception {
        boolean idExists = false;
        System.out.println("Enter ID: ");
        int d = scannerInt.nextInt();
        if (passports.size() == 0) return "Passport pust";
        for (Passport passport : passports) {
            if (passport.getId() == d) {
                idExists = true;
                break;
            }
        }
        if (!idExists) throw new Exception("Id doesn't exists in databse.");
        System.out.println("Enter phone number: (+996)");
        String num = scannerStr.nextLine();
        if (!(num.length() == 13) && !num.contains("+996")) {
            return "Number must contain +996 and consist of 12 digits";
        }
        for (WhatsApp whatsApp : whatsAppsDataBaza) {
            if ((whatsApp.getPhoneNumber().equals(num))) {
                return "Number already registered";
            }
        }
        System.out.println("Enter user name: ");
        String nick = scannerStr.nextLine();
        System.out.println("Enter  password: ");
        String pass = scannerStr.nextLine();
        whatsAppsDataBaza.add(new WhatsApp(d, num, nick, pass, Status.HELLO_I_AM_USING_WHATSAPP));
        System.out.println(whatsAppsDataBaza.get(whatsAppsDataBaza.size() - 1));

        return "\nRegistration completed successfully\n";
    }


    public String signIn(ArrayList<WhatsApp> whatsApps) {
        System.out.println("Enter number: ");
        String num = scannerStr.nextLine();
        System.out.println("Enter password: ");
        String pass = scannerStr.nextLine();

        for (WhatsApp whatsApp : whatsApps) {
            if (whatsApp.phoneNumber.equals(num) && whatsApp.password.equals(pass)) {
                System.out.println(whatsApp);
            } else {
                return "Wrong number or password!";
            }
        }
        return "\nYou have successfully logged in\n";
    }

    public String changeStatus(ArrayList<WhatsApp> whatsApps) {
        System.out.println("Enter password: ");
        String pass = scannerStr.nextLine();
        for (WhatsApp whatsApp : whatsApps) {
            if (whatsApp.getPassword().equals(pass)) {
                System.out.println(whatsApp.getStatus());
                System.out.println(Arrays.toString(Status.values()));
                System.out.println("Write new status: ");
                String stat = scannerStr.nextLine();
                whatsApp.setStatus(Status.valueOf(stat.toUpperCase()));
                System.out.println();
                System.out.println("Status: " + whatsApp.getStatus());
            } else {
                return "Wrong password";
            }
        }
        return "\nStatus changed successfully\n";
    }

    public String removeWhatsApp(ArrayList<WhatsApp> whatsApps) {
        System.out.println("Enter password: ");
        String pass = scannerStr.nextLine();
        if (whatsApps.size() == 0) return "Wrong password!!!";
        for (WhatsApp whatsApp : whatsApps) {
            if (whatsApp.getPassword().equals(pass)) {
                System.out.println("Do you really want to delete whatsapp?  (yes/no):  ");
                String yesOrNo = scannerStr.nextLine();
                if (yesOrNo.trim().toLowerCase().equals("yes")) {
                    whatsApps.remove(whatsApp);
                    return "Account successfully deleted";
                } else return "Thanks for changing your mind)";
            }
            return "Wrong password!!!";
        }
        return null;
    }

    public String saveContacts(ArrayList<WhatsApp> whatsApps) throws Exception {
        try {
            System.out.println("Enter your username: ");
            String username = new Scanner(System.in).nextLine();
            System.out.println("Enter your password: ");
            String password = new Scanner(System.in).nextLine();
            if (username.isEmpty() || password.isEmpty())
                throw new Exception("Username || password can't be empty");
            for (WhatsApp profile : whatsApps) {
                if (profile.getUserName().equals(username) && profile.getPassword().equals(password)) {
                    System.out.println("Enter contact phone number: ");
                    String phoneNumber = new Scanner(System.in).nextLine();
                    if (phoneNumber.length() != 13 || !phoneNumber.startsWith("+996"))
                        throw new Exception("Phone number validation error. Please enter a valid phone number");
                    System.out.println("Enter a contact name: ");
                    String contactName = new Scanner(System.in).nextLine();
                    for (WhatsApp whatsApp : whatsApps) {
                        if (whatsApp.getPhoneNumber().equals(phoneNumber) && whatsApp.getUserName().equals(contactName)) {
                            profile.getContacts().put(contactName, phoneNumber);
                        }
                    }
                    return "Contact added successfully!";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Contact Added Unsuccessfully!";
    }

    public String removeContact(ArrayList<WhatsApp> whatsAppsData) {
        try {
            System.out.println("Your password: ");
            String yourPassword = new Scanner(System.in).nextLine();
            for (WhatsApp proFile : whatsAppsData) {
                if (proFile.getPassword().equals(yourPassword)) {
                    System.out.println("Contact name: ");
                    String nameContact = new Scanner(System.in).nextLine();
                    for (String s : proFile.getContacts().keySet()) {
                        if (s.equals(nameContact)) {
                            proFile.contacts.remove(s);
                            return "Contact successfully deleted";
                        }
                    }
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return "Contact remove unsuccessfully!";
    }

    public ArrayList<WhatsApp> getAllWhatsApp(ArrayList<WhatsApp> whatsApps) {
        return whatsApps;
    }

    public String message(ArrayList<WhatsApp> whatsApps) {
        try {
            System.out.println("Your username: ");
            String userName = new Scanner(System.in).nextLine();

            System.out.println("Your password: ");
            String password = new Scanner(System.in).nextLine();

            if (userName.isEmpty() || password.isEmpty()) {
                return "Password or nickname is not filled";
            }
            for (WhatsApp whatsApp : whatsApps) {
                if (whatsApp.getPassword().equals(password) && whatsApp.getUserName().equals(userName)) {
                    System.out.println("Username contact: ");
                    String name = new Scanner(System.in).nextLine();
                    for (WhatsApp app : whatsApps) {
                        for (String s : whatsApp.getContacts().keySet()) {
                            if (s.equals(name) && app.getUserName().equals(name)) {
                                System.out.println("Write message: ");
                                String mes = new Scanner(System.in).nextLine();
                                app.getMessage().put(mes, whatsApp.getUserName());
                                return " Sent";
                            }
                        }
                    }
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return "No sent!!!";
    }

    public Status[] getAllStatus() {
        return Status.values();
    }

    @Override
    public String toString() {
        return "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n  " +
                "      Profile" +
                "\nID: " + id +
                "\nuserName: " + userName +
                "\nphoneNumber: " + phoneNumber +
                "\nstatus: " + status +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                "\npassword: " + password +
                "\ncontacts: " + contacts +
                "\nmessage: " + message +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
