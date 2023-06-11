import org.json.JSONException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


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

    private StockCollection s;
    public StockGUI(){
        createUIComponents();
        s = new StockCollection();
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
            Ticket.setText(stock.getTicker());
            nameText.setText(stock.getName());
            priceText.setText("$" + stock.getPrice());
            highText.setText("$" + stock.getHigh());
            lowText.setText("$" + stock.getLow());
            changeText.setText(stock.getChange() + "%");
            error.setText("");
            if(!(s.getStockList().contains(name.toUpperCase()))){
                s.addStock(name);
            }
        }catch (JSONException e){
            setClear();
            error.setText("Stock not found or does not exist in this database");
        }
    }

    private void setClear(){
        Ticket.setText("");
        nameText.setText("");
        priceText.setText("");
        highText.setText("");
        lowText.setText("");
        changeText.setText("");
        error.setText("");
    }

    private void gamble(){
        setClear();
        int num = (int)(Math.random()*StockCollection.getStockList().size());
        loadStock(StockCollection.getStockList().get(num));

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


