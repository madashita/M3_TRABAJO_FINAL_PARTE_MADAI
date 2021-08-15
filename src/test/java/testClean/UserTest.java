package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConfigAPI;
import util.GetProperties;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserTest {

    @BeforeEach
    public void before() throws IOException {
        new GetProperties().leerPropiedades();
    }

    @Test
    public void verifyCreateUpdateForUser(){
        JSONObject body = new JSONObject();
        body.put("Email","madai3@ucb2021.com");
        body.put("FullName","Madai3");
        body.put("Password","12345");

        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_USER,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(request);
        response.then()
                .statusCode(200)
                .body("Email", equalTo("madai3@ucb2021.com"));

        JSONObject bodyU = new JSONObject();
        bodyU.put("FullName","MadaiUpdate3");
        request = new RequestInformation(ConfigAPI.UPDATE_USER,bodyU.toString());
        response = FactoryRequest.make(FactoryRequest.POST).send(request);

        response.then()
                .statusCode(200)
                .body("FullName", equalTo("MadaiUpdate3"));

    }

}
