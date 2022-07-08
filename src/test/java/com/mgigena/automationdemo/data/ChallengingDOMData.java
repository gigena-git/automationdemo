package com.mgigena.automationdemo.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;

public class ChallengingDOMData {

  @DataProvider(name = "domElements")
  public Iterator<Object[]> domElements() {
    List<Object[]> tableElements = new ArrayList<>();
    tableElements.add(new Object[] {2, 1, "Iuvaret"});
    tableElements.add(new Object[] {3, 2, "Apeirian"});
    tableElements.add(new Object[] {4, 3, "Adipisci"});
    tableElements.add(new Object[] {5, 4, "Definiebas"});
    tableElements.add(new Object[] {6, 5, "Consequuntur"});
    tableElements.add(new Object[] {7, 6, "Phaedrum"});
    return tableElements.iterator();
  }
}
