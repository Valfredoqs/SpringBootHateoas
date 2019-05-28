package br.senaigo.mobile.interfaces;

import org.slf4j.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;

public interface GenericOperationsController<E> {
	
	ResponseEntity<Resource<E>> post(E entity); //POST 201
	HeadersBuilder<?> put(E entity); //PUT 204 - no content
	HeadersBuilder<?> delete(E entity); //DELETE 204
	ResponseEntity<Resources<E>> get(); // GET 200
	ResponseEntity<Resource<E>> get(Long id); //GET 200
	HeadersBuilder<?> patch(E entity); //PATCH 204
	
	Logger getLogger();
	Class<?> getClassController();
	GenericOperations<E> getGenericOperations();

}
