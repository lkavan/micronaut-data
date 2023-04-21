
package example;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;

@MongoRepository
public interface CarManufacturer3Repository extends CrudRepository<CarManufacturer3, Long> {
}
