package morse;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeTranslator {
    // Mapa care leaga fiecare caracter de text la codul sau Morse
    private static final Map<Character, String> TEXT_TO_MORSE = new HashMap<>();
    
    // Mapa inversa care leaga fiecare cod Morse de caracterul sau
    private static final Map<String, Character> MORSE_TO_TEXT = new HashMap<>();
    
    // Bloc static care initializeaza maparile intre caractere si coduri Morse
    static {
        // Initializare pentru literele alfabetului
        TEXT_TO_MORSE.put('A', ".-");
        TEXT_TO_MORSE.put('B', "-...");
        TEXT_TO_MORSE.put('C', "-.-.");
        TEXT_TO_MORSE.put('D', "-..");
        TEXT_TO_MORSE.put('E', ".");
        TEXT_TO_MORSE.put('F', "..-.");
        TEXT_TO_MORSE.put('G', "--.");
        TEXT_TO_MORSE.put('H', "....");
        TEXT_TO_MORSE.put('I', "..");
        TEXT_TO_MORSE.put('J', ".---");
        TEXT_TO_MORSE.put('K', "-.-");
        TEXT_TO_MORSE.put('L', ".-..");
        TEXT_TO_MORSE.put('M', "--");
        TEXT_TO_MORSE.put('N', "-.");
        TEXT_TO_MORSE.put('O', "---");
        TEXT_TO_MORSE.put('P', ".--.");
        TEXT_TO_MORSE.put('Q', "--.-");
        TEXT_TO_MORSE.put('R', ".-.");
        TEXT_TO_MORSE.put('S', "...");
        TEXT_TO_MORSE.put('T', "-");
        TEXT_TO_MORSE.put('U', "..-");
        TEXT_TO_MORSE.put('V', "...-");
        TEXT_TO_MORSE.put('W', ".--");
        TEXT_TO_MORSE.put('X', "-..-");
        TEXT_TO_MORSE.put('Y', "-.--");
        TEXT_TO_MORSE.put('Z', "--..");

        // Initializare pentru cifre
        TEXT_TO_MORSE.put('0', "-----");
        TEXT_TO_MORSE.put('1', ".----");
        TEXT_TO_MORSE.put('2', "..---");
        TEXT_TO_MORSE.put('3', "...--");
        TEXT_TO_MORSE.put('4', "....-");
        TEXT_TO_MORSE.put('5', ".....");
        TEXT_TO_MORSE.put('6', "-....");
        TEXT_TO_MORSE.put('7', "--...");
        TEXT_TO_MORSE.put('8', "---..");
        TEXT_TO_MORSE.put('9', "----.");

        // Initializare pentru semne de punctuatie comune
        TEXT_TO_MORSE.put('.', ".-.-.-");
        TEXT_TO_MORSE.put(',', "--..--");
        TEXT_TO_MORSE.put('?', "..--..");
        TEXT_TO_MORSE.put('\'', ".----.");
        TEXT_TO_MORSE.put('!', "-.-.--");
        TEXT_TO_MORSE.put('/', "-..-.");
        TEXT_TO_MORSE.put('(', "-.--.");
        TEXT_TO_MORSE.put(')', "-.--.-");
        TEXT_TO_MORSE.put('&', ".-...");
        TEXT_TO_MORSE.put(':', "---...");
        TEXT_TO_MORSE.put(';', "-.-.-.");
        TEXT_TO_MORSE.put('=', "-...-");
        TEXT_TO_MORSE.put('+', ".-.-.");
        TEXT_TO_MORSE.put('-', "-....-");
        TEXT_TO_MORSE.put('_', "..--.-");
        TEXT_TO_MORSE.put('"', ".-..-.");
        TEXT_TO_MORSE.put('$', "...-..-");
        TEXT_TO_MORSE.put('@', ".--.-.");

	    // Pentru a decodifica un cod Morse inapoi in text, avem nevoie de o mapa inversa
	    // In `TEXT_TO_MORSE`, cheia este caracterul (ex: 'A'), iar valoarea este codul Morse (ex: ".-")
	    // Aici, construim o mapa inversa (MORSE_TO_TEXT) unde cheia va fi codul Morse (ex: ".-")
	    // si valoarea va fi caracterul corespunzator (ex: 'A') pentru a permite decodificarea unui cod Morse
        for (Map.Entry<Character, String> entry : TEXT_TO_MORSE.entrySet()) {
        	 // `entry` este o intrare din mapa `TEXT_TO_MORSE`
            // `entry.getKey()` este caracterul, iar `entry.getValue()` este codul Morse asociat cu acel caracter
        	MORSE_TO_TEXT.put(entry.getValue(), entry.getKey());
        }
    }

    // Metoda care convertește textul in cod Morse
    public String textToMorse(String text) {
        if (text == null || text.isEmpty()) {
            return ""; // Daca textul este gol, returneaza un sir gol
        }

        StringBuilder morseBuilder = new StringBuilder();
        text = text.toUpperCase(); // Convertește textul la litere mari

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            
            if (character == ' ') {
                // Spatiile dintre cuvinte sunt reprezentate prin "/"
                morseBuilder.append("/ ");
            } else if (TEXT_TO_MORSE.containsKey(character)) {
                // Daca caracterul exista in mapa TEXT_TO_MORSE, adauga corespondenta Morse
                morseBuilder.append(TEXT_TO_MORSE.get(character)).append(" ");
            }
        }

        return morseBuilder.toString().trim(); // Returneaza codul Morse
    }

    // Metoda care convertește codul Morse in text
    public String morseToText(String morse) {
        if (morse == null || morse.isEmpty()) {
            return ""; // Daca codul Morse este gol, returneaza un sir gol
        }

        StringBuilder textBuilder = new StringBuilder();
        String[] words = morse.split(" / "); // Se imparte textul in cuvinte, separate de "/"
        
        for (String word : words) {
            String[] morseChars = word.split(" "); // Se imparte fiecare cuvant in caractere Morse
            for (String morseChar : morseChars) {
                if (!morseChar.isEmpty() && MORSE_TO_TEXT.containsKey(morseChar)) {
                    // Daca secventa Morse exista in mapa MORSE_TO_TEXT, adauga caracterul corespunzator
                    textBuilder.append(MORSE_TO_TEXT.get(morseChar));
                }
            }
            textBuilder.append(" "); // Adauga un spatiu intre cuvinte
        }

        return textBuilder.toString().trim(); // Returneaza textul decodat
    }
}
