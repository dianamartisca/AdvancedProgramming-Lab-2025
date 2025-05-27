Programul conține fișierul application.properties prin care se setează configurațiile necesare pentru accesarea bazei de date, dar și portul la care ascultă serverul. 

Clasele Continent, Country și City definesc trei entități asociate celor trei tabele existente în baza de date. 

Interfețele CountryRepository și CityRepository extind JpaRepository, o interfață specifică librăriei Spring, care conține metode ce interacționează cu baza de date. 

Clasa CountryController gestionează cererile HTTP pentru ruta /countries. În constructor injectează un obiect de tipul CountryRepository, pentru a se lega de baza de date, iar în metoda getAllCountries răspunde la cereri de tipul GET, returnând toate țările din baza de date. 

Clasa CityController gestionează cererile HTTP pentru ruta /cities. În constructor injectează un obiect de tipul CityRepository și unul de tipul CountryRepository, pentru a se lega de baza de date. În metoda getAllCities răspunde la cereri de tipul GET, returnând toate orașele din baza de date. În addCity răspunde la cereri de tipul POST, inserând un oraș în baza de date. În updateCityName răspunde la cereri de tipul PUT, actualizând numele unui oraș din baza de date. În deleteCity răspunde la cereri de tipul DELETE, ștergând un oraș din baza de date.


În clasa de bază Main se apelează metoda run din cadrul clasei predefinite SpringApplication, pentru a porni aplicația.