package com.example.neo4j_springboot.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValueDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1905122041950251207L;

  private UUID id;
  private String name;
  private Date createdDate;
  private Date updatedDate;
}
