package be.belfius.sbjmscrud.sendreceive;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import be.belfius.sbjmscrud.model.Tagfile;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {

  @JmsListener(destination = "${springjms.myQueue}", containerFactory = "myFactory")
  public void receiveMessage(String tagfile) {  // or String or Tagfile
	  log.info("Receiver is receiving");
	  System.out.println("*******************");
    System.out.println("Artemis Received <" + tagfile + ">\n");
//    return tagfile;
  }
//  -------------------------------------------------------------
  @JmsListener(destination = "tagbox", containerFactory = "myFactory") //, containerFactory = "myFactory"
  public void receiveTagfile(Tagfile tagfile) {
    System.out.println("receiveTagfile <" + tagfile + ">");
  }
//-------------------------------------------------------------
  @JmsListener(destination = "tagBox", containerFactory = "myFactory") //, containerFactory = "myFactory"
  public void receiveTagfileB(Tagfile tagfile) {
    System.out.println("receiveTagfileB <" + tagfile + ">");
  }

}