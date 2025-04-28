Aplicatia are functii de crud pentru toate functionalitatile.

In repos se extinde JPArepository, care implementeaza query-urile automat dupa numele metodei.

Controlerele au autorizare pe roluri dupa admin si user. 

Aplicatia intoarce un shareabale link la creerea unui quiz, link pe care utilizatorii il pot folosi pentru a face un attepmpt pentru un quiz.

Intrebarile sunt de 3 tipuri-  text, numar si multiple choice. 

In servicii sunt instantiate repository-urile si cu ele actionam pe baza de date. 

Pentru transferul de date in controlere folosim dto, cu scopul de a separa clasele de persistenta de cele de transfer.
