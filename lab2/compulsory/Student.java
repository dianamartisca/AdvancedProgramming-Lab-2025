public class Student {
    private String name;
    private Project[] prefProjects;
    private int nrProjects;

    public Student(String name) {
        this.name = name;
        nrProjects = 0;
        prefProjects = new Project[100];
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProject(Project project) {
        prefProjects[nrProjects] = project;
        nrProjects++;
    }

    public String getName() {
        return name;
    }

    public Project[] getPrefProjects() {
        return prefProjects;
    }

    @Override
    public String toString() {
        StringBuilder projectNames = new StringBuilder();
        for (int i = 0; i < nrProjects; i++) {
            projectNames.append(prefProjects[i].getName());
            projectNames.append(" ");
        }
        return "Student " + name + " has preferred projects " + projectNames;
    }

}
