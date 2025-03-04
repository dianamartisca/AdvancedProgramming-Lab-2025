public class Main {
    public static void main(String[] args) {
        Project project1 = new Project("P1", Type.theoretical);
        Project project2 = new Project("P2", Type.practical);
        Project project3 = new Project("P3", Type.practical);
        Project project4 = new Project("P4", Type.practical);

        Student student1 = new Student("Andrei");
        student1.addProject(project1);
        student1.addProject(project2);

        Student student2 = new Student("Alex");
        student2.addProject(project1);
        student2.addProject(project3);

        Student student3 = new Student("Dan");
        student3.addProject(project3);
        student3.addProject(project4);

        Student student4 = new Student("Liviu");
        student4.addProject(project1);
        student4.addProject(project4);

        System.out.println(project1.toString());
        System.out.println(project2.toString());
        System.out.println(project3.toString());
        System.out.println(project4.toString());
        System.out.println(student1.toString());
        System.out.println(student2.toString());
        System.out.println(student3.toString());
        System.out.println(student4.toString());
    }
}