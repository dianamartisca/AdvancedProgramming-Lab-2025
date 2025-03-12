/**
 * Solution este folosit pentru a asigna studenților câte un proiect, pe baza informațiilor din problemă.
 *
 * @see Student
 * @see Project
 * @see Problem
 */
public class Solution {
    private Problem problem;
    private Student[] assignedStudents;
    private Project[] assignedProjects;
    private int assignmentCount;

    /**
     * În constructor se alocă spațiu pentru vectorii necesari și se atribuie problema.
     *
     * @param problem problema
     */
    public Solution(Problem problem) {
        this.problem = problem;
        this.assignedStudents = new Student[problem.getStudents().length];
        this.assignedProjects = new Project[problem.getStudents().length];
        this.assignmentCount = 0;
    }

    /**
     * În metoda assignProjects se utilizează un algoritm greedy pentru a asigna câte un proiect fiecărui student.
     * Mai întâi se apelează metoda bubbleSortStudents, pentru a sorta studenții în funcție de numărul de proiecte preferate.
     * Apoi se realizează asignarea, verificând ca un proiect să nu fie asignat de două ori.
     *
     */
    public void assignProjects() {
        bubbleSortStudents(problem.getStudents());
        for (Student student : problem.getStudents()) {
            if (student == null)
                continue;
            for (Project project : student.getPrefProjects()) {
                if (project == null)
                    continue;
                boolean notYetAssigned = true;
                for (Project assProject : assignedProjects) {
                    if (project.equals(assProject)) {
                        notYetAssigned = false;
                        break;
                    }
                }
                if (notYetAssigned) {
                    assignedStudents[assignmentCount] = student;
                    assignedProjects[assignmentCount] = project;
                    assignmentCount++;
                    break;
                }
            }
        }
    }

    /**
     * În metoda bubbleSortStudents se sortează studenții în ordine crescătoare, în funcție de numărul de proiecte preferate.
     *
     */
    private void bubbleSortStudents(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students[j] == null || students[j + 1] == null)
                    continue;
                if (students[j].getPrefProjects().length < students[j + 1].getPrefProjects().length) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Solution:\n");
        for (int i = 0; i < assignmentCount; i++) {
            sb.append(assignedStudents[i].getName()).append(" -> ").append(assignedProjects[i].getName()).append("\n");
        }
        return sb.toString();
    }
}
