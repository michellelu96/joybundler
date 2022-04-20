package com.michelle.joybundler.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.michelle.joybundler.models.BabyName;

public interface BabyNameRepository extends CrudRepository<BabyName,Long>{
	List<BabyName> findAll();
	Optional<BabyName> findByName(String search);
}
