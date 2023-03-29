package WWapp.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository prepo;

    public List<Product> listAll(){
        return (List<Product>) prepo.findAll(); //Szukanie wszystkich produktów
    }
    public void saveProduct(Product product) {
        prepo.save(product);
    }
    public Product get(int product_id) throws ProductNotFoundException {
        Optional<Product> result = prepo.findById(product_id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new ProductNotFoundException("Nie mogę znaleźc produktu z id: " + product_id);
    }
    public void deleteProduct(Integer product_id) throws ProductNotFoundException {
        Optional<Product> findproduct = prepo.findById(product_id);
        if(product_id == null || product_id == 0) {
            throw new ProductNotFoundException("Nie odnaleziono produktu o id: " + product_id);
        }
        prepo.deleteById(product_id);
    }


}
