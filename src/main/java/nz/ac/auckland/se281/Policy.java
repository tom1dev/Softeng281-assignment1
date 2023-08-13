package nz.ac.auckland.se281;

public abstract class Policy {
  // creates the variables used by all policies
  protected int sum;
  protected Double premium;
  protected Double premiumdiscounted;

  // initialises values
  public Policy(String sum) {
    int intsum = Integer.parseInt(sum);
    this.sum = intsum;
    premium = 0.0;
    premiumdiscounted = 0.0;
  }

  public double getpremiumdiscounted(int amountPolicy) {
    return 0.0;
  }

  public double getpremiumdiscounted(int amountPolicy, int age) {
    return 0.0;
  }

  // all policies are introduced in different ways therefore introduce class is abstract
  public abstract void introduce();
}
