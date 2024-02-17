package br.com.kmpx.pixconsumer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kmpx.pixconsumer.model.Key;

@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {
    Key findByChave(String key);
}