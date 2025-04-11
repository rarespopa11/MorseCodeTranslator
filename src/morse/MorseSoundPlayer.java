package morse;

import javax.sound.sampled.*;

public class MorseSoundPlayer {

    // Durata unui punct in codul Morse in milisecunde
    private static final int DOT_DURATION = 100;

    // Durata unei linii in codul Morse(de 3 ori mai mare decat durata unui punct
    private static final int DASH_DURATION = DOT_DURATION * 3;

    // Pauza dintre elementele codului Morse (punct sau linie)
    private static final int ELEMENT_GAP = DOT_DURATION;

    // Pauza dintre caracterele dintr-un cuvant
    private static final int LETTER_GAP = DOT_DURATION * 3;

    // Pauza dintre cuvintele in codul Morse
    // Se aplica intre cuvinte, semnificand o pauza mai lunga
    private static final int WORD_GAP = DOT_DURATION * 7;

    // Frecventa sunetului care va fi redat, 800Hz este frecventa tipica pentru sunetele Morse
    private static final float FREQUENCY = 800.0f;

    // Metoda care reda sunetul pentru codul Morse
    // Parcurge fiecare caracter din codul Morse si reda sunetul corespunzator
    public void playMorseCodeSound(String morseCode) throws LineUnavailableException, InterruptedException {

        // Definim formatul audio pentru redare
        // 44100Hz este rata standard pentru sunetul de inalta calitate
        // 8 biti, mono (1 canal)
        AudioFormat af = new AudioFormat(44100, 8, 1, true, false);

        // Cream linia de iesire audio
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        
        // Deschidem linia audio cu buffer-ul de 4096 octeti
        line.open(af, 4096);

        // Incepem redarea sunetului
        line.start();

        // Parcurgem fiecare caracter din codul Morse
        for (int i = 0; i < morseCode.length(); i++) {
            char c = morseCode.charAt(i); // Extragem caracterul de la pozitia curenta in codul Morse
            
            // In functie de caracterul din codul Morse, redam un sunet corespunzator
            switch (c) {
                // Daca este un punct (.), redam un ton scurt
                case '.':
                    beep(line, DOT_DURATION); // Sunetul punctului
                    Thread.sleep(ELEMENT_GAP); // Pauza intre elemente
                    break;
                
                // Daca este o linie (-), redam un ton mai lung
                case '-':
                    beep(line, DASH_DURATION); // Sunetul liniei
                    Thread.sleep(ELEMENT_GAP); // Pauza intre elemente
                    break;
                
                case ' ':
                    Thread.sleep(LETTER_GAP); // Pauza intre caracterele aceluiasi cuvant
                    break;
                
                // Daca este un '/', acesta reprezinta separarea cuvintelor
                case '/':
                    Thread.sleep(WORD_GAP); // Pauza intre cuvinte
                    break;
            }
        }

        line.drain();
        line.close();
    }

    // Metoda care creeaza si reda un ton pentru puncte si linii din codul Morse
    private void beep(SourceDataLine line, int duration) {
        // Cream un buffer de octeti pentru a stoca datele audio ale sunetului
        // Marimea buffer-ului depinde de durata sunetului dorit (calculata in functie de frecventa si durata)
        byte[] buf = new byte[duration * 44]; // 44 pentru a reprezenta un singur "puls" audio la 44100 Hz
        
        // Generam semnalul audio 
        for (int i = 0; i < buf.length; i++) {
            // Calculam un unghi pentru semnalul sinusoidal pe baza frecventei si timpului
            // Unghiul este folosit pentru a genera forma undei sinusoidale
            double angle = i / (44100.0 / FREQUENCY) * 2.0 * Math.PI;
            // Sinusoida este scalata pentru a obtine amplitudinea semnalului audio dorita
            buf[i] = (byte) (Math.sin(angle) * 80); // Calculam valoarea semnalului pentru aceasta mostra
        }
        
        // Redam semnalul audio la linia de iesire
        line.write(buf, 0, buf.length);
    }
}
