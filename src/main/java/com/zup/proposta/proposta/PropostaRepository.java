package com.zup.proposta.proposta;

import com.zup.proposta.enums.StatusConsultaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(String documento);

    boolean existsByDocumento(String documento);

    @Query("select p from Proposta p LEFT JOIN p.cartao c where p.estado = 'GERADO'  and c is null")
    List<Proposta> findAllElegiveisSemCartao(StatusConsultaEnum statusConsultaEnum);

}
