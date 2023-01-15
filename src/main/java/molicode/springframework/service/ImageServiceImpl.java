package molicode.springframework.service;

import java.io.IOException;

import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import molicode.springframework.domain.Recipe;
import molicode.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

  private final RecipeRepository recipeRepository;

  @Override
  @Transactional
  public void saveImageFile(Long recipeId, MultipartFile file) {

    try {
      Recipe recipe = recipeRepository.findById(recipeId).get();

      Byte[] byteObjects = new Byte[file.getBytes().length];

      int i = 0;

      for(byte b : file.getBytes()) {
        byteObjects[i++] = b;
      }

      recipe.setImage(byteObjects);

      recipeRepository.save(recipe);

    } catch (IOException e) {
      //todo handle better
      log.error("Error occurred", e);
      throw new RuntimeException(e);
    }

  }

}
