package com.tduhack.dsa;

import com.tduhack.FieldsBase;
import com.tduhack.HasFields;
import com.tduhack.dsa.entity.Problem;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProblemGenerator {

  public List<HasFields> allProblems() {
    final Reflections reflections = new Reflections("com.tduhack.dsa");
    final Set<Class<?>> problemClasses = reflections.getTypesAnnotatedWith(ProblemAnnotation.class);
    return problemClasses.stream()
            .map(p -> p.getAnnotation(ProblemAnnotation.class))
            .map(this::transform)
            .collect(Collectors.toList());
  }

  public List<HasFields> randomGenerate(final int level) {
    return findProblemsWithGivenLevelSum(allProblems(), level);
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

  private HasFields transform(final ProblemAnnotation annotation) {
    return FieldsBase.create()
            .set(Problem.name, annotation.name())
            .set(Problem.level, annotation.level());
  }
}
