package br.com.util;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.math.BigInteger;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class Utils {
	
	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
	/**
     * Linha Dupla [===].
     */
    public static String LD = "=============================================";
    
    public static String LDM = "===========================================================";

    /**
     * Linha Simples [---].
     */
    public static String LS = "-------------------------------------------------------------------------------";
    
    public static String LSM = "--------------------------------------------------------------------------------------------------------";

	/**
     * Metodo que faz a formatacao de numeros com inteiros e fracoes
     *
     * @param valor o valor a ser formatado
     * @param inteiros o minimo de inteiros, que serao completados com ZEROS se
     * preciso
     * @param decimal o minimo de decimais, que serao completados com ZEROS se
     * preciso
     * @param grupo se sera colocado separador de grupo de milhar
     * @return uma String com o numero formatado
     */
    public static String formataNumero(double valor, int inteiros, int decimal, boolean grupo) {
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMinimumIntegerDigits(inteiros);
        nf.setMinimumFractionDigits(decimal);
        nf.setMaximumFractionDigits(decimal);
        nf.setGroupingUsed(grupo);
        return nf.format(valor);
    }
    
    /**
     * Enum que define qual a opcao usadada na formatacao do texto.
     */
    public static enum EDirecao {

        /**
         * Coloca o caracter nos dois lados do texto.
         */
        AMBOS,
        /**
         * Coloca o caracter no lado direto do texo.
         */
        DIREITA,
        /**
         * Coloca o caracter no lado esquerdo do texto.
         */
        ESQUERDA
    };
    
    
    /**
     * Metodo que formata o texto.
     *
     * @param texto o texto a ser formatado.
     * @param caracter o caracter que sera repetido.
     * @param tamanho o tamanho total do texto de resposta.
     * @param direcao a direcao onde colocar os caracteres.
     * @return o texto formatado.
     */
    public static String formataTexto(String texto, String caracter, int tamanho, EDirecao direcao) {
        StringBuilder sb = new StringBuilder();
        int fim = tamanho - texto.length();
        for (int i = 0; i < fim; i++) {
            sb.append(caracter);
        }

        if (direcao == EDirecao.DIREITA) {
            return texto + sb.toString();
        } else if (direcao == EDirecao.ESQUERDA) {
            return sb.toString() + texto;
        } else {
            return sb.toString().substring(0, fim / 2) + texto + sb.toString().substring(fim / 2);
        }
    }
    public static void alinharJButtonSemImagem(JButton button) {
    	button.setPreferredSize(new Dimension(175, 128));
    }
    /**
     * Método responsável por alinha o botão
     * @param button 
     */
    public static void alinharImagemJButton(JButton button) {
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
    }
    
    /**
     * Método responsável por verificar se o numero é primo
     * @param n recebe o valor
     * @return retorna true para numero primo e false para numero não primo
     */
    public static boolean vericaPrimo(String n) {  
        BigInteger numero = new BigInteger(n);  
        boolean flag = false;  
        if (numero.isProbablePrime(Integer.MAX_VALUE)) {  
            flag = true;  
        }  
        return flag;  
    }
    
    /**
     * Metodo responsável por remover R$ e vinrgula
     * @param valor
     * @return 
     */
    public static String removerMoeda(String valor){
        return valor.replace("R$ ","").replace(".","").replace(',','.');
    }
    
    /**
     * Método reponsável por formatar o CNPJ e CPF
     * @param texto
     * @return 
     */
    public static String formataCpfCnpj(String texto){
        String cpfCnpj = removerPontos(texto);
        String cpfCnpjFormat;
        if(cpfCnpj.length() <= 12){
            cpfCnpjFormat = formatar("###.###.###-##", cpfCnpj);
        }else{
            cpfCnpjFormat = formatar("##.###.###/####-##", cpfCnpj);
        }
        return cpfCnpjFormat;
    }
    
    /**
     * Metodo responsavel por remover pontuação
     * @param valor
     * @return 
     */
    public static String removerPontos(String valor){
        return valor.replaceAll("\\D", "");
    }
    
    /**
     * Método responsavel por formatar String
     * @param pattern
     * @param value
     * @return 
     */
    public static String formatar(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Método que verifica se o valor é inteiro ou não.
     * @param valor 
     * @return
     */
    public static boolean isInt(String valor) {  
        try {  
            Integer.parseInt(valor);  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }
    
    /**
     * Metodo que valida se e CPF
     *
     * @param cpf o valor do texto.
     * @return verdadeiro se valido, falso caso contrario.
     */
    public static boolean isCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        } else {
            Pattern p = Pattern.compile(cpf.charAt(0) + "{11}");
            Matcher m = p.matcher(cpf);
            if (m.find()) {
                return false;
            }
        }

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }
    
    /**
     * Metodo que calcula o digito.
     *
     * @param str valor do texto.
     * @param peso array de pesos.
     * @return um numero calculado.
     */
    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }
    
    /**
     * Metodo que valida se e CNPJ
     *
     * @param cnpj o valor do texto.
     * @return verdadeiro se valido, falso caso contrario.
     */
    public static boolean isCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        } else {
            Pattern p = Pattern.compile(cnpj.charAt(0) + "{14}");
            Matcher m = p.matcher(cnpj);
            if (m.find()) {
                return false;
            }
        }

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }
    
    /**
     * Metodo que normaliza os caracteres removendo os acentos.
     *
     * @param texto o texto acentuado.
     * @return o texto sem acentos.
     */
    public static String normaliza(String texto) {
        CharSequence cs = new StringBuilder(texto == null ? "" : texto);
        return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    
    public static String normalizeXML(String xml) {  
        if ((xml != null) && (!"".equals(xml))) {  
            xml = xml.replaceAll("\\r\\n", "");  
            xml = xml.replaceAll(" standalone=\"no\"", "");  
        }  
        return xml;  
    } 
    
    /**
     * Metodo reponsável por completar informações a esquerda
     * 
     * @param valor - valor que ficará a direita
     * @param c - com qual informação será completada - por exemplo por zero "0"
     * @param size - quantidade de caracteres que ficara a esquerda
     * @return 
     */
    public static String completeToLeft(String valor, int c, int size) {  
        String result = valor;  
  
        while (result.length() < size) {  
            result = c + result;  
        }  
  
        return result;  
    }  
    
    public static String completeToRight(String valor, String c, int size) {  
        String result = valor;  
  
        while (result.length() < size) {  
            result = result + c;  
        }  
  
        return result;  
    }
    
    /**
     * Metodo que formata a data.
     *
     * @param data a data do tipo Date.
     * @param formato o formado desejado.
     * @return a data formatada como solicidato.
     */
    public static String formataData(Date data, String formato) {
        try {
            return new SimpleDateFormat(formato).format(data);
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Metodo que formata a data.
     *
     * @param data a data em formato string.
     * @param formato o formado desejado.
     * @return a data como objeto ou null se tiver erro.
     */
    public static Date formataData(String data, String formato) {
        try {
            return new SimpleDateFormat(formato).parse(data);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    /**
     * Metodo que formada data.
     * @param texto data
     * @param formatoAtual - mascara a qual se encontra
     * @param formatoFinal - mascara de como irá ficar
     * @return data formatada
     */
    public static String formataData(String texto, String formatoAtual, String formatoFinal){
        try {
            SimpleDateFormat formato1 = new SimpleDateFormat(formatoAtual);  
            SimpleDateFormat formato2 = new SimpleDateFormat(formatoFinal);  
            return formato2.format(formato1.parse(texto));
        } catch (ParseException ex) {
            return texto;
        }
    }
    
    public static void configuracaoJTable(JTable tabela) {
        tabela.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 20));
        tabela.setFont(new Font("Dialog", Font.BOLD, 20));
        tabela.setRowHeight(30);
    }
    
    public static void tamanhoJTable(JTable tabela, int... tamanho) {
        for (int i=0; i < tamanho.length; i++) {
            tabela.getColumnModel().getColumn(i).setPreferredWidth(tamanho[i]);
        }
    }
}
