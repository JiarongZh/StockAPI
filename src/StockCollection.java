import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;


import java.util.ArrayList;
import java.util.Scanner;
public class StockCollection {
    private static ArrayList<String> stockList;
    private Scanner scan;

    public StockCollection(){
        stockList = new ArrayList<String>();
        stockList.addAll(Arrays.asList("AA","AMZN","BOH","TSLA","COOP","AAPL","ACER","AGFY","XTNT","OGS","SHLS","FRGO","LEO","SCHK","FDUS","ZWS","AHH","GBUY","DRV","RHP"));
    }
    public static ArrayList<String> getStockList(){
        return stockList;
    }

    public static void addStock(String s){
        stockList.add(s);
    }

}
