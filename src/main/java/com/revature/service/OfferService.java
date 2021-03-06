package com.revature.service;

import com.revature.model.*;
import com.revature.repository.OfferRepository;
import java.util.List;

public class OfferService {

    private OfferRepository offerRepository;
    private CarService carService;
    private UserService userService;

    public OfferService() {
        offerRepository = new OfferRepository();
        this.carService = CarService.getInstance();
        this.userService = UserService.getInstance();
    }

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
        this.carService = CarService.getInstance();
        this.userService = UserService.getInstance();
    }


    public List<Offer> getOffers() {
        return offerRepository.getAll();
    }

    public Offer createOffer(Offer offer, int carId) {
        if (carService.getCarById(carId).getStatus().equals(CarStatus.AVAILABLE)) {
            offer.setCarId(carId);
            return offerRepository.create(offer);
        } else
            return null;
    }

    public List<Offer> getAllOffersByStatus(OfferStatus status) {
        return offerRepository.getAllByStatus(status);
    }

    public Offer getOfferById(int id){
        return offerRepository.getById(id);
    }

    public int offerCount() {
        return offerRepository.count();
    }

    public boolean deleteOfferById(int id){
        return offerRepository.deleteById(id);
    }

    // Updates the offer at the current ID
    // Pass in the ID you want to modify wih the new Offer Object.
    public Offer updateOfferById(Offer offer){
        return offerRepository.update(offer);
    }

    public List<Offer> getAllOpenOffersFromASpecificUserId(int id) {
        return offerRepository.getAllOpenOffersByUserId(id);
    }

    public boolean approveOfferById(int offerId, int userId){
        Offer pendingOffer = offerRepository.getById(offerId);
        int carId = pendingOffer.getCarId();
        Car pendingCar = carService.getCarById(carId);
        User pendingUser = userService.getUserById(userId);
        // Check if the current role of the user is an Employee before we approve
        if(pendingUser.getRole().equals(UserRoles.EMPLOYEE))
            // if the offer is Open and the car is Available we continue
            if (pendingOffer.getStatus().equals(OfferStatus.OPEN) && pendingCar.getStatus().equals(CarStatus.AVAILABLE)) {
                // Change the offer status to Accepted and update each result on the database
                pendingOffer.setStatus(OfferStatus.ACCEPTED);
                offerRepository.update(pendingOffer);
                // set the user ID of who made the offer to the Car and change status to Purchased.
                pendingCar.setStatus(CarStatus.PURCHASED).setUserId(pendingOffer.getUserId());
                carService.updateCarById(pendingCar);

                List<Offer> offers = offerRepository.getAllByCarId(carId);
                // Loop through each car ID that is on the Offer
                for (Offer offer: offers) {
                    // If the car ID is the same as the one on the Offer
                    // AND the offer ID does not equal our offer ID
                    if (offer.getCarId() == carId && offer.getId() != offerId) {
                        // Reject all these offers and update the database
                        offer.setStatus(OfferStatus.REJECTED);
                        offerRepository.update(offer);
                    }
                }
                return true;
        }
        return false;
    }
    public boolean denyOfferById(int id, int userId){
        Offer pendingOffer = offerRepository.getById(id);
        int carId = pendingOffer.getCarId();
        Car pendingCar = carService.getCarById(carId);
        if(userService.getUserById(userId).getRole().equals(UserRoles.EMPLOYEE)) {
            if (pendingOffer.getStatus().equals(OfferStatus.OPEN) && pendingCar.getStatus().equals(CarStatus.AVAILABLE)) {
                pendingOffer.setStatus(OfferStatus.REJECTED);
                offerRepository.update(pendingOffer);
                return true;
            }
        }
        return false;
    }

}
