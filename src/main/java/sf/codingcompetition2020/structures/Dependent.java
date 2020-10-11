package sf.codingcompetition2020.structures;

public class Dependent {
	private String firstName;
	private String lastName;

	/**
	* Default Dependent constructor
	*/
	public Dependent(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Builder constructor
	 * @param builder
	 */
	public Dependent(Builder builder) {
		this(builder.firstName, builder.lastName);
	}

	/**
	* Returns value of firstName
	* @return
	*/
	public String getFirstName() {
		return firstName;
	}

	/**
	* Sets new value of firstName
	* @param
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	* Returns value of lastName
	* @return
	*/
	public String getLastName() {
		return lastName;
	}

	/**
	* Sets new value of lastName
	* @param
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static class Builder {
		 private String firstName;
		 private String lastName;

 		public static Builder newBuilder() {
 			return new Builder();
 		}

		 public Builder firstName(String firstName) {
			 this.firstName = firstName;
			 return this;
		}

		 public Builder lastName(String lastName) {
			 this.lastName = lastName;
			 return this;
		}

		public Dependent build() {
			return new Dependent(this);
		}
	}
}
