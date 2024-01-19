package com.example.neo4j_springboot.service;

import com.example.neo4j_springboot.model.dto.CategoryDto;
import com.example.neo4j_springboot.model.node.CategoryNode;
import com.example.neo4j_springboot.model.request.SaveCategoryRequest;
import com.example.neo4j_springboot.repository.CategoryRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

  private final CategoryRepository categoryRepository;

  @Cacheable(value = "category", key = "#id + '_' + #withAttributes", cacheManager = "oneMinuteCacheManager", unless = "#result==null")
  @Transactional(readOnly = true)
  public CategoryDto getCategoryById(final UUID id, Boolean withAttributes) {
    Optional<CategoryNode> categoryNode = categoryRepository.findById(id);
    return categoryNode.map(category -> category.toDto(withAttributes)).orElse(null);
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
      categoryRepository.findById(request.getParentCategoryId())
          .ifPresent(parentCategory -> {
            if (CollectionUtils.isEmpty(parentCategory.getChildCategories())) {
              parentCategory.setChildCategories(List.of(savedCategoryNode));
            } else {
              parentCategory.getChildCategories().add(savedCategoryNode);
            }
            parentCategory.setDeepest(false);
            categoryRepository.save(parentCategory);
          });
    }

    if (!request.getAttributeIdAndRequiredStatus().isEmpty()) {
      categoryRepository.createRelationBetweenAttribute(savedCategoryNode.getId(),
          request.getAttributeIdAndRequiredStatus());
    }

    return savedCategoryNode.toDto(true);
  }
}