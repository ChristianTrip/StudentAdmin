package menues;

import system.UserPasswordStorage;
import users.Admin;
import users.Student;
import users.Teacher;
import users.User;

public class MainMenu extends Menu{


    // CONSTRUCTORS==============================================================================

    public MainMenu(){

        main();
    }

    // METHODS==============================================================================

    private void main() {

        String schoolName = SCHOOL.getNAME().toUpperCase();

        boolean run = true;
        while(run){

            System.out.println("    |=================================|");
            System.out.println("    |=======" + schoolName + " ADMINISTRATION =======|");
            System.out.println("    |=================================|");
            System.out.println("    ||                               ||");
            System.out.println("    ||  1)  LOGIN                    ||");
            System.out.println("    ||  2)  EXIT                     ||");
            System.out.println("    ||_______________________________||");
            System.out.println();

            int choice = validateUserIntInput(0, 2);

            switch (choice){

                case 0:
                    logInAdmin();
                    break;
                case 1:
                    logIn();
                    break;
                case 2:
                    run = false;
                    System.out.println("Bye bye now..");
                    break;
            }
        }
    }

    private void logInAdmin(){
        Admin kulturKongen = new Admin("Klavs", "Bundgaard", 1978);
        AdminMenu skuldborg = new AdminMenu(kulturKongen);
        skuldborg.main();
    }

    private void logIn(){

        UserPasswordStorage ups = new UserPasswordStorage();

        System.out.println("Enter your login information:");
        System.out.println("User ID: ");
        String userID = SCAN.nextLine();

        User userToValidate = null;
        for (User user : SCHOOL.getUsers()) {
            if (userID.equals(user.getID())){
                userToValidate = user;
            }
        }

        System.out.println("Password: ");
        String password = SCAN.nextLine();

            if(userToValidate != null){

                if(ups.validatePassword(userToValidate, password)){

                    if (userToValidate.getClass() == Student.class){
                        new StudentMenu((Student) userToValidate).main();
                    }
                    else if (userToValidate.getClass() == Teacher.class){
                        new TeacherMenu((Teacher) userToValidate).main();
                    }
                }
                else{
                    System.out.println("User: " + userID + " login information could not be found\n" +
                                       "Contact the administrator");
                }
            }
            else{
                System.out.println("Wrong login information");
            }
    }

}
