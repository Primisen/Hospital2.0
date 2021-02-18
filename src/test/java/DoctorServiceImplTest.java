import org.junit.jupiter.api.Test;
import training.nadia.hospital.entity.Doctor;
import training.nadia.hospital.entity.Patient;
import training.nadia.hospital.entity.Treatment;
import training.nadia.hospital.entity.TreatmentType;
import training.nadia.hospital.exception.ServiceException;
import training.nadia.hospital.service.DoctorService;
import training.nadia.hospital.service.impl.DoctorServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServiceImplTest {

    private Patient patient = new Patient();
    private Doctor doctor = new Doctor();
    private DoctorService doctorService = new DoctorServiceImpl();

    @Test
    void setDiagnosisAndTreatmentIfTreatmentAndDiagnosisAreNull() throws ServiceException {

        String diagnosis = null;
        Treatment treatment = null;

        patient.setId(1); //вынести в отдельный метод
        doctor.setId(2);

        doctorService.setDiagnosisAndTreatment(diagnosis, treatment, patient, doctor);

        assertEquals(false, patient.getTreatment().isActive());
    }

    @Test
    void dischargePatient() throws ServiceException {

        patient.setId(1); //вынести в отдельный метод
        doctor.setId(2);

        Treatment treatment = new Treatment();
        treatment.setActive(true);
        treatment.setType(TreatmentType.PROCEDURE);
        treatment.setNumberOfCompletedTherapies(50);
        treatment.setNumberOfTherapies(50);

        patient.setTreatment(treatment);

        doctorService.dischargePatient(patient, null);

        assertEquals(false, patient.getTreatment().isActive());
    }

    @Test
    void dischargePatientIfNumberOfCompletedTherapiesNotEqualsNumberOfAllTherapies() throws ServiceException {

        patient.setId(1); //вынести в отдельный метод
        doctor.setId(2);

        Treatment treatment = new Treatment();
        treatment.setActive(true);
        treatment.setType(TreatmentType.PROCEDURE);
        treatment.setNumberOfCompletedTherapies(34);
        treatment.setNumberOfTherapies(50);

        doctorService.dischargePatient(patient, null);

        patient.setTreatment(treatment);

        assertEquals(true, patient.getTreatment().isActive());
    }
}