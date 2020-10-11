package sf.codingcompetition2020.structures;

public class Agent {

	private int agentId;
	private String area;
	private String language;
	private String firstName;
	private String lastName;

	/**
	 * Default Agent constructor
	 */
	private Agent(int agentId, String area, String language, String firstName, String lastName) {
		this.agentId = agentId;
		this.area = area;
		this.language = language;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Builder constructor
	 * @param builder
	 */
	public Agent(Builder builder) {
		this(
				builder.agentId,
				builder.area,
				builder.language,
				builder.firstName,
				builder.lastName);
	}

	/**
	 * Returns value of agentId
	 * @return
	 */
	public int getAgentId() {
		return agentId;
	}

	/**
	 * Sets new value of agentId
	 * @param
	 */
	public void setAgentId(int agentId) {
		this.agentId = agentId;
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
	 * Returns value of language
	 * @return
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets new value of language
	 * @param
	 */
	public void setLanguage(String language) {
		this.language = language;
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
		private int agentId;
		private String area;
		private String language;
		private String firstName;
		private String lastName;

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder agentId(int agentId) {
			this.agentId = agentId;
			return this;
		}

		public Builder area(String area) {
			this.area = area;
			return this;
		}

		public Builder language(String language) {
			this.language = language;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Agent build() {
			return new Agent(this);
		}
	}
}
