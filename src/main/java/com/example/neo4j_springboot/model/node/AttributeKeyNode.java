package com.example.neo4j_springboot.model.node;

import com.example.neo4j_springboot.model.dto.AttributeKeyDto;
import com.example.neo4j_springboot.model.node.base.BaseNode;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.util.CollectionUtils;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AttributeKeyNode extends BaseNode implements Serializable {

  @Serial
  private static final long serialVersionUID = 7902171952183833726L;

  private String name;
  @Relationship(type = "ATTRIBUTE_VALUE", direction = Relationship.Direction.OUTGOING)
  private List<AttributeValueNode> attributeValueList;

  public AttributeKeyDto toDto() {
    return AttributeKeyDto.builder()
        .id(super.getId())
        .name(name)
        .attributeValueMap(
            !CollectionUtils.isEmpty(attributeValueList) ? attributeValueList.stream()
                .collect(Collectors.toMap(BaseNode::getId,
                    AttributeValueNode::toDto)) : null)
        .createdDate(super.getCreatedDate())
        .updatedDate(super.getUpdatedDate())
        .build();
  }
}
