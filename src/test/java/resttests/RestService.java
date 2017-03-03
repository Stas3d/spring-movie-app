package resttests;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestService {

    public void shouldHaveGivenUserName(final String username) {
        given().
                contentType("application/json")
                .when()
                .get("/movie/users/getUserByName/" + username)
                .then()
                .body(containsString("userId"))
                .body(containsString("userName"))
                .body(containsString("userMail"))
                .body(containsString("birthDate"))
                .statusCode(200);
    }
}
