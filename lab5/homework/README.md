Programul conține clasa record Image, prin care pot fi definite imagini prin nume, dată, taguri și locația din memorie.

În clasa Repository se inițializează o listă de imagini. Clasa conține metoda addImage, prin care se adaugă o imagine în listă, metoda getImages, prin care se returnează lista imaginilor, și metoda displayImage, prin care se afișează o imagine. 

Clasele ImageNotFound și InvalidCommand extind clasa Exception, pentru a crea mesaje mai specifice de posibile erori.

Interfața Command conține o singură metodă, execute, care primește ca parametri un repo și un șir de argumente și poate arunca excepții.

Interfața este implementată de clasele AddCommand, RemoveCommand, UpdateCommand, SaveCommand, LoadCommand și ReportCommand, care suprascriu metoda execute, cu scopuri diferite: adăugarea sau eliminarea unei imagini din repo, actualizarea informațiilor unei imagini, salvarea imaginilor din repo într-un json sau încărcarea conținutului dintr-un json în repo și crearea unui fișier html bazat pe conținutul repo-ului.

În clasa de bază Main este declarat un repo și un HashMap pentru comenzi. Apoi se citesc comenzi de la linia de comandă și se lansează executarea lor, după separarea numelui comenzii de restul argumentelor.




