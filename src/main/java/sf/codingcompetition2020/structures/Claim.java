package sf.codingcompetition2020.structures;

import java.util.List;

public class Claim {

	private int claimId;
	private int customerId;
	private boolean closed;
	private int monthsOpen;

	/**
	* Default Claim constructor
	*/
	public Claim(int claimId, int customerId, boolean closed, int monthsOpen) {
		this.claimId = claimId;
		this.customerId = customerId;
		this.closed = closed;
		this.monthsOpen = monthsOpen;
	}

	/**
	 * constructor based on an entry in a CSV
	 * @param csvEntry
	 */
	public Claim(List<String> csvEntry) {
		this(
				Integer.parseInt(csvEntry.get(0)),
				Integer.parseInt(csvEntry.get(1)),
				Boolean.parseBoolean(csvEntry.get(2)),
				Integer.parseInt(csvEntry.get(3)));
	}

	/**
	 * Builder constructor
	 * @param builder
	 */
	public Claim(Builder builder) {
		this(
				builder.claimId,
				builder.customerId,
				builder.closed,
				builder.monthsOpen);
	}

	/**
	* Returns value of claimId
	* @return
	*/
	public int getClaimId() {
		return claimId;
	}

	/**
	* Sets new value of claimId
	* @param
	*/
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	/**
	* Returns value of customerId
	* @return
	*/
	public int getCustomerId() {
		return customerId;
	}

	/**
	* Sets new value of customerId
	* @param
	*/
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	* Returns value of closed
	* @return
	*/
	public boolean isClosed() {
		return closed;
	}

	/**
	* Sets new value of closed
	* @param
	*/
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	/**
	* Returns value of monthsOpen
	* @return
	*/
	public int getMonthsOpen() {
		return monthsOpen;
	}

	/**
	* Sets new value of monthsOpen
	* @param
	*/
	public void setMonthsOpen(int monthsOpen) {
		this.monthsOpen = monthsOpen;
	}

	@Override
	public String toString() {
		return "Claim{" +
				"claimId=" + claimId +
				", customerId=" + customerId +
				", closed=" + closed +
				", monthsOpen=" + monthsOpen +
				'}';
	}

	public static class Builder {
		 private int claimId;
		 private int customerId;
		 private boolean closed;
		 private int monthsOpen;

 		public static Builder newBuilder() {
 			return new Builder();
 		}

		 public Builder claimId(int claimId) {
			 this.claimId = claimId;
			 return this;
		}

		 public Builder customerId(int customerId) {
			 this.customerId = customerId;
			 return this;
		}

		 public Builder closed(boolean closed) {
			 this.closed = closed;
			 return this;
		}

		 public Builder monthsOpen(int monthsOpen) {
			 this.monthsOpen = monthsOpen;
			 return this;
		}

		public Claim build() {
			return new Claim(this);
		}
	}

}
