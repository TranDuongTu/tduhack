package com.tduhack.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

import java.util.ArrayList;
import java.util.List;

@Api(name = "problem", version = "v1")
public class Problem {

  @ApiMethod(name = "today_problems", path = "today", httpMethod = ApiMethod.HttpMethod.GET)
  public Message todayProblems() {
    final List<String> problems = new ArrayList<>();
    problems.add("Problem 1");
    problems.add("Problem 2");
    final Message result = new Message();
    result.setResult(problems);
    return result;
  }
}
