//: typeinfo/pets/LiteralPetCreator.java
// Using class literals.
package com.budd.java.jdkBasic.container.collection.list.pets.creator;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.Manx.Cymric;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.Manx.Manx;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.dog.Mutt;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.dog.Pug;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.cat.Cat;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.dog.Dog;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.cat.EgyptianMau;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.Pet;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.rodent.Hamster;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.rodent.Mouse;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.rodent.Rat;
import com.budd.java.jdkBasic.container.collection.list.pets.entity.pet.rodent.Rodent;

import java.util.*;

public class LiteralPetCreator extends PetCreator {
  // No try block needed.
  @SuppressWarnings("unchecked")
  public static final List<Class<? extends Pet>> allTypes =
    Collections.unmodifiableList(Arrays.asList(
      Pet.class, Dog.class, Cat.class,  Rodent.class,
      Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
      Cymric.class, Rat.class, Mouse.class, Hamster.class));
  // Types for random creation:
  private static final List<Class<? extends Pet>> types =
    allTypes.subList(allTypes.indexOf(Mutt.class),
      allTypes.size());
  public List<Class<? extends Pet>> types() {
    return types;
  }	
  public static void main(String[] args) {
    System.out.println(types);
  }
} /* Output:
[class typeinfo.pets.Mutt, class typeinfo.pets.Pug, class typeinfo.pets.EgyptianMau, class typeinfo.pets.Manx, class typeinfo.pets.Cymric, class typeinfo.pets.Rat, class typeinfo.pets.Mouse, class typeinfo.pets.Hamster]
*///:~
