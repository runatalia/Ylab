package internship;


public class SnilsValidatorImpl implements SnilsValidator{
    @Override
    public boolean validate(String inputSnils) {
        String snils = inputSnils.replaceAll("[^0-9]","");
        if(!checkSnils(snils)) return false;
        int[] snilsDigit = stringToInt(snils);
        if(snilsDigit == null)
            return false;
        int checkSum = checkSum(snilsDigit);
        if(checkSum/10 ==  snilsDigit[9] && checkSum%10 ==  snilsDigit[10]) return true;
        else return false;
    }
    private int[] stringToInt(String snils) {
        char[] chars = snils.toCharArray();
        int[] resultIntArray = new int[chars.length];
            for (int i=0;i<chars.length;i++) {
                resultIntArray[i] =  Character.digit(chars[i], 10);
            }
            return resultIntArray;
    }
    private boolean checkSnils(String snils) {
        if(snils.length()==11) return true;
        else {System.out.println("Ошибка в введенном СНИЛС");
            return false;}
    }
    public static int checkSum(int[] digits) {
        int sum = 0;
        for (int i = 0, j = 9; i < 9; i++, j--) {
            sum += (digits[i] * j);
        }
        sum = sum % 101;
        if (sum == 100) return 0;
         return sum;
    }
}
