package com.dmnoky.prd.controller;

import com.dmnoky.prd.model.Product;
import com.dmnoky.prd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static java.util.Collections.unmodifiableMap;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    @Qualifier(value = "productService")
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{id}")
    public String getProduct(@PathVariable int id, Model model){
        model.addAttribute("product", productService.getProduct(id));
        return "product";
    }

    @GetMapping(value = "/product/list")
    public String getListProduct(Model model){
        model.addAttribute("list", productService.getAllProducts());
        return "allProducts";
    }

    @GetMapping(value = "/product/add/{id}")
    public String addToBasket(@SessionAttribute(required = false) Map<Product, Integer> basket,
                            @PathVariable int id, HttpSession httpSession){
        Product product = productService.getProduct(id);
        if (basket == null){
            httpSession.setAttribute("basket", singletonMap(product, 1));
        } else {
            Map<Product, Integer> newBasket = new LinkedHashMap<>(basket);
            if (!newBasket.containsKey(product)) newBasket.put(product, 1);
            else newBasket.put(product, newBasket.get(product) + 1);
            httpSession.setAttribute("basket", unmodifiableMap(newBasket));
        }
        return "redirect:/product/{id}";
    }

    @GetMapping(value = "/product/{forwardId}/empty/{mapId}")
    public String emptyBasket(@SessionAttribute Map<Product, Integer> basket,
                              @PathVariable("forwardId") int forwardId,
                              @PathVariable("mapId") int mapId, HttpSession httpSession){
        Product product = productService.getProduct(mapId);
        Map<Product, Integer> newBasket = new LinkedHashMap<>(basket);
        int count = newBasket.get(product)-1;
        if (count > 0) newBasket.put(product, count);
        else newBasket.remove(product);
        httpSession.setAttribute("basket", unmodifiableMap(newBasket));
        return "redirect:/product/{forwardId}";
    }

    @RequestMapping(value = "/product/buy") //TODO
    public String buyProducts(@SessionAttribute Map<Product, Integer> basket,
                              HttpSession httpSession){
        httpSession.setAttribute("basket", null);
        return "redirect:/";
    }

}
