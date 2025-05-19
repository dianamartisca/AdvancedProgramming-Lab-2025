Programul conține clasa HexPlayer, care reprezintă un jucător prin nume, culoare și timpul pe care îl are la dispoziție pentru a juca.

Clasa HexGame implementează logica jocului, declarând o tablă de o anumită dimensiune și un array de jucători. Jucătorii sunt adăugați prin metoda addPlayer. Prin metoda submitMove au loc mutările, dacă acestea sunt valide și jocul încă este în desfășurare. Metoda checkWin verifică dacă un jucător a câștigat, apelând un dfs pe piesele de aceeași culoare. Metoda getBoardString returnează tabla pentru ca jucătorii să pooată vizualiza.

Clasa GameManager permite declararea mai multor jocuri paralele, fiecare având un Id și un obiect de tipul HexGame asociat. 

Clasa ClientThread se conectează la un client prin atributul clientSocket și interacționează cu serverul prin atributul server de tip GameServer. Prin suprascrierea metodei run sunt citite mesaje de la client și apoi afișate răspunsuri adecvate. La comanda create se creează un joc nou, la comanda join se creează un jucător, care se alătură unui joc deja creat, iar la comanda move se pune o piesă nouă pe tablă. Mesajul "stop" determină oprirea serverului, iar mesajul "exit" deconectarea clientului.

Clasa GameServer declară atributul port, la care ascultă serverul, un serverSocket la care se pot conecta clienții și un obiect de tipul GameManager. Metoda start inițializează socket-ul și, printr-un while, așteaptă conexiuni de la clienți. Pentru fiecare conexiune inițializează un ClientThread. Metoda stop oprește serverul, iar metoda isRunnning este utilizată pentru a determina dacă un server mai e pornit.

În cadrul metodei main este setat portul și se creează un obiect de tipul GameServer, apelându-se metode start din cadrul acestuia.