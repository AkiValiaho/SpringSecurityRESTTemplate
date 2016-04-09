package com.valiaho.DAO;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by akivv on 8.4.2016.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
    T save(T persisted);

    List<T> findAll();
}
