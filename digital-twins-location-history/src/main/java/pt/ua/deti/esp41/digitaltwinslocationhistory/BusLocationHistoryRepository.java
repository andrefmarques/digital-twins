package pt.ua.deti.esp41.digitaltwinslocationhistory;

import org.springframework.data.repository.CrudRepository;

public interface BusLocationHistoryRepository extends CrudRepository<BusLocationHistory, Long> {

    Iterable<BusLocationHistory> findByBusId(String busId);

}
