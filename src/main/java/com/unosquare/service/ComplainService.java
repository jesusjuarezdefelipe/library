package com.unosquare.service;


import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unosquare.entity.Complain;

@Service
public class ComplainService {
	
	public Complain registerComplain(Complain complain) throws JMSException, JsonProcessingException {
		
		final ActiveMQConnectionFactory connectionFactory = 
				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        final Destination destination = session.createQueue("LIBRARY.COMPLAINTS");
  

        final MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        
        complain.setDate(new Date());
        
        ObjectMapper mapper = new ObjectMapper();
        
        TextMessage m = session.createTextMessage(mapper.writeValueAsString(complain));
        producer.send(m);
        
        session.commit();

        session.close();
        connection.close();
        
        return complain;
		
	}
	
	public Complain getComplain() throws JMSException, JsonMappingException, JsonProcessingException {
		
		final ActiveMQConnectionFactory connectionFactory = 
				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
        final Connection connection = connectionFactory.createConnection();
 
        connection.start();
 
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        final Destination destination = session.createQueue("LIBRARY.COMPLAINTS");
   
        final javax.jms.MessageConsumer consumer = (javax.jms.MessageConsumer) session.createConsumer(destination);
 
        Message message = consumer.receive(30000);
        
        if (message instanceof TextMessage) {
        	TextMessage m = (TextMessage) message;
        	ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(m.getText(), Complain.class);
        }
 
        consumer.close();
        session.close();
        connection.close();
        
        return null;
	}
	

}

