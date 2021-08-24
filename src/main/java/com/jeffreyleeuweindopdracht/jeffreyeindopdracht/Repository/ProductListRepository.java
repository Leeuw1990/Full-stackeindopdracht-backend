package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ProductList;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long> {

    public Collection<ProductList> findAllByUsers(Users users);
}
