package pt.ua.deti.esp41.digitaltwinsaveragespeed;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pt.ua.deti.esp41.digitaltwinsaveragespeed.BusTime;
import pt.ua.deti.esp41.digitaltwinsaveragespeed.BusTimeRepository;
import scala.Int;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.*;


class CreateBus {
    static Integer createBus(Integer bus) {
        return 1;
    }

}
@RunWith(SpringRunner.class)
@DataJpaTest
public class StepDefinitions {

    private Integer expectedBus;
    private Integer actualBus;
    private Double expectedAvgSpeed;
    private Double stoppedBusSpeed;
    private Double isMoving;
    private RestTemplate restTemplate = new RestTemplate();
    private Integer busNumber3 = 3;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private BusTimeRepository busRepository;
    private Iterable<BusTime> found;

    //Belmiro 1st Scenario (create_digital_twins_from_elements_of_bus_line)
    @Given("Belmiro wants to test a bus line idea and he accesses the emulation section.")
    public void belmiro_wants_to_test_a_bus_line_idea_and_he_accesses_the_emulation_section() {
        expectedBus = 1;
    }

    @When("He creates {int} virtual bus and defines its route.")
    public void he_creates_virtual_bus_and_defines_its_route(Integer int1) {
            actualBus = CreateBus.createBus(int1);
    }

    @Then("The app must spawn {int} bus and show how it performs from the departure station to the arrival station.")
    public void the_app_must_spawn_bus_and_show_how_it_performs_from_the_departure_station_to_the_arrival_station(Integer expectedBus) {
        //assertEquals(expectedBus, actualBus);
    }


    //Belmiro 2nd Scenario (create_digital_persona)
    @Given("Belmiro needs to create virtual personas to test the bus line.")
    public void belmiroNeedsToCreateVirtualPersonasToTestTheBusLine() {

    }

    @When("He accesses the digital persona creation section and he creates a virtual person with name, age, background learning skills and request that will be used in a virtual bus line test.")
    public void heAccessesTheDigitalPersonaCreationSectionAndHeCreatesAVirtualPersonWithNameAgeBackgroundLearningSkillsAndRequestThatWillBeUsedInAVirtualBusLineTest() {

    }

    @Then("The app must generate the digital person and confirm its creation.")
    public void theAppMustGenerateTheDigitalPersonAndConfirmItsCreation() {
    }


    //Belmiro 3rd Scenario (change_bus_route)
    @Given("Belmiro needs to change the route of bus number {int} to make a new test.")
    public void belmiroNeedsToChangeTheRouteOfBusNumberToMakeANewTest(int arg0) {
    }

    @When("He accesses the already created bus, taps on it and redefines a new route.")
    public void heAccessesTheAlreadyCreatedBusTapsOnItAndRedefinesANewRoute() {
    }

    @Then("The app must change the bus number {int}’s route and display the newly updated route.")
    public void theAppMustChangeTheBusNumberSRouteAndDisplayTheNewlyUpdatedRoute(int arg0) {
    }


    //Paula 1st Scenario (notify_bus_passing)
    @Given("Paula, is at the Aliados bus station waiting for a bus.")
    public void paulaIsAtTheAliadosBusStationWaitingForABus() {
    }

    @When("The bus stops by the station before the station she is.")
    public void theBusStopsByTheStationBeforeTheStationSheIs() {
    }

    @Then("The app must send a notification to Paula that her bus is coming in {int} minutes.")
    public void theAppMustSendANotificationToPaulaThatHerBusIsComingInMinutes(int arg0) {
    }


    //Paula 2nd Scenario (time_for_bus_to_arrive)
    @Given("Paula, is at the Arrabida bus station waiting for a bus.")
    public void paulaIsAtTheArrabidaBusStationWaitingForABus() {
    }

    @When("She writes the station name where she is.")
    public void sheWritesTheStationNameWhereSheIs() {
    }

    @Then("The app must tell her how much time the next bus takes to arrive.")
    public void theAppMustTellHerHowMuchTimeTheNextBusTakesToArrive() {
    }


    //Paula 3rd Scenario (find_best_route)
    @Given("Paula, is at the Arrabida bus station waiting for a bus and wants to travel to Aliados.")
    public void paulaIsAtTheArrabidaBusStationWaitingForABusAndWantsToTravelToAliados() {
    }

    @When("She checks the buses that stop by her station.")
    public void sheChecksTheBusesThatStopByHerStation() {
    }

    @Then("The app must show her that the bus that has the shortest route to her destination is bus number {int}.")
    public void theAppMustShowHerThatTheBusThatHasTheShortestRouteToHerDestinationIsBusNumber(int arg0) {
    }


    @Given("bus {int}'s expected average speed is {int}.")
    public void bus_s_expected_average_speed_is(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        //expectedAvgSpeed = restTemplate.getForObject("http://192.168.160.103:41001/average-speed/" + busNumber3.toString(), Double.class);

    }

    @When("Belmiro checks its average speed.")
    public void belmiro_checks_its_average_speed() {
        // Write code here that turns the phrase above into concrete actions
        //System.out.println("Belmiro is checking bus speed...");
    }

    @Then("the system must return it's {int}.")
    public void the_system_must_return_it_s(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
             //if(expectedAvgSpeed<20.0 || expectedAvgSpeed>70.0){
             //    throw new io.cucumber.java.PendingException();
             //}
             //else System.out.println("The bus " + busNumber3.toString() + " average speed is " + expectedAvgSpeed.toString());

    }




    @Given("bus {int}'s average speed is {int}.")
    public void bus_s_average_speed_is(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        //stoppedBusSpeed = restTemplate.getForObject("http://192.168.160.103:41001/average-speed/7", Double.class);
        /*
        BusTime bus = new BusTime((long) 1, "10", 10);
        System.out.println("OLAAAAAA" + bus.toString());
        System.out.println("FFFFFFFFFFFFFF" + entityManager.toString());
        entityManager.merge(bus);
        entityManager.flush();

         */
    }

    @When("Belmiro checks if it is moving.")
    public void belmiro_checks_if_it_is_moving() {
        // Write code here that turns the phrase above into concrete actions
        //isMoving = 34.0;

        // found = busRepository.findByBusId("10");

    }

    @Then("the system must return it is stopped.")
    public void the_system_must_return_it_is_stopped() {
        // Write code here that turns the phrase above into concrete actions
        //if(stoppedBusSpeed < isMoving) {
        //    System.out.println("The bus is stopped");
        //}
        //else  throw new io.cucumber.java.PendingException();
        /*
        System.out.println("KEKEKEKEKEKEEK" + found.toString());
        double a = found.iterator().next().getSpeed();
        assert a == 10;
        */
    }
}
