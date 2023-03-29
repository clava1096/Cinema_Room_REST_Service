package cinema.exceptions;

import org.jetbrains.annotations.NotNull;

public class ErrorResponse {
    @NotNull
    private int status;
    @NotNull
    private String error;

    public ErrorResponse(int status,String error){
        this.error = error;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
