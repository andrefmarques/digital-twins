package pt.ua.deti.esp41.digitaltwinsbusroutes;

import org.springframework.data.repository.CrudRepository;

public interface BusLocationRepository extends CrudRepository<BusLocations, Long> {

    Iterable<BusLocations> findByBusId(String busId);

}
