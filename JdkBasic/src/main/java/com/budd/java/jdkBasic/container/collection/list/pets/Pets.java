//: typeinfo/pets/Pets.java
// Facade to produce a default PetCreator.
package com.budd.java.jdkBasic.container.collection.list.pets;
import com.budd.java.jdkBasic.container.collection.list.pets.creator.LiteralPetCreator;
import com.budd.java.jdkBasic.container.collection.list.pets.creator.PetCreator;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.Pet;

import java.util.*;

public class Pets {
  public static final PetCreator creator =
    new LiteralPetCreator();
  public static Pet randomPet() {
    return creator.randomPet();
  }
  public static Pet[] createArray(int size) {
    return creator.createArray(size);
  }
  public static ArrayList<Pet> arrayList(int size) {
    return creator.arrayList(size);
  }
} ///:~
