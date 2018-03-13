package com.tduhack.dsa;

import com.tduhack.HasFields;
import com.tduhack.JSON;
import com.tduhack.Strings;
import com.tduhack.dsa.entity.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Problems {

  private static final Logger logger = Logger.getLogger(Problems.class.getName());

  public static List<HasFields> selectProblems(final int level) {
    final List<HasFields> problems = getAllProblems();
    return findProblemsWithGivenLevelSum(problems, level);
  }

  public static List<HasFields> getAllProblems() {
    final InputStream inputStream = Problems.class.getResourceAsStream("/problems.txt");
    final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
    try {
      br.readLine();
      String buff;
      final List<HasFields> problems = new ArrayList<>();
      while (Strings.isNotEmpty(buff = br.readLine())) {
        final String[] tokens = buff.split(",");
        final String name = tokens[0].trim();
        final int level = Integer.valueOf(tokens[1]);
        problems.add(JSON.create().set(Problem.name, name).set(Problem.level, level));
      }
      return problems;
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Cannot read problems resources");
      throw new RuntimeException(e);
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        logger.log(Level.SEVERE, "Cannot close resources stream");
        throw new RuntimeException(e);
      }
    }
  }

  private static List<HasFields> findProblemsWithGivenLevelSum(final List<HasFields> problems, final int levelSum) {
    Collections.shuffle(problems);
    final List<HasFields> result = new ArrayList<>();
    int start = 0, end = 0, currentSum = problems.get(0).get(Problem.level);
    while (end < problems.size()) {
      if (currentSum == levelSum) {
        IntStream.range(start, end + 1).forEach(i -> result.add(problems.get(i)));
        break;
      } else if (currentSum > levelSum && start < end) {
        currentSum -= problems.get(start++).get(Problem.level);
      } else {
        currentSum += problems.get(++end).get(Problem.level);
      }
    }
    return result;
  }
}
