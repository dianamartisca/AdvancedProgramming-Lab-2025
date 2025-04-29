Programul conține clasa DatabaseConnection prin care se face conexiunea cu baza de date. Aceasta conține un constructor, o metodă getInstance, prin care se obține instanța unică a clasei (este clasă Singleton), și metoda getConnection.

Clasele ContinentDTO și CountryDTO reprezintă câte un obiect de tipul continent și țară, iar clasele Continent și Country sunt clase de tip DAO ce permit conexiunea la baza de date, având metode de inserare în bază (create) și interogare a bazei (findByName, findById). 

În clasa de bază Main se creează un continent și două țări, apelându-se apoi metodele findByName și findById specifice.