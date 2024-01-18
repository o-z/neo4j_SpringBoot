package com.example.neo4j_springboot.controller;

import com.example.neo4j_springboot.model.dto.AttributeKeyDto;
import com.example.neo4j_springboot.model.request.SaveAttributeRequest;
import com.example.neo4j_springboot.service.AttributeService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attributes")
@RequiredArgsConstructor
@Slf4j
public class AttributeController {

  private final AttributeService attributeService;


  @GetMapping("/{id}")
  public ResponseEntity<AttributeKeyDto> getAttributeById(@PathVariable final UUID id) {
    return ResponseEntity.ok(attributeService.getAttributeById(id));
  }


  @PostMapping
  public ResponseEntity<AttributeKeyDto> saveAttribute(
      @RequestBody final SaveAttributeRequest request) {
    return ResponseEntity.ok(attributeService.saveAttribute(request));
  }
}