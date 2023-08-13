package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class Person {
  // creates the variables needed for Person Class
  private String age;
  private String userName;
  private ArrayList<Policy> policys = new ArrayList<>();
  private int lifePolicyCount;

  // initalises a person values
  public Person(String age, String name) {
    this.age = age;
    userName = name;
    lifePolicyCount = 0;
  }

  // returns the userName
  public String getUserName() {
    return userName;
  }

  // returns the Age
  public String getAge() {
    return age;
  }

  // returns how many policies there are for a certain person
  public int getPolicyAmount() {
    return policys.size();
  }

  // creates a policy for a person
  public void addPolicy(PolicyType type, String[] options) {

    // creates a HomePolicy using the HomePol Class and appends it to the policys listArray
    if (type == PolicyType.HOME) {
      HomePol temp = new HomePol(options[0], options[1], options[2]);
      policys.add(temp);
      MessageCli.NEW_POLICY_CREATED.printMessage("home", userName);

    }

    // creates a CarPolicy using the HomePol Class and appends it to the policies listArray
    else if (type == PolicyType.CAR) {
      CarPolicy temp = new CarPolicy(options[0], options[1], options[2], options[3]);
      policys.add(temp);
      MessageCli.NEW_POLICY_CREATED.printMessage("car", userName);

    } else {
      // if there has been a life policy already created print an error message
      if (lifePolicyCount > 0) {
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(userName);

      }
      // if the age of this is less than 100 then create a life policy and append it to policys
      // arrayList
      // array list
      else if (Integer.parseInt(age) <= 100) {
        LifePolicy temp = new LifePolicy(options[0]);
        policys.add(temp);
        MessageCli.NEW_POLICY_CREATED.printMessage("life", userName);

        // itterates the count for life policies
        lifePolicyCount += 1;
      } else {
        // prints error if age is over 100
        MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(userName);
      }
    }
  }

  public void printPolicys() {
    // if there is no policys do nothing
    if (policys.size() == 0) {

    } else {

      // print all the policies in corrrect format
      for (int i = 0; i < policys.size(); i += 1) {
        policys.get(i).introduce();
      }
    }
  }

  // calculates the total premium of all the policies and returns it as a string
  public String totalPremium() {
    // initilises the premium as 0;
    double totalPremium = 0;
    // loops through all the policies and sums their indiviual premiums

    for (int i = 0; i < policys.size(); i += 1) {
      // if the current policy in policys is a home policy then calculate its premium by inputting
      // the current amount of policies into the getpremiumdiscounted function
      if (policys.get(i) instanceof HomePol) {
        totalPremium += policys.get(i).getpremiumdiscounted(policys.size());
      }
      // if the current policy in policys is a car or life policy then calculate its premium by
      // inputting the current amount of policies, and this age into the getpremiumdiscounted
      // function
      else {
        totalPremium += policys.get(i).getpremiumdiscounted(policys.size(), Integer.parseInt(age));
      }
    }

    // convert totalPremium into a string and return it.
    return String.format("%.0f", totalPremium);
  }
}
