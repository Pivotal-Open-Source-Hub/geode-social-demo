package io.pivotal.happysocial.model;

public class SentimentResult {
  private String sentiment;
  private String personName;
  
  public SentimentResult() {
  }

  public SentimentResult(String sentiment, String personName) {
    super();
    this.sentiment = sentiment;
    this.personName = personName;
  }

  public String getSentiment() {
    return sentiment;
  }

  public void setSentiment(String sentiment) {
    this.sentiment = sentiment;
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  @Override
  public String toString() {
    return "SentimentResult [sentiment=" + sentiment + ", personName=" + personName + "]";
  }
  
  
}
