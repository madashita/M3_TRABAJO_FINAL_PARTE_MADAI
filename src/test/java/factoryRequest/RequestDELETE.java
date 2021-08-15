package factoryRequest;

import io.restassured.response.Response;
import util.ConfigEnv;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RequestDELETE implements IRequest{
    @Override
    public Response send(RequestInformation information) {
        Response response=given()
                            .auth()
                            .preemptive()
                            .basic(ConfigEnv.user,ConfigEnv.password)
                            .log()
                            .all()
                          .when()
                            .delete(information.getUrl());

        response.then()
                .log()
                .all();
        return response;
    }
}
