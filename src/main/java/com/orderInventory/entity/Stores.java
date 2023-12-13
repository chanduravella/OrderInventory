package com.orderInventory.entity;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Stores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storeId;
	private String storeName;
	private String webAddress;
	private String physicalAddress;
	private double latitude;
	private double longitude;
	private String logoMimeType;
	private String logoFileName;
	private String logoCharset;
	private Date logoLastUpdated;

}
