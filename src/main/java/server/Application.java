package server;

import net.avalara.avatax.rest.client.AvaTaxClient;
import net.avalara.avatax.rest.client.TransactionBuilder;
import net.avalara.avatax.rest.client.enums.AvaTaxEnvironment;
import net.avalara.avatax.rest.client.enums.DocumentType;
import net.avalara.avatax.rest.client.enums.TransactionAddressType;
import net.avalara.avatax.rest.client.models.PingResultModel;
import net.avalara.avatax.rest.client.models.TransactionModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("http://localhost:8080");
    }

    @GetMapping("/")
    @ResponseBody
    public String testOfLife() {
        System.out.println("We're Here");
        return "hihi";
    }

    @GetMapping("/ping")
    @ResponseBody
    public String authPage() {
        AvaTaxClient client = new AvaTaxClient("Test", "1.0", "localhost", AvaTaxEnvironment.Sandbox)
                .withSecurity("timbuschofficial@gmail.com", "CodeFellows18"); //replace with your username and password

        try {
            PingResultModel ping = client.ping();
            if (ping.getAuthenticated()) {
                System.out.print("Successfully created a client!");
            }
        } catch (Exception e) {

        }

        try {
            TransactionModel transaction = new TransactionBuilder(client, "DEFAULT", DocumentType.SalesInvoice, "ABC")
                    .withAddress(TransactionAddressType.SingleLocation, "123 Main Street", null, null, "Irvine", "CA", "92615", "US")
                    .withLine(new BigDecimal(100.0), new BigDecimal(1), "P0000000")
                    .Create();
        } catch (Exception e) {
        }
        return client.toString();
    }



}
