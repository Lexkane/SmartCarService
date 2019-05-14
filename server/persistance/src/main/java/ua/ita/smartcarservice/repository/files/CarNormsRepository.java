package ua.ita.smartcarservice.repository.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.files.CarNorms;

@Repository
public interface CarNormsRepository extends JpaRepository<CarNorms, Long> {

}
