package model;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransactionRequest {
    public String type;
    public String companyCode;
    public List<String> lines;
    public String date;
    public String customerCode;
    public String purchaseOrderNo;
    public List<String> addresses;
    public List<String> singleLocation;
    public boolean commit;
    public String currencyCode;
    public String description;

    TransactionRequest (String type, String companyCode, List<String> lines, String date, String customerCode, String
            purchaseOrderNo, List<String> addresses, List<String> singleLocation, Boolean commit, String
            currencyCode, String description) {
        this.type = type;
        this.companyCode = companyCode;
        this.lines = lines;
        this.date = date;
        this.customerCode = customerCode;
        this.purchaseOrderNo = purchaseOrderNo;
        this.addresses = addresses;
        this.singleLocation = singleLocation;
        this.commit = commit;
        this.currencyCode = currencyCode;
        this.description = description;
    }


    public static void main(String[] args) throws JSONException {
        String message;
        JSONObject json = new JSONObject();
        JSONObject lineFill = new JSONObject();
        lineFill.put("number", "1");
        lineFill.put("quantity", 1);
        lineFill.put("amount", 100);
        lineFill.put("taxCode", "PS081282");
        lineFill.put("itemCode", "Y0001");
        lineFill.put("description", "yarn");

        json.put("lines", lineFill);
        json.put("type", "SalesOrder");
        json.put("companyCode", "DEFAULT");
        json.put("date", "2018-08-03");
        json.put("customerCode", "ABC");
        json.put("purchaseOrderNo", "2018-08-03-001");
        JSONObject singleLocationArr = new JSONObject();
        singleLocationArr.put("line1", "2000 Main Street");
        singleLocationArr.put("city", "Irvine");
        singleLocationArr.put("region", "CA");
        singleLocationArr.put("country", "US");
        singleLocationArr.put("postalCode", "92614");
        JSONObject addressesFill = new JSONObject();
        addressesFill.put("singleLocation", singleLocationArr);
        json.put("adresses", addressesFill);
        json.put("commit", true);
        json.put("currencyCode", "USD");
        json.put("description", "yarn");
        message = json.toString();
        System.out.println(message);

        TransactionRequest tr = new TransactionRequest()
    }


}

//{
//        "lines": [
//        {
//        "number": "1",
//        "quantity": 1,
//        "amount": 100,
//        "taxCode": "PS081282",
//        "itemCode": "Y0001",
//        "description": "Yarn"
//        }
//        ],
//        "type": "SalesOrder",
//        "companyCode": "DEFAULT",
//        "date": "2018-08-03",
//        "customerCode": "ABC",
//        "purchaseOrderNo": "2018-08-03-001",
//        "addresses": {
//        "singleLocation": {
//        "line1": "2000 Main Street",
//        "city": "Irvine",
//        "region": "CA",
//        "country": "US",
//        "postalCode": "92614"
//        }
//        },
//        "commit": true,
//        "currencyCode": "USD",
//        "description": "Yarn"
//        }