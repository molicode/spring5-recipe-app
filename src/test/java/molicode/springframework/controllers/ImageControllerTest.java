package molicode.springframework.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import molicode.springframework.commands.RecipeCommand;
import molicode.springframework.service.ImageService;
import molicode.springframework.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

  @Mock
  private ImageService imageService;

  @Mock
  private RecipeService recipeService;

  @InjectMocks
  private ImageController controller;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void getImageForm() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(1L);

    when(recipeService.findCommandById(anyLong())).thenReturn(command);

    mockMvc.perform(get("/recipe/1/image"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("recipe"));

    verify(recipeService, times(1)).findCommandById(anyLong());

  }

  @Test
  public void handleImagePost() throws Exception {
    MockMultipartFile multipartFile =
        new MockMultipartFile("imagefile", "testing.txt", "text/plain",
            "Spring Framework MoliCode".getBytes());

    mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "/recipe/1/show"));

    verify(imageService, times(1)).saveImageFile(anyLong(), any());
  }

  @Test
  public void renderImageFromDB() throws Exception {

    //given
    RecipeCommand command = new RecipeCommand();
    command.setId(1L);

    String s = "fake image text";
    Byte[] bytesBoxed = new Byte[s.getBytes().length];

    int i = 0;

    for (byte primByte : s.getBytes()){
      bytesBoxed[i++] = primByte;
    }

    command.setImage(bytesBoxed);

    when(recipeService.findCommandById(anyLong())).thenReturn(command);

    //when
    MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
        .andExpect(status().isOk())
        .andReturn().getResponse();

    byte[] reponseBytes = response.getContentAsByteArray();

    assertEquals(s.getBytes().length, reponseBytes.length);
  }


}