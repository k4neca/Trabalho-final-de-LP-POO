package Classes;

public class Admin {
    private int adminId;
    private String adminNome;
    private String adminLogin;
    private String adminSenha;

    public Admin(int adminId, String adminNome, String adminLogin, String adminSenha) {
        this.adminId = adminId;
        this.adminNome = adminNome;
        this.adminLogin = adminLogin;
        this.adminSenha = adminSenha;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminNome() {
        return adminNome;
    }

    public void setAdminNome(String adminNome) {
        this.adminNome = adminNome;
    }

    public String getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    public String getAdminSenha() {
        return adminSenha;
    }

    public void setAdminSenha(String adminSenha) {
        this.adminSenha = adminSenha;
    }
}
