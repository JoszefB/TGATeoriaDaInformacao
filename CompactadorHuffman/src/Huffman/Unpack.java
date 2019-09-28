//Joszef Barrionuevo

package Huffman;

import Tree.Node;

public class Unpack extends Huffman {

    public String unpackage(String s) {
        int positionChar = 0, posIniText = 0;
        Node newNode = null;
        String coding = "";
        System.out.println("Texto a ser Descompactado: "+s);
        for (int i=0;i<s.length() ; i++){
            if (i==0 || s.charAt(i-1)!=getMarker() || s.charAt(i)!='-' || s.charAt(i+1)!='>'  ){

                if (i==0 || s.charAt(i-1)==getMarker()){
                    newNode = new Node();
                    newNode.setCharacter(s.charAt(i));
                    positionChar = i+1;
                }else{
                    if (s.charAt(i)==getMarker()){
                        coding = s.substring(positionChar, i);
                        newNode.setId(coding);
                        getCharacter().add(newNode);
                    }
                }

            }else{
                posIniText = i+3;
                break;
            }
        }

        int valueDecimalCharacter=0;
        String textBinary = "";
        System.out.println("Aguarde, descompactando...");
        for (int i=posIniText ; i<s.length()-1 ; i++ ){
            if (i==347)
                System.out.println();
            if (i!=s.length()){
                char c = s.charAt(i);
                if (c=='\n' && (s.substring(i+1, i+5)).equals("<13>") ){
                    valueDecimalCharacter =13;
                    i = i+4;
                }
                else
                    valueDecimalCharacter = (int) c;
                String bin = Integer.toString(valueDecimalCharacter, 2);
                if (bin.length() != 8){
                    int lack = 8 - (bin.length() % 8);
                    for (int j = 0; j < lack; j++) {
                        bin = "0" + bin;
                    }
                }
                textBinary = textBinary + bin;
            }
        }
        System.out.println("\nTexto binario: "+textBinary);
        int posCourt=0;
        String codeMarker = searchId('^', getCharacter());
        posCourt=textBinary.indexOf(codeMarker) +codeMarker.length();
        textBinary=textBinary.substring(posCourt, textBinary.length());
        System.out.println("\nTexto binario arrumado: "+textBinary);
        
        String originalText="";
        int pos=0;
        for (int i=0; i<textBinary.length();i++){
            String bin=textBinary.substring(pos,i);
            char caracter = searchCharacter(bin, getCharacter());
            if ( caracter != 0 ){
                pos=i;
                originalText = originalText + caracter;
            }
        }
        System.out.println("\nTexto descampactado: "+originalText);
        return originalText;
    }

}