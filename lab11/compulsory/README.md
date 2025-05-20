Programul conține fișierul application.properties prin care se setează configurațiile necesare pentru accesarea bazei de date, dar și portul la care ascultă serverul. 

Clasele Continent, Country și City definesc trei entități asociate celor trei tabele existente în baza de date. 

Interfața CountryRepository extinde JpaRepository, o interfață specifică librăriei Spring, care conține metode ce interacționează cu baza de date. 

Clasa CountryController gestionează cererile HTTP pentru ruta /countries. În constructor injectează un obiect de tipul CountryRepository, pentru a se lega de baza de date, iar în metoda getAllCountries răspunde la cereri de tipul GET, returnând toate țările din baza de date. 

În clasa de bază Main se apelează metoda run din cadrul clasei predefinite SpringApplication, pentru a porni aplicația.