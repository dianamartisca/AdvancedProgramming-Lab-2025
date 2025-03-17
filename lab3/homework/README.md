Programul conține clasele Aircraft, Airliner, Freighter, Drone, Flight, Airport și SchedulingProblem și interfețele PassengerCapable și CargoCapable (Airliner, Freighter și Drone extind clasa Aircraft, Airliner implementează interfața PassengerCapable, iar Freighter implementează interfața CargoCapable). 

În clasa Drone există doi constructori, pentru a permite inițializarea atât a dronelor care transportă obiecte, cât și a celor care sunt pentru supraveghere. 

În clasa Flight există un constructor care inițializează un zbor prin ID, aeronavă și timpul de aterizare. Avem metodele setRunway() și getRunway() pentru a seta și obține pista de aterizare a zborului și metoda conflictsWith() pentru a verifica dacă două zboruri sunt în conflict sau nu. 

În clasa Airport există un constructor care inițializează o listă de piste de aterizare pentru un aeroport.

În clasa SchedulingProblem există un constructor care inițializează un aeroport și un set de zboruri. Metoda scheduleFlights() setează câte o pistă de aterizare pentru fiecare zbor, având grijă să nu existe conflicte. 

În clasa Main() se creează un aeroport și un obiect de tipul SchedulingProblem și se adaugă mai multe zboruri pentru a testa algoritmul.
