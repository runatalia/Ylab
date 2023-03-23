package internship.transliterator;


public class TransliteratorImpl implements Transliterator {
  private StringBuilder result = new StringBuilder();

  @Override
  public String transliterate(String source) {
    for (int i = 0; i < source.length(); i++) {
      if (source.charAt(i) < 33 || source.charAt(i) > 90) {
        result.append(getLiterate(source.charAt(i)));
      } else {
        result.append(source.charAt(i));
      }
    }
    return result.toString();
  }

  private String getLiterate(char ch) {
    switch (ch) {
      case 'А': return "A";
      case 'Б': return "B";
      case 'В': return "V";
      case 'Г': return "G";
      case 'Д': return "D";
      case 'Е': return "E";
      case 'Ё': return "E";
      case 'Ж': return "ZH";
      case 'З': return "Z";
      case 'И': return "I";
      case 'Й': return "I";
      case 'К': return "K";
      case 'Л': return "L";
      case 'М': return "M";
      case 'Н': return "N";
      case 'О': return "O";
      case 'П': return "P";
      case 'Р': return "R";
      case 'С': return "S";
      case 'Т': return "T";
      case 'У': return "U";
      case 'Ф': return "F";
      case 'Х': return "KH";
      case 'Ц': return "TS";
      case 'Ч': return "CH";
      case 'Ш': return "SH";
      case 'Щ': return "SHCH";
      case 'Ъ': return "IE";
      case 'Ы': return "Y";
      case 'Ь': return "";
      case 'Э': return "E";
      case 'Ю': return "IU";
      case 'Я': return "IA";
      default: return String.valueOf(ch);
    }
  }
}
