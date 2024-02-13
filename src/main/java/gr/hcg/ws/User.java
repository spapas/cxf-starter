package gr.hcg.ws;

import jakarta.xml.bind.annotation.XmlElement;

public class User {
    @XmlElement(required=true,nillable=false)
    public String first_name;
    @XmlElement(required=true,nillable=false)
    public String last_name;
}
