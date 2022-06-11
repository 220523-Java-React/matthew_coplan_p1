package com.revature.services;

import com.revature.model.*;
import com.revature.repository.CarRepository;
import com.revature.repository.UserRepository;
import com.revature.service.CarService;
import com.revature.service.OfferService;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;


public class CarServiceTest {
    /*

    List<Car> mockedList = Mockito.mock(List.class);

    @Test
    public void carServiceCreateCarAsEmployeeWorksCarCountEquals2(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test1","Test","test","test", UserRoles.EMPLOYEE));
        userService.createNewUser(new User("Test2","Test","test","test", UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test3","Test","test","test", UserRoles.EMPLOYEE));
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.TAKEN),2);
        Assertions.assertEquals(2, carService.carCount());
    }

    @Test
    public void carServiceCreateCarAsCustomerDoesNotWorkCarCountEquals0(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test","Test","test","test",UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test2","Test","test","test",UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test3","Test","test","test",UserRoles.CUSTOMER));
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),1);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),2);
        Assertions.assertEquals(0, carService.carCount());
    }
    @Test
    public void carServiceCheckIfGetCarByIDWorks(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test","Test","test","test",UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test2","Test","test","test",UserRoles.EMPLOYEE));
        userService.createNewUser(new User("Test3","Test","test","test",UserRoles.CUSTOMER));
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),1);
        carService.createCar(new Car("honda", "ford", 2832, CarStatus.AVAILABLE),2);
        Assertions.assertEquals(1, carService.getCarById(1).getId());
    }

     */
}