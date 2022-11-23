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
  void setUp() {

  }

  @Test
  void findByDescription() {
    Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

    assertEquals("Teaspoon", optionalUnitOfMeasure.get().getDescription());
  }

}