package br.com.roger.api.bytepizza.cardapio.repository;

import br.com.roger.api.bytepizza.cardapio.models.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {
    @Query("""
        SELECT s FROM Sabor s
        JOIN FETCH s.categoria c
        WHERE s.ativo = true AND c.ativa = true
        ORDER BY c.ordem, s.preco, s.nome
    """)
    List<Sabor> findAtivosComCategoria();
}
