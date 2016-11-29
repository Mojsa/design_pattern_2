/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2;

import mmojsino_zadaca_2.agencije.Agency;
import mmojsino_zadaca_2.agencije.AgencyFactory;

/**
 *
 * @author Mario
 */
public class Ronioc implements Observer, Visitable {
    
    private Subject topic;

    //required
    private String imeRonioca;
    private int godinaRodjenja;

    //optional
    private String agencija;
    private String agencijaNorm;
    private String rang;
    private String rangNorm;
    private String certifikat;

    public String getCertifikat() {
        return certifikat;
    }

    public String getImeRonioca() {
        return imeRonioca;
    }

    public int getGodinaRodjenja() {
        return godinaRodjenja;
    }

    public String getAgencija() {
        return agencija;
    }

    public String getRang() {
        return rang;
    }

    public String getRangNorm() {
        return rangNorm;
    }

    public String getAgencijaNorm() {
        return agencijaNorm;
    }

    private Ronioc(RoniocBuilder builder) {
        this.agencija = builder.agencija;
        this.agencijaNorm = builder.agencijaNorm;
        this.godinaRodjenja = builder.godinaRodjenja;
        this.imeRonioca = builder.imeRonioca;
        this.rang = builder.rang;
        this.rangNorm = builder.rangNorm;
        this.certifikat = builder.certifikat;
    }

    @Override
    public void update() {
        //samo za trenutnog ronioca (this)
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(this.imeRonioca + " no new messages");
        }else{
            System.out.println(this.imeRonioca + " consuming message: " + msg);
        }
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic = sub;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public static class RoniocBuilder {

        //required
        private String imeRonioca;
        private int godinaRodjenja;

        //optional
        private String agencija;
        private String agencijaNorm;
        private String rang;
        private String rangNorm;
        private String certifikat;

        public RoniocBuilder(String ime, int godina) {
            this.imeRonioca = ime;
            this.godinaRodjenja = godina;
        }

        public RoniocBuilder setAgencija(String agencija) {
            this.agencija = agencija;
            return this;
        }

        public RoniocBuilder setRang(String rang) {
            this.rang = rang;
            return this;
        }

        public RoniocBuilder setAgencijaNorm(String agencijaNorm) {
            this.agencijaNorm = agencijaNorm;
            return this;
        }

        public RoniocBuilder setRangNorm() {
            this.rangNorm = RankingNorm.normalizirajRazinu(this.agencija, this.rang);
            return this;
        }
        
        public RoniocBuilder setCertifikat(){
            AgencyFactory a = new AgencyFactory();
            Agency a1 = a.getAgency(this.agencija);
            this.certifikat = a1.getCertificate(this.rang);
            return this;
        }
        

        public Ronioc build() {
            return new Ronioc(this);
        }
    }

}
