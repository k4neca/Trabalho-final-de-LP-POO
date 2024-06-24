package Classes;

public class Produtos {
    private int produtoId;
    private String produtoMarca;
    private String produtoNome;
    private double produtoValor;
    private int produtoQtd;

    public Produtos(int produtoId, String produtoMarca, String produtoNome, double produtoValor, int produtoQtd) {
        this.produtoId = produtoId;
        this.produtoMarca = produtoMarca;
        this.produtoNome = produtoNome;
        this.produtoValor = produtoValor;
        this.produtoQtd = produtoQtd;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoMarca() {
        return produtoMarca;
    }

    public void setProdutoMarca(String produtoMarca) {
        this.produtoMarca = produtoMarca;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public double getProdutoValor() {
        return produtoValor;
    }

    public void setProdutoValor(double produtoValor) {
        this.produtoValor = produtoValor;
    }

    public int getProdutoQtd() {
        return produtoQtd;
    }

    public void setProdutoQtd(int produtoQtd) {
        this.produtoQtd = produtoQtd;
    }
}
