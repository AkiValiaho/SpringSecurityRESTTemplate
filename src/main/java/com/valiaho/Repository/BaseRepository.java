package com.valiaho.Repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Created by akivv on 4.3.2016.
 */
@NoRepositoryBean
interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
    //Add common interface methods like simple CRUD operations

}
