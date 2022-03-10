package petstore;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import petstore.models.pet.Pet;
import petstore.steps.PetSteps;
import petstore.utils.TestDataGenerator;


@Epic("Pet store")
@Feature("Pet")
@Story("Delete")
@DisplayName("Delete Pet")
public class DeletePetTest extends BaseTest {

    private final PetSteps petSteps = new PetSteps();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Delete pet")
    public void deletePet() {
        petSteps.createPetSuccessfully(fullDataPet)
                .deletePetById(fullDataPet.getId().toString())
                .getNotFoundPetById(fullDataPet.getId().toString());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Attempt to delete pet by non-exist")
    public void deleteNotFoundPetTest() {
        final String notFoundId = "-1";
        petSteps.deleteNotFoundPetById(notFoundId);
    }
}
