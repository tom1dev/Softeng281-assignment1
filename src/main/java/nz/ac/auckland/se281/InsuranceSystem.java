package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  // creates nessisary variables and appends correct data type to them.
  private ArrayList<Person> people = new ArrayList<>();
  private int currentLoad;

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
    currentLoad = -1;
  }

  public void printDatabase() {

    // turns dbcount into a string so that it can be outputted into the correct datatype for
    // MessageCli format
    String stringDataBaseCount = Integer.toString(people.size());

    // outputs correct message depending on amount of accounts created.
    if (people.size() == 0) {

      // prints that their is no accounts in the database
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(stringDataBaseCount, "s", ".");
    } else if (people.size() == 1) {
      // chooses which multiple of the word policy to use based on the amount of policies
      String policyEnd;
      if (people.get(0).getPolicyAmount() != 1) {
        policyEnd = "ies";
      } else {
        policyEnd = "y";
      }

      // prints that their is a account in the database
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(stringDataBaseCount, "", ":");

      // if the profile is loaded print that it is loaded in print db
      if (currentLoad != -1) {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "*** ",
            stringDataBaseCount,
            people.get(0).getUserName(),
            people.get(0).getAge(),
            Integer.toString(people.get(0).getPolicyAmount()),
            policyEnd,
            people.get(0).totalPremium());
        people.get(0).printPolicys();
      } else {
        // prints the account in db
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "",
            stringDataBaseCount,
            people.get(0).getUserName(),
            people.get(0).getAge(),
            Integer.toString(people.get(0).getPolicyAmount()),
            policyEnd,
            people.get(0).totalPremium());
        people.get(0).printPolicys();
      }

    } else {

      // prints that their is multiple accounts in the database
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(stringDataBaseCount, "s", ":");

      // loops untill all user accounts are printed in the database
      for (int i = 0; i < people.size(); i = i + 1) {

        String policyEnd;
        if (people.get(i).getPolicyAmount() == 1) {
          policyEnd = "y";
        } else {
          policyEnd = "ies";
        }

        String strI = Integer.toString(i + 1);

        if (i == currentLoad) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              strI,
              people.get(i).getUserName(),
              people.get(i).getAge(),
              Integer.toString(people.get(i).getPolicyAmount()),
              policyEnd,
              people.get(i).totalPremium());
          people.get(i).printPolicys();
        } else {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              strI,
              people.get(i).getUserName(),
              people.get(i).getAge(),
              Integer.toString(people.get(i).getPolicyAmount()),
              policyEnd,
              people.get(i).totalPremium());
          people.get(i).printPolicys();
        }
      }
    }
    // TODO: Complete this method.
  }

  public void createNewProfile(String userName, String age) {

    userName = titleCase(userName);

    int isunique = findPosition(userName);

    // checks if age only includes integers by changing all charaters in age into ascii values and
    // making sure values are numbers 0-9
    boolean isAgeNumber = true;
    for (int i = 0; i < age.length(); i++) {
      int agelettercheck = (int) age.charAt(i);
      if (agelettercheck < 48) {
        isAgeNumber = false;
      }
      if (agelettercheck > 57) {
        isAgeNumber = false;
      }
    }

    // checks if age is greater than 0
    if (isAgeNumber) {
      if (Integer.parseInt(age) <= 0) {
        isAgeNumber = false;
      }
    }

    // if the userName has less than 3 charaters, age and name are not added
    if (userName.length() <= 2) {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
    }
    // if the age is less than 0 age and name are not inputted to their respective arrays
    else if (isAgeNumber == false) {
      MessageCli.INVALID_AGE.printMessage(age, userName);
    }
    // if their is already a username the same as userName in the array then age and name are not
    // inputted to their respected arrays
    else if (isunique != -1) {
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
    }
    // if a profile is loaded do not create new profile
    else if (currentLoad != -1) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(people.get(currentLoad).getUserName());
    }

    // if it passes all the former tests are passed then age and username are appended to their
    // arrays
    else {
      // apends the age and userName strings to there own arrays to store them
      Person temp = new Person(age, userName);
      people.add(temp);

      // prints that the customers account was created
      MessageCli.PROFILE_CREATED.printMessage(userName, age);
    }
  }

  public void loadProfile(String userName) {

    // makes the input the title case
    userName = titleCase(userName);
    // finds the position of the next username wanted to be loaded
    int testCurrentLoad = findPosition(userName);

    // if the next load isn't negitve 1 then append the position of the next user to be inputted and
    // print a message
    if (testCurrentLoad != -1) {
      currentLoad = findPosition(userName);
      MessageCli.PROFILE_LOADED.printMessage(userName);
    }

    // if no username matches print error message
    else {

      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
    }
  }

  public void unloadProfile() {
    // sets the appendix of loaded profiles to -1 and prints the unload message
    if (currentLoad == -1) {
      MessageCli.NO_PROFILE_LOADED.printMessage();
    } else {
      MessageCli.PROFILE_UNLOADED.printMessage(people.get(currentLoad).getUserName());
      currentLoad = -1;
    }
  }

  public void deleteProfile(String userName) {
    // puts userName in correct format and then finds position of username in array list
    userName = titleCase(userName);
    int position = findPosition(userName);

    // if profile is currently loaded print error when trying to be deleted
    if (currentLoad != -1 && people.get(currentLoad).getUserName().equals(userName)) {
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
    }

    // if profile is not in the database print error
    else if (position == -1) {
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
    }

    // deletes the profile if other 2 cases are failed
    else {
      MessageCli.PROFILE_DELETED.printMessage(userName);
      System.out.print(position);
      System.out.print(currentLoad);

      if (currentLoad > position && currentLoad != -1) {
        currentLoad += -1;
      }
      people.remove(position);
    }
  }

  public int findPosition(String userName) {
    // initalises values
    int position = -1;
    boolean inDataBase = false;

    // loops to check if a given username is stored in a person
    if (people.size() >= 1) {
      for (position = 0; position < people.size(); position = position + 1) {

        if (people.get(position).getUserName().equals(userName)) {
          inDataBase = true;
          break;
        }
      }
    }
    // if not found return negitive one else return the position
    if (inDataBase == false) {
      return -1;
    } else {
      return position;
    }
  }

  public String titleCase(String randomCase) {

    // creates the title case for the inputted username, by seperating the string into substrings.
    // i.e.the first letter is capitalised and the rest is turned into lower case.
    String titleCase =
        randomCase.substring(0, 1).toUpperCase() + randomCase.substring(1).toLowerCase();
    return titleCase;
  }

  public void createPolicy(PolicyType type, String[] options) {
    // IF NO PROFILE IS LOADED PRINT AN ERROR MESSAGE
    if (currentLoad == -1) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
    } else {
      // goes into person to add a policy
      people.get(currentLoad).addPolicy(type, options);
    }
  }
}
