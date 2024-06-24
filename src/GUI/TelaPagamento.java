package GUI;

import Classes.Cliente;
import Classes.Produtos;
import Classes.TipoDePagamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TelaPagamento extends JDialog{
    private JPanel telaPagamentosPanel;
    private JPanel jpEntrega;
    private JPanel jpTipoPagamento;
    private JPanel jpResumoItens;
    private JRadioButton jrbEnderecoCadastrado;
    private JRadioButton jrbLojaFisica;
    private JComboBox listaDeLojas;
    private JRadioButton rbPix;
    private JRadioButton rbCredito;
    private JRadioButton rbDebito;
    private JComboBox listaParcelamento;
    private JLabel jlValorTipoPagamento;
    private JLabel jlValorParcela;
    private JList listItensDoCarrinho;
    private JLabel lblValorFrete;
    private JButton btnFinalizar;
    private JLabel lblEnderecoCadastrado;
    private JLabel lblValorDoPedido;
    private JTextField lblNCartao;
    private JTextField lblCVV;
    private JTextField lblValidade;
    private JPanel jpCartão;

    private double valorFrete;
    private double valorDoPedido;

    private TelaProdutos telaProdutos;
    private Cliente cliente;
    private TipoDePagamento tipoDePagamento;

    public TelaPagamento(JFrame parent, TelaProdutos telaProdutos, Cliente cliente, TipoDePagamento tipoDePagamento){
        super(parent, "Tela de Pagamento", true); // Passando o JFrame pai e o título para o JDialog
        this.telaProdutos = telaProdutos;
        this.cliente = cliente;
        this.tipoDePagamento = tipoDePagamento;
        //setTitle("Tela de Pagamentos");
        setContentPane(telaPagamentosPanel);
        setResizable(false);
        setSize(new Dimension(500, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listaDeLojas.setVisible(false);
        listaParcelamento.setVisible(false);
        lblEnderecoCadastrado.setVisible(false);
        jpCartão.setVisible(false);

        ButtonGroup tipoEntrega = new ButtonGroup();
        tipoEntrega.add(jrbEnderecoCadastrado);
        tipoEntrega.add(jrbLojaFisica);

        ButtonGroup formaPagamento = new ButtonGroup();
        formaPagamento.add(rbPix);
        formaPagamento.add(rbCredito);
        formaPagamento.add(rbDebito);

        // Inicializa o DefaultListModel para a JList de itens do carrinho
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listItensDoCarrinho.setModel(listModel);

        // Atualiza a lista de itens do carrinho na JList
        atualizarListaItensDoCarrinho();

        jrbEnderecoCadastrado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaDeLojas.setVisible(false);
                listaDeLojas.setEnabled(false); // Desabilita o JComboBox ao selecionar entrega no endereço
                lblEnderecoCadastrado.setVisible(true);
                lblEnderecoCadastrado.setText("Endereço: " +cliente.getClienteEndereco());
                calculaFrete();
                atualizarValorDoPedido();
            }
        });
        jrbLojaFisica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblEnderecoCadastrado.setVisible(false);
                listaDeLojas.setVisible(true);
                listaDeLojas.setEnabled(true); // Habilita o JComboBox ao selecionar retirada na loja
                calculaFrete();
                atualizarValorDoPedido();
            }
        });
        //setVisible(true);
        rbPix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valorComDesconto = telaProdutos.getValorTotalCompra() * 0.95;
                valorDoPedido = valorFrete + valorComDesconto;
                jlValorTipoPagamento.setText(String.format("Valor com 5%% de desconto: R$ %.2f", valorComDesconto));
                jlValorParcela.setVisible(false);
                atualizarValorDoPedido(); // Atualiza o valor do pedido quando selecionado Pix
                jpCartão.setVisible(false);
            }
        });
        rbCredito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbCredito.isSelected()) {
                    jpCartão.setVisible(true);
                    listaParcelamento.setVisible(true);
                    listaParcelamento.setEnabled(true);
                    jlValorParcela.setVisible(true);
                    atualizarValoresParcelamento();
                    atualizarValorDoPedido(); // Atualiza o valor do pedido quando selecionado crédito
                }
            }
        });
        rbDebito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbDebito.isSelected()) {
                    jpCartão.setVisible(true);
                    jlValorTipoPagamento.setText(String.format("Valor final: R$ %.2f", telaProdutos.getValorTotalCompra()));
                    jlValorParcela.setVisible(false);
                    valorDoPedido = valorFrete + telaProdutos.getValorTotalCompra();
                    atualizarValorDoPedido(); // Atualiza o valor do pedido quando selecionado débito
                }
            }
        });

        // Definir as opções de parcelamento
        String[] opcoesParcelamento = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        // Adicionar as opções ao comboBox listaParcelamento
        listaParcelamento.setModel(new DefaultComboBoxModel<>(opcoesParcelamento));

        listaParcelamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbCredito.isSelected()) {
                    atualizarValoresParcelamento();
                }
            }
        });
        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbPix.isSelected()) {
                    ImageIcon icon = new ImageIcon("C:/Users/kaneca/Desktop/Workspace/TelecoStore/src/Imagens/pix.png");
                    JOptionPane.showMessageDialog(null, "Utilize seu aplicativo bancario para ler o QR Code e realizar o pagamento\nObrigado por escolher a Teleco Store!", "Pedido realizado", JOptionPane.INFORMATION_MESSAGE,icon);
                } else if (rbCredito.isSelected() || rbDebito.isSelected()) {
                    ImageIcon icon = new ImageIcon("C:/Users/kaneca/Desktop/Workspace/TelecoStore/src/Imagens/cartao-de-credito.png");
                    JOptionPane.showMessageDialog(null, "Compra realizada no cartão Nº " +lblNCartao.getText().trim()+ "\nObrigado por escolher a Teleco Store!", "Pedido realizado", JOptionPane.INFORMATION_MESSAGE,icon);
                }
            }
        });
        lblNCartao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroCartao = lblNCartao.getText().trim();
                tipoDePagamento.setNumCartao(numeroCartao);
            }
        });

        lblCVV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cvvCartao = lblCVV.getText().trim();
                tipoDePagamento.setCvv(cvvCartao);
            }
        });

        lblValidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String validade = lblValidade.getText().trim();
                tipoDePagamento.setValidade(validade);
            }
        });
    }

    private void atualizarValoresParcelamento() {
        int parcelas = Integer.parseInt(listaParcelamento.getSelectedItem().toString());
        double valorFinal = telaProdutos.getValorTotalCompra();
        if (parcelas <= 3) {
            double valorParcela = telaProdutos.getValorTotalCompra() / parcelas;
            jlValorParcela.setText(String.format("%d parcelas de: R$ %.2f (sem juros)", parcelas, valorParcela));
            jlValorTipoPagamento.setText(String.format("Valor final R$ %.2f", valorFinal));
            valorDoPedido = valorFrete + valorFinal;
        } else {
            double valorComJuros = calcularValorComJuros(telaProdutos.getValorTotalCompra(), parcelas);
            jlValorParcela.setText(String.format("%d parcelas de: R$ %.2f (com juros)", parcelas, valorComJuros / parcelas));
            jlValorTipoPagamento.setText(String.format("Valor final R$ %.2f (total com juros)", valorComJuros));
        }
    }

    private double calcularValorComJuros(double valorTotal, int parcelas) {
        // Calcula o valor com juros de 5% ao mês a partir da 4ª parcela
        double valorComJuros = 0;
        double jurosMensal = 0.05; // 5% ao mês

        for (int i = 1; i <= parcelas; i++) {
            if (i <= 3) {
                // Não aplica juros para 1, 2 ou 3 parcelas
                valorComJuros += valorTotal / parcelas;
            } else {
                // A partir da 4ª parcela, aplica juros
                valorComJuros += (valorTotal / parcelas) * (1 + jurosMensal);
            }
        }
        return valorComJuros;
    }

    private void calculaFrete() {
        if (jrbEnderecoCadastrado.isSelected()) {
            valorFrete = 5.00; // Define o valor do frete para endereço cadastrado como R$ 5.00
            lblValorFrete.setText(String.format("Frete = R$ %.2f" ,valorFrete)); // Atualiza o texto do JLabel
        } else {
            valorFrete = 0.00; // Define o valor do frete como zero para retirada na loja
            lblValorFrete.setText(String.format("Frete = R$ %.2f" ,valorFrete)); // Atualiza o texto do JLabel
        }
    }

    private void atualizarListaItensDoCarrinho() {
        // Limpa o modelo da lista antes de adicionar os novos itens
        DefaultListModel<String> listModel = (DefaultListModel<String>) listItensDoCarrinho.getModel();
        listModel.clear();

        // Obtém o carrinho de produtos da TelaProdutos
        ArrayList<Produtos> carrinho = telaProdutos.getCarrinho();

        // Adiciona os itens do carrinho ao modelo da lista
        for (Produtos produto : carrinho) {
            String item = produto.getProdutoMarca() + " " + produto.getProdutoNome() + " - R$ " +
                    produto.getProdutoValor() + " x" + produto.getProdutoQtd();
            listModel.addElement(item);
        }
    }

    private void atualizarValorDoPedido() {
        double valorFinal = 0.0;

        // Verifica qual forma de pagamento está selecionada
        if (rbPix.isSelected()) {
            double valorComDesconto = telaProdutos.getValorTotalCompra() * 0.95;
            valorFinal = valorFrete + valorComDesconto;
        } else if (rbCredito.isSelected()) {
            int parcelas = Integer.parseInt(listaParcelamento.getSelectedItem().toString());
            if (parcelas <= 3) {
                valorFinal = valorFrete + telaProdutos.getValorTotalCompra();
            } else {
                double valorComJuros = calcularValorComJuros(telaProdutos.getValorTotalCompra(), parcelas);
                valorFinal = valorFrete + valorComJuros;
            }
        } else if (rbDebito.isSelected()) {
            valorFinal = valorFrete + telaProdutos.getValorTotalCompra();
        }

        // Atualiza o valor de valorDoPedido
        valorDoPedido = valorFinal;

        // Atualiza o texto do JLabel
        lblValorDoPedido.setText(String.format("Valor do pedido + frete = R$ %.2f", valorDoPedido));
    }

}
