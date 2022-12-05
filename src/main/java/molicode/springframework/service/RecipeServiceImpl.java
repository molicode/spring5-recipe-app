package molicode.springframework.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import molicode.springframework.domain.Recipe;
import molicode.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public Set<Recipe> getRecipes() {
    log.debug("I'm in the service");

    Set<Recipe> recipes = new HashSet<>();
    recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
    return recipes;
  }

  @Override
  public Recipe findById(final Long id){
    Optional<Recipe> recipeOptional = recipeRepository.findById(id);

    if(!recipeOptional.isPresent()){
      throw new RuntimeException("Recipe Not Found!");
    }

    return recipeOptional.get();
  }

}
