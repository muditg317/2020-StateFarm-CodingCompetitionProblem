package sf.codingcompetition2020;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import sf.codingcompetition2020.structures.Agent;
import sf.codingcompetition2020.structures.Claim;
import sf.codingcompetition2020.structures.Customer;
import sf.codingcompetition2020.structures.Vendor;
import sf.codingcompetition2020.utilities.Pair;

public class CodingCompCsvUtil {
	
	/* #1 
	 * readCsvFile() -- Read in a CSV File and return a list of entries in that file.
	 * @param filePath -- Path to file being read in.
	 * @param classType -- Class of entries being read in.
	 * @return -- List of entries being returned.
	 */
	public <T> List<T> readCsvFile(String filePath, Class<T> classType) throws IOException {
		List<T> interpretedFile = new ArrayList<>();

		File csvFile = new File(filePath);
		CsvMapper mapper = new CsvMapper();
		mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
		MappingIterator<Object[]> it = mapper.readerFor(Object[].class).readValues(csvFile);
		it.nextValue();
		it.forEachRemaining(row -> {
			try {
				interpretedFile.add(classType.getConstructor(List.class).newInstance(Arrays.asList(row)));
			} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
		if (classType.equals(Customer.class)) {

		}
//		FileReader fr = new FileReader(csvFile);
//		BufferedReader br = new BufferedReader(fr);
//		String entry;
//		while ((entry = br.readLine()) != null) {
//			List<String> interpretedLine = Arrays.asList(entry.split("\\s*,\\s*"));
//			try {
//				interpretedFile.add(classType.getConstructor(List.class).newInstance(interpretedLine));
//			} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//				e.printStackTrace();
//			}
//		}
		return interpretedFile;
	}

	
	/* #2
	 * getAgentCountInArea() -- Return the number of agents in a given area.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @return -- The number of agents in a given area
	 */
	public int getAgentCountInArea(String filePath, String area) throws IOException {
		return (int) readCsvFile(filePath, Agent.class).stream().filter(agent -> agent.getArea().equals(area)).count();
	}

	
	/* #3
	 * getAgentsInAreaThatSpeakLanguage() -- Return a list of agents from a given area, that speak a certain language.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @param language -- The language spoken by the agent(s).
	 * @return -- The number of agents in a given area
	 */
	public List<Agent> getAgentsInAreaThatSpeakLanguage(String filePath, String area, String language) throws IOException {
		return readCsvFile(filePath, Agent.class).stream().filter(agent -> agent.getArea().equals(area) && agent.getLanguage().equals(language)).collect(Collectors.toList());
	}
	
	
	/* #4
	 * countCustomersFromAreaThatUseAgent() -- Return the number of individuals from an area that use a certain agent.
	 * @param filePath -- Path to file being read in.
	 * @param customerArea -- The area from which the customers should be counted.
	 * @param agentFirstName -- First name of agent.
	 * @param agentLastName -- Last name of agent.
	 * @return -- The number of customers that use a certain agent in a given area.
	 */
	public short countCustomersFromAreaThatUseAgent(Map<String,String> csvFilePaths, String customerArea, String agentFirstName, String agentLastName) throws IOException {
	    Agent desiredAgent = (Agent) readCsvFile(csvFilePaths.get("agentList"), Agent.class).stream().filter(agent -> agent.getFirstName().equals(agentFirstName) && agent.getLanguage().equals(agentLastName)).toArray()[0];
		return (short) readCsvFile(csvFilePaths.get("customerList"), Customer.class).stream().filter(customer -> customer.getArea().equals(customerArea) && customer.getAgentId() == desiredAgent.getAgentId()).count();
	}

	
	/* #5
	 * getCustomersRetainedForYearsByPlcyCostAsc() -- Return a list of customers retained for a given number of years, in ascending order of their policy cost.
	 * @param filePath -- Path to file being read in.
	 * @param yearsOfServeice -- Number of years the person has been a customer.
	 * @return -- List of customers retained for a given number of years, in ascending order of policy cost.
	 */
	public List<Customer> getCustomersRetainedForYearsByPlcyCostAsc(String customerFilePath, short yearsOfService) throws IOException {
		List<Customer> allCustomers = readCsvFile(customerFilePath, Customer.class);
		List<Customer> retainedCustomers = new ArrayList<>();
		for (Customer current : allCustomers) {
			if (current.getYearsOfService() >= yearsOfService) {
				retainedCustomers.add(current);
			}
		}
		Collections.sort(retainedCustomers, Comparator.comparingInt(Customer::getTotalMonthlyPremium));
		return retainedCustomers;
	}

	
	/* #6
	 * getLeadsForInsurance() -- Return a list of individuals who’ve made an inquiry for a policy but have not signed up.
	 * *HINT* -- Look for customers that currently have no policies with the insurance company.
	 * @param filePath -- Path to file being read in.
	 * @return -- List of customers who’ve made an inquiry for a policy but have not signed up.
	 */
	public List<Customer> getLeadsForInsurance(String filePath) throws IOException {
		List<Customer> allCustomers = readCsvFile(filePath, Customer.class);
		List<Customer> customersWithoutPolicy = new ArrayList<>();
		for (Customer current : allCustomers) {
			if (!current.hasAutoPolicy() && !current.hasHomePolicy() && !current.hasRentersPolicy()) {
				customersWithoutPolicy.add(current);
			}
		}
		return customersWithoutPolicy;
	}


	/* #7
	 * getVendorsWithGivenRatingThatAreInScope() -- Return a list of vendors within an area and include options to narrow it down by: 
			a.	Vendor rating
			b.	Whether that vendor is in scope of the insurance (if inScope == false, return all vendors in OR out of scope, if inScope == true, return ONLY vendors in scope)
	 * @param filePath -- Path to file being read in.
	 * @param area -- Area of the vendor.
	 * @param inScope -- Whether or not the vendor is in scope of the insurance.
	 * @param vendorRating -- The rating of the vendor.
	 * @return -- List of vendors within a given area, filtered by scope and vendor rating.
	 */
	public List<Vendor> getVendorsWithGivenRatingThatAreInScope(String filePath, String area, boolean inScope, int vendorRating) throws IOException {
		List<Vendor> allVendors = readCsvFile(filePath, Vendor.class);
		List<Vendor> narrowedVendors = new ArrayList<>();
		for (Vendor current : allVendors) {
			if (current.getVendorRating() == vendorRating && area.equals(current.getArea())) {
				if (inScope) {
					if (current.isInScope()) {
						narrowedVendors.add(current);
					}
				} else {
					narrowedVendors.add(current);
				}
			}
		}
		return narrowedVendors;
	}


	/* #8
	 * getUndisclosedDrivers() -- Return a list of customers between the age of 40 and 50 years (inclusive), who have:
			a.	More than X cars
			b.	less than or equal to X number of dependents.
	 * @param filePath -- Path to file being read in.
	 * @param vehiclesInsured -- The number of vehicles insured.
	 * @param dependents -- The number of dependents on the insurance policy.
	 * @return -- List of customers filtered by age, number of vehicles insured and the number of dependents.
	 */
	public List<Customer> getUndisclosedDrivers(String filePath, int vehiclesInsured, int dependents) throws IOException {
		List<Customer> allCustomers = readCsvFile(filePath, Customer.class);
		List<Customer> filteredCustomers = new ArrayList<>();
		for (Customer current : allCustomers) {
			if (current.getAge() >= 40 && current.getAge() <= 50) {
				if (current.getVehiclesInsured() > vehiclesInsured && current.getDependents().size() <= dependents) {
					filteredCustomers.add(current);
				}
			}
		}
		return filteredCustomers;
	}	


	/* #9
	 * getAgentIdGivenRank() -- Return the agent with the given rank based on average customer satisfaction rating. 
	 * *HINT* -- Rating is calculated by taking all the agent rating by customers (1-5 scale) and dividing by the total number 
	 * of reviews for the agent.
	 * @param filePath -- Path to file being read in.
	 * @param agentRank -- The rank of the agent being requested.
	 * @return -- Agent ID of agent with the given rank.
	 */
	public int getAgentIdGivenRank(String filePath, int agentRank) throws IOException {
		List<Customer> allCustomers = readCsvFile(filePath, Customer.class);
		int numberOfAgents = Integer.MIN_VALUE;
		for (Customer currentCustomer : allCustomers) {
			numberOfAgents = Math.min(numberOfAgents, currentCustomer.getAgentId());
		}
		Map<Integer, List<Integer>> agentRatingsList = new HashMap<>();
		for (Customer currentCustomer : allCustomers) {
			int currentAgentId = currentCustomer.getAgentId();
			int currentAgentRating = currentCustomer.getAgentRating();
			if (agentRatingsList.containsKey(currentAgentId)) {
				List<Integer> currentAgentList = agentRatingsList.get(currentAgentId);
				currentAgentList.set(0, currentAgentList.get(0) + 1);
				currentAgentList.set(1, currentAgentList.get(1) + currentAgentRating);
				agentRatingsList.put(currentAgentId, currentAgentList);
			} else {
				List<Integer> newAgentList = new ArrayList<>();
				newAgentList.add(1);
				newAgentList.add(currentAgentRating);
				agentRatingsList.put(currentAgentId, newAgentList);
			}
		}
		List<Pair<Integer, Double>> agentAverageRatings = new ArrayList<>();
		for (Map.Entry<Integer, List<Integer>> entry : agentRatingsList.entrySet()) {
			Integer agentId = entry.getKey();
			Integer numReviews = entry.getValue().get(0);
			Integer totalRating = entry.getValue().get(1);
			Double averageRating = (double)(totalRating) / numReviews;
			agentAverageRatings.add(new Pair<>(agentId, averageRating));
		}
		Collections.sort(agentAverageRatings, (o1, o2) -> {
			if (o1.getValue2() > o2.getValue2()) {
				return 1;
			} else if (o1.getValue2() < o2.getValue2()) {
				return -1;
			}
			return 0;
		});
		return agentAverageRatings.get(agentRank - 1).getValue1();
	}	

	
	/* #10
	 * getCustomersWithClaims() -- Return a list of customers who’ve filed a claim within the last <numberOfMonths> (inclusive). 
	 * @param filePath -- Path to file being read in.
	 * @param monthsOpen -- Number of months a policy has been open.
	 * @return -- List of customers who’ve filed a claim within the last <numberOfMonths>.
	 */
	public List<Customer> getCustomersWithClaims(Map<String, String> csvFilePaths, short monthsOpen) throws IOException {
		String customerListFilePath = csvFilePaths.get("customerList");
		String claimsListFilePath = csvFilePaths.get("claimList");
		List<Customer> allCustomers = readCsvFile(customerListFilePath, Customer.class);
		List<Claim> allClaims = readCsvFile(claimsListFilePath, Claim.class);
		List<Customer> customersWithClaims = new ArrayList<>();
		for (Claim currentClaim : allClaims) {
			if (currentClaim.getMonthsOpen() <= monthsOpen) {
				int currentClaimCustomerId = currentClaim.getCustomerId();
				customersWithClaims.add(allCustomers.get(currentClaimCustomerId));
			}
		}
		return customersWithClaims;
	}

	/* Custom methods: 1
	 * getCustomersBasedOnNumberOfPolicies() -- Return a list of customers who have <numberOfPolicies> policies (inclusive).
	 * @param filePath -- Path to file being read in.
	 * @param numberOfPolicies -- Number of months a policy has been open.
	 * @return -- List of customers who have <numberOfPolicies> policies.
	 */
	public List<Customer> getCustomersBasedOnNumberOfPolicies(String filePath, int numberOfPolicies) throws IOException {
		return readCsvFile(filePath, Customer.class).stream().filter(customer -> {
			int policyCount = 0;
			if (customer.hasAutoPolicy()) {
				policyCount++;
			}
			if (customer.hasHomePolicy()) {
				policyCount++;
			}
			if (customer.hasRentersPolicy()) {
				policyCount++;
			}
			return policyCount == numberOfPolicies;
		}).collect(Collectors.toList());
	}

	/* Custom methods: 2
	 * getVendorsForCustomerBasedOnArea() -- Return a list of vendors that operate in the area of a given customer.
	 * @param csvFilePaths -- Paths to files being read in.
	 * @param firstName -- last name of customer
	 * @param lastName -- first name of customer
	 * @return -- list of vendors that operate in the area of a given customer
	 */
	public List<Vendor> getVendorsForCustomerBasedOnArea(Map<String, String> csvFilePaths, String firstName, String lastName) throws IOException {
		String customerListFilePath = csvFilePaths.get("customerList");
		String vendorListFilePath = csvFilePaths.get("vendorFilePath");
		List<Customer> allCustomers = readCsvFile(customerListFilePath, Customer.class);
		List<Vendor> allVendors = readCsvFile(vendorListFilePath, Vendor.class);
		String desiredArea = "";
		List<Vendor> vendorsInArea = new ArrayList<>();
		for (Customer current : allCustomers) {
			if (current.getFirstName().equals(firstName) && current.getLastName().equals(lastName)) {
				desiredArea = current.getArea();
				break;
			}
		}
		for (Vendor current : allVendors) {
			if (current.getArea().equals(desiredArea)) {
				vendorsInArea.add(current);
			}
		}
		return vendorsInArea;
	}
}
