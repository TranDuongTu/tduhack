package com.tduhack.appengine;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.tduhack.Field;
import com.tduhack.HasFields;
import com.tduhack.HasId;
import com.tduhack.HasKeyName;

import java.util.Iterator;
import java.util.Map;

public class Bean implements HasFields {
  private final Entity entity;

  public Bean(final Entity entity) {
    this.entity = entity;
    set(HasId.id, entity.getKey().getId());
    set(HasKeyName.keyName, entity.getKey().getName());
  }

  public String kind() {
    return entity.getKind();
  }

  @Override
  public <T> T get(Field<T> field) {
    return (T) entity.getProperty(field.name());
  }

  @Override
  public <T> Bean set(Field<T> field, T value) {
    entity.setProperty(field.name(), value);
    return this;
  }

  @Override
  public boolean contains(Field field) {
    return entity.hasProperty(field.name());
  }

  @Override
  public Iterator<Field> iterator() {
    final Map<String, Object> properties = entity.getProperties();
    final Iterator<String> fields = properties.keySet().iterator();
    return new Iterator<Field>() {
      @Override
      public boolean hasNext() {
        return fields.hasNext();
      }

      @Override
      public Field next() {
        final String field = fields.next();
        final Object value = properties.get(field);
        return new Field(field, guessType(value));
      }
    };
  }

  Entity getEntity() {
    return entity;
  }

  public String keyCode() {
    return entity.getKey().isComplete() ? KeyFactory.keyToString(entity.getKey()) : null;
  }

  @Override
  public String toString() {
    return entity.toString();
  }
}
