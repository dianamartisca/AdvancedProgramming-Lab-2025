/**
 * Clasa Main în care se testează funcționalitățile celorlalte clase.
 *
 * @see Problem
 * @see Project
 * @see Student
 * @see Teacher
 * @see Solution
 */
public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem();

        Project project1 = new Project("AI Chatbot", Type.practical);
        Project project2 = new Project("Software Engineering", Type.theoretical);
        Project project3 = new Project("Database", Type.theoretical);
        Project project4 = new Project("TCP Server", Type.practical);

        Student student1 = new Student("Andrei", "23.02.2003", "344");
        Student student2 = new Student("Nicoleta", "05.04.2004", "677");
        Student student3 = new Student("Maria", "08.09.2004", "877");
        Student student4 = new Student("Mircea", "02.06.2004", "987");

        Teacher teacher1 = new Teacher("Popescu", "20.02.1980");
        Teacher teacher2 = new Teacher("Ionescu", "05.09.1990");

        student1.addProject(project2);

        student2.addProject(project1);
        student2.addProject(project3);

        student3.addProject(project2);
        student3.addProject(project4);
        student3.addProject(project1);

        student4.addProject(project3);
        student4.addProject(project4);

        teacher1.addProject(project4);
        teacher1.addProject(project2);

        teacher2.addProject(project3);
        teacher2.addProject(project1);

        problem.addStudent(student1);
        problem.addStudent(student2);
        problem.addStudent(student3);
        problem.addStudent(student4);
        problem.addProject(project1);
        problem.addProject(project2);
        problem.addProject(project3);
        problem.addProject(project4);
        problem.addTeacher(teacher1);
        problem.addTeacher(teacher2);

        System.out.println(problem.toString());

        Solution solution = new Solution(problem);
        solution.assignProjects();
        System.out.println(solution);
    }
}
