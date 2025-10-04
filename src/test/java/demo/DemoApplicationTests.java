package demo;

import demo.HMS.entities.PatientEntity;
import demo.HMS.repositoies.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void patients() {
        PatientEntity patient = PatientEntity
                .builder().email("patien-2@email.com").name("patient-2")
                .birthDate(LocalDate.of(2004, 9, 10)).build();
        System.out.println("OBJ : " + patient);
        patientRepository.save(patient);
        System.out.println("✅ Patient saved successfully!");
    }

    @Test
    public void getAllPatients() {
        List<PatientEntity> patients = patientRepository.findAll();
        if (patients.isEmpty()) {
            System.out.println("No patients found!");
        } else {
            patients.forEach(patient -> System.out.println(patient.getEmail()));
        }
    }
}
