Programul conține clasele Problem, Solution, Project, Student, Teacher și Person (Student și Teacher extind clasa Person). 

În clasa Problem putem adăuga studenți, profesori și proiecte, putem obține toate persoanele prin metoda getAllPersons() și putem afișa datele problemei prin suprascrierea metodei toString().

În clasa Solution putem adăuga o problemă, putem asigna un proiect fiecărui student prin metoda assignProjects() - care folosește un algoritm greedy și utilizează pentru sortare metoda bubbleSortStudents() - și putem afișa soluția prin suprascrierea metodei toString().

Clasele Person, Student. Teacher și Project suprascriu metoda equals(Object obj) pentru a putea fi utilizată la adăugarea în problemă a studenților, profesorilor și proiectelor, ca acestea să nu se repete.
