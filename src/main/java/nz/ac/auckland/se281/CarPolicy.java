package nz.ac.auckland.se281;

public class CarPolicy extends Policy {
  private boolean breakDownCover;
  private String makeAndModle;
  private String licensePlate;

  // initilises variables
  public CarPolicy(String sum, String makeAndModel, String licensePlate, String breakDownCover) {
    // initises policy class
    super(sum);

    this.licensePlate = licensePlate;
    this.makeAndModle = makeAndModel;
    // turns BreakDownCover String into its title case
    breakDownCover =
        breakDownCover.substring(0, 1).toUpperCase() + breakDownCover.substring(1).toLowerCase();
    // turns string breakdowncover into a boolean
    if (breakDownCover.equals("Yes")) {
      this.breakDownCover = true;
    } else {
      this.breakDownCover = false;
    }
  }

  // calulates the discounts on premiums
  private void calculatingdiscount(int amountPolicy) {
    // if their is less than 2 polices there is no discount
    if (amountPolicy < 2) {
      premiumdiscounted = premium;
    }
    // if their is  2 policies there is 10% discount
    else if (amountPolicy == 2) {
      premiumdiscounted = premium - premium / 10;
    }
    // if their is more than 2 policies there is a 20% discount
    else {
      premiumdiscounted = premium - (premium * 2) / 10;
    }
  }

  // calculates the base premium
  private void calculatingPremium(int age) {
    // premium is 15% of sum if under 25, else it is 10%
    if (age < 25) {
      premium = sum * 0.15;
    } else {
      premium = sum * 0.10;
    }
    // add 80 doollars to premium if they wanted breakDownCover
    if (breakDownCover == true) {
      premium += 80;
    }
  }

  // prints the car policy in correct format
  @Override
  public void introduce() {
    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
        makeAndModle,
        Integer.toString(sum),
        String.format("%.0f", premium),
        String.format("%.0f", premiumdiscounted));
  }

  // calculates the final premium and returns
  @Override
  public double getpremiumdiscounted(int amountPolicy, int age) {
    calculatingPremium(age);
    calculatingdiscount(amountPolicy);
    return premiumdiscounted;
  }
}
