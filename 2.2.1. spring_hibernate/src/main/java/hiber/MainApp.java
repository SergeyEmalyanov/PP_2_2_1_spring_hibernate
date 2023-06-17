package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        User user5 = new User("User5", "Lastname5", "user5@mail.ru");
        User user6 = new User("User6", "Lastname6", "user6@mail.ru");
        User user7 = new User("User7", "Lastname7", "user7@mail.ru");
        User user8 = new User("User8", "Lastname8", "user8@mail.ru");

        Car car5 = new Car("A", 1);
        Car car6 = new Car("B", 2);
        Car car7 = new Car("C", 3);
        Car car8 = new Car("D", 4);

        user5.setCar(car5);
        user6.setCar(car6);
        user7.setCar(car7);
        user8.setCar(car8);

        userService.add(user5);
        userService.add(user6);
        userService.add(user7);
        userService.add(user8);

        List<User> allUsers = userService.listUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
        System.out.println(userService.userByModelAndSeries("C", 3));
        // Не существующий автомобиль, вернется null.
        System.out.println(userService.userByModelAndSeries("D", 5));

        context.close();
    }
}
