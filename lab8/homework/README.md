Programul conține clasa DatabaseConnection prin care se face conexiunea cu baza de date, utilizând biblioteca HikariCP. Clasa conține un bloc static de inițializare prin care se creează un obiect de tipul HikariDataSource, stocat în variabila statică dataSource, și metoda getDataSource.

În clasa DatabaseSetup se află metoda initializeDatabase, prin care se creează tabelul cities, și metoda importData, responsabilă de inserarea datelor din fișierul concap.csv în tabelul cities. 

Clasa City reprezintă un oraș, având un constructor și o metodă calculateDistance, prin care se calculează și returnează distanța dintre două orașe, dar și metoda getName.

În clasa de bază Main se apelează metoda de inițializare a bazei de date și cea de importare a datelor, iar apoi se apelează o metodă numită getDistanceBetweenRandomCities. În această metodă se selectează două orașe random din tabelul cities și se calculează distanța dintre ele, prin apelarea metodei calculateDistance.