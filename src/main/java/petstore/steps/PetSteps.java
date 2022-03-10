package petstore.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import petstore.controllers.pet.PetController;
import petstore.models.apiResonse.ApiResponse;
import petstore.models.pet.Pet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetSteps extends PetController {

    @Step("Create pet successfully {pet}")
    public PetSteps createPetSuccessfully(Pet pet) {
        Response response = postPet(pet);
        assertStatusCode(HttpStatus.SC_OK, response);
        return this;
    }

    @Step("Post Invalid Object {pet}")
    public PetSteps postBadRequest(Object pet) {
        Response response = postPet(pet);
        assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
        return this;
    }

    @Step("Successfully get pet by Id {petId}")
    public Pet getPetById(String petId) {
        Response response = getPet(petId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return response.as(Pet.class);
    }

    @Step("Request non-exist pet id with get method {petId}")
    public PetSteps getNotFoundPetById(String petId) {
        Response response = getPet(petId);
        assertStatusCode(HttpStatus.SC_NOT_FOUND, response);
        assertErrorMessage("Pet not found", response.as(ApiResponse.class));
        return this;
    }

    @Step("Validate pet fields {expectedPet}")
    public PetSteps assertPetData(Pet expectedPet) {
        Pet pet = getPetById(expectedPet.getId().toString());
        assertThat(pet, equalTo(expectedPet));
        return this;
    }

    @Step("Successfully delete an existing pet by id {petId}")
    public PetSteps deletePetById(String petId) {
        Response response = deletePet(petId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return this;
    }

    @Step("Attempt to delete non-exist pet by id {petId}")
    public PetSteps deleteNotFoundPetById(String petId) {
        Response response = deletePet(petId);
        assertStatusCode(HttpStatus.SC_NOT_FOUND, response);
        return this;
    }

    @Step("Successful update of pet data {pet}")
    public PetSteps putPetSuccessfully(Pet pet) {
        Response response = putPet(pet);
        assertStatusCode(HttpStatus.SC_OK, response);
        assertPetData(pet);
        return this;
    }

    @Step("Put an Invalid object {pet}")
    public PetSteps putBadRequest(Object pet) {
        Response response = putPet(pet);
        assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
        return this;
    }


    @Step("Check status code {ExpectedStatus}")
    private void assertStatusCode(int ExpectedStatus, Response response) {
        assertThat(response.statusCode(), equalTo(ExpectedStatus));
    }

    @Step("Check message {expectedMessage}")
    public void assertErrorMessage(String expectedMessage, ApiResponse apiResponse) {
        assertThat(apiResponse.getMessage(), equalTo(expectedMessage));
    }

}
