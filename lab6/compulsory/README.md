Programul conține clasele Dot și Line care permit inițializarea unui punct și a unei linii. 

Clasa ConfigPanel creează câmpul în care poate fi introdus numărul de puncte și butonul de start. Prin apăsarea butonului de start este apelată metoda startNewGame din clasa de bază MainFrame.

Clasa ControlPanel creează butoanele de Save, Load și Exit. La butonul Exit se oprește programul.

Clasa DrawingPanel conține o listă de puncte și una de linii. Prin metoda generateDots se curăță lista de puncte și apoi se adaugă în listă numărul de puncte specificate în căsuța din ConfigPanel, a căror poziție este generată random. Metoda paintComponent curăță și resetează panoul, iar apoi desenează punctele pe tablă.

Clasa de bază MainFrame conține câte o componentă din fiecare tip: ConfigPanel, DrawingPanel și ControlPanel. În constructor se setează titlul, dimensiunea ferestrei și pozițiile celor trei componente. În metoda startNewGame se apelează metoda generateDots din clasa DrawingPanel. 