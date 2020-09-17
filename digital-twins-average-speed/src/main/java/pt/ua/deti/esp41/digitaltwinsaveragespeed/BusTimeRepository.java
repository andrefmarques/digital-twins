package pt.ua.deti.esp41.digitaltwinsaveragespeed;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BusTimeRepository extends CrudRepository<BusTime, Long> {

    Iterable<BusTime> findByBusId(String busId);

//    @Query("FROM BusTime AS b WHERE b.busId=busID")
//    Iterable<BusTime> findByBusID(@Param("busID") String busID);

}
