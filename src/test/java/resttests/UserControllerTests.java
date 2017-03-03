package resttests;


import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class UserControllerTests {

    private static final String RESPONSE_STRING = "{\"userId\":1,\"userName\":\"Vasya\",\"userMail\":\"vasya@mail.ru\",\"birthDate\":\"4.10.1984\"}";
    private RestService service;

    @Before
    public void setUp() {
        service = new RestService();
    }

    @Test
    public void shouldHaveUserVasya() {
        when()
                .get("/movie/users/getUserByName/Vasya")
                .then()
                .body(equalTo(RESPONSE_STRING));
    }

    @Test
    public void shouldExistsUserVasya() {
        when()
                .get("/movie/users/checkIfUserExists/Vasya")
                .then()
                .body(equalTo("true"));
    }

    @Test
    public void shouldNotExistsUserPetr() {
        when()
                .get("/movie/users/checkIfUserExists/Petr")
                .then()
                .body(equalTo("false"));
    }

    @Test
    public void shouldGetUserById() {
        when()
                .get("/movie/users/getUserById/1")
                .then()
                .body(equalTo(RESPONSE_STRING));
    }

    @Test
    public void shouldGetUserVasya() {
        service.shouldHaveGivenUserName("Vasya");
    }

    @Test
    public void shouldGetUserPete() {
        service.shouldHaveGivenUserName("Pete");
    }

    @Test
    public void ddd(){
        expect().body("userId", equalTo(1)).when().get("http://localhost:8080/movie/users/getUserById/1");
    }
}