import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.SQLOutput;
import java.util.Locale;

public class StockAPI extends JFrame{

    private static String BASE_URL = "https://api.stockdata.org/v1/data/quote?symbols=";
    private static String API = "MafSn36nzGWVW348Ool0wf4s0KlrLe1WfrJz55XO";
    private JButton submitButton;
    private JTextField Ticket;


    public static Stock getStockInfo(String ticker){
        ticker = ticker.toUpperCase();
        String url = BASE_URL + ticker + "&api_token=" + API;
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
        double high = dataObj.getDouble("day_high");
        double low = dataObj.getDouble("day_low");
        double price = dataObj.getDouble("price");
        double change = dataObj.getDouble("day_change");
        Stock s = new Stock(name,high,low,price,change);
        return s;
    }

}