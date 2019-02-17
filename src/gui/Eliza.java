package gui;

import game.HangmanApp;
import oop.Response;
import util.FileOperationOnList;
import util.Randomize;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Eliza extends JFrame {

    private String oldMsg;
    private String newline;
    private List<String> history;
    private Response response;
    private Randomize random;
    private HangmanApp hangman;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonGroup buttonGroup1;
    private JRadioButton capitalRadioButton;
    private JRadioButton gameRadioButton;
    private JPanel inputPanel;
    private JLabel label;
    private JPanel northPanel;
    private JRadioButton pigRadioButton;
    private JRadioButton redRadioButton;
    private JButton sendButton;
    private JPanel southPanel;
    private JTextField textField;
    private JTextPane textPane;
    private JScrollPane textPaneScrollPane;
    // End of variables declaration//GEN-END:variables

    public Eliza() {
        oldMsg = "";
        newline = "<br/>";
        response = new Response();
        random = new Randomize();
        hangman = new HangmanApp(newline);
        initComponents();
        Toolkit theKit = getToolkit();
        Dimension wndSize = theKit.getScreenSize();
        setSize(wndSize.width / 2, wndSize.height / 2);
        setLocationRelativeTo(null);
        appendToPane(textPane, welcome());
        process();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Eliza app = new Eliza();
            app.setVisible(true);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new ButtonGroup();
        inputPanel = new JPanel();
        southPanel = new JPanel();
        pigRadioButton = new JRadioButton();
        capitalRadioButton = new JRadioButton();
        redRadioButton = new JRadioButton();
        gameRadioButton = new JRadioButton();
        northPanel = new JPanel();
        textField = new JTextField();
        sendButton = new JButton();
        label = new JLabel();
        textPaneScrollPane = new JScrollPane();
        textPane = new JTextPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        inputPanel.setLayout(new BorderLayout());

        buttonGroup1.add(pigRadioButton);
        pigRadioButton.setText("Pig Latin");
        pigRadioButton.addItemListener(evt -> pigRadioButtonItemStateChanged(evt));
        southPanel.add(pigRadioButton);

        buttonGroup1.add(capitalRadioButton);
        capitalRadioButton.setText("Capital");
        capitalRadioButton.addItemListener(evt -> capitalRadioButtonItemStateChanged(evt));
        southPanel.add(capitalRadioButton);

        buttonGroup1.add(redRadioButton);
        redRadioButton.setText("Red");
        redRadioButton.addItemListener(evt -> redRadioButtonItemStateChanged(evt));
        southPanel.add(redRadioButton);

        buttonGroup1.add(gameRadioButton);
        gameRadioButton.setText("Game");
        gameRadioButton.addItemListener(evt -> gameRadioButtonItemStateChanged(evt));
        southPanel.add(gameRadioButton);

        inputPanel.add(southPanel, BorderLayout.SOUTH);

        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.LINE_AXIS));

        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });
        northPanel.add(textField);

        sendButton.setText("Send");
        sendButton.addActionListener(evt -> sendButtonActionPerformed(evt));
        northPanel.add(sendButton);

        inputPanel.add(northPanel, BorderLayout.CENTER);

        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        label.setFont(new Font("Tahoma", Font.BOLD, 18)); // NOI18N
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText("ELIZA THERAPY SOFTWARE");
        getContentPane().add(label, BorderLayout.NORTH);

        textPane.setEditable(false);
        textPane.setContentType("text/html"); // NOI18N
        textPaneScrollPane.setViewportView(textPane);

        getContentPane().add(textPaneScrollPane, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldKeyPressed(KeyEvent evt) {//GEN-FIRST:event_textFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }

        // Get last message typed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            String currentMessage = textField.getText().trim();
            textField.setText(oldMsg);
            oldMsg = currentMessage;
        }

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            String currentMessage = textField.getText().trim();
            textField.setText(oldMsg);
            oldMsg = currentMessage;
        }
    }//GEN-LAST:event_textFieldKeyPressed

    private void sendButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendMessage();
    }//GEN-LAST:event_sendButtonActionPerformed

    private void capitalRadioButtonItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_capitalRadioButtonItemStateChanged
        if (capitalRadioButton.isSelected()) {
            response.setCap(true);
        } else {
            response.setCap(false);
        }
    }//GEN-LAST:event_capitalRadioButtonItemStateChanged

    private void pigRadioButtonItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_pigRadioButtonItemStateChanged
        if (pigRadioButton.isSelected()) {
            response.setPig(true);
        } else {
            response.setPig(false);
        }
    }//GEN-LAST:event_pigRadioButtonItemStateChanged

    private void gameRadioButtonItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_gameRadioButtonItemStateChanged
        if (gameRadioButton.isSelected()) {
            hangman = new HangmanApp(newline);
            hangman.setStart(true);
            print(hangman.getGameStatus(""));
        }
    }//GEN-LAST:event_gameRadioButtonItemStateChanged

    private void redRadioButtonItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_redRadioButtonItemStateChanged
        if (redRadioButton.isSelected()) {
            response.setRed(true);
        } else {
            response.setRed(false);
        }
    }//GEN-LAST:event_redRadioButtonItemStateChanged

    private void formWindowClosing(WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JOptionPane.showMessageDialog(this, exit());
    }//GEN-LAST:event_formWindowClosing

    public String welcome() {
        history = new ArrayList<>();
        FileOperationOnList fo = new FileOperationOnList(history, "history.html");
        try {
            fo.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        history = fo.getDocument();
        String item = "";
        do {
            int index = random.getRandomNumber(0, history.size());
            if (index != 0) {
                item = history.get(index);
            } else {
                break;
            }
        } while (item.equals("") || item.equals("$"));

        history.clear();
        return input("Welcome to Eliza<br />"
                + "Good day , I remember, Last time you were talking about " + item);

    }

    public void process() {
        print("What is your problem? Enter your response here or exit the chat: ");
    }

    public String exit() {
        String exit = input("Thank you for using Eliza");
        FileOperationOnList fo = new FileOperationOnList(history, "history.html");
        try {
            fo.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exit;
    }

    //output
    public void print(String str) {
        appendToPane(textPane, str);
        history.add(str);
    }

    public void println(String str) {
        appendToPane(textPane, str + newline);
        history.add(str + "\n");
    }

    //input
    public String input(String str) {
        history.add(str + "\n");
        return str;
    }

    // sending messages
    public void sendMessage() {
        String message = textField.getText().toLowerCase().trim();
        if (message.equals("")) {
            return;
        }
        this.oldMsg = message;
        textField.requestFocus();
        textField.setText("");
        appendToPane(textPane, input(message + "</br>"));

        if (gameRadioButton.isSelected()) {
            println(hangman.getGameStatus(message));
            if (hangman.isStop()) {//if game won or loose then clear selection
                buttonGroup1.clearSelection();
            }
        } else {
            println(response.getAnswer(message));
        }
        System.out.println(response.getBooleanStatus() + hangman.getBooleanStatus());
    }

    public void appendToPane(JTextPane tp, String message) {
        HTMLDocument doc = (HTMLDocument) tp.getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit) tp.getEditorKit();
        try {
            editorKit.insertHTML(doc, doc.getLength(), message, 0, 0, null);
            tp.setCaretPosition(doc.getLength());
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        }
    }
}
