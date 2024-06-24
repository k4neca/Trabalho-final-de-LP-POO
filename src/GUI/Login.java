package GUI;

import Classes.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JDialog{
    private JPanel loginPanel;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnAcessar;
    private JButton btnCancelar;
    private JButton btnCadastrar;

    public Cliente cliente;

    public Login(JFrame parent) {
        super(parent, "Tela de login", true); // Passando o JFrame pai e o título para o JDialog
        setContentPane(loginPanel);
        setResizable(false);
        setSize(new Dimension(450, 474));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnAcessar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clienteEmail = tfEmail.getText();
                String clientePassword = String.valueOf(pfPassword.getPassword());

                cliente = getAuthenticatedUser(clienteEmail, clientePassword);

                if (cliente != null){
                    System.out.println("Autenticação concluída para " +cliente.getClienteNome());
                    System.out.println("             Telefone: " +cliente.getClienteTelefone());
                    System.out.println("             Endereço: " +cliente.getClienteEndereco());
                    System.out.println("             E-mail: " +cliente.getClienteEmail());
                    System.out.println("             CPF: " +cliente.getClienteCPF());

                    TelaProdutos telaProdutos = new TelaProdutos(null, cliente);
                    telaProdutos.setVisible(true);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(Login.this, "E-mail ou senha incorretos","Tente novamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha o diálogo de login
            }
        });
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroCliente cadastroCliente = new CadastroCliente();
                cadastroCliente.setVisible(true);
            }
        });
    }

    private Cliente getAuthenticatedUser(String clienteEmail, String clientePassword) {
        Cliente cliente = null;

        String url = "jdbc:sqlite:C:/Users/kaneca/Desktop/Workspace/TelecoStore/src/scripts/script_teleco_store";
        String user = "root";
        String password = "";

        try {
            Connection conexao = DriverManager.getConnection(url, user, password);

            Statement stmt = conexao.createStatement();
            String sql = "SELECT * FROM Cliente WHERE clienteEmail = ? AND clientePassword = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1,clienteEmail);
            preparedStatement.setString(2,clientePassword);

            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()){
                cliente = new Cliente();
                cliente.setClienteNome(resultado.getString("clienteNome"));
                cliente.setClienteTelefone(resultado.getString("clienteTelefone"));
                cliente.setClienteEndereco(resultado.getString("clienteEndereco"));
                cliente.setClienteEmail(resultado.getString("clienteEmail"));
                cliente.setClienteCPF(resultado.getString("clienteCPF"));
                cliente.setClientePassword(resultado.getString("clientePassword"));
            }

            stmt.close();
            conexao.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cliente;
    }
}
