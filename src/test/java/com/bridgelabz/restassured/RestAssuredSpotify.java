package com.bridgelabz.restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredSpotify {

    public String token = "";
    public static String userID = "";
    public static String playlistID = "";

    @BeforeTest
    public void setUp() {
        token = "Bearer BQDHuwcHyucXx5NY5B8NCwUIfyflaC-57Bemjij_lWAMN9zUUG1a5plhhuDgODesoD4dmNfaRRQfPCMfutEm1u9TkanRTj53yLWh70uX95F8d-MHqW8HeuENQdsIm4_F8PDc9mGdzzKol7aggmVmPUsA4G5emZ3hmneLAwfyqsEw8AlzLwLUdImFb3W-ynfqAugTJOMHhwIcTK-FVFNUJOWzleKKgyeiNUgyJGv-xbCJ0YSZhIp4Az6W_aZHPUn7TgVEC6FQVowWTuB9g74s5nTPICg3ssSR42kbITvh";
    }

    @Test(priority = 1)
    public void getCurrentUsersProfile_GET_REQUEST() {
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

    @Test(priority = 2)
    public void getUserProfile_GET_REQUEST() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/" + userID + "/");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 3)
    public void createNewPlaylist_POST_REQUEST() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body("{\"name\":\"Latest Bollywood Songs\",\"description\":\"Bollywood songs\",\"public\":\"false\"}")
                .when()
                .post("https://api.spotify.com/v1/users/uclfdt3jie5b7vorl9472d5g5/playlists");
        String playlistName = response.path("name");
        Assertions.assertEquals("Latest Bollywood Songs",playlistName);
        response.prettyPrint();
    }

    @Test(priority = 4)
    public void searchItems_GET_REQUEST() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/search?q=Jubin&type=track");

        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test(priority = 5)
    public void addItem_POST_REQUEST() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .post("https://api.spotify.com/v1/playlists/1UlW6cCJJpi7nJx7hvOxks/tracks?uris=spotify:track:2ufLEVbzvEcJ3subW8jBUp");
        response.prettyPrint();
    }

    @Test(priority = 6)
    public void userPlaylist_POST_REQUEST() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/uclfdt3jie5b7vorl9472d5g5/playlists");
       int total = response.path("total");
        response.prettyPrint();
       String [] playlistArray = new String[total];
       for(int index = 0; index < total; index++){
           playlistArray[index] = response.path("items["+index+"].name");
           System.out.println("Playlist : "+playlistArray[index]);
       }
    }
}
