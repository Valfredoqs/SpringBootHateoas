package br.senaigo.mobile.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senaigo.mobile.entities.People;
import br.senaigo.mobile.repositories.PeopleRepository;
import br.senaigo.mobile.service.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService {

	Logger logger = LoggerFactory.getLogger(PeopleService.class);
	
	@Autowired
	public PeopleRepository peopleRepository;
	
	@Override
	public People post(People entity) {
		try {
			logger.debug("\tMétodo POST executado.");
			logger.debug("\tMétodo POST invocado");
			logger.debug(String.format("\tValor recebido: %s",entity.toString()));
			
			peopleRepository.save(entity);
			
			logger.info(String.format("\tValor persistido: %s",entity.toString()));
			return entity;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir registro. \nMensagem:%s",e.getMessage()));
		}
		return null;
	}

	@Override
	public People get(People entity) {
		try {
			peopleRepository.getOne(entity.getIdPeople());
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
		return null;
	}

	@Override
	public void put(People entity) {
		try {
			peopleRepository.getOne(entity.getIdPeople());
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
	}

	@Override
	public void delete(People entity) {
		try {
			peopleRepository.delete(entity);
		} catch (Exception e) {
			logger.error("Error ao deletar registro.");
		}
	}

	@Override
	public void patch(People entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<People> post(List<People> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(List<People> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<People> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void patch(List<People> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<People> get() {
		try {
			return peopleRepository.findAll();
		} catch (Exception e) {
			logger.error("Error ao recuperar registro." +e.getMessage());
		}
		return null;
	}

}
