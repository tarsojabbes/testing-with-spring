package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import org.springframework.stereotype.Service;

@Service
public class ProdutoValidarCodigoBarrasImpl implements ProdutoValidarCodigoBarras {
    @Override
    public boolean isValidCodigoBarras(Produto p) {
       String codigoBarras = p.getCodigoBarra();
       int parte1 = 0;
       for (int i = codigoBarras.length() - 2; i >= 0; i = i - 2) {
           parte1 += Integer.parseInt(String.valueOf(codigoBarras.charAt(i)));
       }
       int parte2 = parte1 * 3;

       int parte3 = 0;
       for (int i = codigoBarras.length() - 3; i >= 0; i = i - 2) {
           parte3 += Integer.parseInt(String.valueOf(codigoBarras.charAt(i)));
       }

       int parte4 = parte2 + parte3;

       int digitoVerificador = 0;
       while ((parte4 + digitoVerificador)%10 != 0) {
           digitoVerificador++;
       }

       return digitoVerificador == Integer.parseInt(String.valueOf(codigoBarras.charAt(codigoBarras.length() - 1)));
    }
}
