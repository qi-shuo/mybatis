package test;

import java.util.List;

/**
 * @author qishuo
 * @date 2021/1/27 11:36 下午
 */
public interface UserMapper {
    List<User> getUserByName(String name);
}
