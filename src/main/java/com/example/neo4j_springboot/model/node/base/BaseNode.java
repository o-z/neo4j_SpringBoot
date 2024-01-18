package com.example.neo4j_springboot.model.node.base;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@Data
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseNode implements Serializable {

  @Serial
  private static final long serialVersionUID = 1990514793103401195L;

  @Id
  @GeneratedValue(GeneratedValue.UUIDGenerator.class)
  private UUID id;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date updatedDate;

  @Version
  private Long version;
}
