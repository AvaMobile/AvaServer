package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRequest {
    public String type;
    public String companyCode;
    public String date;
    public String customerCode;
    public String purchaseOrderNo;
    public Boolean commit;
    public String currencyCode;
    public ArrayList<Map<String, Object>> lines;
    public HashMap<String, HashMap> addresses;

    public static void main(String[] args) {
        TransactionRequest test = new TransactionRequest("SalesInvoice","DEFAULT","1",1,100,"PS081282","Y0001",
                "2018-08-03","ABC",
                "2018-8-03-001",true,"USD","yarn","2000 Main Street", "Irvine", "CA", "US", "92614");
        String str1 = null;
        try {
            str1 = new ObjectMapper().writeValueAsString(test);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str1);


    }

    TransactionRequest(String type, String companyCode, String number, int quantity, int amount, String taxCode, String itemCode,
      String date, String customerCode, String purchaseOrderNo, Boolean commit, String currencyCode, String description, String address, String city, String region, String country, String postalCode) {
        this.type = type;
        this.companyCode = companyCode;
        this.date = date;
        this.customerCode = customerCode;
        this.purchaseOrderNo = purchaseOrderNo;
        this.commit = commit;
        this.currencyCode = currencyCode;

        HashMap<String, Object> linesmap = new HashMap<String, Object>();
        linesmap.put("number", number);
        linesmap.put("quantity", quantity);
        linesmap.put("amount", amount);
        linesmap.put("taxCode", taxCode);
        linesmap.put("itemCode", itemCode);
        linesmap.put("description", description);
        this.lines = new ArrayList<Map<String, Object>>();
        this.lines.add(linesmap);

        HashMap<String, String> addymap = new HashMap<>();
        addymap.put("line1", address);
        addymap.put("city", city);
        addymap.put("region", region);
        addymap.put("country", country);
        addymap.put("postalCode", postalCode);

        HashMap<String, HashMap> singleLocation = new HashMap<>();
        singleLocation.put("singleLocation", addymap);

        this.addresses = new HashMap<String, HashMap>();
        this.addresses = singleLocation;
    }
}