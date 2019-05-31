package br.senaigo.mobile.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senaigo.mobile.entities.Product;
import br.senaigo.mobile.repositories.ProductRepository;
import br.senaigo.mobile.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	public ProductRepository  productRepository;
	
	@Override
	public Product post(Product entity) {
		try {
			logger.debug("\tMétodo POST executado.");
			logger.debug("\tMétodo POST invocado");
			logger.debug(String.format("\tValor recebido: %s",entity.toString()));
			
			productRepository.save(entity);
			
			logger.info(String.format("\tValor persistido: %s",entity.toString()));
			return entity;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir registro. \nMensagem:%s",e.getMessage()));
		}
		return null;
	}
	@Override
	public Product get(Product entity) {
		try {
			productRepository.getOne(entity.getIdProduct());
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
		return null;
	}


	@Override
	public void put(Product entity) {
		try {
			productRepository.getOne(entity.getIdProduct());
		} catch (Exception e) {
			logger.error("Error ao recuperar método GET.");
		}
	}
	@Override
	public void delete(Product entity) {
		try {
			productRepository.delete(entity);
		} catch (Exception e) {
			logger.error("Error ao deletar registro.");
		}
	}

	@Override
	public void patch(Product entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> post(List<Product> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(List<Product> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<Product> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void patch(List<Product> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> get() {
		try {
			return productRepository.findAll();
		} catch (Exception e) {
			logger.error("Error ao recuperar registro." +e.getMessage());
		}
		return null;
	}

}
