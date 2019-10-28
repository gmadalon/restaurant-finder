package br.com.gmcb.restaurantfinder.controller.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import br.com.gmcb.restaurantfinder.domainobject.Restaurant;
import br.com.gmcb.restaurantfinder.model.RestaurantDTO;

@Mapper
public interface RestaurantMapper {

	RestaurantMapper MAPPER = Mappers.getMapper(RestaurantMapper.class);
	
    List<RestaurantDTO> makeListRestaurantDTO(Iterable<Restaurant> restaurants);
	
	@Mappings(value = {  })
	Restaurant makeRestaurant(RestaurantDTO restaurantDTO);

	@InheritInverseConfiguration
	RestaurantDTO makeRestaurantDTO(Restaurant restaurant);
	
    @AfterMapping
    default void setLatLong(@MappingTarget RestaurantDTO restaurantDTO, Restaurant restaurant) {
    	String location = restaurant.getLocation();
    	if(StringUtils.hasText(location)) {
        	location = location.substring(7, location.length()-1);
        	String arr[] = location.split(" ");
        	restaurantDTO.setLatitude(Double.valueOf(arr[1]));
        	restaurantDTO.setLongitude(Double.valueOf(arr[0]));
    	}
    	
    }
    
    @AfterMapping
    default void setLatLong(@MappingTarget Restaurant restaurant, RestaurantDTO restaurantDTO) {
    	
    	if( restaurantDTO.getLatitude() != null && restaurantDTO.getLongitude() != null ) {
    		restaurant.setLocation("POINT(" + restaurantDTO.getLongitude() + " " + restaurantDTO.getLatitude() + ")" );
    	}
    	
    }



}
