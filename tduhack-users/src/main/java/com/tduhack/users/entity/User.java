package com.tduhack.users.entity;

import com.tduhack.Field;
import com.tduhack.HasId;

public interface User extends HasId {
  String TYPE = "User";
  Field<String> email = new Field<>("email", String.class);
  Field<String> first_name = new Field<>("first_name", String.class);
  Field<String> last_name = new Field<>("last_name", String.class);
}
