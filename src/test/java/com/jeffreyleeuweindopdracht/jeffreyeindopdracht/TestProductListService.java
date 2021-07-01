package com.jeffreyleeuweindopdracht.jeffreyeindopdracht;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestProductListService {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void itShouldReturnAProductList(){
        //Arrange
        String productListName = "Boekenkast";


        //Act


        //Assert

    }



}
