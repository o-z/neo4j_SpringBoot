package com.example.neo4j_springboot.repository;

import com.example.neo4j_springboot.model.node.AttributeKeyNode;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends Neo4jRepository<AttributeKeyNode, UUID> {

}