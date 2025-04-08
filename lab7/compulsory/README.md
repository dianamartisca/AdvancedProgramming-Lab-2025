Programul conține clasa Tile, în care se păstrează informațiile despre o piesă de joc, care reprezintă o literă și are un număr de puncte, și clasa Bag, care reprezintă săculețul în care se regăsesc piesele de joc. Acestea sunt memorate printr-o listă de obiecte de tip Tile. În constructor se creează săculețul, iar prin metoda extractTiles(int count) se extrag count piese. 

În clasa Board se creează o listă de cuvinte pentru a memora cuvintele adăugate pe tabla de joc, prin metoda submitWord. 

În clasa Player se declară un jucător (prin nume și Id), un săculeț și o tablă. Prin suprascrierea metodei run din clasa Thread este simulat jocul. Se utilizează Lock și Condition pentru a asigura sincronizarea threadurilor (jucătorii extrag și pun litere pe tablă pe rând). 

În clasa de bază Main se inițializează un săculeț, o tablă și doi playeri. Jocul începe pentru amândoi prin apelarea metodei start pentru fiecare în parte (firele de execuție sunt în paralel), iar la final (prin apelarea metodei join se așteaptă ca ambele fire de execuție să se încheie) se afișează cuvintele de pe tablă. 