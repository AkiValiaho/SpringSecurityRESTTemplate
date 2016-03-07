package com.valiaho.Repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by akivv on 4.3.2016.
 */
@NoRepositoryBean
interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
    //Add common interface methods like simple CRUD operations
    void delete(T deleted);
    List<T> findAll();
    Optional<T> findOne(ID id);
    T save(T persisted);
}
