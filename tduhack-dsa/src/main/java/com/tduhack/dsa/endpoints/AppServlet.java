package com.tduhack.dsa.endpoints;

import com.tduhack.HasFields;
import com.tduhack.HasName;
import com.tduhack.appengine.DataStore;
import com.tduhack.appengine.Store;
import com.tduhack.dsa.Problems;
import com.tduhack.dsa.entity.Problem;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AppServlet extends HttpServlet {

  private static final Logger logger = Logger.getLogger(AppServlet.class.getName());

  @Override
  public void init() {
    syncProblems();
  }

  private void syncProblems() {
    logger.log(Level.INFO, "Syncing annotated problems");
    final Store store = new DataStore();
    final Set<String> savedProblems = store.query("Problem").stream()
            .map(p -> p.get(HasName.name))
            .collect(Collectors.toSet());
    final List<HasFields> problems = Problems.getAllProblems();
    for (final HasFields problem : problems) {
      final String name = problem.get(Problem.name);
      final int level = problem.get(Problem.level);
      if (!savedProblems.contains(name)) {
        logger.log(Level.INFO, "Saving problem: " + name);
        store.save(store.create("Problem").set(Problem.name, name).set(Problem.level, level));
      }
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.getWriter().print("Welcome to DSA!!!");
  }
}
