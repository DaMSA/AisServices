package dk.dma.aisservices.core.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the ais_vessel_track database table.
 * 
 */
@NamedQueries({ @NamedQuery(name = "AisVesselTrack:get", query = "SELECT vt FROM AisVesselTrack vt WHERE vt.mmsi = :mmsi AND vt.time > :from ORDER BY vt.id DESC") })
@Entity
@Table(name = "ais_vessel_track")
public class AisVesselTrack implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int mmsi;
	private Double lat;
	private Double lon;
	private Double sog;
	private Double cog;
	private Date time;
	private Date validTo;
	private Date created;
	private AisVesselTarget aisVesselTarget;

	public AisVesselTrack() {
		this.created = new Date();
	}
	
	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false, updatable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable = false)
	public int getMmsi() {
		return this.mmsi;
	}

	public void setMmsi(int mmsi) {
		this.mmsi = mmsi;
	}

	public Double getCog() {
		return this.cog;
	}

	@Column(nullable = false)
	public void setCog(Double cog) {
		this.cog = cog;
	}

	@Column(nullable = false)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Column(nullable = false)
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Column(name="valid_to", nullable = false)
	public Date getValidTo() {
		return validTo;
	}
	
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	@Column(nullable = false)
	public Double getLat() {
		return this.lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Column(nullable = false)
	public Double getLon() {
		return this.lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	@Column(nullable = false)
	public Double getSog() {
		return this.sog;
	}

	public void setSog(Double sog) {
		this.sog = sog;
	}

	// bi-directional one-to-one association to AisVesselTarget
	@OneToOne
	@JoinColumn(name = "mmsi", nullable = false, insertable = false, updatable = false)
	public AisVesselTarget getAisVesselTarget() {
		return this.aisVesselTarget;
	}

	public void setAisVesselTarget(AisVesselTarget aisVesselTarget) {
		this.aisVesselTarget = aisVesselTarget;
	}

	@Transient
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AisVesselTrack [id=");
		builder.append(id);
		builder.append(", mmsi=");
		builder.append(mmsi);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lon=");
		builder.append(lon);
		builder.append(", sog=");
		builder.append(sog);
		builder.append(", cog=");
		builder.append(cog);
		builder.append(", time=");
		builder.append(time);
		builder.append(", validTo =");
		builder.append(validTo);
		builder.append(", created=");
		builder.append(created);
		builder.append("]");
		return builder.toString();
	}

}