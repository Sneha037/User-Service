package org.example.userservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel
{
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long Id;

      @CreatedDate
      private Date createdAt;

      @LastModifiedDate
      private Date lastModifiedAt;
}
