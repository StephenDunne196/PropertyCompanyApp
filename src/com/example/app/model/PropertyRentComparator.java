
package com.example.app.model;

import java.util.Comparator;

public class PropertyRentComparator implements Comparator<Property>{

    @Override
    public int compare(Property p1, Property p2) {
      return (int)(p1.getRent() - p2.getRent());
    }
    
    
    
    
    
}