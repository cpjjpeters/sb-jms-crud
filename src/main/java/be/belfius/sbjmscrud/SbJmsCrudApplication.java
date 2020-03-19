package be.belfius.sbjmscrud;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import be.belfius.sbjmscrud.model.JsonLine;
import be.belfius.sbjmscrud.model.Tagfile;
import be.belfius.sbjmscrud.repository.JsonLineRepository;
import be.belfius.sbjmscrud.sendreceive.Receiver;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJms
@Slf4j
public class SbJmsCrudApplication  { //implements CommandLineRunner


	@Autowired
	Receiver receiver;
	
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the message
		// converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		factory.setConnectionFactory(connectionFactory);
	    factory.setErrorHandler(t -> {
	         log.error("Error in listener!", t);
	       });
		return factory;
	}

    
	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	public static void main(String[] args) {
		log.info("<<<< starting SbJmsCrudApplication >>>>");
		ConfigurableApplicationContext context = SpringApplication.run(SbJmsCrudApplication.class, args);

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// Send a message with a POJO - the template reuse the message converter
		System.out.println("-------------------------");
		System.out.println("Blah bla message.");
		System.out.println("-------------------------");
//		jmsTemplate.convertAndSend("tagbox", new Tagfile(createStringFromFile()));
//		jmsTemplate.convertAndSend("myQueue","messageCreator");
//		jmsTemplate.convertAndSend("tagbox", "this is amessage");
//		jmsTemplate.convertAndSend("tagBox", "this is Bmessage");
	}
//	HashMap<String, String> map = new HashMap<String, String>();
//	public String boDN,boVs, jsonId, key, value=null;
//	private JsonLine jsonLine = new JsonLine(jsonId,  key,  value);
//	@Autowired
//	private JsonLineRepository repo ;
////	private JsonLineService service = new JsonLineService();
//	
//	public static void main(String[] args) {
//		log.info("<<<< starting SbJmsCrudApplication >>>>");
//		SpringApplication.run(SbJmsCrudApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		String tf = null;
//		try {
//			receiver.receiveMessage(tf);
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
//			System.out.println(tf.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
////		String message=null;
////					//			List<String> allLines = Files.readAllLines(Paths.get("D:\\Data\\20L00029.txt"));
////					List<String> allLines= listener.receive(message);
////					
////					for (String line : allLines) {
////						System.out.println(line);
////						String[] parts = line.split("=", 2);
////						log.info("split {} = {}",parts[0].substring(1,parts[0].length()-1), parts[1]);
////						if (parts.length >= 2)
////						{
////							String key = parts[0].substring(1,parts[0].length()-1);
////							String value = parts[1];
////							map.put(key, value);
////							if (key.equals("boDealNumber")){
////								boDN=value;
////								log.info("boDN={}",value);
////							}
////							if (key.equals("boDealVersion")){
////								boVs=value;
////								log.info("boVs={}",value);
////							}
////						} else {
////							System.out.println("ignoring line: " + line);
////						}
////		
////					}
////					for (String line : allLines) {
////						String[] parts = line.split("=", 2);
////		//				log.info("split {} = {}",parts[0].substring(1,parts[0].length()-1), parts[1]);
////						if (parts.length >= 2)
////						{
////							jsonLine = new JsonLine();
////							jsonLine.setJsonId(boDN+boVs);
////							log.info("ts={}",LocalDateTime.now());
////							jsonLine.setSqlTimestamp(java.sql.Timestamp.valueOf(LocalDateTime.now()));
////							jsonLine.setKey(parts[0].substring(1,parts[0].length()-1))  ;
////							jsonLine.setValue( parts[1]) ;
////		//					repo.save(jsonLine);
////		//					repo.save(new JsonLine(java.sql.Timestamp.valueOf(LocalDateTime.now()),boDN,parts[0].substring(1,parts[0].length()-1), parts[1]));
////		//					service.createJsonLine(jsonLine);
////							log.info("save {} = {}",parts[0].substring(1,parts[0].length()-1), parts[1]);
////						} else {
////							System.out.println("ignoring line: " + line);
////						}
////					}
//	}

}
