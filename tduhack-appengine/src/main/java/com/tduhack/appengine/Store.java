package com.tduhack.appengine;

public interface Store {
  Bean create(final String kind, final Long id);

  Bean create(final String kind);

  void save(final Bean bean);

  Bean find(final String keyCode);

  Bean find(final String kind, final Long id);
}
