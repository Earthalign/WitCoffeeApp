package WWapp;

import WWapp.Product.Product;
import WWapp.Product.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository prepo;

    @Test
    public void testProductCreate() {
        Product product = new Product();
        product.setProduct_name("Kawa Etiopiaa 300g");
        product.setProduct_price(25);
        product.setProduct_description("Kawa z najlepszych plantacji z Etiopii");
        product.setProduct_inStock(10);
        Product savedProduct = prepo.save(product);
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getProduct_id()).isGreaterThan(0);
    }

    @Test
    public void testList(){

       Iterable <Product> products = prepo.findAll();
       Assertions.assertThat(products).hasSizeGreaterThan(0);
        for (Product product : products) {
            System.out.println(product);
        }

    }


    @Test
    public void testEdit (){

        Integer productId = 1;
        Optional<Product> optionalProduct = prepo.findById(productId);
        Product product = optionalProduct.get();
        product.setProduct_inStock(10);
        prepo.save(product);

        Product updatedProduct = prepo.findById(productId).get();
        Assertions.assertThat(updatedProduct.getProduct_inStock()).isEqualTo(10);

    }

    @Test
    public void testGet(){

        Integer productId = 1;
        Optional<Product> optionalProduct = prepo.findById(productId);
        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());
    }

    @Test
    public void testDelete(){
        Integer productId = 2;
        prepo.deleteById(productId);
        Optional<Product> optionalProduct = prepo.findById(productId);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }

}
