package parser;

import static java.lang.Double.parseDouble;

public class Parser {

    public static String parse (String str){
        String[] stray = new String[2];
        stray[0] = str;
        stray[1] = "";

        while (stray[0].contains("taxCode\"")) {
            stray = parse(stray);
        }
        return stray[1];
    }

    private static String[] parse(String[] strarry){
        String str = strarry[0];
        int TCindex = str.indexOf("taxCode\"");
        if(strarry[1].length() != 0) strarry[1]+= ", ";
        strarry[1] += str.substring(TCindex + 10, TCindex + 18);
        strarry[0] = str.substring(TCindex+18);
        return strarry;
    }

    public static String parseJSON (String str){
        Double taxRate=0.0;
        int summaryindex = str.indexOf("summary");
        str = str.substring(summaryindex);
        while (str.contains("\"rate\"")){
            int rateIndex = str.indexOf("\"rate\"");
            str = str.substring(rateIndex+8);
            int endIndex = str.indexOf(",");
            String rate = str.substring(0,endIndex);
            taxRate += parseDouble(rate);
            str = str.substring(endIndex);
        }
        return taxRate.toString();
    }

    public static String parseJSONForTaxability (String str) {
        String results;
        int isTaxableIndex = str.indexOf("isItemTaxable");
        String taxable = str.substring(isTaxableIndex + 16, isTaxableIndex + 20);
        if (taxable.contains("ru")) {
            results = "Yes";
        } else {
            results = "No";
        }
        return results;
    }
}
