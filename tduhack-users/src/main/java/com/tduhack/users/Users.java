package com.tduhack.users;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

@Api(name = "users", version = "v1")
public class Users {

  @ApiMethod(name = "login", path = "login", httpMethod = ApiMethod.HttpMethod.POST)
  public User login(@Named("username") String userName, @Named("password") String password) {
    return new User();
  }
}
