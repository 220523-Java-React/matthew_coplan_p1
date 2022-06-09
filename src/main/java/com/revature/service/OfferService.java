package com.revature.service;

import com.revature.model.Car;
import com.revature.model.CarStatus;
import com.revature.model.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferService {

    private static final List<Offer> offers = new ArrayList<>();

    public List<Offer> getOffers() {
        return offers;
    }

    // IF Car is AVAIABLE then user can make an offer. Also, GET USERID of user making offer.
    public void createOffer(Offer offer, int carId) {
        List<Car> cars = CarService.getCars();
        for (int i = 0; i < CarService.carCount(); i++) {
            if (cars.get(i).status.equals(CarStatus.AVAILABLE) && cars.get(i).getId() == carId)
                offers.add(offer);
        }
    }

    public static int offerSize() {
        return offers.size();
    }
}
