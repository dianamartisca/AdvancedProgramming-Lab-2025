/**
 * Student este folosit pentru a memora datele despre un student. Este o specializare a clasei Person.
 *
 * @see Person
 */
public class Student extends Person {
    private String registrationNumber;
    private Project[] prefProjects;
    private int nrProjects;

    /**
     * În constructor se atribuie numele, data nașterii și numărul de înregistrare pentru un student.
     *
     * @param name numele studentului
     * @param dateOfBirth data nașterii studentului
     * @param registrationNumber numărul de înregistrare al studentului
     */
    public Student(String name, String dateOfBirth, String registrationNumber) {
        super(name, dateOfBirth);
        this.registrationNumber = registrationNumber;
        this.prefProjects = new Project[100];
        this.nrProjects = 0;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * În metoda addProject se adaugă un proiect preferat de un student.
     *
     * @param project proiect
     */
    public void addProject(Project project) {
        prefProjects[nrProjects] = project;
        nrProjects++;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Project[] getPrefProjects() {
        return prefProjects;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Student student = (Student) obj;
        return registrationNumber.equals(student.registrationNumber);
    }

    @Override
    public String toString() {
        StringBuilder projectNames = new StringBuilder();
        for (int i = 0; i < nrProjects; i++) {
            projectNames.append(prefProjects[i].getName());
            projectNames.append(", ");
        }
        projectNames.setLength(projectNames.length() - 2);
        return "Student " + name + " is born on " + dateOfBirth + ", has registration number " + registrationNumber + " and has preferred projects " + projectNames + ".";
    }

}