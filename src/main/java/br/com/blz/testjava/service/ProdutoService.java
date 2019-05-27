package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto addProduto(Produto produto) {
        if(produtoRepository.findOne(produto.getSku()) != null) {
            if (produtoRepository.findOne(produto.getSku()).equals(produto.getSku())) {
                throw new RuntimeException("Produtos Iguais");
            }
        }
        return produtoRepository.saveAndFlush(produto);
    }
    public Produto updateProduto(Long sku,Produto newProduto) {
        Produto produto = produtoRepository.findOne(sku);
        produto.setName(newProduto.getName());
        produto.setInventory(newProduto.getInventory());
        produto.setMarketable(newProduto.getMarketable());
        return produtoRepository.saveAndFlush(produto);
    }
    public Produto searchProduto(Long sku){
        int soma=0;
        Produto produto = produtoRepository.findOne(sku);
        Inventory inventory = produto.getInventory();

        for(Warehouse warehouse: inventory.getWarehouses()){
            soma += warehouse.getQuantity();
        }
        inventory.setQuantity(soma);
        if(inventory.getQuantity()>0){
            produto.setInventory(inventory);
            produto.setMarketable(true);
            return produto;
        }
        return produto;
    }
    public void deleteProduto(Long sku){
        produtoRepository.delete(sku);
    }
}
