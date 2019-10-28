package br.com.gmcb.restaurantfinder.repository;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gmcb.restaurantfinder.domainobject.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, UUID> {
	
    @Query(value = "SELECT re  FROM Restaurant re WHERE dwithin(re.location, :point, 0.000013841198* :distance) = true")
    @Cacheable("findByPosition")
    Iterable<Restaurant> findByPosition(@Param("point") String point, @Param("distance") double distance );

}
