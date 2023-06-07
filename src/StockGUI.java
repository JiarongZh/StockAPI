import org.json.JSONException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Random;


public class StockGUI extends JFrame implements ActionListener{
    private JTextField Ticket;
    private JButton submitButton;
    private JButton Clear;
    private JLabel name;
    private JTextArea nameText;
    private JTextArea priceText;
    private JTextArea highText;
    private JTextArea lowText;
    private JTextArea changeText;
    private JTextArea error;
    private JPanel mainPanel;
    private JButton gamble;

    private Stock stock;

    public StockGUI(){
        createUIComponents();
    }

    private void createUIComponents(){
        setContentPane(mainPanel);
        setTitle("Stock API");
        setSize(500,450);
        setLocation(450,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        submitButton.addActionListener(this);
        Clear.addActionListener(this);
        gamble.addActionListener(this);
        setVisible(true);
    }

    private void loadStock(String name){
        try {
            stock = StockAPI.getStockInfo(name);
            nameText.setText(stock.getTicker());
            priceText.setText("$" + stock.getPrice());
            highText.setText("$" + stock.getHigh());
            lowText.setText("$" + stock.getLow());
            changeText.setText(stock.getChange() + "%");
        }catch (JSONException e){
            setClear();
            error.setText("Stock not found or does not exist in this database");
        }
    }

    private void setClear(){
        nameText.setText("");
        priceText.setText("");
        highText.setText("");
        lowText.setText("");
        changeText.setText("");
        error.setText("");
    }

    private void gamble(){
        setClear();
        boolean bool = true;
        while(bool){
            String gambol = "";
            for(int i = 0; i<4; i++) {
                Random r = new Random();
                char c = (char) (r.nextInt(26) + 'a');
                gambol += c;
            }
            System.out.println(gambol);
            loadStock(gambol.toUpperCase());
            if(error.getText().length()==0){
                bool = false;
            }
        }
    }

@Override
    public void actionPerformed(ActionEvent e){
        Object a = e.getSource();
        if(a instanceof JButton){
            JButton b = (JButton) a;
            if(b.getText().equals("Submit")){
                loadStock(Ticket.getText().toUpperCase());
            }else if(b.getText().equals("Gamble")){
                gamble();
            }else{
                setClear();
            }
        }
    }
}


