package com.example.neo4j_springboot.controller;

import com.example.neo4j_springboot.model.dto.CategoryDto;
import com.example.neo4j_springboot.model.request.SaveCategoryRequest;
import com.example.neo4j_springboot.service.CategoryService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

  private final CategoryService categoryService;


  @GetMapping("/{id}")
  public ResponseEntity<CategoryDto> getCategoryById(@PathVariable final UUID id,
      @RequestParam("withAttributes") Boolean withAttributes) {
    return ResponseEntity.ok(categoryService.getCategoryById(id, withAttributes));
  }

  @PostMapping
  public ResponseEntity<CategoryDto> saveCategory(@RequestBody final SaveCategoryRequest request) {
    return ResponseEntity.ok(categoryService.saveCategory(request));
  }

}