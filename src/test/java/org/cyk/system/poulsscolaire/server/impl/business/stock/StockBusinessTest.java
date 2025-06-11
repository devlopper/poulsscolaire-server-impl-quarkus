package org.cyk.system.poulsscolaire.server.impl.business.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockBusinessTest {

  StockCreateBusiness createBusiness = new StockCreateBusiness();
  
  @Test
  void create_computeName_whenBlank() {
    assertEquals("Stock C 0", createBusiness.computeName(null, "C", 0));
  }
  
  @Test
  void create_computeName_whenNotBlank() {
    assertEquals("N", createBusiness.computeName("N", "C", 0));
  }
}
