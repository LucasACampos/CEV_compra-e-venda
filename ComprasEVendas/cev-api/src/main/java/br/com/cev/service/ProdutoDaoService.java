package br.com.cev.service;

import br.com.cev.dao.ProdutoDao;
import br.com.cev.entity.Produto;
import br.com.cev.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoDaoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoDaoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Optional<ProdutoDao> getById(Long id) {

        try {
            Produto produto = produtoRepository.getReferenceById(id);

            return Optional.of(new ProdutoDao(produto));

        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }

    }


    public Page<ProdutoDao> getProdutoListPaginada(Pageable pageable) {

        Page<Produto> produtoPage = produtoRepository.findAll(pageable);

        return produtoPage.map(ProdutoDao::new);
    }
}
