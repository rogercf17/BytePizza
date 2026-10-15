package br.com.roger.api.bytepizza.loja.repository;

import br.com.roger.api.bytepizza.loja.models.HorarioFuncionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamento, Long> { }
