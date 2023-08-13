package nz.ac.auckland.se281;

public class LifePolicy extends Policy {
  // initilises variables
  public LifePolicy(String sum) {
    // initises policy class
    super(sum);
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
  private void calculatingPremium(double age) {
    premium = sum * ((1 + age / 100) / 100);
  }

  // prints the Life policy in correct format
  @Override
  public void introduce() {

    String premiumStr = String.format("%.0f", premium);
    String discountStr = String.format("%.0f", premiumdiscounted);
    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(Integer.toString(sum), premiumStr, discountStr);
  }

  // calculates the final premium and returns
  @Override
  public double getpremiumdiscounted(int amountPolicy, int age) {
    calculatingPremium(age);
    calculatingdiscount(amountPolicy);
    return premiumdiscounted;
  }
}
