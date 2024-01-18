package com.example.neo4j_springboot.controller;

import com.example.neo4j_springboot.model.dto.CategoryDto;
import com.example.neo4j_springboot.model.request.SaveCategoryRequest;
import com.example.neo4j_springboot.service.CategoryService;
import java.util.Map;
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
  public ResponseEntity<CategoryDto> getCategoryById(@PathVariable final UUID id) {
    return ResponseEntity.ok(categoryService.getCategoryById(id));
  }

  @GetMapping
  public ResponseEntity<Map<UUID, CategoryDto>> getAllCategories(
      @RequestParam("deepest") Boolean deepest,
      @RequestParam("page") Integer page,
      @RequestParam("Size") Integer size) {
    return ResponseEntity.ok(categoryService.getAllCategories(deepest, page, size));
  }


  @PostMapping
  public ResponseEntity<CategoryDto> saveCategory(@RequestBody final SaveCategoryRequest request) {
    return ResponseEntity.ok(categoryService.saveCategory(request));
  }

}