package server;

import com.google.gson.Gson;
import net.avalara.avatax.rest.client.AvaTaxClient;
import net.avalara.avatax.rest.client.TransactionBuilder;
import net.avalara.avatax.rest.client.enums.AvaTaxEnvironment;
import net.avalara.avatax.rest.client.enums.DocumentType;
import net.avalara.avatax.rest.client.enums.TransactionAddressType;
import net.avalara.avatax.rest.client.models.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import parser.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;


@Controller
public class CreateTransaction {
    //    @GetMapping("/create/transaction")
//    @ResponseBody
//    public String authPage() {
//        AvaTaxClient client = new AvaTaxClient("Test", "1.0", "localhost", AvaTaxEnvironment.Production)
//                .withSecurity("timbuschofficial@gmail.com", "CodeFellows18"); //replace with your username and password
//
//        try {
//            PingResultModel ping = client.ping();
//            if (ping.getAuthenticated()) {
//                System.out.print("Successfully created a client!");
//            }
//        } catch (Exception e) {
//
//        }
//
//        try {
//            TransactionModel transaction = new TransactionBuilder(client, "DEFAULT", DocumentType.SalesOrder, "ABC")
//                    .withAddress(TransactionAddressType.SingleLocation, "4126 3rd NW", null, null, "Seattle", "WA",
//                            "98107", "US")
//                    .withLine(new BigDecimal(100.0), new BigDecimal(1), "PH404450")
//                    .Create();
//        } catch (Exception e) {
//        }
//        String response = null;
//        return response;
//    }
    @GetMapping("/createtransaction")
    @ResponseBody
    public String queryByTaxCode() {
        //need to pass taxCode and address as argument.
        String builder = null;
//        HttpSession httpSession = request.getSession();
        //expanding this but not functioning at the the moment just hardcoding
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        CreateTransactionModel transactionModel = new CreateTransactionModel();
        LineItemModel line = new LineItemModel();
        line.setTaxCode("PH404450");
        line.setDescription("HardCoded in for Now");
        BigDecimal hardcode = BigDecimal.valueOf(100);
        line.setAmount(hardcode);
        line.setQuantity(hardcode);
        ArrayList<LineItemModel> lines = new ArrayList<>();
        lines.add(line);

        AddressesModel adModel = new AddressesModel();
        AddressLocationInfo adInfo = new AddressLocationInfo();
        adInfo.setPostalCode("98107");

        adModel.setSingleLocation(adInfo);

        transactionModel.setDate(date);
        transactionModel.setCompanyCode("DEFAULT");
        transactionModel.setCustomerCode("ABC");
        transactionModel.setType(DocumentType.SalesOrder);
        transactionModel.setCurrencyCode("USD");
        transactionModel.setCommit(false);
        transactionModel.setAddresses(adModel);
        transactionModel.setLines(lines);
        Gson gson = new Gson();
        try {
            String userNamePass = Base64.getEncoder().encodeToString(("timbuschofficial@gmail.com:CodeFellows18")
                    .getBytes());
            String requestUrl = "https://rest.avatax.com/api/v2/transactions/create";
            StringEntity requestEntity = new StringEntity(gson.toJson(transactionModel), ContentType.APPLICATION_JSON);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(requestUrl);
            httpPost.addHeader("accept", "application/json");
            httpPost.addHeader("authorization", "Basic " + userNamePass);
            httpPost.setEntity(requestEntity);

            HttpResponse response = client.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != 201 && (response.getStatusLine().getStatusCode() != 200)) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }


            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String reader;
            while ((reader = bufferedReader.readLine()) != null) {
                builder = reader;
            }
            System.out.println(Parser.parseJSON(builder));
            double parsed =  Parser.parseJSON(builder);
            System.out.println(parsed);
            return builder;
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println(builder);
        return builder;
    }
}
