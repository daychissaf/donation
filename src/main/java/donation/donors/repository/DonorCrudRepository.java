package donation.donors.repository;

import donation.donors.model.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorCrudRepository extends CrudRepository<Donor, Long> {
}
