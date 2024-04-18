package com.rmarcello.eventhubutils.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EventHubProducerController {

  @Autowired
  private EventHubProducerClient producerClient;

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @PostMapping("/send")
  public ResponseEntity send(@RequestBody String body) {

    if(body==null || body.length()< 10)
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    producerClient.send( Collections.singletonList(new EventData(body)) );
      
    return new ResponseEntity<>(HttpStatus.OK);
  }
  

}
