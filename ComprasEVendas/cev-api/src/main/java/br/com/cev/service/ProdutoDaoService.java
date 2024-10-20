package br.com.cev.service;

import br.com.cev.dao.ProdutoCreateDao;
import br.com.cev.dao.ProdutoDao;
import br.com.cev.entity.Produto;
import br.com.cev.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
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

    public ProdutoDao create(ProdutoCreateDao produtoCreateDao) {

        Produto novoProduto = new Produto(
                null,
                produtoCreateDao.nome(),
                produtoCreateDao.preco(),
                produtoCreateDao.descricao()
        );

        Produto produto = produtoRepository.save(novoProduto);

        return new ProdutoDao(
                produto
        );
    }

    public Optional<ProdutoDao> patch(ProdutoDao produtoParaAtualizar) {

        boolean exists = produtoRepository.existsById(produtoParaAtualizar.id());

        if(!exists){
            return Optional.empty();
        }

        Produto produto = produtoRepository.getReferenceById(produtoParaAtualizar.id());

        Produto produtoAtualizado = new Produto(
                produto.getId(),
                StringUtils.isNotBlank(produtoParaAtualizar.nome()) ? produtoParaAtualizar.nome() : produto.getNome(),
                produtoParaAtualizar.preco() != null ? produtoParaAtualizar.preco() : produto.getPreco(),
                StringUtils.isNotBlank(produtoParaAtualizar.descricao()) ? produtoParaAtualizar.descricao() : produto.getDescricao()
        );

        return Optional.of(new ProdutoDao(produtoRepository.save(produtoAtualizado)));
    }

    public ProdutoDao put(ProdutoDao produtoDao) {

        Produto produto = new Produto(
                produtoDao.id(),
                produtoDao.nome(),
                produtoDao.preco(),
                produtoDao.descricao()
        );

        return new ProdutoDao(produtoRepository.save(produto));
    }

    public boolean delete(Long produtoId) {

        boolean exists = produtoRepository.existsById(produtoId);

        if(exists){
            produtoRepository.deleteById(produtoId);
            return true;
        }

        return false;
    }
}
