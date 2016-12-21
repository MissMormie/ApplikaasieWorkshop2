package applikaasie.domein.bestelling;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonja
 */
public class BestelArtikel {
    private int id;
    
    @NotNull
    private int aantal;
    private int bestelId;
    
    @NotNull
    private int artikelId;
    private boolean deleted;
    private boolean bezorgd;
    
    
    public BestelArtikel (){};
    
    public BestelArtikel(int artikelId, int aantal) {
        this.artikelId = artikelId;
        this.aantal = aantal;      
    }
    
    public BestelArtikel (int bestelId, int artikelId, int aantal) {
        this.bestelId = bestelId;
        this.artikelId = artikelId;
        this.aantal = aantal;
    }
    
    public BestelArtikel (int id, int bestelId, int artikelId, int aantal, boolean deleted, boolean bezorgd) {
        this.id = id;
        this.bestelId = bestelId;
        this.artikelId = artikelId;
        this.aantal = aantal;
        this.deleted = deleted;
        this.bezorgd = bezorgd;
    }
    
    public int getId () {
        return id;
    }
    public void setId (int id) {
        this.id = id;
    }
    public int getAantal () {
        return aantal;
    }
    public void setAantal (int aantal) {
        this.aantal = aantal;
    }
    public int getBestelId () {
        return bestelId;
    }
    public void setBestelId (int bestelId) {
        this.bestelId = bestelId;
    }
    public int getArtikelId () {
        return artikelId;
    }
    public void setArtikelId (int artikelId) {
        this.artikelId = artikelId;
    }
    public boolean isDeleted () {
        return deleted;
    }
    public void setDeleted (boolean deleted) {
        this.deleted = deleted;
    }
    public boolean isBezorgd () {
        return bezorgd;
    }
    public void setBezorgd (boolean bezorgd) {
        this.bezorgd = bezorgd;
    }
}
