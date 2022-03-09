package br.com.escola.validator;

import java.util.InputMismatchException;

public class Validator {

    public static boolean isRealID(String realID) {
        if (realID.equals("00000000000") ||
                realID.equals("11111111111") ||
                realID.equals("22222222222") || realID.equals("33333333333") ||
                realID.equals("44444444444") || realID.equals("55555555555") ||
                realID.equals("66666666666") || realID.equals("77777777777") ||
                realID.equals("88888888888") || realID.equals("99999999999") ||
                (realID.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = realID.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = realID.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            return (dig10 == realID.charAt(9)) && (dig11 == realID.charAt(10));
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public static String getCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

}
