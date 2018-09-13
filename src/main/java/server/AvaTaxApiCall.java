package server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AvaTaxApiCall {

    private String type;

    public AvaTaxApiCall() {

    }

    public String getType() {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                '}';
    }
}
