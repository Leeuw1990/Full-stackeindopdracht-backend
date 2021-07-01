package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Collection<Product> findAllByShopName(String shopName);

}
