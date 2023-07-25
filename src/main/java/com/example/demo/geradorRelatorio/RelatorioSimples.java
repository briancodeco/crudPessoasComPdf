package com.example.demo.geradorRelatorio;

import com.example.demo.model.Pessoa;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

public class RelatorioSimples implements Relatorio {
    private List<Pessoa> list;
    private Document documentoPdf;
    private String caminhoRelatorio = "RelatorioSimples.pdf";

    public RelatorioSimples(List<Pessoa> list)  {
        this.list = list;
        this.documentoPdf = new Document();
        try {
            PdfWriter.getInstance(this.documentoPdf, new FileOutputStream(caminhoRelatorio));
            this.documentoPdf.open();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void gerarCabecalho()  {
        Paragraph paragrafoTitulo = new Paragraph();
        paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
        paragrafoTitulo.add(new Chunk("Relatorio de Pessoas"));
        try{
            documentoPdf.add(paragrafoTitulo);
            documentoPdf.add(new Paragraph(""));
            documentoPdf.add(new Paragraph(""));

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    public void gerarCorpo () {
        try {
            for (Pessoa p : this.list) {
                Paragraph paragrafoNome = new Paragraph();
                paragrafoNome.setAlignment(Element.ALIGN_CENTER);
                paragrafoNome.add(new Chunk("Nome: " + p.getNome()));

                Paragraph paragrafoTelefone = new Paragraph();
                paragrafoTelefone.setAlignment(Element.ALIGN_CENTER);
                paragrafoTelefone.add(new Chunk("Telefone: " + p.getTelefone()));

                Paragraph paragrafoEmail = new Paragraph();
                paragrafoEmail.setAlignment(Element.ALIGN_CENTER);
                paragrafoEmail.add(new Chunk("Nome: " + p.getEmail()));

                Paragraph paragrafoCpf = new Paragraph();
                paragrafoCpf.setAlignment(Element.ALIGN_CENTER);
                paragrafoCpf.add(new Chunk("Nome: " + p.getCpf()));

                Paragraph paragrafoEpace = new Paragraph("------------------------------------------------------------------------------");
                paragrafoEpace.setAlignment(Element.ALIGN_CENTER);
                documentoPdf.add(paragrafoNome);
                documentoPdf.add(paragrafoTelefone);
                documentoPdf.add(paragrafoEmail);
                documentoPdf.add(paragrafoCpf);
                documentoPdf.add(paragrafoEpace);
                documentoPdf.add(new Paragraph(""));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void gerarRodape(){

    }
    public void imprimir(){
        if(documentoPdf != null && documentoPdf.isOpen()){
            documentoPdf.close();
        }
    }
}
