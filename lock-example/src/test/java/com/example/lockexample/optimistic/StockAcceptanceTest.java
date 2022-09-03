package com.example.lockexample.optimistic;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.lockexample.dto.StockRequest;
import com.example.lockexample.dto.StockResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockAcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 동시에_재고를_차감한다() throws InterruptedException, ExecutionException {
        StockResponse 재고 = N개의_재고를_생성(1L).as(StockResponse.class);

        int numberOfThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        int failedCount = 0;
        List<Future<?>> tasks = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Future<?> task = executorService.submit(() -> N개의_재고를_차감(재고.getId(), 1L));
            tasks.add(task);
        }

        for (Future<?> task : tasks) {
            ExtractableResponse<Response> response = (ExtractableResponse<Response>) task.get();
            System.out.println("res: " + response.statusCode());
            System.out.println("res: " + response.asString());
            if (response.statusCode() == 500 &&
                "이미 재고가 차감되었습니다.".equals(response.asString())) {
                failedCount++;
            }
        }

        assertThat(failedCount).isEqualTo(3);
    }

    ExtractableResponse<Response> N개의_재고를_생성(Long stockCount) {
        StockRequest stockRequest = new StockRequest("돈까스", stockCount);

        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(stockRequest)
            .when().post("/stocks")
            .then().log().all()
            .extract();
    }

    ExtractableResponse<Response> N개의_재고를_차감(Long stockId, Long pickingCount) {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("pickingCount", pickingCount)
            .when().put("/stocks/{stockId}/decrease", stockId)
            .then().log().all()
            .extract();
    }
}
