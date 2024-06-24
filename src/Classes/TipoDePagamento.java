package Classes;

public class TipoDePagamento extends Pagamentos {
    private String numCartao;
    private String validade;
    private String cvv;

    public TipoDePagamento(String numCartao, String cvv, String validade) {
        this.numCartao = numCartao;
        this.cvv = cvv;
        this.validade = validade;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String pagar() {
        return "Compra realizada no cartão Nº " +getNumCartao() + "\nObrigado por escolher a Teleco Store!";
    }
}
