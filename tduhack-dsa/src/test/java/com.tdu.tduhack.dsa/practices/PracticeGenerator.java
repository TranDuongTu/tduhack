package com.tdu.tduhack.dsa.practices;

import com.tdu.tduhack.dsa.Problem;
import org.reflections.Reflections;

import java.util.*;

public class PracticeGenerator {

  public static void main(final String[] args) {
    final Reflections reflections = new Reflections("com.tdu.tduhack.dsa");
    final Set<Class<?>> problems = reflections.getTypesAnnotatedWith(Problem.class);

    final Map<Integer, List<String>> groupByLevel = new TreeMap<>();
    for (final Class problem : problems) {
      final Problem annotation = (Problem) problem.getAnnotation(Problem.class);
      final String name = annotation.name();
      if (!groupByLevel.containsKey(annotation.level())) {
        groupByLevel.put(annotation.level(), new ArrayList<>());
      }
      groupByLevel.get(annotation.level()).add(name);
    }

    final Calendar c = new GregorianCalendar();
    c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);
    final long time = c.getTime().getTime();
    for (final Integer level : groupByLevel.keySet()) {
      final List<String> levelProblems = groupByLevel.get(level);
      System.out.println("Level " + level + ": " + levelProblems.size() + " problem(s)");
      System.out.println(" -> Today problem: " + levelProblems.get((int) (time % levelProblems.size())));
    }
  }
}
