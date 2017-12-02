package org.sselab.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sselab.entity.DataSource;
import org.sselab.entity.Pinker;
import org.sselab.service.RestService;


@RestController
public class TestController {

  @Autowired
  RestService service;

  @GetMapping("/config")
  public DataSource getDataSourceByRest(@RequestParam(required = false) String port) {
    return service.getConfigByRest(port);
  }


  @GetMapping(value = "/pinker", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Pinker getPinkerFromConfigServer() {
    return service.getPinkerFromConfigServer();
  }
}

