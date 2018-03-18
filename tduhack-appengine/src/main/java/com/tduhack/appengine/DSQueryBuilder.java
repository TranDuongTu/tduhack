package com.tduhack.appengine;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.tduhack.Field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DSQueryBuilder implements QueryBuilder<Bean> {

  private final Query query;
  private final DataStore store;
  private final List<Query.Filter> filterList = new ArrayList<>();
  private final FetchOptions options = FetchOptions.Builder.withChunkSize(500);

  DSQueryBuilder(final String kind, final DataStore store) {
    this.query = new Query(kind);
    this.store = store;
  }

  @Override
  public <V> QueryBuilder<Bean> whereEquals(Field<V> field, V value) {
    filterList.add(new Query.FilterPredicate(field.name(), Query.FilterOperator.EQUAL, value));
    return this;
  }

  @Override
  public Iterable<Bean> collect() {
    final Iterator<Bean> iter = iterator();
    return () -> iter;
  }

  @Override
  public Iterator<Bean> iterator() {
    final Iterator<Entity> iter = query();
    return new Iterator<Bean>() {
      @Override
      public boolean hasNext() {
        return iter.hasNext();
      }

      @Override
      public Bean next() {
        return new Bean(iter.next());
      }
    };
  }

  private Iterator<Entity> query() {
    if (query.getFilter() == null && !filterList.isEmpty()) {
      if (filterList.size() > 1) {
        query.setFilter(new Query.CompositeFilter(Query.CompositeFilterOperator.AND, filterList));
      } else {
        query.setFilter(filterList.get(0));
      }
    }
    return store.prepared(query).asIterator(options);
  }
}
