package br.com.gmcb.restaurantfinder.domainobject;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;


import lombok.Data;

@Data
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;
	
	@Column(nullable = true, columnDefinition = "GEOMETRY")
	private String location;
	
	
	
}
