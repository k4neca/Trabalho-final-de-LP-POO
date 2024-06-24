package GUI;

import Classes.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CadastroCliente extends JDialog {
    public JPanel cadastroPanel;
    private JTextField tfNome;
    private JTextField tfTelefone;
    private JTextField tfEndereco;
    private JTextField tfEmail;
    private JTextField tfCPF;
    private JPasswordField pfPassword;
    private JPasswordField pfConfPassword;
    private JButton btnCadastrar;
    private JButton btnCancelar;

    public CadastroCliente(){
        setTitle("Novo cadastro de cliente");
        setContentPane(cadastroPanel);
        setMinimumSize(new Dimension(550,480));
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void cadastrarCliente() {
        String nome = tfNome.getText();
        String telefone = tfTelefone.getText();
        String endereco = tfEndereco.getText();
        String email = tfEmail.getText();
        String cpf = tfCPF.getText();
        String senha = String.valueOf(pfPassword.getPassword());
        String confirmSenha = String.valueOf(pfConfPassword.getPassword());

        if (nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || email.isEmpty() || cpf.isEmpty() || senha.isEmpty() || confirmSenha.isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor preencha todos os campos", "Tente novamente", JOptionPane.ERROR_MESSAGE);
            return;
        } if (!senha.equals(confirmSenha)){
            JOptionPane.showMessageDialog(this,"A confirmação de senha não está de acordo", "Tente novamente", JOptionPane.ERROR_MESSAGE);
            return;
        }
        cliente = adicionarCliente(nome,telefone,endereco,email,cpf,senha,confirmSenha);

        if (cliente != null){
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            dispose();
        }else {
            JOptionPane.showMessageDialog(this, "Falha ao registrar cliente","Tente novamente!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Cliente cliente;

    private Cliente adicionarCliente(String nome, String telefone, String endereco, String email, String cpf, String senha, String confirmSenha) {
        Cliente cliente = null;

        final String url = "jdbc:sqlite:C:/Users/kaneca/Desktop/Workspace/TelecoStore/src/scripts/script_teleco_store";
        final String user = "root";
        final String password = "";

        try {
            Connection conexao = DriverManager.getConnection(url, user, password);

            Statement stmt = conexao.createStatement();
            String sql = "INSERT INTO Cliente (clienteNome,clienteTelefone,clienteEndereco,clienteEmail,clienteCPF,clientePassword)"+"VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1,nome);
            preparedStatement.setString(2,telefone);
            preparedStatement.setString(3,endereco);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,cpf);
            preparedStatement.setString(6,senha);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                cliente = new Cliente();
                cliente.setClienteNome(nome);
                cliente.setClienteTelefone(telefone);
                cliente.setClienteEndereco(endereco);
                cliente.setClienteEmail(email);
                cliente.setClienteCPF(cpf);
                cliente.setClientePassword(senha);
            }

            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    /*public static void main(String[] args) {
        CadastroCliente cadastrar = new CadastroCliente();
        Cliente cliente = cadastrar.cliente;
        if (cliente != null){
            System.out.println(cliente.getClienteNome() +" foi registrado com sucesso!");
        }else {
            System.out.println("Cadastro cancelado");
        }
    }*/
}
