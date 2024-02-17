package br.com.kmpx.pixproducer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kmpx.pixproducer.config.model.Pix;

@Repository
public interface PixRepository extends JpaRepository<Pix, Integer> {

}