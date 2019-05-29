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
import br.senaigo.mobile.entities.Product;
import br.senaigo.mobile.interfaces.GenericOperations;
import br.senaigo.mobile.service.ProductService;

public class ProductController extends PadraoController<Product>{

	
	@Autowired
	public ProductService productService;

	
	@Override
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaTypes.HAL_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Resource<Product>> post(@RequestBody Product product) {
		try {
			productService.post(product);
			getLogger().info("Registro inserido");

			Link link = linkTo(ProductController.class).slash(product.getIdProduct()).withSelfRel();
			Resource<Product> result = new Resource<Product>(product, link);
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
	public ResponseEntity<Resources<Product>> get() {
		try {
			List<Product> products = productService.get();

			getLogger().info(String.format("Registro(s) recuperados(s): %s", products.toString()));

			Link link = linkTo(ProductController.class).withSelfRel();
			Resources<Product> result = new Resources<Product>(products, link);
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
	public ResponseEntity<Resource<Product>> get(@PathVariable("id") Long id) {
		try {
			Product product = productService.get(Product.builder().idProduct(id).build());
			getLogger().info(String.format("Registro recuperado: %s", product.toString()));
			Link link = linkTo(ProductController.class).slash(product.getId()).withSelfRel();
			Resource<Product> result = new Resource<Product>(product, link);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			getLogger().error(String.format("Erro ao executar o método GET.\nMensagem: %s", e.getMessage()));
			return ResponseEntity.badRequest().body(null);
		}
	}

	@Override
	public Class<?> getClassController() {
		return ProductController.class;
	}

	@Override
	public GenericOperations<Product> getGenericOperations() {
		return productService;
	}

}
