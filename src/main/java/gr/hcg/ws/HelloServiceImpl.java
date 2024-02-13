package gr.hcg.ws;

import jakarta.jws.WebService;

@WebService(endpointInterface = "gr.hcg.ws.HelloService", serviceName = "HelloService")
public class HelloServiceImpl {
    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }

    public String sayHiToUser(User user) {
        System.out.println("sayHiToUser called");
        return "Hello "  + user.first_name + " " + user.last_name;
    }
}
