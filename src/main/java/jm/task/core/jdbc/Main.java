package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();


        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 25);
        System.out.println("User с именем — Иван добавлен в базу данных");

        userService.saveUser("Петр", "Петров", (byte) 30);
        System.out.println("User с именем — Петр добавлен в базу данных");

        userService.saveUser("Анна", "Сидорова", (byte) 22);
        System.out.println("User с именем — Анна добавлен в базу данных");

        userService.saveUser("Гариб", "Мейланов", (byte) 28);
        System.out.println("User с именем — Гариб добавлен в базу данных");

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
