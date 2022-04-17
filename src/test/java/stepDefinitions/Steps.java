package stepDefinitions;

import configuration.Configurations;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.groovy.parser.antlr4.GroovyParser;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Steps {

//    public static String BASE_URI="https://bookstore.toolsqa.com";
//    public static String USERNAME= "TOOLSQA-Test";
//    public static String PASSWORD= "Test@@123";
//    public static String USER_ID ="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";

    public static Response response;
    public static String token;
    public static String jsonString;
    public static String bookId;
    Properties prop;

    @Given("User is authorized")
    public void user_is_authorized() {
        Configurations.readProperties(prop.getProperty("BASE_URI"));
//        RestAssured.baseURI=BASE_URI;
        RequestSpecification request= RestAssured.given();

        request.header("Content-Type","application/json");
        response= request.body("{ \"userName\": \"" + prop.getProperty("USERNAME") + "\", \"password\": \""+prop.getProperty("PASSWORD")+"\"}").
                post("/Account/v1/GenerateToken");
        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
    }
    @Given("list of books are available")
    public void list_of_books_are_available() {
    Configurations.readProperties(prop.getProperty("BASE_URI"));
 //   RestAssured.baseURI=BASE_URI;
    RequestSpecification request= RestAssured.given();
    response=request.get("/BookStore/v1/Books");

    jsonString= response.asString();
    List<Map<String, String>> books= JsonPath.from(jsonString).get("books");
    Assert.assertTrue(books.size()>0);

    bookId= books.get(0).get("isbn");
    }
    @When("User add a book to list")
    public void user_add_a_book_to_list() {
        Configurations.readProperties(prop.getProperty("BASE_URI"));
   // RestAssured.baseURI= BASE_URI;
    RequestSpecification request=RestAssured.given();
    request.header("Content-Type","application/json").
            header("Authentication","Bearer"+token);
    response=request.body("{\"userId\":\""+prop.getProperty("USER_ID")+"\","+"\"collectionOfIsbn\":[{\"isbn\": \""+bookId+"\"}]}").
            post("/BookStore/v1/Books" );
    }
    @Then("The book is added to list")
    public void the_book_is_added_to_list() {

        Assert.assertEquals(201, response.getStatusCode());
    }
    @When("User remove a book from list")
    public void user_remove_a_book_from_list() {
        Configurations.readProperties(prop.getProperty("BASE_URI"));
//        RestAssured.baseURI= BASE_URI;
    RequestSpecification request= RestAssured.given();
    request.header("Content-Type","application/json").
            header("Authentication","Bearer"+token);
    response=request.body("{\"isbn\":\""+bookId+"\",\"userId\":\""+prop.getProperty("USER_ID")+"\"}").
            delete("/BookStore/v1/Books");
    }
    @Then("The book is removed")
    public void the_book_is_removed() {

        Assert.assertEquals(204,response.getStatusCode());
    }
}
