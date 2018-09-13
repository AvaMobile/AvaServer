package server;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Base64;

//            https://hc.apache.org/httpcomponents-client-ga/quickstart.html reference guide
//OOf I feel goofy we can go into the SDK and find a bunch of this info
//https://github.com/avadev/AvaTax-REST-V2-JRE-SDK/blob/master/src/main/java/net/avalara/avatax/rest/client/models/ItemModel.java

@Controller
public class TaxCodeSearch {
    @GetMapping("/taxcode")
    @ResponseBody
    public String searchTaxCode() {
        String builder = null;
        try {
            String userNamePass = Base64.getEncoder().encodeToString(("timbuschofficial@gmail.com:CodeFellows18")
                    .getBytes());
            String requestUrl = "https://rest.avatax.com/api/v2/definitions/taxcodes";


            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet requestBuilder = new HttpGet(requestUrl);
            requestBuilder.addHeader("accept", "application/json");
            requestBuilder.addHeader("authorization", "Basic " + userNamePass);

            HttpResponse response = client.execute(requestBuilder);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Search Failed : Error Code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String reader;
            while ((reader = bufferedReader.readLine()) != null) {
                builder = reader;
            }
            System.out.println(Parser.parse(builder));
            String parsed =  Parser.parse(builder);
            String[] length = parsed.split(",");
            System.out.println(length.length);
            return builder;
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        builder = Parser.parse(builder);
        System.out.println(builder);
        return builder;
    }


}
