package com.example.neo4j_springboot.service;

import com.example.neo4j_springboot.model.dto.CategoryDto;
import com.example.neo4j_springboot.model.node.CategoryNode;
import com.example.neo4j_springboot.model.request.SaveCategoryRequest;
import com.example.neo4j_springboot.repository.CategoryRepository;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

  private final CategoryRepository categoryRepository;

  @Cacheable(value = "category", key = "#id", cacheManager = "oneMinuteCacheManager", unless = "#result==null")
  @Transactional(readOnly = true)
  public CategoryDto getCategoryById(final UUID id) {
    Optional<CategoryNode> categoryNode = categoryRepository.findById(id);
    return categoryNode.map(CategoryNode::toDto).orElse(null);
  }

  @Transactional
  public Map<UUID, CategoryDto> getAllCategories(final Boolean deepest, Integer page,
      Integer size) {
    Page<CategoryNode> categoryNodeEntities = categoryRepository.getCategoryNodeByDeepest(
        deepest, Pageable.ofSize(size).withPage(page));
    return categoryNodeEntities.stream().collect(
        Collectors.toMap(CategoryNode::getId, CategoryNode::toDto));
  }

  @Transactional
  @CachePut(value = "category", key = "#result.id", cacheManager = "oneMinuteCacheManager", unless = "#result==null")
  public CategoryDto saveCategory(final SaveCategoryRequest request) {

    CategoryNode categoryNode = CategoryNode.builder()
        .name(request.getName())
        .deepest(request.getDeepest())
        .createdDate(new Date())
        .build();

    CategoryNode savedCategoryNode = categoryRepository.save(categoryNode);
    if (request.getParentCategoryId() != null) {
      categoryRepository.createRelationBetweenChildToParent(savedCategoryNode.getId(),
          request.getParentCategoryId());

    }
    if (!request.getAttributeIdAndRequiredStatus().isEmpty()) {

      categoryRepository.createRelationBetweenAttribute(savedCategoryNode.getId(),
          request.getAttributeIdAndRequiredStatus());
    }
    return savedCategoryNode.toDto();
  }
}