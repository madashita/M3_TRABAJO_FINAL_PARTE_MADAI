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


public class ItemTest {

    @BeforeEach
    public void before() throws IOException {
        new GetProperties().leerPropiedades();
    }

    @Test
    public void verifyCRUDforItem(){
        JSONObject body = new JSONObject();
        body.put("Content","Madai_Item4");

        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_ITEM,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(request);
        response.then()
                .statusCode(200)
                .body("Content", equalTo("Madai_Item4"));
        String id = response.then().extract().path("Id")+"";
        body.put("Content","Madai_Item4Update");
        request = new RequestInformation(ConfigAPI.UPDATE_ITEM.replace("ID",id),body.toString());
        response = FactoryRequest.make(FactoryRequest.PUT).send(request);

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Madai_Item4Update"));


        request = new RequestInformation(ConfigAPI.DELETE_ITEM.replace("ID",id),"");
        response = FactoryRequest.make(FactoryRequest.DELETE).send(request);

        response.then()
                .statusCode(200)
                .body("Deleted", equalTo(true));

    }

}
