package br.com.gustavo.simi.Config;

import br.com.gustavo.simi.Domain.Entities.*;
import br.com.gustavo.simi.Domain.Enums.Period;
import br.com.gustavo.simi.Domain.Enums.Role;
import br.com.gustavo.simi.Domain.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SemesterRepository semesterRepository;
    private final SpecialtyRepository specialtyRepository;
    private final LocationRepository locationRepository;
    private final PreceptorRepository preceptorRepository;
    private final StudentRepository studentRepository;
    private final ShiftRepository shiftRepository;

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedSpecialties();
        seedSemester();
        seedLocations();
        seedPreceptors();
        seedStudents();
        seedShifts();
    }

    private void seedUsers() {
        if(userRepository.count() == 0) {
            User coordinator = User.builder()
                    .name("Admin")
                    .email("admin@mail.com")
                    .role(Role.COORDINATOR)
                    .build();
            userRepository.save(coordinator);

            User studentUser = User.builder()
                    .name("Student 1")
                    .email("student@mail.com")
                    .role(Role.STUDENT)
                    .build();
            userRepository.save(studentUser);

            User preceptorUser = User.builder()
                    .name("Preceptor 1")
                    .email("preceptor@mail.com")
                    .role(Role.PRECEPTOR)
                    .build();
            userRepository.save(preceptorUser);
        }
    }

    private void seedSpecialties() {
        if(specialtyRepository.count() == 0) {
            Specialty specialty = Specialty.builder()
                    .name("Ambulatório")
                    .build();
            specialtyRepository.save(specialty);
        }
    }

    private void seedSemester() {
        if(semesterRepository.count() == 0) {
            User coordinator = (User) userRepository.findByRole(Role.COORDINATOR).get(0);
            Semester semester = Semester.builder()
                    .label("2025.2")
                    .startsAt(LocalDate.of(2025,8,30))
                    .endsAt(LocalDate.of(2025,10,30))
                    .coordinator(coordinator)
                    .build();
            semesterRepository.save(semester);
        }
    }

    private void seedLocations() {
        if(locationRepository.count() == 0) {
            User coordinator = (User) userRepository.findByRole(Role.COORDINATOR).get(0);
            Specialty specialty = specialtyRepository.findAll().get(0);
            Location location = Location.builder()
                    .name("Hospital Regional de João Pessoa")
                    .state("PB")
                    .address("Av. Epitácio Pessoa, 1000")
                    .city("João Pessoa")
                    .coordinator(coordinator)
                    .specialties(Set.of(specialty))
                    .build();
            locationRepository.save(location);
        }
    }

    private void seedPreceptors() {
        if(preceptorRepository.count() == 0) {
            User preceptorUser = (User) userRepository.findByRole(Role.PRECEPTOR).get(0);
            User coordinator = (User) userRepository.findByRole(Role.COORDINATOR).get(0);
            Preceptor preceptor = Preceptor.builder()
                    .user(preceptorUser)
                    .coordinator(coordinator)
                    .crm("CRP1234")
                    .build();
            preceptorRepository.save(preceptor);
        }
    }

    private void seedStudents() {
        if(studentRepository.count() == 0) {
            User studentUser = (User) userRepository.findByRole(Role.STUDENT).get(0);
            User coordinator = (User) userRepository.findByRole(Role.COORDINATOR).get(0);
            Student student = Student.builder()
                    .user(studentUser)
                    .coordinator(coordinator)
                    .registration("20250001")
                    .period(Period.valueOf("P9"))
                    .build();
            studentRepository.save(student);
        }
    }

    private void seedShifts() {
        if(shiftRepository.count() == 0) {
            Semester semester = semesterRepository.findAll().get(0);
            Location location = locationRepository.findAll().get(0);
            Specialty specialty = specialtyRepository.findAll().get(0);
            Preceptor preceptor = preceptorRepository.findAll().get(0);
            Shift shift = Shift.builder()
                    .semester(semester)
                    .location(location)
                    .specialty(specialty)
                    .preceptor(preceptor)
                    .startsAt(LocalDateTime.of(2025, 9, 28, 7,0))
                    .endsAt(LocalDateTime.of(2025, 9, 28, 13,0))
                    .maxSlots(5)
                    .build();
            shiftRepository.save(shift);
        }
    }
}
