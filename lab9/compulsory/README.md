Programul conține fișierul persistence.xml prin care se creează un persistence unit și se specifică entitățile existente și configurațiile necesare pentru accesarea bazei de date.

Clasele Continent și Country definesc două entități asociate celor două tabele existente în baza de date. În cadrul acestora se declară și un NamedQuery pentru a realiza o căutare după nume în tabele.

Clasa EntityManagerFactorySingleton creează un obiect static de tipul EntityManagerFactory asociat cu persistence unit-ul declarat în fișierul xml. Prin această clasă se asigură existența unei singure instanțe de tipul EntityManagerFactory. 

Clasele ContinentRepository și CountryRepository conțin metode care interacționează cu baza de date prin instanța EntityManagerFactorySingleton. Metoda create inserează în baza de date, iar metodele findById și findByName returneză entități după id și nume. 

În clasa de bază Main se declară două obiecte de tipul ContinentRepository și CountryRepository, iar apoi se testează metodele create și find din cadrul acestor clase.