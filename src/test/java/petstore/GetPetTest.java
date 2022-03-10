package petstore;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import petstore.models.pet.Pet;
import petstore.steps.PetSteps;
import petstore.utils.TestDataGenerator;

@Epic("Pet store")
@Feature("Pet")
@Story("Get")
@DisplayName("Get Pet")
public class GetPetTest extends BaseTest {

    private final PetSteps petSteps = new PetSteps();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();
    private final String notFoundId = "-1";

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Get pet")
    public void getPetTest() {
        petSteps.createPetSuccessfully(fullDataPet).assertPetData(fullDataPet);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Attempt to request get pet by non-exist Id ")
    public void getNotFoundPetTest() {
        petSteps.getNotFoundPetById(notFoundId);
    }
}
