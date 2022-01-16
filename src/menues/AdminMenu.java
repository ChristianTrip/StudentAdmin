package menues;

import users.*;

public class AdminMenu extends Menu{

    // CONSTRUCTORS==============================================================================

    public AdminMenu(Admin admin){

        this.currentUser = admin;
    }

    // METHODS==============================================================================

    public void main() {

        boolean run = true;
        while (run){

            System.out.println("    ||  Admin menu for " + currentUser.getFullName());
            System.out.println("    ||                                          ");
            System.out.println("    ||  1)   USER SETUP                         ");
            System.out.println("    ||  2)   SUBJECT SETUP                      ");
            System.out.println("    ||  3)   VIEW SCHOOL INFO                   ");
            System.out.println("    ||  4)   LOGOUT                             ");
            System.out.println("    ||__________________________________________");
            System.out.println();

            int choice = validateUserIntInput(1, 4);

            switch (choice){
                case 1:
                    new UserSetupMenu((Admin)this.currentUser).main();
                    break;
                case 2:
                    new SubjectSetupMenu((Admin)this.currentUser).main();
                    break;
                case 3:
                    System.out.println(SCHOOL.toString());
                    break;
                case 4:
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

}
