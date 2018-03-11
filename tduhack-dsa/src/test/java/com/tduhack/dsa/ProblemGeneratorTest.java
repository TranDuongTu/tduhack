package com.tduhack.dsa;

import com.tduhack.HasFields;
import com.tduhack.dsa.entity.Problem;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProblemGeneratorTest {

  @Test
  public void testTotalLevelOfGeneratedProblems() {
    final int LEVEL = 10;
    final ProblemGenerator generator = new ProblemGenerator(LEVEL);
    final List<HasFields> problems = generator.randomGenerate();
    assertThat(problems).isNotEmpty();

    final int sumLevel = problems.stream()
            .map(p -> p.get(Problem.level))
            .reduce(0, (x, y) -> x + y);
    assertThat(sumLevel).isEqualTo(LEVEL);
  }
}