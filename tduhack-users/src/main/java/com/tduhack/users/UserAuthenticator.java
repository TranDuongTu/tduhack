package com.tduhack.users;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Authenticator;
import com.tduhack.appengine.Bean;
import com.tduhack.appengine.DataStore;
import com.tduhack.appengine.Store;
import com.tduhack.utils.Strings;

import javax.servlet.http.HttpServletRequest;

public class UserAuthenticator implements Authenticator {

  @Override
  public User authenticate(HttpServletRequest request) {
    final String authorizationHeader = request.getHeader("Authorization");
    if (Strings.isEmpty(authorizationHeader)) {
      return null;
    }

    final String userId = authorizationHeader.replace("Bearer ", "");
    final Store store = new DataStore();
    final Bean user = store.find(com.tduhack.users.entity.User.TYPE, Long.valueOf(userId));

    return user == null ? null : new User(userId, user.get(com.tduhack.users.entity.User.email));
  }
}
