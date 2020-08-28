package com.mr.texasholdem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    while (true) {
      String[] tokens = getTokens();
      if (tokens.length == 0)
        return;
      CommunityCards communityCards = new CommunityCards(tokens[0]);
      List<SevenCard> sevenCards = new ArrayList<>();
      for (int a = 1; a < tokens.length; a++) {
        SevenCard sevenCard = new SevenCard(communityCards, tokens[a]);
        sevenCards.add(sevenCard);
      }
      Collections.sort(sevenCards);
      System.out.print(communityCards.toString());
      System.out.print(sevenCards.stream().reduce("", (s, sc) -> s.concat(" " + sc.getHoleCardCodes()), String::concat));
      System.out.println();
    }
  }

  private static String[] getTokens() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String inputString = null;
    String[] tokens;
    while (true) {
      try {
        inputString = reader.readLine();
      }
      catch (IOException e) {
        System.out.println("input error: ");
        e.printStackTrace(System.out);
      }
      if (inputString == null) {
        return new String[] {};
      }
      tokens = inputString.split("\\s+");
      if (tokens.length < 2) {
        System.out.println("wrong input data");
      }
      else {
        return tokens;
      }
    }

  }
}
