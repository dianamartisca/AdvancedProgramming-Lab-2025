/**
 * Teacher este folosit pentru a memora datele despre un profesor. Este o specializare a clasei Person.
 *
 * @see Person
 */
public class Teacher extends Person {
    private Project[] propProjects;
    private int nrProjects;

    /**
     * În constructor se atribuie numele și data nașterii pentru un profesor.
     *
     * @param name numele profesorului
     * @param dateOfBirth data nașterii profesorului
     */
    public Teacher(String name, String dateOfBirth) {
        super(name, dateOfBirth);
        this.propProjects = new Project[100];
        this.nrProjects = 0;
    }

    /**
     * În metoda addProject se adaugă un proiect propus de un profesor.
     *
     * @param project proiect
     */
    public void addProject(Project project) {
        propProjects[nrProjects] = project;
        nrProjects++;
    }

    public Project[] getPropProjects() {
        return propProjects;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        StringBuilder projectNames = new StringBuilder();
        for (int i = 0; i < nrProjects; i++) {
            projectNames.append(propProjects[i].getName());
            projectNames.append(", ");
        }
        projectNames.setLength(projectNames.length() - 2);
        return "Teacher " + name + " is born on " + dateOfBirth + " and proposes projects " + projectNames + ".";
    }
}
