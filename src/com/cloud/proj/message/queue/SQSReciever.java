package com.cloud.proj.message.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class SQSReciever extends SimpleQueueService {
	
	public SQSReciever(String queueName) {
		super(queueName);
	}
	
	public List<String> receiveMessages() {
		List<String> messageText = new ArrayList<String>();
		System.out.println("Receiving messages from " + queueName + "\n");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        for (Message message : messages) {
            System.out.println("  Message");
            System.out.println("    MessageId:     " + message.getMessageId());
            System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("    Body:          " + message.getBody());
            for (Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("  Attribute");
                System.out.println("    Name:  " + entry.getKey());
                System.out.println("    Value: " + entry.getValue());
            }
            messageText.add(message.getBody());
        }
        System.out.println();

        // Delete a message
        System.out.println("Deleting the messages\n");
        for (Message message : messages) {
            String messageRecieptHandle = message.getReceiptHandle();
            sqs.deleteMessage(new DeleteMessageRequest(queueUrl, messageRecieptHandle)); 	
        }
        return messageText;
	}
}
