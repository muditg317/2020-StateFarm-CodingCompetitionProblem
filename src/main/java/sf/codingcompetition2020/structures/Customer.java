package sf.codingcompetition2020.structures;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class Customer {

  private int customerId;
  private String firstName;
  private String lastName;
  private int age;
  private String area;
  private int agentId;
  private short agentRating;
  private String primaryLanguage;
  private List<Dependent> dependents;
  private boolean homePolicy;
  private boolean autoPolicy;
  private boolean rentersPolicy;
  private int totalMonthlyPremium;
  private short yearsOfService;
  private int vehiclesInsured;

  /**
  * Default Customer constructor
  */
  public Customer(int customerId, String firstName, String lastName, int age, String area, int agentId, short agentRating, String primaryLanguage, List<Dependent> dependents, boolean homePolicy, boolean autoPolicy, boolean rentersPolicy, String totalMonthlyPremium, short yearsOfService, int vehiclesInsured) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.area = area;
    this.agentId = agentId;
    this.agentRating = agentRating;
    this.primaryLanguage = primaryLanguage;
    this.dependents = dependents;
    this.homePolicy = homePolicy;
    this.autoPolicy = autoPolicy;
    this.rentersPolicy = rentersPolicy;
    this.totalMonthlyPremium = Integer.parseInt(totalMonthlyPremium.substring(1));
    this.yearsOfService = yearsOfService;
    this.vehiclesInsured = vehiclesInsured;
  }

  /**
   * constructor based on an entry in a CSV
   * @param csvEntry
   */
  public Customer(List<String> csvEntry) throws JsonProcessingException {
    this(
        Integer.parseInt(csvEntry.get(0)),
        csvEntry.get(1),
        csvEntry.get(2),
        Integer.parseInt(csvEntry.get(3)),
        csvEntry.get(4),
        Integer.parseInt(csvEntry.get(5)),
        Short.parseShort(csvEntry.get(6)),
        csvEntry.get(7),
        csvEntry.get(8).isEmpty() ? new ArrayList<>() : new ObjectMapper().readValue(csvEntry.get(8), new TypeReference<List<Dependent>>(){}),
        Boolean.parseBoolean(csvEntry.get(9)),
        Boolean.parseBoolean(csvEntry.get(10)),
        Boolean.parseBoolean(csvEntry.get(11)),
        csvEntry.get(12),
        Short.parseShort(csvEntry.get(13)),
        Integer.parseInt(csvEntry.get(14))
        );
  }

  /**
   * Builder constructor
   * @param builder
   */
  public Customer(Builder builder) {
    this(
        builder.customerId,
        builder.firstName,
        builder.lastName,
        builder.age,
        builder.area,
        builder.agentId,
        builder.agentRating,
        builder.primaryLanguage,
        builder.dependents,
        builder.homePolicy,
        builder.autoPolicy,
        builder.rentersPolicy,
        builder.totalMonthlyPremium,
        builder.yearsOfService,
        builder.vehiclesInsured);
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

  /**
  * Returns value of age
  * @return
  */
  public int getAge() {
    return age;
  }

  /**
  * Sets new value of age
  * @param
  */
  public void setAge(int age) {
    this.age = age;
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
  * Returns value of agentRating
  * @return
  */
  public short getAgentRating() {
    return agentRating;
  }

  /**
  * Sets new value of agentRating
  * @param
  */
  public void setAgentRating(short agentRating) {
    this.agentRating = agentRating;
  }

  /**
  * Returns value of primaryLanguage
  * @return
  */
  public String getPrimaryLanguage() {
    return primaryLanguage;
  }

  /**
  * Sets new value of primaryLanguage
  * @param
  */
  public void setPrimaryLanguage(String primaryLanguage) {
    this.primaryLanguage = primaryLanguage;
  }

  /**
  * Returns value of dependents
  * @return
  */
  public List<Dependent> getDependents() {
    return dependents;
  }

  /**
  * Sets new value of dependents
  * @param
  */
  public void setDependents(List<Dependent> dependents) {
    this.dependents = dependents;
  }

  /**
  * Returns value of homePolicy
  * @return
  */
  public boolean hasHomePolicy() {
    return homePolicy;
  }

  /**
  * Sets new value of homePolicy
  * @param
  */
  public void setHomePolicy(boolean homePolicy) {
    this.homePolicy = homePolicy;
  }

  /**
  * Returns value of autoPolicy
  * @return
  */
  public boolean hasAutoPolicy() {
    return autoPolicy;
  }

  /**
  * Sets new value of autoPolicy
  * @param
  */
  public void setAutoPolicy(boolean autoPolicy) {
    this.autoPolicy = autoPolicy;
  }

  /**
  * Returns value of rentersPolicy
  * @return
  */
  public boolean hasRentersPolicy() {
    return rentersPolicy;
  }

  /**
  * Sets new value of rentersPolicy
  * @param
  */
  public void setRentersPolicy(boolean rentersPolicy) {
    this.rentersPolicy = rentersPolicy;
  }

  /**
  * Returns value of totalMonthlyPremium
  * @return
  */
  public int getTotalMonthlyPremium() {
    return totalMonthlyPremium;
  }

  /**
  * Sets new value of totalMonthlyPremium
  * @param
  */
  public void setTotalMonthlyPremium(String totalMonthlyPremium) {
    this.totalMonthlyPremium = Integer.parseInt(totalMonthlyPremium.substring(1));
  }

  /**
  * Returns value of yearsOfService
  * @return
  */
  public short getYearsOfService() {
    return yearsOfService;
  }

  /**
  * Sets new value of yearsOfService
  * @param
  */
  public void setYearsOfService(short yearsOfService) {
    this.yearsOfService = yearsOfService;
  }

  /**
  * Returns value of vehiclesInsured
  * @return
  */
  public int getVehiclesInsured() {
    return vehiclesInsured;
  }

  /**
  * Sets new value of vehiclesInsured
  * @param
  */
  public void setVehiclesInsured(int vehiclesInsured) {
    this.vehiclesInsured = vehiclesInsured;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "customerId=" + customerId +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", age=" + age +
        ", area='" + area + '\'' +
        ", agentId=" + agentId +
        ", agentRating=" + agentRating +
        ", primaryLanguage='" + primaryLanguage + '\'' +
        ", dependents=" + dependents +
        ", homePolicy=" + homePolicy +
        ", autoPolicy=" + autoPolicy +
        ", rentersPolicy=" + rentersPolicy +
        ", totalMonthlyPremium=" + totalMonthlyPremium +
        ", yearsOfService=" + yearsOfService +
        ", vehiclesInsured=" + vehiclesInsured +
        '}';
  }

  public static class Builder {
     private int customerId;
     private String firstName;
     private String lastName;
     private int age;
     private String area;
     private int agentId;
     private short agentRating;
     private String primaryLanguage;
     private List<Dependent> dependents;
     private boolean homePolicy;
     private boolean autoPolicy;
     private boolean rentersPolicy;
     private String totalMonthlyPremium;
     private short yearsOfService;
     private int vehiclesInsured;

     public static Builder newBuilder() {
       return new Builder();
     }

     public Builder customerId(int customerId) {
       this.customerId = customerId;
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

     public Builder age(int age) {
       this.age = age;
       return this;
    }

     public Builder area(String area) {
       this.area = area;
       return this;
    }

     public Builder agentId(int agentId) {
       this.agentId = agentId;
       return this;
    }

     public Builder agentRating(short agentRating) {
       this.agentRating = agentRating;
       return this;
    }

     public Builder primaryLanguage(String primaryLanguage) {
       this.primaryLanguage = primaryLanguage;
       return this;
    }

     public Builder dependents(List<Dependent> dependents) {
       this.dependents = dependents;
       return this;
    }

     public Builder homePolicy(boolean homePolicy) {
       this.homePolicy = homePolicy;
       return this;
    }

     public Builder autoPolicy(boolean autoPolicy) {
       this.autoPolicy = autoPolicy;
       return this;
    }

     public Builder rentersPolicy(boolean rentersPolicy) {
       this.rentersPolicy = rentersPolicy;
       return this;
    }

     public Builder totalMonthlyPremium(String totalMonthlyPremium) {
       this.totalMonthlyPremium = totalMonthlyPremium;
       return this;
    }

     public Builder yearsOfService(short yearsOfService) {
       this.yearsOfService = yearsOfService;
       return this;
    }

     public Builder vehiclesInsured(int vehiclesInsured) {
       this.vehiclesInsured = vehiclesInsured;
       return this;
    }

    public Customer build() {
      return new Customer(this);
    }
  }
}
