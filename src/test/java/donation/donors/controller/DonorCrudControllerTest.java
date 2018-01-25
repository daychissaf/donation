package donation.donors.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import donation.donors.model.Donor;
import donation.donors.service.DonorCrudService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DonorCrudControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DonorCrudService donorService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnDonorsList() throws Exception {
        when(donorService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/donors/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(Collections.emptyList())));

        verify(donorService).getAll();
    }

    @Test
    public void shouldAddDonors() throws Exception {
        Donor expectedDonor = new Donor();
        expectedDonor.setName("donorName");

        when(donorService.add(expectedDonor)).thenReturn(expectedDonor);

        String donorJson = mapper.writeValueAsString(expectedDonor);
        mockMvc.perform(post("/api/donors")
                .content(donorJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ArgumentCaptor<Donor> donorCaptor = ArgumentCaptor.forClass(Donor.class);
        verify(donorService).add(donorCaptor.capture());

        Donor actualDonor = donorCaptor.getValue();
        assertEquals(expectedDonor.getName(), actualDonor.getName());
    }

    @Test
    public void shouldUpdateDonor() throws Exception {
        Donor newDonorData = new Donor();
        newDonorData.setName("donorName");

        Donor donorToUpdate = new Donor();
        donorToUpdate.setId(2L);

        when(donorService.getById(donorToUpdate.getId())).thenReturn(donorToUpdate);
        when(donorService.update(donorToUpdate, newDonorData)).thenReturn(donorToUpdate);

        String donorJson = mapper.writeValueAsString(newDonorData);
        mockMvc.perform(put("/api/donors/" + donorToUpdate.getId())
                .content(donorJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ArgumentCaptor<Donor> donorCaptor = ArgumentCaptor.forClass(Donor.class);
        verify(donorService).update(eq(donorToUpdate), donorCaptor.capture());

        Donor actualDonor = donorCaptor.getValue();
        assertEquals(newDonorData.getName(), actualDonor.getName());
    }

    @Test
    public void shouldDeleteDonor() throws Exception {
        Donor donorToDelete = new Donor();
        donorToDelete.setId(2L);

        mockMvc.perform(delete("/api/donors/" + donorToDelete.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(donorService).delete(donorToDelete.getId());
    }

    @Test
    public void shouldGetDonorById() throws Exception {
        Donor donorToRetrieve = new Donor();
        donorToRetrieve.setId(2L);

        mockMvc.perform(get("/api/donors/" + donorToRetrieve.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(donorService).getById(donorToRetrieve.getId());
    }
}
