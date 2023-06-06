import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
        setVisible(true);
    }

    private void loadStock(String name){
        stock = StockAPI.getStockInfo(name);
        nameText.setText(stock.getTicker());
        priceText.setText("$" + stock.getPrice());
        highText.setText("$" + stock.getHigh());
        lowText.setText("$" + stock.getLow());
        changeText.setText(stock.getChange() + "%");
    }

    private void setClear(){
        nameText.setText("");
        priceText.setText("");
        highText.setText("");
        lowText.setText("");
        changeText.setText("");
    }

@Override
    public void actionPerformed(ActionEvent e){
        Object a = e.getSource();
        if(a instanceof JButton){
            JButton b = (JButton) a;
            if(b.getText().equals("Submit")){
                loadStock(Ticket.getText().toUpperCase());
            }else{
                setClear();
            }
        }
    }
}


