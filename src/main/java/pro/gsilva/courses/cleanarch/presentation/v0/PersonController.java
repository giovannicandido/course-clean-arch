package pro.gsilva.courses.cleanarch.presentation.v0;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.gsilva.courses.cleanarch.application.PersonUseCase;
import pro.gsilva.courses.cleanarch.domain.person.PersonId;
import pro.gsilva.courses.cleanarch.presentation.v0.request.PersonRequest;
import pro.gsilva.courses.cleanarch.presentation.v0.response.PersonResponse;
import pro.gsilva.courses.cleanarch.presentation.v0.response.PersonResponseList;
import pro.gsilva.courses.cleanarch.domain.error.ResourceNotFoundException;

import static pro.gsilva.courses.cleanarch.presentation.v0.Constants.API_ROOT;
import static pro.gsilva.courses.cleanarch.presentation.v0.Constants.API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping( PersonController.CTRL_PATH)
@Tag(name = Constants.PERSON_SUMMARY, description = Constants.PERSON_DESCRIPTION)
public class PersonController {
    public static final String CTRL_PATH = API_ROOT + API_VERSION + "/persons";
    private final PersonUseCase personUseCase;

    @GetMapping
    public PersonResponseList findAll() {
        return PersonResponseList.of(personUseCase.findAll());
    }

    @GetMapping( path = "/{id}" )
    public PersonResponse findPersonById(@PathVariable Long id) {
        return personUseCase.findPersonById(PersonId.of(id))
                .map(PersonResponse::of)
                .orElseThrow(ResourceNotFoundException.ofId(id.toString()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse createNewPerson(@RequestBody @Valid PersonRequest personRequest) {
        return PersonResponse.of(personUseCase.createNewPerson(personRequest.toDomain()));
    }

    @PutMapping(path = "/{id}")
    public PersonResponse updatePerson(@PathVariable Long id, @RequestBody @Valid PersonRequest personRequest) {
        var person = personRequest.toDomain(id);
        return PersonResponse.of(personUseCase.updatePerson(person));
    }

    @DeleteMapping(path = "/{id}")
    public void deletePersonById(@PathVariable Long id) {
        personUseCase.deletePerson(PersonId.of(id));
    }

}
