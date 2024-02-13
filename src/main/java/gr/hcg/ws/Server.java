package gr.hcg.ws;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import java.util.HashMap;
import java.util.Map;

public class Server {
    protected Server() throws Exception {
        System.out.println("Starting Server");
        HelloServiceImpl implementor = new HelloServiceImpl();
        String address = "http://localhost:9000/helloWorld";
        Endpoint.publish(address, implementor, new LoggingFeature());
        String address2 = "http://localhost:9000/helloWorld2";
        Endpoint.publish(address2, implementor, new LoggingFeature());

        EndpointImpl jaxWsEndpoint = (EndpointImpl) jakarta.xml.ws.Endpoint.publish("http://localhost:9000/helloWorld3", implementor, new LoggingFeature());

        org.apache.cxf.endpoint.Endpoint cxfEndpoint = jaxWsEndpoint.getServer().getEndpoint();
        Map<String,Object> inProps = new HashMap<String,Object>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerPasswordHandler.class.getName());

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
        cxfEndpoint.getInInterceptors().add(wssIn);

        //Map<String,Object> outProps = new HashMap<String,Object>();

        //WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        //cxfEndpoint.getOutInterceptors().add(wssOut);

    }

    public static void main(String[] args) throws Exception {
        new Server();
        /*
        System.out.println("Server ready...");
        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);

         */
    }

    /* OR

    HelloWorldImpl implementor = new HelloWorldImpl();
    JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
    svrFactory.setServiceClass(HelloWorld.class);
    svrFactory.setAddress("http://localhost:9000/helloWorld");
    svrFactory.setServiceBean(implementor);
    svrFactory.create();
     */
}
