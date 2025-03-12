/**
 * Person este folosit pentru a memora datele despre o persoană (student sau profesor).
 *
 * @see Project
 */
public abstract class Person {
    protected String name;
    protected String dateOfBirth;

    /**
     * În constructor se atribuie numele și data nașterii pentru o persoană.
     *
     * @param name numele persoanei
     * @param dateOfBirth data nașterii persoanei
     */
    public Person(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return name.equals(person.name) && dateOfBirth.equals(person.dateOfBirth);
    }

    @Override
    public String toString() {
        return name + " is born on " + dateOfBirth + ".";
    }
}
