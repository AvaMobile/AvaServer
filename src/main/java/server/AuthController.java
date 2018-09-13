package server;


import net.avalara.avatax.rest.client.AvaTaxClient;
import net.avalara.avatax.rest.client.enums.AvaTaxEnvironment;
import net.avalara.avatax.rest.client.models.PingResultModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//This will just set us up for authorization from android though we still need to figure out the shape of the request
// from android.
@Controller
@SessionAttributes({"username" , "password"})
public class AuthController {
    @GetMapping("/authorization")
    @ResponseBody
    public Boolean authorization(HttpServletRequest request, Model model, @RequestParam String username, @RequestParam
            String password){
        HttpSession httpSession = request.getSession();
        AvaTaxClient client = new AvaTaxClient("Test","1.0","localhost",
                AvaTaxEnvironment.Production).withSecurity(username,password);
        try{
            PingResultModel ping = client.ping();
            if(ping.getAuthenticated()){
                System.out.println("Authenticated");
                httpSession.setAttribute("username",username);
                httpSession.setAttribute("password",password);
                httpSession.setAttribute("isLoggedIn", true);
                return true;
            }else{
                System.out.println("Bad Login");
                return false;
            }
        }catch(Exception e){
            System.out.println("InAuth");
            System.out.println(e);
        }
        return true;
    }
}
