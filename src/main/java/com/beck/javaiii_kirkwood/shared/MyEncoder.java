package com.beck.javaiii_kirkwood.shared;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class MyEncoder implements Encoder.Text<MyJson> {


  @Override
  public String encode(MyJson myJson) throws EncodeException {
    return myJson.toString();
  }
}
