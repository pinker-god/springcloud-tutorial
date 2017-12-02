package com.kelan.web;

import com.kelan.entity.DataSourceConfig;
import com.kelan.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd031 on 2017/9/26.
 */
@RestController
public class TestController {

  @Autowired
  TestService testService;


  @GetMapping("/hello")

  public String test(@RequestParam String name) {
    return "hello" + name + "->" + testService.getConfig().getPort();
  }

  @GetMapping("/config")
  @HystrixCommand(defaultFallback = "errorConfig")
  public DataSourceConfig config() {
    return testService.getConfig();
  }

  public DataSourceConfig errorConfig() {
    return new DataSourceConfig("error", "error", "error", "error", "error");
  }

  @PostMapping("/config")
  public List<DataSourceConfig> dataSourceConfig(@RequestBody DataSourceConfig dataSource) {
    List<DataSourceConfig> list = new ArrayList<>();
    list.add(dataSource);
    list.add(testService.getConfig());
    return list;
  }

  @PutMapping("/config")
  public void sourceConfig(@RequestBody DataSourceConfig dataSource) {
    dataSource.setPort(testService.getConfig().getPort());
    dataSource.setUsername("Http PUT");
  }

  @DeleteMapping("/config")
  public void deleteDataSource(@RequestBody DataSourceConfig dataSource, @RequestParam String id) {
    testService.getConfig();
    System.out.println(dataSource + "->" + id);
  }

  @DeleteMapping("/config/{id}")
  public void deleteDataSource1(@RequestBody DataSourceConfig dataSource, @PathVariable String id) {
    testService.getConfig();
    System.out.println(dataSource + "->" + id);
  }
}
