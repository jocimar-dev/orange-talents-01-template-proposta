package com.zup.proposta.carteira;

import com.zup.proposta.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByTipoCarteiraAndCartao(CarteiraTipoenum carteiraTipoenum, Cartao cartao);
}