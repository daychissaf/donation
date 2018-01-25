package donation.donors.service;

import donation.donors.model.Donor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DonorCrudServiceImplTest {

    @Autowired
    DonorCrudService service;

    @MockBean
    CrudRepository<Donor, Long> repository;

    @Test
    public void shouldUpdateDonorProperties() {
        Donor initialDonorData = new Donor();
        initialDonorData.setId(1L);
        initialDonorData.setName("donor1");
        initialDonorData.setAge(20);

        Donor newDonorData = new Donor();
        newDonorData.setId(12L);
        newDonorData.setName("donor12");
        newDonorData.setAge(22);

        service.update(initialDonorData, newDonorData);

        assertEquals(new Long(1), initialDonorData.getId());
        assertEquals(newDonorData.getName(), initialDonorData.getName());
        assertEquals(newDonorData.getAge(), initialDonorData.getAge());
    }

}
