package com.svenruppert.junit5.basics.c06.example.services.login;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit5.basics.c06.example.services.CreateEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.DeleteEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.UpdateEntityResponse;

import java.util.Map;

import static java.util.HashMap.newHashMap;

public class LoginRepository implements HasLogger {

  public final static Login ANONYMOUS_LOGIN = new Login();
  private static final GlobalUIDGenerator globalUIDGenerator = GlobalUIDGenerator.getInstance();
  private static final Map<String, Login> userLoginRepoLoginName = newHashMap(100);
  private static final Map<Integer, Login> userLoginRepoUID = newHashMap(100);

  public Login userLoginByUID(int uid) {
    return userLoginRepoUID.getOrDefault(uid, ANONYMOUS_LOGIN);
  }

  public Login userLoginByLoginName(String loginName) {
    return userLoginRepoLoginName.getOrDefault(loginName, ANONYMOUS_LOGIN);
  }

  public DeleteEntityResponse<Login> deleteLogin(Login login) {
    logger().warn("deleting login {}", login);
    userLoginRepoLoginName.remove(login.loginName());
    userLoginRepoUID.remove(login.uid());
    return new DeleteEntityResponse<>(true, "Login " + login.loginName() + " deleted", login);
  }

  public CreateEntityResponse<Login>  createLogin(Login login){
    return createLogin(login.loginName(), login.passwordHash(), login.salt());
  }

  public CreateEntityResponse<Login> createLogin(String loginName,
                                                 String passwordHash,
                                                 String salt) {
    logger().info("creating login {}", loginName);
    if (userLoginRepoLoginName.containsKey(loginName)) {
      return new CreateEntityResponse<>(false, "Login " + loginName + " already exists", ANONYMOUS_LOGIN);
    }
    int nextUID = globalUIDGenerator.getNextUID();
    try {
      Login value = new Login(loginName, passwordHash, salt, nextUID);
      userLoginRepoUID.put(nextUID, value);
      userLoginRepoLoginName.put(value.loginName(), value);
      return new CreateEntityResponse<>(true, "Login " + loginName + " created", value);
    } catch (Exception e) {
      userLoginRepoUID.remove(nextUID);
      userLoginRepoLoginName.remove(loginName);
      return new CreateEntityResponse<>(false, e.getMessage(), ANONYMOUS_LOGIN);
    }
  }

  public UpdateEntityResponse<Login> changePassword(int uid, String passwordHash, String salt) {
    if (userLoginRepoUID.containsKey(uid)) {
      Login login = userLoginRepoUID.get(uid);
      Login newEntry = new Login(login.loginName(), passwordHash, salt, login.uid());
      userLoginRepoUID.replace(uid, newEntry);
      userLoginRepoLoginName.replace(login.loginName(), newEntry);
      return new UpdateEntityResponse<>(true, "Login " + login.loginName() + " updated", newEntry);
    } else {
      logger().warn("trying to update not know user with UID {}", uid);
      return new UpdateEntityResponse<>(false, "trying to update not know user with UID " + uid, ANONYMOUS_LOGIN);
    }
  }

  public UpdateEntityResponse<Login> updateLogin(Login login) {
    logger().info("updating login {}", login);
    if (userLoginRepoLoginName.containsKey(login.loginName()) &&
        userLoginRepoUID.containsKey(login.uid())) {
      userLoginRepoLoginName.put(login.loginName(), login);
      userLoginRepoUID.put(login.uid(), login);
      return new UpdateEntityResponse<>(true, "Login " + login.uid() + " updated", login);
    } else {
      return new UpdateEntityResponse<>(false, "Login " + login.uid() + " not found / not updated", ANONYMOUS_LOGIN);
    }
  }

}
