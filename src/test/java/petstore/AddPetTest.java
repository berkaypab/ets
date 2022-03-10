package petstore;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import petstore.data.IncorrectData;
import petstore.models.pet.Pet;
import petstore.steps.PetSteps;
import petstore.utils.TestDataGenerator;

@Epic("Pet store")
@Feature("Pet")
@Story("Post")
@DisplayName("Add Pet")
public class AddPetTest extends BaseTest {

    private final PetSteps petSteps = new PetSteps();
    private final Pet minDataPet = TestDataGenerator.generateMinDataPet();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Adding a pet with minimal set of fields")
    public void createMinDataPet() {
        petSteps.createPetSuccessfully(minDataPet);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Adding pet with max set of fields")
    public void createFullDataPet() {
        petSteps.createPetSuccessfully(fullDataPet);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Post method with an Incorrect request")
    public void postIncorrectJson() {
        petSteps.postBadRequest(IncorrectData.INCORRECT_JSON);
    }
}
