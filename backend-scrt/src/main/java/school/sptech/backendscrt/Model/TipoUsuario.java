package school.sptech.backendscrt.Model;

public enum TipoUsuario {

    VOLUNTARIO(0),
    ADMINISTRADOR(1);

    public int idUsuario;
    TipoUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
