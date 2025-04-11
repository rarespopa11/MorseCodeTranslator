package morse;

import com.formdev.flatlaf.FlatDarkLaf;
//import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class MorseCodeTranslatorGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton translateButton;
    private JButton playButton;
    private JComboBox<String> directionComboBox;
    
    private MorseCodeTranslator translator = new MorseCodeTranslator();
    private MorseSoundPlayer soundPlayer = new MorseSoundPlayer();

    // Constructorul principal pentru interfata grafica
    public MorseCodeTranslatorGUI() {
        // Seteaza aspectul interfetei folosind FlatLaf (tema dark)
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf()); // Sau FlatLightLaf pentru un stil mai luminos
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace(); // Daca nu se poate aplica tema, afiseaza eroarea
        }

        // Configurarea ferestrei aplicatiei
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setResizable(false); // Fereastra nu poate fi redimensionata

        // Crearea panoului principal folosind BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Configurarea panoului pentru intrare (input area)
        JPanel inputPanel = createPanel("Intrare", new Color(33, 33, 33));
        inputTextArea = new JTextArea(5, 30);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        inputTextArea.setBackground(new Color(50, 50, 50));
        inputTextArea.setForeground(Color.WHITE);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputPanel.add(inputScrollPane);

        // Configurarea panoului pentru optiuni
        JPanel optionsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Optiuni"));
        optionsPanel.setBackground(new Color(40, 40, 40));

        directionComboBox = new JComboBox<>(new String[]{"Text → Morse", "Morse → Text", "Auto-detectare"});
        directionComboBox.setFont(new Font("Arial", Font.PLAIN, 14)); 
        directionComboBox.setBackground(new Color(60, 60, 60));
        directionComboBox.setForeground(Color.WHITE);
        optionsPanel.add(directionComboBox);

        // Configurarea panoului pentru butoane - Traducere si Redare
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(new Color(40, 40, 40));

        translateButton = createButton("Traducere", new Color(33, 150, 243), new Color(33, 81, 243)); 
        buttonPanel.add(translateButton);

        playButton = createButton("Reda Sunetul", new Color(76, 175, 80), new Color(100, 230, 118)); 
        buttonPanel.add(playButton);

        optionsPanel.add(buttonPanel); // Adaugam panoul cu butoane la panoul principal

        // Configurarea panoului pentru iesire (rezultate)
        JPanel outputPanel = createPanel("Rezultat", new Color(33, 33, 33)); 
        outputTextArea = new JTextArea(5, 30);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputTextArea.setBackground(new Color(50, 50, 50));
        outputTextArea.setForeground(Color.WHITE); // Culoare text
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputPanel.add(outputScrollPane);

        // Adaugam panoul principal la fereastra
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);        

        configureEventListeners();
    }

    // Metoda pentru a crea un panou cu un titlu si culoare personalizata
    private JPanel createPanel(String title, Color backgroundColor) {
        JPanel panel = new JPanel(new BorderLayout(5, 5)); 
        panel.setBackground(backgroundColor); 
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), title, TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        return panel;
    }

    // Metoda pentru a crea un buton cu efecte de hover
    private JButton createButton(String text, Color normalColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(80, 30));
        button.setMinimumSize(new Dimension(80, 30)); 
        button.setMaximumSize(new Dimension(80, 30));
        button.setBackground(normalColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecte de hover pentru buton
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor); // Cand mouse-ul intra pe buton, schimba culoarea
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(normalColor); // Cand mouse-ul paraseste butonul, revine la culoarea normala
            }
        });

        return button;
    }

    // Configureaza evenimentele pentru butoane
    private void configureEventListeners() {
        translateButton.addActionListener(e -> translate());
        playButton.addActionListener(e -> playMorseSound());
    }

    // Metoda pentru a traduce textul din input
    private void translate() {
        String input = inputTextArea.getText().trim(); // Preia textul din zona de input
        if (input.isEmpty()) return; // Daca inputul este gol, nu face nimic

        String result;
        int direction = directionComboBox.getSelectedIndex(); // Obține directia aleasa (Text→Morse, Morse→Text, Auto-detectare)

        if (direction == 0) {
            // Text → Morse
            result = translator.textToMorse(input);
        } else if (direction == 1) {
            // Morse → Text
            result = translator.morseToText(input);
        } else {
            // Auto-detectare
            if (isMorseCode(input)) {
                result = translator.morseToText(input);
            } else {
                result = translator.textToMorse(input);
            }
        }

        outputTextArea.setText(result); // Afiseaza rezultatul in zona de output
    }

    // Metoda pentru a verifica daca un input este cod Morse
    private boolean isMorseCode(String input) {
        return input.matches("^[.\\-/ ]+$"); // Verifica daca inputul contine doar puncte, linii si spatii (caractere valide in Morse)
    }

    // Metoda pentru a reda sunetul codului Morse
    private void playMorseSound() {
        String morseCode;
        if (isMorseCode(inputTextArea.getText())) {
            // Daca inputul este deja cod Morse, folosim direct acest text
            morseCode = inputTextArea.getText();
        } else if (isMorseCode(outputTextArea.getText())) {
            // Daca rezultatul este cod Morse, folosim acest text
            morseCode = outputTextArea.getText();
        } else {
            // Daca niciunul dintre ele nu este cod Morse, generam un cod Morse din textul de intrare
            morseCode = translator.textToMorse(inputTextArea.getText());
            outputTextArea.setText(morseCode);
        }

        if (morseCode.isEmpty()) return;

        // Dezactiveaza butoanele in timpul redarii
        translateButton.setEnabled(false);
        playButton.setEnabled(false);

        // Reda sunetul Morse intr-un thread de fundal
        new Thread(() -> {
            try {
                soundPlayer.playMorseCodeSound(morseCode); // Reda sunetul Morse
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Eroare la redarea sunetului: " + ex.getMessage());
            } finally {
                // Reactiveaza butoanele dupa redare
                SwingUtilities.invokeLater(() -> {
                    translateButton.setEnabled(true);
                    playButton.setEnabled(true);
                });
            }
        }).start();
    }

    // Functia main care ruleaza aplicatia
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MorseCodeTranslatorGUI gui = new MorseCodeTranslatorGUI();
            gui.setVisible(true); // Afiseaza interfata grafica
        });
    }
}
