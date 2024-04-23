package com.rmarcello.eventhubutils.controllers;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.rmarcello.eventhubutils.beans.Result;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class EventHubProducerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventHubProducerController.class);

  @Autowired
  private EventHubProducerClient producerClient;

  @Operation(summary = "Utily method only to check if the service is up & running")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Just an answer to the ping (Pong)")
  })
  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @Operation(summary = "Sends messages to the EventHub.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "when the message is sent withoud problems"),
      @ApiResponse(responseCode = "400", description = "when the message is not well formed"),
      @ApiResponse(responseCode = "500", description = "when there's something wrong")
  })
  @PostMapping("/send")
  public ResponseEntity<Result> send(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Json to send", required = true) @RequestBody String body) {
    LOGGER.info("send - START, body= {}", body);
    ResponseEntity<Result> r = null;
    try {
      if (body == null || body.length() < 10)
        return new ResponseEntity<Result>(new Result("400", "Body error"), HttpStatus.BAD_REQUEST);

      if (producerClient == null)
        return new ResponseEntity<Result>(new Result("500","internal error"), HttpStatus.INTERNAL_SERVER_ERROR);

      producerClient.send(Collections.singletonList(new EventData(body)));

      r = new ResponseEntity<Result>(new Result("200","ok, message sent"), HttpStatus.OK);

    } catch (Exception e) {
      LOGGER.error("error found", e);
    } finally {
      LOGGER.info("send - STOP " + r);
    }
    return r;
  }

}
