/**
 * Problem este folosit pentru a memora studenții, profesorii și proiectele implicate într-o problemă.
 *
 * @see Student
 * @see Teacher
 * @see Project
 */
public class Problem {
    private Student[] students;
    private Teacher[] teachers;
    private Project[] projects;
    private int nrStudents, nrTeachers, nrProjects;

    /**
     * În constructor se alocă spațiu pentru vectorii necesari.
     *
     */
    public Problem() {
        this.students = new Student[100];
        this.teachers = new Teacher[100];
        this.projects = new Project[100];
        this.nrStudents = 0;
        this.nrTeachers = 0;
        this.nrProjects = 0;
    }

    /**
     * În metoda addStudent se adaugă un student.
     *
     * @param student student
     */
    public void addStudent(Student student) {
        if (nrStudents >= students.length) {
            System.out.println("Cannot add more students!");
            return;
        }
        for (int i = 0; i < nrStudents; i++) {
            if (students[i].equals(student)) {
                System.out.println("Student already exists!");
                return;
            }
        }
        students[nrStudents++] = student;
    }

    /**
     * În metoda addTeacher se adaugă un profesor.
     *
     * @param teacher profesor
     */
    public void addTeacher(Teacher teacher) {
        if (nrTeachers >= teachers.length) {
            System.out.println("Cannot add more teachers!");
            return;
        }
        for (int i = 0; i < nrTeachers; i++) {
            if (teachers[i].equals(teacher)) {
                System.out.println("Teacher already exists!");
                return;
            }
        }
        teachers[nrTeachers++] = teacher;
    }

    /**
     * În metoda addProject se adaugă un proiect.
     *
     * @param project proiect
     */
    public void addProject(Project project) {
        if (nrProjects >= projects.length) {
            System.out.println("Cannot add more projects!");
            return;
        }
        for (int i = 0; i < nrProjects; i++) {
            if (projects[i].equals(project)) {
                System.out.println("Project already exists!");
                return;
            }
        }
        projects[nrProjects++] = project;
    }

    public Student[] getStudents(){
        return this.students;
    }

    public Project[] getProjects(){
        return this.projects;
    }

    public Teacher[] getTeachers(){
        return this.teachers;
    }

    /**
     * Prin metoda getAllPersons se returnează toate persoanele implicate în problemă.
     *
     * @return persons
     */
    public Person[] getAllPersons() {
        Person[] persons = new Person[nrStudents + nrTeachers];
        int nrPersons = 0;

        for (int i = 0; i < nrStudents; i++) {
            persons[nrPersons++] = students[i];
        }
        for (int i = 0; i < nrTeachers; i++) {
            persons[nrPersons++] = teachers[i];
        }

        return persons;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Problem:\n");

        sb.append("Students are:\n");
        for (int i = 0; i < nrStudents; i++) {
            sb.append(students[i].toString()).append("\n");
        }

        sb.append("Teachers:\n");
        for (int i = 0; i < nrTeachers; i++) {
            sb.append(teachers[i].toString()).append("\n");
        }

        sb.append("Projects:\n");
        for (int i = 0; i < nrProjects; i++) {
            sb.append(projects[i].toString()).append("\n");
        }

        return sb.toString();
    }
}
