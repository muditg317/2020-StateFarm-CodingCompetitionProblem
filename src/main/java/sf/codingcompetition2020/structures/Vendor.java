package sf.codingcompetition2020.structures;

import java.util.List;

public class Vendor {
	private int vendorId;
	private String area;
	private int vendorRating;
	private boolean inScope;

	/**
	* Default Vendor constructor
	*/
	public Vendor(int vendorId, String area, int vendorRating, boolean inScope) {
		this.vendorId = vendorId;
		this.area = area;
		this.vendorRating = vendorRating;
		this.inScope = inScope;
	}

	/**
	 * constructor based on an entry in a CSV
	 * @param csvEntry
	 */
	public Vendor(List<String> csvEntry) {
		this(
				Integer.parseInt(csvEntry.get(0)),
				csvEntry.get(1),
				Integer.parseInt(csvEntry.get(2)),
				Boolean.parseBoolean(csvEntry.get(3)));
	}

	/**
	 * Builder constructor
	 * @param builder
	 */
	public Vendor(Builder builder) {
		this(
				builder.vendorId,
				builder.area,
				builder.vendorRating,
				builder.inScope);
	}

	/**
	* Returns value of vendorId
	* @return
	*/
	public int getVendorId() {
		return vendorId;
	}

	/**
	* Sets new value of vendorId
	* @param
	*/
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	/**
	* Returns value of area
	* @return
	*/
	public String getArea() {
		return area;
	}

	/**
	* Sets new value of area
	* @param
	*/
	public void setArea(String area) {
		this.area = area;
	}

	/**
	* Returns value of vendorRating
	* @return
	*/
	public int getVendorRating() {
		return vendorRating;
	}

	/**
	* Sets new value of vendorRating
	* @param
	*/
	public void setVendorRating(int vendorRating) {
		this.vendorRating = vendorRating;
	}

	/**
	* Returns value of inScope
	* @return
	*/
	public boolean isInScope() {
		return inScope;
	}

	/**
	* Sets new value of inScope
	* @param
	*/
	public void setInScope(boolean inScope) {
		this.inScope = inScope;
	}

	@Override
	public String toString() {
		return "Vendor{" +
				"vendorId=" + vendorId +
				", area='" + area + '\'' +
				", vendorRating=" + vendorRating +
				", inScope=" + inScope +
				'}';
	}

	public static class Builder {
		 private int vendorId;
		 private String area;
		 private int vendorRating;
		 private boolean inScope;

 		public static Builder newBuilder() {
 			return new Builder();
 		}

		 public Builder vendorId(int vendorId) {
			 this.vendorId = vendorId;
			 return this;
		}

		 public Builder area(String area) {
			 this.area = area;
			 return this;
		}

		 public Builder vendorRating(int vendorRating) {
			 this.vendorRating = vendorRating;
			 return this;
		}

		 public Builder inScope(boolean inScope) {
			 this.inScope = inScope;
			 return this;
		}

		public Vendor build() {
			return new Vendor(this);
		}
	}
}
