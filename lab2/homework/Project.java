/**
 * Project este folosit pentru a memora datele despre un proiect.
 *
 * @see Person
 */
public class Project {
    private String name;
    private Type type;

    /**
     * În constructor se atribuie numele și tipul pentru un proiect.
     *
     * @param name numele proiectului
     * @param type tipul proiectului
     */
    public Project(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project project = (Project) obj;
        return name.equals(project.name) && type == project.type;
    }

    @Override
    public String toString() {
        return "Project " + name + " is of type " + type + ".";
    }
}