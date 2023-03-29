Lezione 2: Pawtropolis
Obiettivi
Realizzare una prima versione di base, giocabile, di un'avventura testuale ambientata a Pawtropolis, lo zoo modellato nella lezione precedente.
Descrizione
Nella precedente lezione abbiamo gettato le basi del dominio di uno zoo, senza però nessuna funzionalità rilevante, se non quelle di aggiunta e ricerca di animali, per il momento fini a sé stesse.
In questa lezione useremo gli elementi presenti, insieme ad altri che introdurremo per l'occasione, per realizzare una prima versione di base giocabile di un'avventura testuale.
Nella fattispecie, il giocatore potrà:

interagire col sistema tramite comandi testuali e riceverà feedback da esso nello stesso modo;
spostarsi tra le stanze di Pawtropolis;
raccogliere e lasciare degli oggetti nelle stanze;
Come sempre, tutto ciò che non viene richiesto esplicitamente è a libera interpretazione.
Unica accortezza, l'uso dell'inglese in tutto il codice (al massimo i commenti in italiano).

Codice di base
Viene fornita un'implementazione iniziale da cui partire.
In particolare, sono implementati tutti i requisiti delle precedenti lezioni ed è presente lo scheletro delle nuove funzionalità da aggiungere.

N.B.: il codice fornito costituisce una linea guida, uno spunto di riflessione, NON è inteso per essere copiato ed usato al posto del proprio.
Il codice che andrà sviluppato deve avere le funzionalità richieste ma non deve essere realizzato necessariamente nello stesso modo di quello qui allegato.

Vi invito pertanto a "fare vostri" i concetti illustrati nel codice fornito ma di non sostituirlo al vostro, fate piuttosto crescere ed evolvere quello che avete già realizzato ;)

Requisiti
È richiesto di introdurre il concetto di giocatore, di itemStored e di borsa per itemStored.
Inoltre, per potersi spostare nella mappa di Pawtropolis è necessario modellare il concetto di stanza.
Una stanza ha delle stanze adiacenti collegate tramite le sue porte e dentro ogni stanza possono essere presenti NPC (animali) e oggetti.

Ogni classe introdotta dovrà fornire i metodi di base necessari all'interazione con essa (getter e setter) e quelli richiesti all'espletazione dei requisiti in oggetto.

Dominio
Si richiede di implementare le seguenti classi di dominio con i relativi attributi:

Player
nome
punti-vita
Bag
elenco di itemStored
slot totali a disposizione (valore immutabile, rappresenta la dimensione della borsa)
Item
nome
descrizione
slot richiesti
Room
nome
elenco itemStored presenti
elenco animali presenti
elenco stanze adiacenti [vedi codice esempio] (massimo 4, identificate con "nord", "sud", "est", "ovest")
PS: il termine "elenco" è volutamente vago e improprio, scegliere l'implementazione che si ritiene più adatta.

Esempio:
Dando il comando go nord, chiedo alla stanza corrente in cui mi trovo di darmi la stanza che ha a nord (getRoom("nord")).
Se tale stanza non è presente, informo il giocatore e non faccio nulla, altrimenti cambio la stanza corrente con quella ottenuta e ne stampo il contenuto (vedi comando go di seguito).

Operazioni richieste
Il giocatore deve potersi muovere nella mappa tramite comandi testuali del tipo:

go <direzione>
look
bag
get <itemStored>
drop <itemStored>
Comandi
go
Cambia la stanza corrente del giocatore in quella da lui scelta tramite la direzione indicata.
Se non esiste nessuna stanza in quella direzione, non cambiare stanza corrente e comunicarlo al giocatore.
Es.:
go ovest
Output: descrizione della nuova stanza (nome, contenuto, NPC presenti)
Es.:

>go ovest

You are in Room621.
Items: ball, collar, chair  
NPC: Asdrubale(Tiger), Gianpiergiacomo(Lion)
look
Ripete la descrizione della stanza corrente, con lo stesso formato e informazioni del comando precedente.

bag
Elenca il contenuto della borsa del giocatore.
Es.:
bag
Output: elenco degli itemStored contenuti nella borsa
Es.:

>bag

In bag: ball, collar
get
Aggiunge alla borsa del giocatore l'itemStored specificato come parametro (nome dell'itemStored) e lo rimuove dalla stanza.
Se non è presente nella stanza, comunicarlo al giocatore e non fare nulla.
Se nella borsa non restano slot sufficienti, comunicarlo al giocatore e non fare nulla.
Es.:
get ball
Output: niente

drop
Aggiunge alla stanza corrente l'itemStored specificato come parametro (nome dell'itemStored) e lo rimuove dalla borsa del giocatore.
Se non è presente nella borsa, comunicarlo al giocatore e non fare nulla.
Es.:
drop collar
Output: niente
