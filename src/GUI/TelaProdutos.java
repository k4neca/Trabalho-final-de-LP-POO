package GUI;

import Classes.Cliente;
import Classes.Produtos;
import Classes.TipoDePagamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaProdutos extends JDialog{
    private JButton btnA25;
    private JButton btnA55;
    private JButton btnS24;
    private JButton btn10A;
    private JButton btnRN13;
    private JButton btnPX6;
    private JButton btnI13;
    private JButton btnI14;
    private JButton btnI15;
    private JPanel panelProdutos;
    private JButton btnFinalizarCompra;
    private JButton btnCancelar;
    private JPanel galaxyA55Panel;
    private JPanel galaxyA25Panel;
    private JPanel galaxyS24Panel;
    private JPanel note13Panel;
    private JPanel px6Panel;
    private JPanel ip13Panel;
    private JPanel ip14Panel;
    private JPanel ip15Panel;
    private JPanel redmi10APanel;
    private JPanel carrinhoPanel;
    private JLabel lblValorTotal;
    private JList listItensDoCarrinho;
    private JButton btnLimparCarrinho;

    private double valorTotalCompra;

    private ArrayList<Produtos> listaProdutos;
    private ArrayList<Produtos> carrinho;

    private Cliente cliente;
    private TipoDePagamento tipoDePagamento;

    DefaultListModel<String> listModel;

    public TelaProdutos(JFrame parent, Cliente cliente) {
        super(parent, "Tela de Produtos", true); // Passando o JFrame pai e o título para o JDialog
        this.cliente = cliente; // Armazenando o cliente recebido
        setContentPane(panelProdutos);
        setResizable(false);
        setSize(new Dimension(750, 850));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        valorTotalCompra = 0.0;

        listaProdutos = new ArrayList<>();
        carrinho = new ArrayList<>();

        listaProdutos.add(new Produtos(1, "Samsung", "Galaxy A25 5G", 1500.00, 1000));
        listaProdutos.add(new Produtos(2, "Samsung", "Galaxy A55 5G", 1900.00, 1000));
        listaProdutos.add(new Produtos(3, "Samsung", "Galaxy S24 Ultra", 9000.00, 1000));
        listaProdutos.add(new Produtos(4, "Xiaomi", "Redmi 10A", 1700.00, 1000));
        listaProdutos.add(new Produtos(5, "Xiaomi", "Redmi Note 13 5G", 2300.00, 1000));
        listaProdutos.add(new Produtos(6, "Xiaomi", "POCO X6 5G", 2600.00, 1000));
        listaProdutos.add(new Produtos(7, "Apple", "iPhone 13", 5500.00, 1000));
        listaProdutos.add(new Produtos(8, "Apple", "iPhone 14", 6000.00, 1000));
        listaProdutos.add(new Produtos(9, "Apple", "iPhone 15", 7300.00, 1000));

        // Inicializa o DefaultListModel para a JList de itens do carrinho
        listModel = new DefaultListModel<>();
        listItensDoCarrinho.setModel(listModel);

        btnA25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho("Samsung", "Galaxy A25 5G", 1500.00);
            }
        });

        btnA55.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho("Samsung", "Galaxy A55 5G", 1900.00);
            }
        });

        btnS24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho("Samsung", "Galaxy S24 Ultra", 9000.00);
            }
        });

        btn10A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho("Xiaomi", "Redmi 10A", 1700.00);
            }
        });

        btnRN13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho("Xiaomi", "Redmi Note 13 5G", 2300.00);
            }
        });

        btnPX6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho("Xiaomi", "POCO X6 5G", 2600.00);
            }
        });

        btnI13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho("Apple", "iPhone 13", 5500.00);
            }
        });

        btnI14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho("Apple", "iPhone 14", 6000.00);
            }
        });

        btnI15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho("Apple", "iPhone 15", 7300.00);
            }
        });

        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPagamento telaPagamento = new TelaPagamento(parent, TelaProdutos.this, cliente, tipoDePagamento);
                telaPagamento.setVisible(true);
            }
        });
        btnLimparCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpar o carrinho (ArrayList)
                carrinho.clear();

                // Reiniciar o valor total da compra
                valorTotalCompra = 0.0;

                // Atualizar a lista de itens do carrinho na interface gráfica
                atualizarListaItensDoCarrinho();

                // Atualizar o valor total da compra na interface gráfica
                atualizarValorTotalCompra();
            }
        });
    }

    // Método para adicionar produto ao carrinho
    private void adicionarAoCarrinho(String produtoMarca, String produtoNome, double produtoValor) {
        boolean encontrado = false;

        // Verifica se o produto já está no carrinho
        for (Produtos p : carrinho) {
            if (p.getProdutoMarca().equals(produtoMarca) && p.getProdutoNome().equals(produtoNome)) {
                // Se encontrado, incrementa a quantidade e marca como encontrado
                p.setProdutoQtd(p.getProdutoQtd() + 1);
                encontrado = true;
                break; // Sai do loop pois o produto foi encontrado
            }
        }

        // Se não encontrado, adiciona um novo produto ao carrinho
        if (!encontrado) {
            int novoId = carrinho.size() + 1;
            Produtos novoProduto = new Produtos(novoId, produtoMarca, produtoNome, produtoValor, 1);
            carrinho.add(novoProduto);
        }
        valorTotalCompra += produtoValor;

        // Exibe mensagem de debug
        System.out.println("Produto adicionado ao carrinho: " + produtoMarca + " " + produtoNome);
        // Atualiza a lista de itens do carrinho na interface gráfica
        atualizarListaItensDoCarrinho();

        // Atualiza o valor total da compra na interface gráfica
        atualizarValorTotalCompra();
    }

    // Método para atualizar a lista de itens do carrinho na interface gráfica
    private void atualizarListaItensDoCarrinho() {
        listModel.clear(); // Limpa a lista antes de adicionar os novos itens

        for (Produtos p : carrinho) {
            String item = p.getProdutoMarca() + " " + p.getProdutoNome() + " - R$ " + p.getProdutoValor() + " x" + p.getProdutoQtd();
            listModel.addElement(item); // Adiciona o item formatado ao DefaultListModel
        }
    }

    public double getValorTotalCompra() {
        return valorTotalCompra;
    }

    public ArrayList<Produtos> getCarrinho() {
        return carrinho;
    }

    // Método para atualizar o valor total da compra na interface gráfica
    private void atualizarValorTotalCompra() {
        lblValorTotal.setText("R$ "+String.format("%.2f", valorTotalCompra));
    }
}
