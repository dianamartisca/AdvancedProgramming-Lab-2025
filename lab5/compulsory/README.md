Programul conține clasa record Image, prin care pot fi definite imagini prin nume, dată, taguri și locația din memorie.

În clasa Repository se inițializează o listă de imagini. Clasa conține metoda addImage prin care se adaugă o imagine în listă și metoda displayImage prin care se afișează o imagine. Aceasta este căutată în listă după nume, apoi, prin declararea unui obiect de tip File, aceasta este căutată în memorie după locația ei. Se utilizează un try-catch pentru a verifica dacă imaginea poate fi deschisă folosind clasa Desktop. În caz contrar, se aruncă o excepție folosind clasa IOException. 

În clasa de bază Main, se declară un repository în care se adaugă două imagini. La final se încearcă afișarea uneia dintre ele. 

