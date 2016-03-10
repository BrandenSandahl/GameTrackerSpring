package com.theironyard.Services;

import com.theironyard.Entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by branden on 3/9/16 at 11:28.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User findFirstByName(String userName);
}
