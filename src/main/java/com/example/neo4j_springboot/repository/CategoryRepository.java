package com.example.neo4j_springboot.repository;

import com.example.neo4j_springboot.model.node.CategoryNode;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends Neo4jRepository<CategoryNode, UUID> {

  @Query("MATCH path = (n:Category)-[:CATEGORY_PARENT_OF]->(m:Category) RETURN n, relationships(path) as rels, m")
  List<CategoryNode> getCategoryNodeByDepth(@Param("depthParam") Integer depth);


  @Query(value = "MATCH (c:CategoryNode), (s:AttributeKeyNode) " +
      "WHERE c.id = $categoryId AND s.id in :#{#attributeKeys.keySet()} " +
      "WITH c, s, $attributeKeys AS attributeKeys " +
      "CREATE (c)-[r:CATEGORY_ATTRIBUTE {required: attributeKeys[s.id]}]->(s)")
  void createRelationBetweenAttribute(UUID categoryId, Map<String, Boolean> attributeKeys);

}