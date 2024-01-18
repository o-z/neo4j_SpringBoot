package com.example.neo4j_springboot.repository;

import com.example.neo4j_springboot.model.node.CategoryNode;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends Neo4jRepository<CategoryNode, UUID> {

  Page<CategoryNode> getCategoryNodeByDeepest(Boolean deepest, Pageable pageable);

  @Query(value = "MATCH (c:CategoryNode), (p:CategoryNode) " +
      "WHERE c.id = $childId AND p.id = $parentId " +
      "CREATE (c)-[r:CATEGORY_CHILD_OF]->(p) SET p.deepest = false")
  void createRelationBetweenChildToParent(UUID childId, UUID parentId);

  @Query(value = "MATCH (c:CategoryNode), (s:AttributeKeyNode) " +
      "WHERE c.id = $categoryId AND s.id in :#{#attributeKeys.keySet()} " +
      "WITH c, s, $attributeKeys AS attributeKeys " +
      "CREATE (c)-[r:CATEGORY_ATTRIBUTE {required: attributeKeys[s.id]}]->(s)")
  void createRelationBetweenAttribute(UUID categoryId, Map<String, Boolean> attributeKeys);

}