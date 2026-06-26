package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoJDBCImpl();


        userDao.createUsersTable();


        userDao.saveUser("Иван", "Иванов", (byte) 25);
        System.out.println("User с именем — Иван добавлен в базу данных");

        userDao.saveUser("Петр", "Петров", (byte) 30);
        System.out.println("User с именем — Петр добавлен в базу данных");

        userDao.saveUser("Анна", "Сидорова", (byte) 22);
        System.out.println("User с именем — Анна добавлен в базу данных");

        userDao.saveUser("Гариб", "Мейланов", (byte) 28);
        System.out.println("User с именем — Гариб добавлен в базу данных");


        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }


        userDao.cleanUsersTable();


        userDao.dropUsersTable();



    }
}
