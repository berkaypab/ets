package petstore;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import petstore.data.IncorrectData;
import petstore.models.pet.Pet;
import petstore.steps.PetSteps;
import petstore.utils.TestDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Pet store")
@Feature("Pet")
@Story("Put")
@DisplayName("Update Pet")
public class UpdatePetTest extends BaseTest {

    private final PetSteps petSteps = new PetSteps();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();
    private final Pet modifiedPet = fullDataPet.toBuilder().name("hey").build();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Update Data for pet")
    public void updateFullDataPet() {
        petSteps.createPetSuccessfully(fullDataPet)
                .putPetSuccessfully(modifiedPet)
                .assertPetData(modifiedPet);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Create a new pet using the put method")
    public void putNewPet() {
        petSteps.putPetSuccessfully(fullDataPet).assertPetData(fullDataPet);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Incorrect request body with put method")
    public void putIncorrectJson() {
        petSteps.putBadRequest(IncorrectData.INCORRECT_JSON);
    }
}
