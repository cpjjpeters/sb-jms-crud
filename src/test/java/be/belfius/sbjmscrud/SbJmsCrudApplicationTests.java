package be.belfius.sbjmscrud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.belfius.sbjmscrud.sendreceive.MessageSender;


@SpringBootTest
class SbJmsCrudApplicationTests {

	@Autowired
	MessageSender sender;
	@Test
	void contextLoads() {
		sender.send("Hello Spring JMS !!!");
	}

}
