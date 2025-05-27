Programul conține fișierul application.properties prin care se setează portul la care va fi disponibilă aplicația. 

Clasa RestTemplateConfig expune un bean de tipul RestTemplate, care permite altor componente din aplicație să facă cereri HTTP către alte servere.

Clasa ApiClientController are rolul de a se comporta ca un client, ce redirecționează requesturile primite către server, folosind un obiect de tipul RestTemplate. În constructor se injectează un RestTemplate și un șir ce reprezintă URL-ul serverului. Metodele ce urmează reprezintă aceleași endpointuri ca cele din server, pe care le apelează corespunzător.

În clasa de bază Main se apelează metoda run din cadrul clasei predefinite SpringApplication, pentru a porni aplicația.