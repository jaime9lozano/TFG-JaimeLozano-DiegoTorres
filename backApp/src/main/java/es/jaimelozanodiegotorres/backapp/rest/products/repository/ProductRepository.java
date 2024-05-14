package es.jaimelozanodiegotorres.backapp.rest.products.repository;


import es.jaimelozanodiegotorres.backapp.rest.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de productos que extiende de JpaRepository y JpaSpecificationExecutor
 * para poder realizar operaciones de persistencia sobre la base de datos.
 * También se utiliza la anotación @Repository para indicar que es un repositorio de Spring.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}