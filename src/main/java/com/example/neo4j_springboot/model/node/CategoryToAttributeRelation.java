package com.example.neo4j_springboot.model.node;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryToAttributeRelation implements Serializable {

  @Serial
  private static final long serialVersionUID = 5582209938326034698L;

  @RelationshipId
  private Long id;
  private Boolean required;
  @TargetNode
  private AttributeKeyNode attributeKey;
}
