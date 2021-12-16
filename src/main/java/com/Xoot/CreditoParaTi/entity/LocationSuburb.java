package com.Xoot.CreditoParaTi.entity;

import com.Xoot.CreditoParaTi.dto.LocationsSuburbDTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@NamedNativeQuery(name = "LocationSuburb.getDirectionToCp",
		query = "SELECT ls.id as idSuburb," +
				"ls.code as code," +
				"ls.name as name," +
				"ls.zip_code as zipCode," +
				"ls.type_suburb as typeSuburb," +
				"ls.counties_id as countiesId," +
				"ls.city_id  as cityId," +
				"ls.state_code  as stateCode," +
				"ls.counties_code  as countiesCode," +
				"ls.cities_code as citiesCode," +
				"lc.name as city,lcs.name  as county, ls2.name as state FROM locations_suburb ls " +
				"left join locations_cities lc " +
				"on lc.code = ls.cities_code " +
				"and lc.counties_code = ls.counties_code " +
				"and lc.state_code  = ls.state_code " +
				"left join locations_counties lcs " +
				"on lcs.code = ls.counties_code " +
				"and lcs.state_id  = ls.state_code " +
				"left join locations_states ls2 " +
				"on ls2.id = ls.state_code where zip_code = :zipcode ORDER by ls.name ",
		resultSetMapping = "Mapping.LocationsSuburbDTO")
@SqlResultSetMapping(name = "Mapping.LocationsSuburbDTO",
		classes = @ConstructorResult(targetClass = LocationsSuburbDTO.class,
				columns = {
						@ColumnResult(name = "idSuburb"),
						@ColumnResult(name = "code"),
						@ColumnResult(name = "name"),
						@ColumnResult(name = "zipCode"),
						@ColumnResult(name = "typeSuburb"),
						@ColumnResult(name = "countiesId"),
						@ColumnResult(name = "cityId"),
						@ColumnResult(name = "stateCode"),
						@ColumnResult(name = "countiesCode"),
						@ColumnResult(name = "citiesCode"),
						@ColumnResult(name = "state"),
						@ColumnResult(name = "county"),
						@ColumnResult(name = "city")
				}))
@Entity
@Table(name = "locations_suburb")
public class LocationSuburb implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idSuburb;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "type_suburb")
	private String typeSuburb;

	@Column(name = "counties_id")
	private Integer countiesId;

	@Column(name = "city_id")
	private Integer cityId;

	@Column(name = "state_code")
	private String stateCode;

	@Column(name = "counties_code")
	private String countiesCode;

	@Column(name = "cities_code")
	private String citiesCode;

	@Column(name = "status_flag")
	private Integer status_flag;


	@Column(name = "crtd_on")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date crtd_on;

	@Column(name = "crtd_by")
	private String crtd_by;

	@Column(name = "mdfd_on")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date mdfd_on;

	@Column(name = "mdfd_by")
	private String mdfd_by;

	@PrePersist
	public void prePersist() {
		crtd_on = new java.util.Date();
		mdfd_on = new java.util.Date();
		status_flag = 1;
	}

	public Integer getIdSuburb() {
		return idSuburb;
	}

	public void setIdSuburb(Integer idSuburb) {
		this.idSuburb = idSuburb;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTypeSuburb() {
		return typeSuburb;
	}

	public void setTypeSuburb(String typeSuburb) {
		this.typeSuburb = typeSuburb;
	}

	public Integer getCountiesId() {
		return countiesId;
	}

	public void setCountiesId(Integer countiesId) {
		this.countiesId = countiesId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountiesCode() {
		return countiesCode;
	}

	public void setCountiesCode(String countiesCode) {
		this.countiesCode = countiesCode;
	}

	public String getCitiesCode() {
		return citiesCode;
	}

	public void setCitiesCode(String citiesCode) {
		this.citiesCode = citiesCode;
	}

	public Integer getStatus_flag() {
		return status_flag;
	}

	public void setStatus_flag(Integer status_flag) {
		this.status_flag = status_flag;
	}

	public Date getCrtd_on() {
		return crtd_on;
	}

	public void setCrtd_on(Date crtd_on) {
		this.crtd_on = crtd_on;
	}

	public String getCrtd_by() {
		return crtd_by;
	}

	public void setCrtd_by(String crtd_by) {
		this.crtd_by = crtd_by;
	}

	public Date getMdfd_on() {
		return mdfd_on;
	}

	public void setMdfd_on(Date mdfd_on) {
		this.mdfd_on = mdfd_on;
	}

	public String getMdfd_by() {
		return mdfd_by;
	}

	public void setMdfd_by(String mdfd_by) {
		this.mdfd_by = mdfd_by;
	}
}
