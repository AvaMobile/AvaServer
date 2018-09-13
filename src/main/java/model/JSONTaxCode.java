package model;

import java.util.HashMap;
import java.util.Map;

public class JSONTaxCode {

    public static Map<String, String> parseProductCodes(String str){
        Map<String,String> productCodes= new HashMap();
        while (str.contains("taxCode")){
            int index = str.indexOf("taxCode");
            String taxcode = str.substring(index + 10, index + 18);
            index = str.indexOf("description");
            str=str.substring(index+14);
            index = str.indexOf(",");
            String description = str.substring(0, index);
            str = str.substring(index);
            productCodes.put(description,taxcode);
        }
        return productCodes;
    }

}
