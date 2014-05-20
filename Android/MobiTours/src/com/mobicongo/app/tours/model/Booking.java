/***********************************************************************
 * Module:  Booking.java
 * Author:  Mishka
 * Purpose: Defines the Class Booking
 ***********************************************************************/
package com.mobicongo.app.tours.model;

import java.util.*;


public class Booking {
   
   
	public enum PaymentModes {
        CASH("Paiement Cash"),
        VISA_CARD("Paiement Visa"),
        MASTER_CARD("Paiement Card"),
        WALLETS("Google Wallets");

        final String mPm;

        private PaymentModes(String pm) {
            this.mPm = pm;
        }
    }
	
   private int bookingId;
   private String bookingDesc;
   private Date dateTrip;
   private int duration;
   private PaymentModes paymentMode;
   private Date bookingDate;
   private int userId;
   private int courtierId;
   private int pointofinterestid;
   private int idsite;
   

   public Long newBooking() {
      // TODO: implement
      return null;
   }
   
   public void sendMail() {
      // TODO: implement
   }

}