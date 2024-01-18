package com.example.neo4j_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;

@EnableNeo4jAuditing
@EnableCaching
@SpringBootApplication
public class Neo4jSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(Neo4jSpringBootApplication.class, args);
  }

}
