package br.com.roger.api.bytepizza.loja.repository;

import br.com.roger.api.bytepizza.loja.models.ConfiguracaoLoja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoLojaRepository extends JpaRepository<ConfiguracaoLoja, Long> { }
