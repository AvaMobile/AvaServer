package server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AvaTaxApiCall {

    private String type;
    private Value value;

    public AvaTaxApiCall() {

    }

    public String getType() {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public void setValue (Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
}
