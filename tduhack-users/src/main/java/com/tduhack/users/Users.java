package com.tduhack.users;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ForbiddenException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.tduhack.appengine.Bean;
import com.tduhack.appengine.DataStore;
import com.tduhack.appengine.Store;
import com.tduhack.users.dto.UserInfo;
import com.tduhack.users.entity.Credential;
import com.tduhack.users.entity.User;
import com.tduhack.utils.Strings;

@Api(name = "users", version = "v1", authenticators = {UserAuthenticator.class})
public class Users {

  @ApiMethod(name = "register", path = "register", httpMethod = ApiMethod.HttpMethod.POST)
  public UserInfo register(final UserInfo userInfo, @Named("password") String password) throws Exception {
    if (!validateUserInfo(userInfo) || Strings.isEmpty(password)) {
      throw new BadRequestException("Registering info is not complete");
    }

    final Store store = new DataStore();
    final Bean existing = findUser(store, userInfo.getEmail());
    if (existing != null) {
      throw new ForbiddenException("Email is not available");
    }

    final Bean newUser = store.save(store.create(User.TYPE)
      .set(User.email, userInfo.getEmail())
      .set(User.first_name, userInfo.getFirstName())
      .set(User.last_name, userInfo.getLastName()));
    final Bean credential = store.create(Credential.TYPE);
    credential.set(Credential.user, newUser.id());
    credential.set(Credential.password, password);
    store.save(credential);

    final String token = Tokens.issueToken(String.valueOf(newUser.id()));
    return success(newUser, token);
  }

  private Bean findUser(final Store store, final String email) {
    return store.query(User.TYPE).whereEquals(User.email, email).first();
  }

  private boolean validateUserInfo(final UserInfo userInfo) {
    return Strings.isNotEmpty(userInfo.getEmail())
      && Strings.isNotEmpty(userInfo.getFirstName())
      && Strings.isNotEmpty(userInfo.getLastName());
  }

  private UserInfo success(final Bean user, final String token) {
    final UserInfo userInfo = new UserInfo();
    userInfo.setEmail(user.get(User.email));
    userInfo.setFirstName(user.get(User.first_name));
    userInfo.setLastName(user.get(User.last_name));
    userInfo.setToken(token);
    return userInfo;
  }

  @ApiMethod(name = "auth", path = "auth", httpMethod = ApiMethod.HttpMethod.POST)
  public UserInfo authenticate(@Named("email") String email, @Named("password") String password) throws Exception {
    if (Strings.isEmpty(email) || Strings.isEmpty(password)) {
      throw new BadRequestException("Email and Password are required");
    }

    final Store store = new DataStore();
    final Bean user = findUser(store, email);
    if (user == null) {
      throw new ForbiddenException("User with email " + email + " is not exists");
    }

    if (!matchPassword(store, user, password)) {
      throw new UnauthorizedException("Invalid credential");
    }
    return success(user, Tokens.issueToken(String.valueOf(user.id())));
  }

  private boolean matchPassword(final Store store, final Bean user, final String password) {
    final Bean credential = store.query(Credential.TYPE).whereEquals(Credential.user, user.id()).first();
    return credential != null && credential.is(Credential.password, password);
  }

  @ApiMethod(name = "get_user_info", path = "user", httpMethod = ApiMethod.HttpMethod.GET)
  public UserInfo getUserInfo(final com.google.api.server.spi.auth.common.User user) throws Exception {
    if (user == null) {
      throw new UnauthorizedException("Unauthorized");
    }

    final Store store = new DataStore();
    final Bean userData = findUser(store, user.getEmail());
    return success(userData, null);
  }
}
