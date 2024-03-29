package com.example.notesStorage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T extends BaseEntity<ID>, ID> extends JpaRepository<T, ID> {


}
