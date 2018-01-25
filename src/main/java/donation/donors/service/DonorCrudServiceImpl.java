package donation.donors.service;

import donation.common.service.CrudServiceImpl;
import donation.donors.model.Donor;
import org.springframework.stereotype.Service;

@Service
public class DonorCrudServiceImpl extends CrudServiceImpl<Donor> implements DonorCrudService {
}
