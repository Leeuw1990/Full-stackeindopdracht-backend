package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository;


import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductFileDBRepository extends JpaRepository<Product, String> {

}
