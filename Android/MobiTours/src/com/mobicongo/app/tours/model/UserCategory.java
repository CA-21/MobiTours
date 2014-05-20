/***********************************************************************
 * Module:  UserCategory.java
 * Author:  Mishka
 * Purpose: Defines the Class UserCategory
 ***********************************************************************/

package com.mobicongo.app.tours.model;

import java.util.*;


public class UserCategory {

   private int categoryId;

   private int category;
   
   public java.util.Collection<User> user;
   

   public Long insertCatergory() {
      // TODO: implement
      return null;
   }
   
   public Long deleteCategory() {
      // TODO: implement
      return null;
   }
   
   
   public void addUser(User newUser) {
      if (newUser == null)
         return;
      if (this.user == null)
         this.user = new java.util.HashSet<User>();
      if (!this.user.contains(newUser))
         this.user.add(newUser);
   }
   

   public void removeUser(User oldUser) {
      if (oldUser == null)
         return;
      if (this.user != null)
         if (this.user.contains(oldUser))
            this.user.remove(oldUser);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllUser() {
      if (user != null)
         user.clear();
   }

}