package com.example.neo4j_springboot.model.node;

import com.example.neo4j_springboot.model.dto.CategoryDto;
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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryNode extends BaseNode implements Serializable {

  @Serial
  private static final long serialVersionUID = 1905122041950251207L;

  private String name;
  private Boolean deepest;
  @Relationship(type = "CATEGORY_PARENT_OF", direction = Relationship.Direction.OUTGOING)
  private List<CategoryNode> childCategories;
  @Relationship(type = "CATEGORY_ATTRIBUTE", direction = Relationship.Direction.OUTGOING)
  private List<CategoryToAttributeRelation> attributes;

  public CategoryDto toDto(Boolean withAttributes) {
    return CategoryDto.builder()
        .id(super.getId())
        .name(name)
        .deepest(deepest)
        .childCategoryMap(!CollectionUtils.isEmpty(childCategories) ? childCategories.stream()
            .collect(Collectors.toMap(BaseNode::getId,
                categoryNode -> categoryNode.toDto(withAttributes))) : null)
        .attributeKeyMap(
            Boolean.TRUE.equals(withAttributes) && !CollectionUtils.isEmpty(attributes)
                ? attributes.stream()
                .collect(Collectors.toMap(attribute -> attribute.getAttributeKey().getId(),
                    attribute -> attribute.getAttributeKey().toDto())) : null)
        .createdDate(super.getCreatedDate())
        .updatedDate(super.getUpdatedDate())
        .build();
  }
}
