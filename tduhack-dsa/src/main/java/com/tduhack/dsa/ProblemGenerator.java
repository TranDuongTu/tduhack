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
import java.util.stream.Stream;

public class ProblemGenerator {

  private final int level;

  public ProblemGenerator(final int level) {
    this.level = level;
  }

  public List<HasFields> randomGenerate() {
    final Reflections reflections = new Reflections("com.tduhack.dsa");
    final Set<Class<?>> problemClasses = reflections.getTypesAnnotatedWith(ProblemAnnotation.class);
    final List<ProblemAnnotation> allProblemAnnotations = problemClasses.stream()
            .map(p -> p.getAnnotation(ProblemAnnotation.class))
            .collect(Collectors.toList());
    return findProblemsWithGivenLevelSum(allProblemAnnotations, level)
            .map(p -> FieldsBase.create()
                    .set(Problem.name, p.name())
                    .set(Problem.level, p.level()))
            .collect(Collectors.toList());
  }

  private static Stream<ProblemAnnotation> findProblemsWithGivenLevelSum(final List<ProblemAnnotation> problems, final int levelSum) {
    Collections.shuffle(problems);
    final List<ProblemAnnotation> result = new ArrayList<>();
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
    return result.stream();
  }
}
