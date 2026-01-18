package dev.reservationsrvc.controller;

import dev.reservationsrvc.persistence.reservation.Reservation;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ReservationResourceTest {

    @TestHTTPEndpoint(ReservationResource.class)
    @TestHTTPResource
    URL reservationResource;

    @Test
    public void testReservationIds() {
        String startDate = "2026-01-23";
        String endDate = "2026-01-30";

        ReservationReq reservationReq = new ReservationReq(484L,
                                                           LocalDate.parse(startDate),
                                                           LocalDate.parse(endDate)
        );

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(reservationReq)
                .when()
                .post(reservationResource)
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "car", nullValue(), // we used a random car ID
                        "startDate", equalTo(startDate),
                        "endDate", equalTo(endDate)
                );
    }
}