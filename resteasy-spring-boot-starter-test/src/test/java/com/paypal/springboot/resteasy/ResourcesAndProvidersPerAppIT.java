package com.paypal.springboot.resteasy;

import com.sample.app.Application;
import com.test.resourcesandprovidersperapp.ResourcesProvidersPerJaxrsAppBootApplication;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.util.SocketUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * This is an integration test based on a simple sample application and
 * very common use cases (see sample-app project)
 *
 * @author facarvalho
 */
public class ResourcesAndProvidersPerAppIT {

    @BeforeClass
    public void startingApplicationUp() {
        RestAssured.basePath = "resandprovapp";
        int port = SocketUtils.findAvailableTcpPort();
        RestAssured.port = port;

        SpringApplication springApplication = new SpringApplication(ResourcesProvidersPerJaxrsAppBootApplication.class);
        springApplication.run("--server.port=" + port).registerShutdownHook();
    }

    @AfterClass
    public void shuttingDownApplication() {
        Response response = given().basePath("/").post("/shutdown");
        response.then().statusCode(200).body("message", equalTo("Shutting down, bye..."));
    }

    @Test
    public void happyPathTest() {
        Response response = given().body("hello").post("/echo");
        response.then().statusCode(200).body("echoText", equalTo("hello_echoed_v1"));
    }



}
