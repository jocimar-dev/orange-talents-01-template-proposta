package com.zup.proposta.bloqueios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository extends JpaRepository<BloqueioCartao, Long> {
}
