# Lezione 1

## Obiettivi 
- Creare un progetto iniziale che funga da base per le successive lezioni.

## Descrizione 
Creare un progetto in Java, con un IDE a piacere (si suggerisce Eclipse o IntelliJ) che rappresenti il seguente dominio.  
Ogni dettaglio non esplicitato è di libera interpretazione.  
I dati gestiti possono essere letti da input o direttamente hard-codati (per il momento), alias scritti direttamente nel sorgente.

## Dominio 
Si è interessati a rappresentare le informazioni riguardanti la gestione di uno zoo.  
All'interno dello zoo, in questa versione iniziale, sono presenti 3 specie di animali:
- leoni
- tigri
- aquile 

Si è interessati a memorizzare, per ogni specie:
- nome (nickname, un nome proprio dell'esemplare)
- cibo preferito
- età
- data in cui è entrato a far parte dello zoo
- peso
- altezza

Inoltre, per leoni e tigri, memorizzare la lunghezza della coda; per le aquile, l'apertura alare.


## Operazioni richieste 

Deve essere possibile effettuare le seguenti interrogazioni al sistema: 
- per ogni specie, ricerca dell'esemplare più alto e più basso
- per ogni specie, ricerca dell'esemplare più pesante e più leggero
- per gli animali dotati di coda, l'esemplare con la coda più lunga di tutto lo zoo nel complesso
- per gli animali dotati di ali, l'esemplare con la maggiore apertura alare
