package com.example;

import com.example.model.*;
import com.example.repository.*;
import com.example.service.PagamentoService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab_04");

        ImovelRepository imovelRepository = new ImovelRepository(emf);
        ClienteRepository clienteRepository = new ClienteRepository(emf);
        LocacaoRepository locacaoRepository = new LocacaoRepository(emf);
        ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(emf);
        AluguelRepository aluguelRepository = new AluguelRepository(emf);
        PagamentoService pagamentoService = new PagamentoService(aluguelRepository);

        // Adicionar e listar imóveis
        Imovel imovel = new Imovel();
        imovel.setEndereco("Rua A, 123");
        imovel.setDisponivel(true);
        imovelRepository.inserir(imovel);

        List<Imovel> imoveis = imovelRepository.listarTodos();
        imoveis.forEach(i -> System.out.println(i.getEndereco()));

        // Adicionar e listar clientes
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");
        cliente.setNome("João");
        clienteRepository.inserir(cliente);

        List<Cliente> clientes = clienteRepository.listarTodos();
        clientes.forEach(c -> System.out.println(c.getNome()));

        // Adicionar e listar locações
        Locacao locacao = new Locacao();
        locacao.setImovel(imovel);
        locacao.setCliente(cliente);
        locacao.setAtiva(true);
        locacaoRepository.inserir(locacao);

        List<Locacao> locacoes = locacaoRepository.listarTodos();
        locacoes.forEach(l -> System.out.println(l.getCliente().getNome()));

        // Registrar pagamento de aluguel
        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(cliente);
        aluguel.setLocacao(locacao);
        aluguel.setValor(1000);
        aluguel.setDataVencimento(LocalDate.now().minusDays(5));
        aluguelRepository.inserir(aluguel);

        pagamentoService.registrarPagamento(aluguel.getId(), LocalDate.now());

        Aluguel aluguelAtualizado = aluguelRepository.buscarPorId(aluguel.getId());
        System.out.println("Valor atualizado do aluguel: " + aluguelAtualizado.getValor());

        emf.close();
    }
}
