package com.example.neo4j_springboot.model.request;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Data;

@Data
public class SaveCategoryRequest {

  private String name;
  private UUID parentCategoryId;
  private Boolean deepest;
  private Map<String, Boolean> attributeIdAndRequiredStatus = new HashMap<>();
  ;


}
