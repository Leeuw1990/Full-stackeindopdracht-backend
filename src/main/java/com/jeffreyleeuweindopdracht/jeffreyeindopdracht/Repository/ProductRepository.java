package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    boolean existsById(Optional<Product> product);
    Page<Product> findByProductListId(Long product_list_id, Pageable pageable);
    Optional<Product> findByIdAndProductListId(String id, Long product_list_id);
}

//comment
