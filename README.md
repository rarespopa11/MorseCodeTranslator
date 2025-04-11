# Morse Code Translator

## Descriere
Acesta este un proiect Java pentru **traducerea între text și cod Morse**. Aplicația oferă și posibilitatea de a reda sunetul corespunzător fiecărui caracter din codul Morse, utilizând un ton sinusoidal. Codul Morse este redat printr-un ton scurt pentru puncte și un ton mai lung pentru linii, iar între ele există pauze pentru a marca spațiile dintre caractere și cuvinte.

## Caracteristici

- **Conversia textului în cod Morse**: Transformă un text normal într-un șir de caractere Morse.
- **Conversia codului Morse în text**: Permite utilizatorilor să convertească codul Morse înapoi în text.
- **Redarea audio a codului Morse**: Redă sunetele corespunzătoare fiecărui caracter Morse (puncte și linii).
- **Interfață grafică**: Interfața este simplă și ușor de utilizat, realizată cu ajutorul bibliotecii **Swing**.
- **Compatibilitate**: Funcționează pe orice platformă care suportă Java.

## Tehnologii utilizate

- **Java**: Limbajul de programare folosit pentru implementarea aplicației.
- **Swing**: Biblioteca pentru interfața grafică a utilizatorului.
- **FlatLaf**: Biblioteca pentru aspect modern al aplicației.
- **javax.sound.sampled**: Pentru generarea și redarea sunetului Morse.

## Instalare

### 1. Cerințe

 **Java 8 sau mai mare** 
 **IDE recomandat: Eclipse, IntelliJ IDEA sau NetBeans** 

### 2. Adăugarea dependenței FlatLaf
Dacă nu folosești Maven, trebuie să adaugi manual bibliotecile FlatLaf în classpath-ul proiectului tău.
Descarcă și adaugă fișierele JAR în folderul libs al proiectului.
În Eclipse, adaugă fișierele JAR în build path-ul proiectului.

### 3. Rularea aplicației
După ce ai configurat totul, poți rula aplicația din IDE-ul tău. Aplicația va deschide o fereastră în care poți introduce un text și să selectezi direcția de conversie (Text → Morse sau Morse → Text).

### 4. Utilizare
Introdu un text în câmpul de intrare. Poți scrie orice text, inclusiv litere, cifre și semne de punctuație.

Alege direcția traducerii:

Text → Morse: Dacă dorești să convertești un text într-un șir de caractere Morse.

Morse → Text: Dacă ai deja un cod Morse și dorești să-l convertești înapoi în text.

Auto-detectare: Aplicația va analiza automat textul introdus și va alege direcția corectă de conversie (Text → Morse sau Morse → Text).

Apasă butonul "Traducere": Acesta va efectua conversia textului sau a codului Morse și va afișa rezultatul în câmpul de ieșire.

Apasă butonul "Redă Sunetul": După ce ai obținut rezultatul conversiei, poți asculta sunetul corespunzător codului Morse generat. Aplicația va reda tonuri pentru fiecare punct și linie din codul Morse, respectând pauzele dintre caractere și cuvinte.

### Exemple de Utilizare
Exemplu 1: Text → Morse
Input: HELLO

Output: .... . .-.. .-.. ---

Exemplu 2: Morse → Text
Input: .... . .-.. .-.. ---

Output: HELLO

Exemplu 3: Redare Sunet Morse
Apăsând butonul "Redă Sunetul", se va reda sunetul corespunzător codului Morse pentru textul introdus. De exemplu, pentru "HELLO", vor fi redare 5 tonuri pentru literele corespunzătoare și pauze între ele.



 


