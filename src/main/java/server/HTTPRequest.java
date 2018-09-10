package server;

import net.avalara.avatax.rest.client.AvaTaxClient;
import net.avalara.avatax.rest.client.models.CreateTransactionModel;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class HTTPRequest {
    public void postRequest() throws Exception {
        String url = "https://sandbox-rest.avatax.com/api/v2/transactions/create"
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

//        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("companyCode", "DEVGUIDE"));
        urlParameters.add(new BasicNameValuePair("code", "1001"));
        urlParameters.add(new BasicNameValuePair("type", "SalesOrder"));
        urlParameters.add(new BasicNameValuePair("date", "2018-9-10"));
        urlParameters.add(new BasicNameValuePair("customerCode", "EXAMPLECUSTOMER"));

        AvaTaxClient ATC = new AvaTaxClient();

        CreateTransactionModel model = new CreateTransactionModel();

        ATC.createTransaction("include", model);





    }
}
