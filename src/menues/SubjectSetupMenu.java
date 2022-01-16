package menues;

import institution.Subject;
import users.Admin;
import users.User;

public class SubjectSetupMenu extends AdminMenu{

    // CONSTRUCTORS==============================================================================

    public SubjectSetupMenu(Admin admin) {

        super(admin);
    }

    // METHODS==============================================================================

    public void main() {

        boolean run = true;
        while(run){

            System.out.println("    |================================|");
            System.out.println("    |====== Subject Setup menu ======|");
            System.out.println("    |================================|");
            System.out.println("    ||                                ");
            System.out.println("    ||  1)  Create new subject        ");
            System.out.println("    ||  2)  Delete a subject          ");
            System.out.println("    ||  3)  View all subjects         ");
            System.out.println("    ||  4)  Back to Admin menu        ");
            System.out.println();

            int choice = validateUserIntInput(1, 4);

            switch (choice){

                case 1:
                    createSubject();
                    break;
                case 2:
                    deleteSubject();
                    break;
                case 3:
                    viewSubjects();
                    break;
                case 4:
                    run = false;
                    break;
            }
        }
    }

    private void createSubject(){
        System.out.println("Enter a name for the subject");
        String name = SCAN.nextLine();
        System.out.println("How many ESTA points does " + name + " have?");
        double esta = validateUserFloatInput(0, 30);

        this.SCHOOL.addSubject(new Subject(name, esta));
    }

    private void deleteSubject(){
        viewSubjects();
        System.out.println("Input a number corisponding to the subject you want to delete");
        int index = validateUserIntInput(1, this.SCHOOL.getSubjects().size());
        Subject toRemove = this.SCHOOL.getSubjects().get(index - 1);
        this.SCHOOL.removeSubject(toRemove);
        System.out.println(toRemove.getName() + " has been deleted.");

        // removing subject for each user
        for (User user : this.SCHOOL.getUsers()) {
            user.removeSubject(toRemove);
        }
    }

    private void viewSubjects(){

        int count = 1;
        for (Subject subject : this.SCHOOL.getSubjects()) {
            System.out.println(count + ") " + subject.getName() + " (" + subject.getEcts() + " ECTS-points)");
            count ++;
        }
    }

}
