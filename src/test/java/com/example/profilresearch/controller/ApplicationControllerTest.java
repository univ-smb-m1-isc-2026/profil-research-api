package com.example.profilresearch.controller;

import com.example.profilresearch.dto.ApplicationRequest;
import com.example.profilresearch.dto.ApplicationResponse;
import com.example.profilresearch.dto.QuestionApplicationRequest;
import com.example.profilresearch.dto.QuestionApplicationResponse;
import com.example.profilresearch.entity.Format;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * On test ici le Parking controleur uniquement
 * (le endpoint, la sécurité avec le token et l'appel au service)
 *
 * On ne test pas la logique métier, ça se fera dans le fichier ParkingServiceTest.java
 * On ne test pas les interactions avec la bdd, ça se fera dans le fichier ParkingRepositoryTest.java
 * */
@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc; // Outil pour simuler des appels HTTP sans lancer le serveur

    @MockBean //les service avec MockBean sont mocké, donc on ne les test pas ici
    private ApplicationService applicationService; // Simule le service métier

    @Autowired
    private ObjectMapper objectMapper; // Utilitaire Jackson pour transformer des objets en JSON

    @Test
    @WithMockUser
        // Simule un utilisateur par défaut
    void call_endpoint_AddApplication_and_should_return_200() throws Exception {
        // Préparation des données
        ApplicationRequest request = new ApplicationRequest();
        request.setMail("test@mail.com");
        request.setFirstname("test");
        request.setLastname("test");
        request.setJobOffer_id(1L);
        // responses
        QuestionApplicationRequest qjr = new QuestionApplicationRequest();
        qjr.setId_question(1L);
        ArrayList<String> array = new ArrayList<String>();
        array.add("text");
        qjr.setResponses(array);
        List<QuestionApplicationRequest> qar = new ArrayList<QuestionApplicationRequest>();
        qar.add(qjr);
        request.setResponses(qar);

        // On définit le comportement du mock : quand on appelle creerParking, il retourne un succès
        Mockito.when(applicationService.createApplication(Mockito.any()))
                .thenReturn("Application ajouté");

        // Exécution et vérification
        mockMvc.perform(post("/api/application/addApplication")
                        .with(csrf()) //ajoute un jeton CSRF valide à la requête de test (pas necessaire pour les get)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))) // Conversion de l'objet DTO en String JSON
                .andExpect(status().isOk()); // On vérifie que le contrôleur répond bien 200 (OK)
    }

    @Test
    @WithMockUser
    void call_endpoint_GetApplicationByJobOffer_and_should_return_200() throws Exception {
        String jobOfferId = "1";

        ApplicationResponse mockResponse = new ApplicationResponse();
        mockResponse.setId(1L);
        mockResponse.setMail("test@mail.com");
        mockResponse.setFirstname("test");
        mockResponse.setLastname("test");
        mockResponse.setId_job_offer(1L);

        List<ApplicationResponse> list = List.of(mockResponse);

        Mockito.when(applicationService.getApplicationByJobOffer(Mockito.anyString()))
                .thenReturn(list);

        mockMvc.perform(get("/api/application/getApplicationByJobOffer/{jobOfferId}", jobOfferId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void call_endpoint_GetAllResponsesByApplication_and_should_return_200() throws Exception {
        String applicationId = "1";

        QuestionApplicationResponse mockResponse = new QuestionApplicationResponse();
        mockResponse.setId(1L);
        mockResponse.setId(1L);
        mockResponse.setId_application(1L);
        // question
        Question quest = new Question();
        quest.setId(1L);
        quest.setTitle("title");
        quest.setFormat(Format.TEXT);
        ArrayList<String> array = new ArrayList<String>();
        quest.setChoices(array);
        mockResponse.setId_question(quest);
        // response
        array.add("choice");
        mockResponse.setResponses(array);

        List<QuestionApplicationResponse> list = List.of(mockResponse);

        Mockito.when(applicationService.getAllQuestionApplication(Mockito.anyString()))
                .thenReturn(list);

        mockMvc.perform(get("/api/application/getAllResponses/{applicationId}", applicationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void call_endpoint_DeleteApplication_and_should_return_200() throws Exception {
        String applicationId = "1";

        Mockito.doNothing() //parce que la méthode return void
                .when(applicationService)
                .deleteById(Mockito.anyString());

        mockMvc.perform(delete("/api/application/delete/{applicationId}", applicationId)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}