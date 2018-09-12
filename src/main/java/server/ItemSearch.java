package server;

import model.ItemSearchModel;
import net.avalara.avatax.rest.client.models.ItemModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;

//OOf I feel goofy we can go into the SDK and find a bunch of this info
//https://github.com/avadev/AvaTax-REST-V2-JRE-SDK/blob/master/src/main/java/net/avalara/avatax/rest/client/models/ItemModel.java

@Controller
//            https://hc.apache.org/httpcomponents-client-ga/quickstart.html reference guide
//How do we handle these passwords
public class ItemSearch {
    @GetMapping("/search/item")
    public String searchItem() {
        ItemSearchModel itemSearch = new ItemSearchModel("ceramic");
        ItemModel itemModel = new ItemModel();
        itemModel.
        String builder = null;
        try {
            String userNamePass = Base64.getEncoder().encodeToString(("timbuschofficial@gmail.com:CodeFellows18")
                    .getBytes());
//            https://rest.avatax.com/api/v2/items?%24filter=description%20contains%20ceramic
            String requestURL = "https://rest.avatax.com/api/v2/items?%24filter=" + URLEncoder.encode(itemSearch
                    .getDescription(), "UTF-8");


            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet requestBuilder = new HttpGet(requestURL);
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
                String[] outputArray = reader.split("[^a-zA-Z0-9.\\s]");
                System.out.println("Output " + Arrays.deepToString(outputArray));

                if (reader.contains("ceramic")) {
                    System.out.println("Output only = " + reader);
                }
                builder = reader;
            }

            return builder;
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return builder;
        //honest thought this might return the full object
    }

}
