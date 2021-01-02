import org.junit.jupiter.api.Test;
import training.nadia.hospital.entity.Nurse;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.exception.ServiceException;
import training.nadia.hospital.service.NurseService;
import training.nadia.hospital.service.impl.NurseServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class NurseServiceImplTest {

    @Test
    void performTheTherapy() throws ServiceException {

        Patient patient = new Patient();
        patient.setId(1);
        patient.setTreatment(new Treatment());
        patient.getTreatment().setNumberOfTherapies(10);
        patient.getTreatment().setNumberOfCompletedTherapies(3);

        Nurse nurse = new Nurse();
        nurse.addPatient(patient);

        NurseService nurseService = new NurseServiceImpl();

        nurseService.performTheTherapy(nurse, patient.getId());

        assertEquals(4, patient.getTreatment().getNumberOfCompletedTherapies());
    }
}