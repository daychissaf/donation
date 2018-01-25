package donation.donors.controller;


import donation.common.controller.CrudController;
import donation.common.service.CrudService;
import donation.donors.model.Donor;
import donation.donors.service.DonorCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/donors")
public class DonorCrudController extends CrudController<Donor> {

    @Autowired
    private DonorCrudService donorService;

    @Override
    public CrudService<Donor> service() {
        return this.donorService;
    }
}
