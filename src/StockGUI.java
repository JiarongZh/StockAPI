import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.event.ActionListener;
import java.util.Locale;

public class StockGUI extends JFrame{

    private static String BASE_URL = "https://api.stockdata.org/v1/data/quote?symbols=";
    private static String API = "MafSn36nzGWVW348Ool0wf4s0KlrLe1WfrJz55XO";
    private JButton submitButton;
    private JTextField Ticket;


    public static void getStockInfo(String ticker){
        ticker = ticker.toUpperCase();
        String url = BASE_URL + ticker + "&" + API;
        String urlResponse = "";
        try {
            URI myUri = URI.create(url);
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            urlResponse = response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject(urlResponse);
        JSONArray arr = jsonObj.getJSONArray("data");
        JSONObject dataObj = arr.getJSONObject(0);
        String name = dataObj.getString("name");
    }

}
