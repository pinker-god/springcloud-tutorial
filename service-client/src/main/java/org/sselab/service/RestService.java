package org.sselab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.sselab.entity.DataSource;
import org.sselab.entity.Pinker;

import java.util.Optional;

@Service
public class RestService {

  @Autowired
  RestTemplate restTemplate;

  @Value("${pinker.name}")
  private String name;
  @Value("${pinker.pwd}")
  private String pwd;
  @Value("${pinker.grade}")
  private String grade;

  public DataSource getConfigByRest(String port) {
    Optional<String> p = Optional.ofNullable(port);
    return restTemplate.getForObject("http://localhost:" + p.orElse("8506") + "/service-a/config?accessToken=jhg", DataSource.class);
  }

  public Pinker getPinkerFromConfigServer() {
    return new Pinker(name, pwd, grade);
  }
}
