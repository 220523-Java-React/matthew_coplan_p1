package com.revature.services;

import com.revature.model.Car;
import com.revature.model.Offer;
import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.service.CarService;
import com.revature.service.OfferService;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OfferServiceTest {

    @Test
    public void offerServiceCreateOfferWorks(){
        OfferService offerService = new OfferService();
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createNewUser(new User("Test1","Test","test","test", UserRoles.EMPLOYEE));
        userService.createNewUser(new User("Test2","Test","test","test", UserRoles.CUSTOMER));
        userService.createNewUser(new User("Test3","Test","test","test", UserRoles.EMPLOYEE));
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),0);
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.TAKEN),2);
        offerService.createOffer(new Offer(500, Offer.Status.OPEN),0);
        offerService.createOffer(new Offer(500, Offer.Status.OPEN),1);
        Assertions.assertEquals(1, OfferService.offerSize());
    }
}
