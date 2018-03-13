package com.tduhack.dsa.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.tduhack.HasFields;
import com.tduhack.dsa.Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(name = "dsa", version = "v1")
public class DSA {

  @ApiMethod(name = "all_problems", path = "problems", httpMethod = ApiMethod.HttpMethod.GET)
  public ProblemList allProblems() {
    final ProblemList problemList = new ProblemList();
    problemList.setProblems(generateProblems(0));
    return problemList;
  }

  @ApiMethod(name = "problems_with_level", path = "problems/{level}", httpMethod = ApiMethod.HttpMethod.GET)
  public ProblemList problemsWithLevel(final @Named("level") Integer level) {
    final ProblemList problemList = new ProblemList();
    problemList.setProblems(generateProblems(level));
    return problemList;
  }

  private List<Problem> generateProblems(final int level) {
    final Problems generator = new Problems();
    final List<HasFields> problems = level == 0 ? generator.getAllProblems() : generator.selectProblems(level);
    return problems == null ? new ArrayList<>() : problems.stream()
            .map(this::transform).collect(Collectors.toList());
  }

  private Problem transform(final HasFields hasFields) {
    final Problem problem = new Problem();
    problem.setName(hasFields.get(com.tduhack.dsa.entity.Problem.name));
    problem.setLevel(hasFields.get(com.tduhack.dsa.entity.Problem.level));
    return problem;
  }
}
