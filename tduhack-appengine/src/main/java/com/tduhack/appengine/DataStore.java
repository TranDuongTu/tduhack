package com.tduhack.appengine;

import com.google.appengine.api.datastore.*;

public class DataStore implements Store {

  private final DatastoreService ds;

  public DataStore() {
    ds = DatastoreServiceFactory.getDatastoreService();
  }

  @Override
  public QueryBuilder<Bean> query(String kind) {
    return new DSQueryBuilder(kind, this);
  }

  @Override
  public Bean create(String kind, Long id) {
    final Entity entity = createEntity(kind, id);
    return new Bean(entity);
  }

  @Override
  public Bean create(String kind) {
    final Entity entity = createEntity(kind, null);
    return new Bean(entity);
  }

  private Entity createEntity(final String kind, final Long id) {
    return id == null ? new Entity(kind) : new Entity(kind, id);
  }

  @Override
  public void save(final Bean bean) {
    final Entity entity = bean.getEntity();
    ds.put(entity);
  }

  @Override
  public Bean find(final String keyCode) {
    return find(KeyFactory.stringToKey(keyCode));
  }

  @Override
  public Bean find(String kind, Long id) {
    return find(KeyFactory.createKey(kind, id));
  }

  private Bean find(final Key key) {
    try {
      final Entity entity = ds.get(key);
      return new Bean(entity);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  PreparedQuery prepared(final Query query) {
    return ds.prepare(query);
  }
}
