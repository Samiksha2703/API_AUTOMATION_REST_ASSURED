package com.bridgelabz.restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class RestAssuredSpotify {

    public String token = "";
    public static String userID = "";

    @BeforeTest
    public void setUp() {
        token = "Bearer BQAU4BvVhhpRMhtjZODNkur8lowR_E3QYo10O8bcYIm0xaf6V6vAtODcsdOiCM3jUyQpDUXRgm6wGQtpSS-s85K59rytEFGeXnp6ADCqSktNvR6lvWheKCFq8oFwQ3HjdyRQEhSsF4AjWR3wKH7CeggRhmx_FeWvpen7rwtoiGKJiYwYPk28dg3jBFpEi5d6ylfzWwmpiMwhnQhMR14QxrFM7BzXtfNph0DT-oUepo0sJfFDzzR6LGJRI35K6g0hsKoRDATBp0p7ODwLctEUF6T-a4rddz2qoGPuPHHh";
    }

    @Test
    public void getCurrentUsersProfile() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.prettyPrint();
        userID = response.path("id");
        System.out.println("UserID :" + userID);
    }

    @Test
    public void getUserProfile() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/" + userID + "/");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void createPlaylist() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body("{\"name\":\"MorningVibes\",\"description\":\"Bollywood songs\",\"public\":\"false\"}")
                .when()
                .post("https://api.spotify.com/v1/users/uclfdt3jie5b7vorl9472d5g5/playlists");
        response.then().assertThat().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void searchItem() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/search?q=Jubin&type=track");
        response.prettyPrint();
    }

    @Test
    public void addItem() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .post("https://api.spotify.com/v1/playlists/5g3AU9TZQmmcqnkBw9CW7R/tracks?uris=spotify%3Aartist%3A1tqysapcCh1lWEAc9dIFpa");
        response.prettyPrint();
    }
}
