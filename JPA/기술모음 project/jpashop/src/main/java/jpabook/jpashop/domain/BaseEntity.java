package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter
public class BaseEntity {
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
