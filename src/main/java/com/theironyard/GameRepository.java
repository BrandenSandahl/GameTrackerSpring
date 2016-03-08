package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by branden on 3/8/16 at 11:55.
 */

public interface GameRepository extends CrudRepository<Game, Integer> {
}
