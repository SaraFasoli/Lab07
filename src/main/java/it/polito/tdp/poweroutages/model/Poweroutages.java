package it.polito.tdp.poweroutages.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Poweroutages  implements Comparable<Poweroutages>{
	private Integer id;
	private Integer event_type_id;
	private Integer tag_id;
	private Integer area_id;
	private Integer nerc_id;
	private Integer responsible_id;
	private Integer customers_affected;
	private LocalDateTime date_event_began;
	private LocalDateTime date_event_finished;
	private int year;
	private long hours;
	
	public Poweroutages(Integer id, Integer event_type_id, Integer tag_id, Integer area_id, Integer nerc_id,
			Integer responsible_id, Integer customers_affected, LocalDateTime date_event_began,
			LocalDateTime date_event_finished) {
		this.id = id;
		this.event_type_id = event_type_id;
		this.tag_id = tag_id;
		this.area_id = area_id;
		this.nerc_id = nerc_id;
		this.responsible_id = responsible_id;
		this.customers_affected = customers_affected;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		year=this.date_event_began.getYear();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEvent_type_id() {
		return event_type_id;
	}

	public void setEvent_type_id(Integer event_type_id) {
		this.event_type_id = event_type_id;
	}

	public Integer getTag_id() {
		return tag_id;
	}

	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}

	public Integer getArea_id() {
		return area_id;
	}

	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}

	public Integer getNerc_id() {
		return nerc_id;
	}

	public void setNerc_id(Integer nerc_id) {
		this.nerc_id = nerc_id;
	}

	public Integer getResponsible_id() {
		return responsible_id;
	}

	public void setResponsible_id(Integer responsible_id) {
		this.responsible_id = responsible_id;
	}

	public Integer getCustomers_affected() {
		return customers_affected;
	}

	public void setCustomers_affected(Integer customers_affected) {
		this.customers_affected = customers_affected;
	}

	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}

	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}

	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}

	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}
	
	public long calcolaOre() {
		hours=date_event_began.until(date_event_finished, ChronoUnit.HOURS);
		return hours;
	}
	
	
	

	public int getYear() {
		return year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	

	@Override
	public int compareTo(Poweroutages o) {
		// TODO Auto-generated method stub
		return this.date_event_began.compareTo(o.date_event_began);
	}

	@Override
	public String toString() {
		return ""+year+" "+date_event_began+" "+date_event_finished+" "+this.calcolaOre()+" "+customers_affected;
				
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poweroutages other = (Poweroutages) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
