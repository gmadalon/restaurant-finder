package br.com.gmcb.restaurantfinder.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.gmcb.restaurantfinder.domainobject.Restaurant;

@Component
public class DataLoader implements ApplicationRunner {

	private RestaurantRepository restaurantRepository;

	@Autowired
	public DataLoader(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	public void run(ApplicationArguments args) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName("Restaurant Name");
		restaurant.setDescription("Description of the restaurant");
		restaurant.setLocation("POINT(13.341432 52.504891)");
		restaurantRepository.save(restaurant);
	}
}