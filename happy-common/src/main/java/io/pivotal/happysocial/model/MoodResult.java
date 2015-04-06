package io.pivotal.happysocial.model;

public class MoodResult {
  private String mood;
  private String personName;

  public MoodResult(String mood, String personName) {
    super();
    this.mood = mood;
    this.personName = personName;
  }

  public String getMood() {
    return mood;
  }

  public void setMood(String mood) {
    this.mood = mood;
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }
}
