package WWapp.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")        //Uzyskiwanie wszystkich produktów
    public String showAllProducts(Model model) {

        List<Product> productList = productService.listAll();   //ListAll z ProductService
        model.addAttribute("productList", productList);

        return "allProducts";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle","Dodaj nowe produkty ");
        return "new_product";
    }


    @PostMapping("/products/save")
    public String addProduct(Product product, RedirectAttributes r) {
        productService.saveProduct(product);
        r.addFlashAttribute("message", "Produkt został dodany ");
        return "redirect:/products";
    }


    @GetMapping("/products/edit/{product_id}")
    public String editProduct(@PathVariable("product_id") int product_id, Model model, RedirectAttributes r) {
        try {
            Product product = productService.get(product_id);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", " Edytuję produkt (o id: ) " + product_id);
            return "new_product";
        } catch (ProductNotFoundException p) {
            p.printStackTrace();
            r.addFlashAttribute("message", p.getMessage());
            return "redirect:/products";
        }
    }

    @GetMapping("/products/delete/{product_id}")
    public String deleteProduct(@PathVariable("product_id") int product_id, RedirectAttributes r) {
        try {
            productService.deleteProduct(product_id);
            r.addFlashAttribute("messagee", " Produkt został usunięty ");
            System.out.println("Skasowałeś produkt o id: " + product_id );
        } catch (ProductNotFoundException p) {
            p.printStackTrace();
            r.addFlashAttribute("messagee", p.getMessage());
        }
        return "redirect:/products";
    }


}
