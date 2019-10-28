package br.com.gmcb.restaurantfinder.controller.delegate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.gmcb.restaurantfinder.RestaurantsApiDelegate;
import br.com.gmcb.restaurantfinder.controller.mapper.RestaurantMapper;
import br.com.gmcb.restaurantfinder.domainobject.Restaurant;
import br.com.gmcb.restaurantfinder.model.RestaurantDTO;
import br.com.gmcb.restaurantfinder.repository.RestaurantRepository;

@Component
public class RestaurantsApiDelegateImpl implements RestaurantsApiDelegate {

	RestaurantRepository restaurantRepository;
	
	@Autowired
	public RestaurantsApiDelegateImpl (RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}
	@Override
	public ResponseEntity<RestaurantDTO> create(RestaurantDTO restaurantDTO) {
		Restaurant restaurant = RestaurantMapper.MAPPER.makeRestaurant(restaurantDTO);
		restaurant.setId(null);
		restaurant = restaurantRepository.save(restaurant);
		return new ResponseEntity<RestaurantDTO>(RestaurantMapper.MAPPER.makeRestaurantDTO(restaurant),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Void> delete(UUID restaurantId) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		if(restaurant.isPresent()) {
			restaurantRepository.delete(restaurant.get());
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RestaurantDTO>> getAll() {
		
		Iterable<Restaurant> restaurants = restaurantRepository.findAll();

		
		return new ResponseEntity<List<RestaurantDTO>>(RestaurantMapper.MAPPER.makeListRestaurantDTO(restaurants),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RestaurantDTO> getById(UUID restaurantId) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		if(restaurant.isPresent()) {
			return new ResponseEntity<RestaurantDTO>( RestaurantMapper.MAPPER.makeRestaurantDTO(restaurant.get()), HttpStatus.OK);		
		} else {
			return new ResponseEntity<RestaurantDTO>( HttpStatus.NOT_FOUND);		
		}
		
	}

	@Override
	public ResponseEntity<List<RestaurantDTO>> restaurantsSearch(Double lat, Double _long, Integer distance) {
		
		Iterable<Restaurant> restaurants =restaurantRepository.findByPosition("POINT(" + _long + " " + lat +")", distance);
		return new ResponseEntity<List<RestaurantDTO>>(RestaurantMapper.MAPPER.makeListRestaurantDTO(restaurants),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> update(UUID restaurantId, RestaurantDTO restaurantDTO) {
		Restaurant restaurant = RestaurantMapper.MAPPER.makeRestaurant(restaurantDTO);
		restaurantRepository.save(restaurant);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}
