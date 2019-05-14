package ua.ita.smartcarservice.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.booking.WorkDependency;

@Repository
public interface WorkDependencyRepository extends JpaRepository<WorkDependency, Long> {

}
