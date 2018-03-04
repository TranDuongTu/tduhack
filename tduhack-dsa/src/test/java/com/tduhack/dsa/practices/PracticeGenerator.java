package com.tduhack.dsa.practices;

import com.tduhack.dsa.Problem;
import org.junit.Test;
import org.reflections.Reflections;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class PracticeGenerator {

  private static final int DAILY_LEVEL = 7;

  @Test
  public void generateTodayProblems() throws Exception {
    final Reflections reflections = new Reflections("com.tdu.tduhack.dsa");
    final Set<Class<?>> problemClasses = reflections.getTypesAnnotatedWith(Problem.class);
    final List<Problem> problems = new ArrayList<>();
    problemClasses.forEach(p -> problems.add(p.getAnnotation(Problem.class)));
    final List<Problem> todayProblems = findProblemsWithGivenLevelSum(problems, DAILY_LEVEL);

    final String fileName = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
    final String filePath = "src/test/resources/DevJournal/" + fileName + ".txt";
    final BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
    System.out.println("Today problem(s):");
    for (final Problem problem : todayProblems) {
      System.out.println(" - " + problem.name());
      bw.write(problem.name() + "\n");
    }
    bw.close();
  }

  private List<Problem> findProblemsWithGivenLevelSum(final List<Problem> problems, final int levelSum) {
    Collections.shuffle(problems);
    final List<Problem> result = new ArrayList<>();
    int start = 0, end = 0, currentSum = problems.get(0).level();
    while (end < problems.size()) {
      if (currentSum == levelSum) {
        IntStream.range(start, end + 1).forEach(i -> result.add(problems.get(i)));
        break;
      } else if (currentSum > levelSum && start < end) {
        currentSum -= problems.get(start++).level();
      } else {
        currentSum += problems.get(++end).level();
      }
    }
    return result;
  }
}
