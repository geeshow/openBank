package com.ken207.openbank.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Getter @Setter
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity<T extends BaseEntity<?>> implements Comparable<T> {

    @Id
    @GeneratedValue
    private Long id;

//    @JsonProperty(access = READ_ONLY)
//    @Column(name="CREATED_DATE",nullable=false,updatable=false)
//    @CreatedDate
//    private LocalDateTime createdDate;
//
//    @JsonProperty(access = READ_ONLY)
//    @Column(name="UPDATED_DATE",nullable=false)
//    @LastModifiedDate
//    private LocalDateTime modifiedDate;

    @Override
    public int compareTo(T o) {
        if(this == o) return 0;
        return Long.compare(this.getId(), o.getId());
    }

}
