package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Specimen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String latinName;
	private String origin;
	private String storageLocation;
	private Date dateArrived;
	private String description;

	public Specimen(String latinName, String origin, String storageLocation, Date dateArrived, String description) {
		super();
		this.latinName = latinName;
		this.origin = origin;
		this.storageLocation = storageLocation;
		this.dateArrived = dateArrived;
		this.description = description;
	}

	public Specimen(Long id, String latinName, String origin, String storageLocation, Date dateArrived,
			String description) {
		super();
		this.id = id;
		this.latinName = latinName;
		this.origin = origin;
		this.storageLocation = storageLocation;
		this.dateArrived = dateArrived;
		this.description = description;
	}

	public Specimen() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public Date getDateArrived() {
		return dateArrived;
	}

	public void setDateArrived(Date dateArrived) {
		this.dateArrived = dateArrived;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateArrived == null) ? 0 : dateArrived.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((latinName == null) ? 0 : latinName.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((storageLocation == null) ? 0 : storageLocation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specimen other = (Specimen) obj;
		if (dateArrived == null) {
			if (other.dateArrived != null)
				return false;
		} else if (!dateArrived.equals(other.dateArrived))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latinName == null) {
			if (other.latinName != null)
				return false;
		} else if (!latinName.equals(other.latinName))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (storageLocation == null) {
			if (other.storageLocation != null)
				return false;
		} else if (!storageLocation.equals(other.storageLocation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Specimen [id=" + id + ", latinName=" + latinName + ", origin=" + origin + ", storageLocation="
				+ storageLocation + ", dateArrived=" + dateArrived + ", description=" + description + "]";
	}

}
