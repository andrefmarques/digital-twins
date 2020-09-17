/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.esp41.digitaltwinslocationrt;

/**
 *
 * @author Tiago Fonseca
 */
import org.springframework.data.repository.CrudRepository;

public interface BusLocationRTRepository extends CrudRepository<BusLocationRT, Long> {
    Iterable<BusLocationRT> findByBusId(String busId);
}
