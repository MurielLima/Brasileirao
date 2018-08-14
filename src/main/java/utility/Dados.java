/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Jogo;
import model.Time;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Muriel
 */
public class Dados {

    private BufferedReader br = null;
    private String nomeArq;
    private Jogo jgLinha;
    List<Time> lstTimes = new ArrayList<>();

    public Dados(String nomeArq) {
        Time timeAux;
        int i, j;
        this.nomeArq = nomeArq;
    }

    private void ordena() {
        Time timeAux;
        int i, j;
        i = 1;
        while (i <= lstTimes.size() - 1) {
            j = i;
            while ((j > 0) && (((lstTimes.get(j - 1).getPontos()) < (lstTimes.get(j).getPontos())) || ((lstTimes.get(j - 1).getPontos()) == (lstTimes.get(j).getPontos()) && (((lstTimes.get(j - 1).getSaldoGols()) < (lstTimes.get(j).getSaldoGols()))) || ((lstTimes.get(j - 1).getVitorias()) < (lstTimes.get(j).getVitorias()))))) {
//Saldo de gols e vitorias
                timeAux = lstTimes.get(j - 1);
                lstTimes.set(j - 1, lstTimes.get(j));
                lstTimes.set(j, timeAux);
                j--;
            }
            i++;
        }
    }

    public List<Time> ler() {
        String linha;

        try {
            br = new BufferedReader(new FileReader(nomeArq));
            while ((linha = br.readLine()) != null) {
                jgLinha = new Jogo(linha);
                analisa(jgLinha);
            }
            // Ordena
            ordena();
            Integer i = 0;
            for (Time t : lstTimes) {
                t.setClas(++i);
            }

        } catch (Exception e) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            }
        }

        return lstTimes;
    }
    public void gravarJogosTime(Time time){
        try{
            String nomeArq = "C:\\Engenharia-de-Software\\2-Ano\\Linguagens-de-Programacao\\2-bimestre\\Brasileirao\\" + time.getNome() + ".json";
            File file = new File(nomeArq);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            
            JSONObject eJSON = new JSONObject();
            for (Jogo j : time.getJogos()){
                eJSON.put("timeA",j.getTimeA());
                eJSON.put("golA",j.getGolA());
                eJSON.put("timeB",j.getTimeB());
                eJSON.put("golB",j.getGolB());
                bw.write(eJSON.toString()+ "\n");
            }
            bw.close();
        }catch(Exception e){
            
        }
        
    }
    private Time achaTime(String nomeBusca) {

        for (Time t : lstTimes) {
            if (t.getNome().equals(nomeBusca)) {
                return t;
            }
        }
        // Criando novo time caso o mesmo não exista.
        Time timeNovo = new Time(nomeBusca);
        lstTimes.add(timeNovo);
        return (timeNovo);
    }

    private void analisa(Jogo jg) {
        Time posTimeA = achaTime(jg.getTimeA());
        Time posTimeB = achaTime(jg.getTimeB());

        posTimeA.getJogos().add(jg);
        posTimeB.getJogos().add(jg);

        if (jg.getGolA() > jg.getGolB()) {   // A ganhou.
            posTimeA.addVitorias();
            posTimeB.addDerrotas();
        } else if (jg.getGolA() < jg.getGolB()) {    // B ganhou.
            posTimeB.addVitorias();
            posTimeA.addDerrotas();
        } else {    // Empate.
            posTimeA.addEmpates();
            posTimeB.addEmpates();
        }
        posTimeA.addGolPro(jg.getGolA());
        posTimeB.addGolPro(jg.getGolB());

        posTimeA.addGolContra(jg.getGolB());
        posTimeB.addGolContra(jg.getGolA());
    }
}
