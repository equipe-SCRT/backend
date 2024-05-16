package school.sptech.backend.utils.exception;

public class NaoEncontradoException extends RuntimeException{
    public NaoEncontradoException(String entidade) {
        super(String.format("%s não encontrado",entidade));
    }
}
