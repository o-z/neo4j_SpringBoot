package com.example.neo4j_springboot.model.node;

import com.example.neo4j_springboot.model.dto.AttributeValueDto;
import com.example.neo4j_springboot.model.node.base.BaseNode;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AttributeValueNode extends BaseNode implements Serializable {

  @Serial
  private static final long serialVersionUID = 1905122041950251207L;

  private String name;

  public AttributeValueDto toDto() {
    return AttributeValueDto.builder()
        .id(super.getId())
        .name(name)
        .createdDate(super.getCreatedDate())
        .updatedDate(super.getUpdatedDate())
        .build();
  }
}
