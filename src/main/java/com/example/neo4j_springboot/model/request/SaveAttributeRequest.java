package com.example.neo4j_springboot.model.request;

import java.util.List;
import lombok.Data;

@Data
public class SaveAttributeRequest {

  private String name;
  private List<String> values;
}
