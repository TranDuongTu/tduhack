package com.tduhack.dsa;

import com.tduhack.HasFields;
import com.tduhack.dsa.entity.Problem;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProblemGeneratorTest {

  private static final ProblemGenerator generator = new ProblemGenerator();

  @Test
  public void testTotalLevelOfGeneratedProblems() {
    final int LEVEL = 10;
    final List<HasFields> problems = generator.randomGenerate(LEVEL);
    assertThat(problems).isNotEmpty();

    final int sumLevel = problems.stream()
            .map(p -> p.get(Problem.level))
            .reduce(0, (x, y) -> x + y);
    assertThat(sumLevel).isEqualTo(LEVEL);
  }

  @Test
  public void syncProblemsData() throws Exception {
    final List<HasFields> problems = generator.getAnnotatedProblems();

    final String filePath = "src/main/resources/problems.txt";
    final BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
    bw.write("Name,Level\n");
    for (final HasFields problem : problems) {
      final String name = problem.get(Problem.name);
      final int level = problem.get(Problem.level);
      bw.write(name + "," + level + "\n");
    }
    bw.close();
  }
}