package com.example.removie.cinema.repository;

import com.example.removie.cinema.CinemaEntity;
import org.springframework.data.repository.CrudRepository;

public interface CurrentCinemaRepository extends CrudRepository<CinemaEntity, String> {

}
