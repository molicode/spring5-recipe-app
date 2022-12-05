package molicode.springframework.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import molicode.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UnitOfMeasureRepositoryTest {

  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;

  @BeforeEach
  public void setUp() throws Exception {
  }

  @Test
  public void findByDescription() throws Exception {

    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

    assertEquals("Teaspoon", uomOptional.get().getDescription());
  }

  @Test
  public void findByDescriptionCup() throws Exception {

    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

    assertEquals("Cup", uomOptional.get().getDescription());
  }

}