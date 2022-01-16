package menues;

import institution.School;
import users.User;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Menu {

    // INSTANCE FIELDS==============================================================================

    protected static final School SCHOOL = new School(); // creating a new object of thie school class, loads in all data about users and subjects, to be accesed in the menues.
    protected static final Scanner SCAN = new Scanner(System.in);
    protected User currentUser;


    // METHODS==============================================================================

    protected static boolean logOut(){

        System.out.println("Are you sure you want to log out?");
        System.out.println("1) Yes");
        System.out.println("2) No");

        int choice = validateUserIntInput(1, 2);

        if (choice == 1){
            // When ever you log out as a user you also save the changes made in the folder resources.
            SCHOOL.saveUsers();
            SCHOOL.saveSubjects();
            return true;
        }
        return false;
    }

    protected static int validateUserIntInput(int minValue, int maxValue){

        int returnNum = 0;
        boolean run = true;
        while (run) {
            try {
                returnNum = SCAN.nextInt();
                if (returnNum >= minValue && returnNum <= maxValue){
                    SCAN.nextLine();
                    run = false;
                }
                else {
                    System.out.println("The number has to be between " + (minValue) + " and " + (maxValue));
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! try a number");
                SCAN.nextLine();
            }
        }
        return returnNum;
    }

    protected static double validateUserFloatInput(double minValue, double maxValue){

        double returnNum = 0;
        boolean run = true;
        while (run) {
            try {
                returnNum = SCAN.nextDouble();
                if (returnNum >= minValue && returnNum <= maxValue){
                    SCAN.nextLine();
                    run = false;
                }
                else {
                    System.out.println("The number has to be between " + (minValue) + " and " + (maxValue));
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! try a number");
                SCAN.nextLine();
            }
        }
        return returnNum;
    }

}
