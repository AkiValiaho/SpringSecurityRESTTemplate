package com.valiaho.Repository;

/**
 * Created by akivv on 2.3.2016.
 */

import com.valiaho.Domain.PersistedUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistedUserRepository extends CrudRepository<PersistedUser, Long> {

}
