package menues;

import institution.Subject;
import users.Student;

public class StudentMenu extends Menu {

    // CONSTRUCTORS==============================================================================

    public StudentMenu(Student student) {
        this.currentUser = student;
    }

    // METHODS==============================================================================

    public void main() {

        boolean run = true;
        while (run){

            System.out.println("      __________________________________________");
            System.out.println("    ||  Student menu for " + currentUser.getFullName());
            System.out.println("    ||  - UserID : " + currentUser.getID());
            System.out.println("    ||  - E-mail : " + currentUser.getEmail());
            System.out.println("    ||__________________________________________");
            System.out.println("    ||                               ");
            System.out.println("    ||  1)  ENROLL TO A SUBJECT      ");
            System.out.println("    ||  2)  VIEW ACTIVE SUBJECTS     ");
            System.out.println("    ||  3)  LOGOUT                   ");
            System.out.println("    ||__________________________________________");
            System.out.println();

            int choice = validateUserIntInput(1, 3);

            switch (choice){
                case 1:
                    enroll();
                    break;
                case 2:
                    viewEnrooledSubjects();
                    break;
                case 3:
                    boolean exit = logOut();
                    if (!exit){
                        break;
                    }else{
                        SCHOOL.saveUsers();
                        SCHOOL.saveSubjects();
                        System.out.println("You are now logged out");
                        run = false;

                    }

            }
        }
    }

    private void changePassword(){

    }

    private void enroll(){

        this.SCHOOL.viewSubjects();

        System.out.println("Enroll to a subject, enter a number");
        int index = validateUserIntInput(1, SCHOOL.getSubjects().size());
        Subject subject = SCHOOL.getSubjects().get(index -1);
        System.out.println("You selected " + subject.getName());

        subject.addUser(this.currentUser);
        this.currentUser.addSubject(subject);
    }

    private void viewEnrooledSubjects(){

        System.out.println(this.SCHOOL.getUserSubjectsAsString(this.currentUser));
    }

}
