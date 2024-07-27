package com.example.service;

import com.example.model.Aluguel;
import com.example.repository.AluguelRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PagamentoService {

    private final AluguelRepository aluguelRepository;

    public PagamentoService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    @Transactional
    public void registrarPagamento(Long aluguelId, LocalDate dataPagamento) {
        Aluguel aluguel = aluguelRepository.buscarPorId(aluguelId);

        if (aluguel != null) {
            aluguel.setDataPagamento(dataPagamento);

            long diasAtraso = ChronoUnit.DAYS.between(aluguel.getDataVencimento(), dataPagamento);
            double multa = 0;

            if (diasAtraso > 0) {
                multa = Math.min(0.33 * diasAtraso, 20) / 100 * aluguel.getValor();
            }

            aluguel.setValor(aluguel.getValor() + multa);
            aluguelRepository.atualizar(aluguel);
        }
    }
}
