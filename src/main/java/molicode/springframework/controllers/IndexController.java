package molicode.springframework.controllers;

import java.util.Optional;

import molicode.springframework.domain.Category;
import molicode.springframework.domain.UnitOfMeasure;
import molicode.springframework.repositories.CategoryRepository;
import molicode.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  private final CategoryRepository categoryRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
    this.categoryRepository = categoryRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  @RequestMapping({"", "/", "index"})
  public String getIndexPage() {

    Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
    Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

    System.out.println("Cat id is: " + categoryOptional.get().getId());
    System.out.println("UOM Id is: " + unitOfMeasure.get().getId());

    return "index";
  }

}
