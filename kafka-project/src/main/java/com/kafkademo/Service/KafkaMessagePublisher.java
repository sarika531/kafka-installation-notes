package com.kafkademo.Service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagePublisher {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	/**
	 * 
	 * @param message
	 */
	   public void sendMessageToTopic(String message) {
	        CompletableFuture<SendResult<String, Object>> future = 
	            template.send("kafkademo", message);

	        future.whenComplete((result, ex) -> {
	            if (ex == null) {
	                // If message sent successfully, print the message and offset.
	                System.out.println("Sent message =[" + message + "] with offset={" + 
	                        result.getRecordMetadata().offset() + "}");
	            } else {
	                // If there was an error sending the message, print the error message.
	                System.out.println("Unable to send message =[" + message + "] due to: " + ex.getMessage());
	            }
	        });
	    }
	}