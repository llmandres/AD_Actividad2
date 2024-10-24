package modelo.entidad;

import java.io.Serializable;

public class Coche implements Serializable {
	

    private static final long serialVersionUID = -6044813081411287646L;

    private long id;
    private String marca;
    private String modelo;
    private TipoDeMotor tipoMotor;


    public Coche( String marca, String modelo, TipoDeMotor tipoMotor) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
    }
    

    public Coche() {
		super();
	}

    public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public TipoDeMotor getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(TipoDeMotor tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    @Override
    public String toString() {
        return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", tipoMotor=" + tipoMotor + "]";
    }

}
