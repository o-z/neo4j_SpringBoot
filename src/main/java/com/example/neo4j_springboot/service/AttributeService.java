package com.example.neo4j_springboot.service;

import com.example.neo4j_springboot.model.dto.AttributeKeyDto;
import com.example.neo4j_springboot.model.node.AttributeKeyNode;
import com.example.neo4j_springboot.model.node.AttributeValueNode;
import com.example.neo4j_springboot.model.request.SaveAttributeRequest;
import com.example.neo4j_springboot.repository.AttributeRepository;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttributeService {

  private final AttributeRepository attributeRepository;

  @Cacheable(value = "attribute", key = "#id", cacheManager = "oneMinuteCacheManager", unless = "#result==null")
  @Transactional(readOnly = true)
  public AttributeKeyDto getAttributeById(final UUID id) {
    return attributeRepository.findById(id).map(AttributeKeyNode::toDto).orElse(null);
  }

  @CachePut(value = "attribute", key = "#result.id", cacheManager = "oneMinuteCacheManager", unless = "#result==null")
  @Transactional
  public AttributeKeyDto saveAttribute(final SaveAttributeRequest request) {

    AttributeKeyNode attributeKeyNode = AttributeKeyNode.builder()
        .name(request.getName())
        .attributeValueList(
            request.getValues().stream().map(value -> AttributeValueNode.builder()
                    .name(value)
                    .build())
                .collect(Collectors.toList())

        )
        .build();
    return attributeRepository.save(attributeKeyNode).toDto();
  }
}