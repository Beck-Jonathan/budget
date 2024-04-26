package com.beck.javaiii_kirkwood.learnx.controllers;

import com.beck.javaiii_kirkwood.shared.MyDecoder;
import com.beck.javaiii_kirkwood.shared.MyEncoder;
import com.beck.javaiii_kirkwood.shared.MyJson;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Flow;

@ServerEndpoint(
    value = "/group-chat/endpoint",
    encoders = {MyEncoder.class},
    decoders = {MyDecoder.class}
)
public class GroupChatEndpoint {
  private static final Set<Session> subscribers = Collections.synchronizedSet(new HashSet<Session>());

  @OnOpen
  public void onOpen(Session session) {
    subscribers.add(session);
    System.out.println("Subscriber count: " + subscribers.size());
  }
  @OnClose
  public void onClose(Session session) {
    subscribers.remove(session);
    System.out.println("Subscriber count: " + subscribers.size());
  }
  @OnError
  public void onError(Throwable throwable) {
    System.err.println("ERROR: " + throwable.getMessage());
  }

  @OnMessage
  public void onMessage(MyJson myJson, Session session) throws EncodeException, IOException {
    for (Session subscriber : subscribers){
      if(!subscriber.equals(session)) {
        subscriber.getBasicRemote().sendObject(myJson);
      }
    }


  }

}
