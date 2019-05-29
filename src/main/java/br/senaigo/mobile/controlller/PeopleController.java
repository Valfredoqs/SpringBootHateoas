package br.senaigo.mobile.controlller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.senaigo.mobile.controlller.abstracts.PadraoController;
import br.senaigo.mobile.entities.People;
import br.senaigo.mobile.interfaces.GenericOperations;
import br.senaigo.mobile.service.PeopleService;

public class PeopleController extends PadraoController<People> {

	@Autowired
	public PeopleService peopleService;

	@Override
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaTypes.HAL_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Resource<People>> post(@RequestBody People people) {
		try {
			peopleService.post(people);
			getLogger().info("Registro inserido");

			Link link = linkTo(PeopleController.class).slash(people.getIdPeople()).withSelfRel();
			Resource<People> result = new Resource<People>(people, link);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} catch (Exception e) {
			getLogger().error(String.format("Erro ao executar o método POST.\nMensagem: %s", e.getMessage()));
			return ResponseEntity.badRequest().body(null);
		}
	}

	@Override
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			MediaTypes.HAL_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resources<People>> get() {
		try {
			List<People> peoples = peopleService.get();

			getLogger().info(String.format("Registro(s) recuperados(s): %s", peoples.toString()));

			Link link = linkTo(PeopleController.class).withSelfRel();
			Resources<People> result = new Resources<People>(peoples, link);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			getLogger().error(String.format("Erro ao executar o método GET.\nMensagem: %s", e.getMessage()));
			return ResponseEntity.badRequest().body(null);
		}
	}

	@Override
	@GetMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE, MediaTypes.HAL_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resource<People>> get(@PathVariable("id") Long id) {
		try {
			People people = peopleService.get(People.builder().idPeople(id).build());
			getLogger().info(String.format("Registro recuperado: %s", people.toString()));
			Link link = linkTo(PeopleController.class).slash(people.getId()).withSelfRel();
			Resource<People> result = new Resource<People>(people, link);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			getLogger().error(String.format("Erro ao executar o método GET.\nMensagem: %s", e.getMessage()));
			return ResponseEntity.badRequest().body(null);
		}
	}

	@Override
	public Class<?> getClassController() {
		return PeopleController.class;
	}

	@Override
	public GenericOperations<People> getGenericOperations() {
		return peopleService;
	}

}
