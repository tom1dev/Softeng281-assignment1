package nz.ac.auckland.se281;

public class HomePol extends Policy {
  private String address;
  private boolean rental;

  // initilises variables
  public HomePol(String sum, String address, String rentalStr) {
    // initises policy class
    super(sum);

    this.address = address;
    // turns Rental String into its title case
    rentalStr = rentalStr.substring(0, 1).toUpperCase() + rentalStr.substring(1).toLowerCase();

    // turns string rental string into a boolean
    if (rentalStr.equals("Yes")) {
      rental = true;
    } else {
      rental = false;
    }
  }

  // calculates the total premium
  private void calculatingPremium(int amountPolicy) {
    // calculates base premium
    if (rental == true) {
      premium = sum * 0.02;
    } else {
      premium = sum * 0.01;
    }

    // calculates premium after discounts
    if (amountPolicy < 2) {
      premiumdiscounted = premium;
    } else if (amountPolicy == 2) {
      premiumdiscounted = premium - premium / 10;
    } else {
      premiumdiscounted = premium - (premium * 2) / 10;
    }
  }

  // prints the Home policy in correct format
  @Override
  public void introduce() {

    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
        address,
        Integer.toString(sum),
        String.format("%.0f", premium),
        String.format("%.0f", premiumdiscounted));
  }

  // calculates the final premium and returns
  @Override
  public double getpremiumdiscounted(int amountPolicies) {
    calculatingPremium(amountPolicies);
    return premiumdiscounted;
  }
}
