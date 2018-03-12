package com.tduhack.dsa.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.tduhack.HasFields;
import com.tduhack.dsa.ProblemGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Api(name = "dsa", version = "v1")
public class DSA {

  @ApiMethod(name = "problems_with_level", path = "problems/{level}", httpMethod = ApiMethod.HttpMethod.GET)
  public ProblemList problemsWithLevel(final @Named("level") Integer level) {
    final ProblemGenerator generator = new ProblemGenerator();
    final List<Problem> problems = generator.randomGenerate(level).stream()
            .map(this::transform).collect(Collectors.toList());
    final ProblemList problemList = new ProblemList();
    problemList.setProblems(problems);
    return problemList;
  }

  private Problem transform(final HasFields hasFields) {
    final Problem problem = new Problem();
    problem.setName(hasFields.get(com.tduhack.dsa.entity.Problem.name));
    problem.setLevel(hasFields.get(com.tduhack.dsa.entity.Problem.level));
    return problem;
  }
}
