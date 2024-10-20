package br.com.cev.restcontrollers;

import br.com.cev.dao.ProdutoDao;
import br.com.cev.service.ProdutoDaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/produto")
public class ProdutoRestController extends AbstractRestController{

    private final ProdutoDaoService produtoDaoService;

    public ProdutoRestController(
            ProdutoDaoService produtoDaoService
    ) {
        this.produtoDaoService = produtoDaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDao> getProdutoById(
            @PathVariable Long id
    ){
        Optional<ProdutoDao> produtoDao = produtoDaoService.getById(id);

        return produtoDao
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping()
    public ResponseEntity<Page<ProdutoDao>> getProdutoList(
            @PageableDefault Pageable pageable
    ){
        Page<ProdutoDao> produtoListPaginada = produtoDaoService.getProdutoListPaginada(pageable);

        return ResponseEntity.ok(produtoListPaginada);
    }

}
