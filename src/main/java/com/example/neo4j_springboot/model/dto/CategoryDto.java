package com.example.neo4j_springboot.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1905122041950251207L;

  private UUID id;
  private String name;
  private Boolean deepest;
  private Map<UUID, CategoryDto> childCategoryMap;
  private Map<UUID, AttributeKeyDto> attributeKeyMap;
  private Date createdDate;
  private Date updatedDate;

}
