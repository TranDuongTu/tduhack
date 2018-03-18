package com.tduhack.users.entity;

import com.tduhack.Field;
import com.tduhack.HasKeyName;

public interface Credential extends HasKeyName {
  String TYPE = "Credential";

  Field<Long> user = new Field<>("user_id", Long.class);
  Field<String> password = new Field<>("password", String.class);
}
