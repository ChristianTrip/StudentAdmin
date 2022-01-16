package menues;

import institution.Subject;
import system.UserPasswordStorage;
import users.Admin;
import users.Student;
import users.Teacher;
import users.User;

public class UserSetupMenu extends AdminMenu {

    // CONSTRUCTORS==============================================================================

    public UserSetupMenu(Admin admin) {
        super(admin);
    }

    // METHODS==============================================================================

    public void main() {

        boolean run = true;
        while(run){

            System.out.println("    |===================================|");
            System.out.println("    |========= User Setup menu =========|");
            System.out.println("    |===================================|");
            System.out.println("    ||                                   ");
            System.out.println("    ||  1)  Create new user              ");
            System.out.println("    ||  2)  Delete a user                ");
            System.out.println("    ||  3)  View all users               ");
            System.out.println("    ||  4)  Enroll a user to a subject   ");
            System.out.println("    ||  5)  Remove a user from a subject ");
            System.out.println("    ||  6)  Back to admin menu           ");
            System.out.println();

            int choice = validateUserIntInput(1, 6);

            switch (choice){

                case 1:
                    createUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    viewUsers();
                    break;
                case 4:
                    enroll();
                    break;
                case 5:
                    disenroll();
                    break;
                case 6:
                    run = false;
                    break;
            }
        }
    }

    private void createUser() {

        UserPasswordStorage ups = new UserPasswordStorage();

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("|                   CREATE A NEW USER                   |");
        System.out.println("=========================================================");
        System.out.println();
        System.out.println("1) Student");
        System.out.println("2) Teacher");
        int choice = validateUserIntInput(1, 2);

        System.out.println("Enter users first name: ");
        String firstName = SCAN.nextLine();
        System.out.println("Enter users last name: ");
        String lastName = SCAN.nextLine();
        System.out.println("Enter users birth year: ");
        int birthYear = validateUserIntInput(1950, 2015);

        System.out.println("Create a password");
        System.out.println("A password must contain atleast 8 symbols, whereas minimum two has to be numbers.\n" +
                "There has to be both uppercase and lowercase letters. Every symbol including space can be used.");
        String password = SCAN.nextLine();

        while (!ups.validPassword(password)){
            password = SCAN.nextLine();
        }

        User newUser = null;

        if (choice == 1) {
            newUser = new Student(firstName.trim(), lastName.trim(), birthYear);
        }
        else if (choice == 2) {
            newUser = new Teacher(firstName.trim(), lastName.trim(), birthYear);
        }

        if(newUser != null){
            this.SCHOOL.addUser(newUser);
            ups.logUser(newUser, password);
        }
        else{
            System.out.println("The user is null!");
        }
    }

    private void deleteUser(){

        System.out.println(this.SCHOOL.getListOfNumberedUsers());

        System.out.println("Input a number corisponding to the user you want to delete");
        int index = validateUserIntInput(1, this.SCHOOL.getUsers().size());
        User toRemove = this.SCHOOL.getUsers().get(index - 1);
        this.SCHOOL.removeUser(toRemove);
        System.out.println(toRemove.getFullName() + " has been deleted.");
    }

    private void enroll() {

        System.out.println(this.SCHOOL.getListOfNumberedUsers());
        System.out.println("Select a user you want to enroll");
        int userIndex = validateUserIntInput(1, this.SCHOOL.getUsers().size());
        User toEnroll = this.SCHOOL.getUsers().get(userIndex - 1);
        System.out.println("You selected " + toEnroll.getFullName());
        this.SCHOOL.viewSubjects();
        System.out.println("Select a subjet for the user to enroll");
        int subjectIndex = validateUserIntInput(1, this.SCHOOL.getSubjects().size());
        Subject subject = this.SCHOOL.getSubjects().get(subjectIndex - 1);
        System.out.println("You selected " + subject.getName());

        subject.addUser(toEnroll);
        toEnroll.addSubject(subject);
    }

    private void disenroll(){

        System.out.println(this.SCHOOL.getListOfNumberedUsers());
        System.out.println("Select a user you want to disenroll");
        int userIndex = validateUserIntInput(1, this.SCHOOL.getUsers().size());
        User toDisenroll = this.SCHOOL.getUsers().get(userIndex - 1);
        System.out.println("You selected " + toDisenroll.getFullName());
        System.out.println("These are " + toDisenroll.getFullName() + "'s current subjects. ");
        toDisenroll.viewSubjects();
        System.out.println("Select a subjet for the user to disenroll");
        int subjectIndex = validateUserIntInput(1, toDisenroll.getSubjects().size());
        Subject subject = this.SCHOOL.getSubjects().get(subjectIndex - 1);
        System.out.println("You selected " + subject.getName());

        subject.removeUser(toDisenroll);
        toDisenroll.removeSubject(subject);
    }

    private void viewUsers() {

        System.out.println(SCHOOL.getPrintoutOfAllUsers());
    }

}
