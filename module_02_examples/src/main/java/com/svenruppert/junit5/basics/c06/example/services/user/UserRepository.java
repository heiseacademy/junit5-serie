package com.svenruppert.junit5.basics.c06.example.services.user;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit5.basics.c06.example.services.CreateEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.DeleteEntityResponse;

import java.util.Map;

import static java.util.HashMap.newHashMap;

public class UserRepository
    implements HasLogger {

  public static final User ANONYMOUS_USER = new User();
  private static final Map<Integer, User> USER_REPO = newHashMap(100);

  public void clearRepository() {
    USER_REPO.clear();
  }

  public User userByUID(int uid) {
    return USER_REPO.getOrDefault(uid, ANONYMOUS_USER);
  }

  public DeleteEntityResponse<User> deleteUser(User user) {
    USER_REPO.remove(user.uid());
    return new DeleteEntityResponse<>(true, String.format("User %s deleted", user.uid()), user);
  }


  public CreateEntityResponse<User> createUser(int uid, String forename, String surname) {
    User value = new User(uid, forename, surname);
    USER_REPO.put(uid, value);
    return new CreateEntityResponse<>(true, "User " + uid + " created", value);
  }
}
